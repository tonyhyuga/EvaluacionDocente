package com.myapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.TipoAmbito;

public interface AmbitoRepository extends JpaRepository<Ambito, Integer>{

	@Query("Select ambito from Ambito ambito where ambito.claseUady.id = :idclase "
			+ "and ambito.persona.id =:idprof ")
	Ambito getAmbito(@Param("idclase") Integer idclase, @Param("idprof") Integer idprof);
	
	@Query("Select ambito from Ambito ambito where ambito.claseUady.id = :idclase "
			+ "and ambito.persona.id =:idprof and ambito.tipoAmbito.tipo=:tipo ")
	Ambito getAmbito(@Param("idclase")Integer idclase, @Param("idprof")Integer idprof, @Param("tipo")String tipo);

	@Query("Select ambito from TipoAmbito ambito where ambito.id =:tipo ")
	TipoAmbito getTipoAmbito(@Param("tipo")int tipo);
	
	@Query("Select cr from CuestionarioResuelto cr "
			+ "join cr.ambito ambito "
			+ "join ambito.tipoAmbito tipo "
			+ "join ambito.claseUady clase "
			+ "join clase.asignaturaBase asig "
			+ "join ambito.persona p "
			+ "join ambito.institucion inst "
			+ "join ambito.periodoCurso pc "
			+ "join pc.anioEscolar a "
			+ "where p.id =:idPersona and asig.id=:idAsignatura and pc.indice=:indice and a.id=:idAnio "
			+ "and pc.interCurso=:intercurso and pc.indiceIntercurso=:indiceInter "
			+ "and ambito.formaDeEvaluar=:tipo and inst.id=:idCentro and cr.completado=true and tipo.id=2 ")
	List<CuestionarioResuelto> getAmbitosToReporte(@Param("idAsignatura")int asignatura,@Param("idPersona")int idProfesor,@Param("idAnio")int idAnio,
			@Param("indice")short indice,@Param("intercurso")boolean intercurso,@Param("indiceInter")short indiceInter,
			@Param("tipo")String tipo,@Param("idCentro")int idDependencia);

	
}
