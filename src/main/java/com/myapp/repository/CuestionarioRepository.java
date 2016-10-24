package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.encuestas.Cuestionario;

public interface CuestionarioRepository extends JpaRepository<Cuestionario, Integer> {

	@Query("Select cuestionario from Cuestionario cuestionario "
			+ "join cuestionario.grupopreguntas grupo "
			+ "join grupo.preguntas preg "
			+ "join preg.respuestas opcion "
			+ "join preg.tipoPregunta tipo "
			+ "where cuestionario.tipoinstrumento=:id ")
	Cuestionario getInstumento(@Param("id")Integer id);
}
