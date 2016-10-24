package com.myapp.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.PeriodoCurso;

public interface PeriodoCursoRepository extends JpaRepository<PeriodoCurso,Long> {
	
	@Query("select pc from PlanDeEstudios pl, PeriodoCurso pc "
			+ "join pl.programaEducativo pr "
			+ "where pc.institucion = pr.centroDocente and pl.id=:idPlan "
			+ "and pc.periodoLectivoIni <= :fecha and :fecha <= pc.periodoLectivoFin ")
	PeriodoCurso getPeridoCursoActual( @Param("idPlan") Integer idPlan,@Param("fecha") Calendar fecha);

}
