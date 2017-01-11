package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{


	@Query(value="select distinct persona from Empleado emp "
			+ "join emp.persona persona "
			+ "where  INSTR(lower(CONCAT(IFNULL(persona.nombres,''),' ',IFNULL(persona.apellidoPaterno,''), ' ',IFNULL(persona.apellidoMaterno,''))), :criterio)>0  "
			)
	List<Persona> buscarEmpleado(@Param("criterio") String criterio);
}
