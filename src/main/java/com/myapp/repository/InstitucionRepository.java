package com.myapp.repository;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.Institucion;
import com.myapp.domain.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Usuario entity.
 */
@SuppressWarnings("unused")
public interface InstitucionRepository extends JpaRepository<Institucion,Integer> {

	
	@Query("Select distinct inst from ProgramaEducativo prog "
			+ "join prog.centroDocente inst where inst.tipoInstitucion.id=3 and prog.tipoNivel.id=2  ")
	List<Institucion> getDependencias();
	
	@Query("Select distinct inst from Institucion inst "
			+ "where inst.id in (:id)  ")
	List<Institucion> getDependencias(@Param("id") List<Integer> id);
}
