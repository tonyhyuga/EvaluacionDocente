package com.myapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.myapp.domain.encuestas.Ambito;
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
}
