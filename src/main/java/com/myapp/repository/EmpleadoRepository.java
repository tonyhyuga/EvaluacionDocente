package com.myapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Integer>{

	@Query("select distinct empleado from Institucion inst, ClaseUADY clase join inst.empleados empleado "
			+ "join clase.sinodo sinodo "
			+ "join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "where inst.id=:id and anio.id=18025 and sinodo.id=empleado.id ")
	Page<Empleado> getEmpleadosByInstitucion(Pageable pageable, @Param("id") Integer id);
	
	@Query("select emp from Empleado emp "
			+ "join emp.persona p "
			+ "where p.id=:id ")
	Empleado getEmpleadoByPersona(@Param("id") Integer id);
}
