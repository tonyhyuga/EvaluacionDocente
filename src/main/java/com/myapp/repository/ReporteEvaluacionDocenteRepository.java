package com.myapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.ReporteEvaluacionDocente;
import com.myapp.domain.ReporteEvaluativo;
import com.myapp.domain.ReporteEvaluativoLibre;
import com.myapp.domain.ReporteEvaluativoOyO;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;

public interface ReporteEvaluacionDocenteRepository extends JpaRepository<ReporteEvaluacionDocente, Integer>{

	@Query("select distinct tipo from TipoActividadEvaluacionDocente tipo ")
	List<TipoActividadEvaluacionDocente> getTipoActividades();
	
	@Query("select reporte from ReporteEvaluativoOyO reporte "
			+ "join reporte.anioEscolar anio "
			+ "join reporte.institucion inst "
			+ "join reporte.profesor per "
			+ "join reporte.asignatura asig "
			+ "where anio.id=:idAnio and inst.id=:idCentro and per.id=:idPersona "
			+ "and reporte.indicePeriodoEvalaucion=:indice and asig.id=:idAsignatura "
			+ "and reporte.formaDeEvaluar=:tipo ")
	ReporteEvaluativo getReporteOO(@Param("idAsignatura")Integer idAsignatura, 
			@Param("idPersona")Integer idPersona, 
			@Param("idCentro")Integer centroDocente, @Param("idAnio")Integer idAnio, 
			@Param("indice")Integer indice,@Param("tipo") String tipo);
	
	@Query("select reporte from ReporteEvaluativo reporte "
			+ "join reporte.anioEscolar anio "
			+ "join reporte.institucion inst "
			+ "join reporte.profesor per "
			+ "join reporte.asignatura asig "
			+ "where anio.id=:idAnio and inst.id=:idCentro and per.id=:idPersona "
			+ "and reporte.indicePeriodoEvalaucion=:indice and asig.id=:idAsignatura "
			+ "and reporte.formaDeEvaluar=:tipo ")
	ReporteEvaluativo getReporteLI(@Param("idAsignatura")Integer idAsignatura, 
			@Param("idPersona")Integer idPersona, 
			@Param("idCentro")Integer centroDocente, @Param("idAnio")Integer idAnio, 
			@Param("indice")Integer indice,@Param("tipo") String tipo);

	@Query("select reporte from ReporteEvaluativo reporte "
			+ "join reporte.anioEscolar anio "
			+ "join reporte.institucion inst "
			+ "join reporte.profesor per "
			+ "where anio.id=:idAnio and inst.id=:idCentro and per.id=:idPersona "
			+ "and reporte.indicePeriodoEvalaucion=:indice and reporte.status='liberar' ")
	Page<ReporteEvaluativo> getMisReportes(Pageable pageable,@Param("idPersona")Integer id,
			@Param("indice") Integer indice,@Param("idAnio") Integer anio,@Param("idCentro") Integer centro);
	
	@Query("select reporte from ReporteEvaluativoLibre reporte "
			+ "where reporte.id=:id ")
	ReporteEvaluativoLibre getReporteLibre(@Param("id")Integer id);
	
	@Query("select reporte from ReporteEvaluativoOyO reporte "
			+ "where reporte.id=:id ")
	ReporteEvaluativoOyO getReporteOO(@Param("id")Integer id);
	
	
	@Query("select reporte from ReporteEvaluativo reporte "
			+ "join reporte.anioEscolar anio "
			+ "join reporte.institucion inst "
			+ "join reporte.profesor per "
			+ "where anio.id=:idAnio and inst.id=:idCentro "
			+ "and reporte.indicePeriodoEvalaucion=:indice and (reporte.status='liberar' or reporte.status='revisar' ) ")
	Page<ReporteEvaluativo> getReportesRevision(Pageable pageable,
			@Param("indice") Integer indice,@Param("idAnio") Integer anio,@Param("idCentro") Integer centro);
	
}
