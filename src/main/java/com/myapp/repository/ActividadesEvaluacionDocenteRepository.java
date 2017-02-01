package com.myapp.repository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;

public interface ActividadesEvaluacionDocenteRepository extends JpaRepository<ActividadesEvaluacionDocente, Integer>{

	@Query("select distinct acti from ActividadesEvaluacionDocente acti " +
			"join acti.tipoActividad tipo " +
			"where tipo.id=:tipoActividad " )
	Page<ActividadesEvaluacionDocente> findAllByTipo(Pageable pageable, @Param("tipoActividad")  int tipoActividad);
	
	
	@Query("select distinct acti from ActividadesEvaluacionDocente acti " +
			"join acti.tipoActividad tipo " +
			"where tipo.id=:tipoActividad and acti.inicio <=now() and now() <= acti.fin " )
	ActividadesEvaluacionDocente getActividadActual(@Param("tipoActividad")  int tipoActividad);

	@Query("select distinct tipo from TipoActividadEvaluacionDocente tipo ")
	List<TipoActividadEvaluacionDocente> getTipoActividades();

	@Query("select distinct actividad from ActividadesEvaluacionDocente actividad "
			+ "join actividad.tipoActividad tipo "
			+ "where actividad.institucion.id=:idinstitucion and tipo.id=:idTipoActividadEva "
			+ "and actividad.indicePeriodo=:indice "
			+ "and actividad.anioEscolar.id=:anioEscolar "
			+ "and actividad.inicio <= now() and now() <= actividad.fin ")
	ActividadesEvaluacionDocente getActividadActualByTipo(@Param("idinstitucion")int idinstitucion,
			@Param("idTipoActividadEva") Integer idTipoActividadEva,
			@Param("indice")Integer indicePeriodo,@Param("anioEscolar")Integer idAnioEscolar);

	@Query("select distinct actividad from ActividadesEvaluacionDocente actividad "
			+ "join actividad.tipoActividad tipo "
			+ "where actividad.institucion.id=:idinstitucion and tipo.id=:idTipoActividadEva "
			+ "and actividad.anioEscolar.id=:idAnio and actividad.indicePeriodo= :indicePeriodo ")
	ActividadesEvaluacionDocente  existActividad(@Param("idAnio")int idAnio, @Param("indicePeriodo") Integer indicePeriodo,
			@Param("idinstitucion")int idinstitucion,@Param("idTipoActividadEva") Integer idTipoActividadEva);
}
