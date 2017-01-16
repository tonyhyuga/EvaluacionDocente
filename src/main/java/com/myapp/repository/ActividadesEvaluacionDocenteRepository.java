package com.myapp.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;

public interface ActividadesEvaluacionDocenteRepository extends JpaRepository<ActividadesEvaluacionDocente, Integer>{

	@Query("select distinct acti from ActividadesEvaluacionDocente acti " +
			"join acti.tipoActividad tipo " +
			"where tipo.id=:tipoActividad " )
	Page<ActividadesEvaluacionDocente> findAllByTipo(Pageable pageable, @Param("tipoActividad")  int tipoActividad);
	
	
	@Query("select distinct acti from ActividadesEvaluacionDocente acti " +
			"join acti.tipoActividad tipo " +
			"where tipo.id=:tipoActividad and acti.inicio <=now() and now() <= acti.fin " )
	ActividadesEvaluacionDocente getActividadActual(@Param("tipoActividad")  int tipoActividad);

	@Query("select distinct tipo from TipoActividadEvaluacionDocente tipo ")
	List<TipoActividadEvaluacionDocente> getTipoActividades();

	
}
