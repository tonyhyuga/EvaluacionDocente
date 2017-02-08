package com.myapp.repository;

import java.util.ArrayList;
import java.util.List;

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

	@Query(value="select rp.respuestaseleccionada,rp.idpregunta,cu.idpersonaencuestada "
			+ " from ambito a "
			+ "inner join cuestionarioresuelto cu on cu.idambito=a.id "
			+ "inner join respuestapregunta rp on rp.idcuestionarioresuelto=cu.id "
			+ "inner join pregunta p on p.id=rp.idpregunta "
			+ "where a.id=:ambito and a.idpersona != cu.idpersonaencuestada "
			+ "order by cu.idpersonaencuestada, rp.idpregunta,p.indiceorden asc",nativeQuery=true)
	ArrayList<Object[]> getCuestionariosAlumnosReporte(@Param("ambito")Integer idAmbito);
	
	@Query(value="select rp.respuestaseleccionada, rp.idpregunta,cu.idpersonaencuestada "
			+ " from ambito a "
			+ "inner join cuestionarioresuelto cu on cu.idambito=a.id "
			+ "inner join respuestapregunta rp on rp.idcuestionarioresuelto=cu.id "
			+ "inner join pregunta p on p.id=rp.idpregunta "
			+ "where a.id=:ambito and a.idpersona=cu.idpersonaencuestada "
			+ "order by cu.idpersonaencuestada, rp.idpregunta,p.indiceorden asc ",nativeQuery=true)
	ArrayList<Object[]> getCuestionariosProfesorReporte(@Param("ambito")Integer idAmbito);
}
