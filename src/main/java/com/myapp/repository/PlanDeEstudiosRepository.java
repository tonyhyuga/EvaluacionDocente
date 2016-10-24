package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.PlanDeEstudios;

public interface PlanDeEstudiosRepository extends JpaRepository<PlanDeEstudios, Long> {
	
	@Query("Select plan from PlanDeEstudios plan where plan.id=:id")
	PlanDeEstudios getPlanDeEstudios(@Param("id") Integer id);
	
	@Query("Select distinct plan from MovimientoDeInscripcion mov,StatusAlumnoPlanDeEstudios sap "
			+ "join mov.planDeEstudios plan "
			+ "join plan.programaEducativo pro "
			+ "join mov.alumnoUADYMatriculado al "
			+ "join sap.statusAlumno st "
			+ "where al.id=:id and mov.cancelado=false and pro.tipoNivel.id=2 "
			+ "and sap.alumnoUADYMatriculado.id=al.id and plan.id=sap.planDeEstudios.id "
			+ "and st.id=1 ")
	List<PlanDeEstudios> getPlanesDeEstudiosInscritosAlumno(@Param("id") Integer id);

}
