package com.myapp.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.Usuario;

@SuppressWarnings("unused")
public interface AnioEscolarRepository extends JpaRepository<AnioEscolar, Integer>{
	
	@Query("Select anioEsc from AnioEscolar anioEsc where anioEsc.id=18025")
	AnioEscolar getAnioActual();
	
	@Query("Select anioEsc from AnioEscolar anioEsc where anioEsc.calendarInicio<=:fecha "
			+ "and anioEsc.calendarFin >=:fecha ")
	AnioEscolar getAnioActual(@Param("fecha")Calendar fecha);
	
	@Query("Select anioEsc from AnioEscolar anioEsc order by id desc ")
	List<AnioEscolar> getAllDesc();
}
