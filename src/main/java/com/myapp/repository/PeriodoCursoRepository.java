package com.myapp.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.PeriodoCurso;

public interface PeriodoCursoRepository extends JpaRepository<PeriodoCurso,Long> {
	
	@Query("select pc from ActividadesDePeriodoCurso acp "
			+ "join acp.planDeEstudios pl "
			+ "join acp.periodoCurso pc "
			+ "where pl.id=:idPlan "
			+ "and pc.calendarInicio <= :fecha and :fecha <= pc.calendarFin ")
	PeriodoCurso getPeridoCursoActual( @Param("idPlan") Integer idPlan,@Param("fecha") Calendar fecha);

}
