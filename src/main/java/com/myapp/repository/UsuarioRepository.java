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
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

	@Query("Select logueo from UsuarioAlumno logueo where logueo.login='05001663'")
	Page<Usuario> findAll(Pageable pageable);
	
	@Query("Select user from Usuario user where user.login=:login")
	Optional<Usuario> findUsuarioByLogin(@Param("login") String login);
	
	@Query("Select user from Usuario user where user.login=:login")
	Usuario getUsuarioByLogin(@Param("login") String login);
	
	@Query("Select  inst from UsuarioEmpleado user "
			+ "join user.perfiles pf "
			+ "join pf.institucion inst "
			+ "join user.empleado emp "
			+ "join pf.rol r "
			+ "where r.rol=:rol and emp.persona.id=:idpersona ")
	List<Institucion> getInstitucionByRol(@Param("idpersona") Integer idpersona,@Param("rol") String rol);
}
