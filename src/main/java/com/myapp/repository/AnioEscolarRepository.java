package com.myapp.repository;

import java.util.Calendar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.Usuario;

@SuppressWarnings("unused")
public interface AnioEscolarRepository extends JpaRepository<AnioEscolar, Long>{
	
	@Query("Select anioEsc from AnioEscolar anioEsc where anioEsc.id=18025")
	AnioEscolar getAnioActual();
	
	@Query("Select anioEsc from AnioEscolar anioEsc where anioEsc.calendarInicio<=:fecha "
			+ "and anioEsc.calendarFin >=:fecha ")
	AnioEscolar getAnioActual(@Param("fecha")Calendar fecha);

}
