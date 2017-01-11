package com.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

	@Query("Select perfil from Perfil perfil "
			+ "join perfil.rol rol "
			+ "where rol.id=:idRol and perfil.activo='T'")
	List<Perfil> getPerfiles(@Param("idRol") Integer idrol);
	
	@Query("Select perfil from Perfil perfil "
			+ "join perfil.rol rol "
			+ "where rol.id=:idRol and perfil.activo='T'")
	Page<Perfil> getPerfilesPageable(Pageable pageable, @Param("idRol")Integer idrol);

}
