package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.encuestas.CuestionarioResuelto;

public interface CuestionarioResueltoRepository extends JpaRepository<CuestionarioResuelto, Integer> {

//	@Query("Select resuelto from RespuestaPregunta respuesta "
//			+ "join respuesta.cuestionarioResuelto resuelto "
//			+ "join fetch resuelto.cuestionario cuestionario "
//			+ "join fetch resuelto.ambito ambito "
//			+ "join fetch cuestionario.grupopreguntas grupo "
//			+ "join fetch grupo.preguntas preg "
//			+ "join fetch preg.respuestas opcion "
//			+ "join fetch preg.tipoPregunta tipo "
//			//+ "join fetch resuelto.respuestasPregunta respuesta "
//			+ "join  respuesta.pregunta pregt "
//			+ "where ambito.id=:ambito and resuelto.personaEncuestada.id=:persona order by grupo.id")
//	@Query("Select resuelto from RespuestaPregunta respuesta ")
//	CuestionarioResuelto getCuestionarioResuelto2(@Param("ambito")Integer ambito, @Param("persona")Integer persona);
	
	@Query("Select resuelto from Ambito ambito "
			+ "join  ambito.cuestionariosResueltos resuelto "
			+ "join fetch resuelto.respuestasPregunta respuesta "
			+ "join fetch resuelto.cuestionario cuestionario "
//			+ "join fetch resuelto.ambito ambito "
			+ "join fetch cuestionario.grupopreguntas grupo "
			+ "join fetch grupo.preguntas preg "
			+ "join fetch preg.respuestas opcion "
			+ "join fetch preg.tipoPregunta tipo "
			+ "join fetch respuesta.personaEncuestada p "
			+ "join fetch resuelto.personaEncuestada per "
			//+ "join fetch resuelto.respuestasPregunta respuesta "
			+ "join  respuesta.pregunta pregt "
			+ "where ambito.id=:ambito and resuelto.personaEncuestada.id=:persona order by grupo.id")
	CuestionarioResuelto getCuestionarioResuelto(@Param("ambito")Integer ambito, @Param("persona")Integer persona);
}
