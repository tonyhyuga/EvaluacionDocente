package com.myapp.repository;

import java.util.List;

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
	
	@Query(value="Select distinct cu.id from actividadesevaluaciondocente act "+
			"inner join grupoalumnos ga on ga.idinstitucion =act.idinstitucion "+
			"inner join periodocurso pc on pc.id =ga.idperiodocurso "+
			"inner join claseuady cl on cl.id=ga.id "+
			"inner join ambito am on am.idclaseuady=cl.id "+
			"inner join cuestionarioresuelto cu on cu.idambito =am.id "+
			"where act.inicio<=now() and now()<=act.fin "+
			"and act.idanioescolar=pc.idanioescolar and pc.indice=act.indiceperiodo and "+
			"pc.intercurso='F' and act.idtipoactividadevaluaciondocente=2 and "+
			"cu.idpersonaencuestada=:id ",nativeQuery=true)
	List<Integer> getIdsCuestionariosActuales(@Param("id")Integer id);
}
