package com.myapp.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.MovimientoInscripcionGrupo;
import com.myapp.domain.Usuario;
import com.myapp.domain.encuestas.CuestionarioResuelto;

@SuppressWarnings("unused")
public interface ClaseUADYRepository extends JpaRepository<ClaseUADY,Integer>{
	
	
	@Query("select clase from ClaseUADY clase join clase.movimientosInscripcionGrupo grupos "
			+ "join grupos.alumnoUADYMatriculado alumno "
			+ "join clase.periodoCurso pc "
			+ "where alumno.id =:idAlumno and pc.id=:idPC ")
	Page<ClaseUADY> getClaseUADYByAlumno(Pageable pageable, @Param("idAlumno") Integer idAlumno,@Param("idPC")Integer idPeridocurso);

	@Query("select distinct clase, prof from ClaseUADY clase join clase.movimientosInscripcionGrupo grupos "
			+ "join grupos.alumnoUADYMatriculado alumno "
			+ "join clase.periodoCurso pc "
			+ "join clase.sinodo prof "
			+ "where alumno.id =:idAlumno and pc.id=:idPC ")
	Page<Object[]> getClasesUADYByAlumno(Pageable pageable, @Param("idAlumno") Integer idAlumno,@Param("idPC")Integer idPeridocurso);
			
	@Query("select cu from CuestionarioResuelto cu where cu.id in (:ids) and cu.personaEncuestada.id=:idAlumno "
			+ "and cu.completado=false ")
	Page<CuestionarioResuelto> getClasesConCuestionariosNoResueltosByAlumno(Pageable pageable, @Param("idAlumno") Integer idAlumno,@Param("ids")List<Integer> ids);
	
////////////////////////////////////////////////////////////////////////////////////////////
	
	@Query("select distinct clase from ClaseUADY clase join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "join clase.sinodo prof "
			+ "join clase.institucion inst "
		//	+ "join prof.instituciones inst "
			+ "join inst.empleados empl "
			+ "where prof.id = empl.id and anio.id=18025 and inst.id=:id and clase.mefi='T'  ")
	Page<ClaseUADY> getClasesUADYByInstitucion(Pageable pageable,@Param("id") int id);
	
	@Query("select distinct clase,prof from ClaseUADY clase join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "join clase.sinodo prof "
			+ "join clase.institucion inst "
			//+ "join prof.instituciones inst "
			+ "join inst.empleados empl "
			+ "where prof.id = empl.id and anio.id=18025 and inst.id=:id and clase.mefi='T'  ")
	List<Object[]> getClasesUADYPorInstitucion(@Param("id") int id);
//	@Query("select  clase,prof from ClaseUADY clase "
//			+ "join clase.periodoCurso pc "
//			+ "join clase.sinodo prof "
//			+ "join clase.institucion inst "
//			+ "join inst.empleados empl "
//			+ "where prof.id = empl.id and inst.id=:id and clase.mefi='T'  "
//			+ "and pc.periodoLectivoIni <= :fecha and pc.periodoLectivoFin >= :fecha")
//	@Query("select  distinct clase,prof from ClaseUADY clase "
//			+ "join clase.movimientosInscripcionGrupo grupo  "
//			+ "join grupo.planDeEstudios pl "
//			+ "join pl.programaEducativo pr "
//			+ "join pr.tipoNivel nivel "
//			+ "join clase.periodoCurso pc "
//			+ "join clase.sinodo prof "
//			+ "join clase.institucion inst "
//			+ "join inst.empleados empl "
//			+ "where prof.id = empl.id and inst.id=:id and clase.mefi='T' and nivel.id=2 "
//			+ "and pc.periodoLectivoIni <= :fecha and pc.periodoLectivoFin >= :fecha")
//	Page<Object[]> getClasesUADYPorInstitucion(Pageable pageable,@Param("id") int id,@Param("fecha") Calendar fecha);

	@Query(value="select  distinct clase, prof from ClaseUADY clase "
			+ "join clase.movimientosInscripcionGrupo grupo  "
			+ "join clase.asignaturaBase asig "
			+ "join clase.grupo g "
			+ "join grupo.planDeEstudios pl "
			+ "join pl.programaEducativo pr "
			+ "join pr.tipoNivel nivel "
			+ "join clase.periodoCurso pc "
			+ "join clase.sinodo prof "
			+ "join prof.persona persona "
			+ "join clase.institucion inst "
			+ "where  inst.id in (:id) and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
			+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo "
			+ "and ( "
			+ "asig.nombre like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoPaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoMaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.nombres like CONCAT('%',:filtro,'%')"
			+ "or g.grupo like CONCAT('%',:filtro,'%') "
			+ ") "
			+ "order by asig.nombre", countQuery= 
			"select count(clase.id) from ClaseUADY clase "
					+ "join clase.movimientosInscripcionGrupo grupo "
					+ "join clase.asignaturaBase asig "
					+ "join clase.grupo g "
					+ "join grupo.planDeEstudios pl "
					+ "join pl.programaEducativo pr "
					+ "join pr.tipoNivel nivel "
					+ "join clase.periodoCurso pc "
					+ "join clase.sinodo prof "
					+ "join prof.persona persona "
					+ "join clase.institucion inst "
					+ "where  inst.id in (:id) and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
					+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo and ( asig.nombre like CONCAT('%',:filtro,'%') or persona.apellidoPaterno like CONCAT('%',:filtro,'%') or persona.apellidoMaterno like CONCAT('%',:filtro,'%') or persona.nombres like CONCAT('%',:filtro,'%') or g.grupo like CONCAT('%',:filtro,'%') ) group by clase.id,prof.id order by asig.nombre" )
	Page<Object[]> getClasesUADYPorInstitucion(Pageable pageable,@Param("id") List<Integer> id,@Param("idAnio") Integer idAnio,@Param("indicePeriodo") Short indicePeriodo,@Param("filtro") String filtro);
	
	
	////////////////////////////////////////////////////////////////////
	@Query(value="select  distinct clase, prof from ClaseUADY clase "
			+ "join clase.movimientosInscripcionGrupo grupo  "
			+ "join clase.asignaturaBase asig "
			+ "join clase.grupo g "
			+ "join grupo.planDeEstudios pl "
			+ "join pl.programaEducativo pr "
			+ "join pr.tipoNivel nivel "
			+ "join clase.periodoCurso pc "
			+ "join clase.sinodo prof "
			+ "join prof.persona persona "
			+ "join clase.institucion inst "
			+ "where  inst.id in (:id) and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
			+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo "
			+ "and ( "
			+ "asig.nombre like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoPaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoMaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.nombres like CONCAT('%',:filtro,'%') "
			+ "or g.grupo like CONCAT('%',:filtro,'%') "
			+ ") "
			//una subquery
			//+ "and clase =( select a.claseUady from Ambito a) "
			//+ "and persona=( select a.persona from Ambito a) "
			+ "and null=(select ta.tipo from Ambito a join a.tipoAmbito ta where ta.tipo ='Evaluaciones' and a.claseUady = clase and a.persona=persona ) "
			//
			+ "order by asig.nombre", countQuery= 
			"select count(clase.id) from ClaseUADY clase "
					+ "join clase.movimientosInscripcionGrupo grupo "
					+ "join clase.asignaturaBase asig "
					+ "join clase.grupo g "
					+ "join grupo.planDeEstudios pl "
					+ "join pl.programaEducativo pr "
					+ "join pr.tipoNivel nivel "
					+ "join clase.periodoCurso pc "
					+ "join clase.sinodo prof "
					+ "join prof.persona persona "
					+ "join clase.institucion inst "
					+ "where  inst.id in (:id) and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
					+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo and ( asig.nombre like CONCAT('%',:filtro,'%') or persona.apellidoPaterno like CONCAT('%',:filtro,'%') or persona.apellidoMaterno like CONCAT('%',:filtro,'%') or persona.nombres like CONCAT('%',:filtro,'%') or g.grupo like CONCAT('%',:filtro,'%') ) and null=(select ta.tipo from Ambito a join a.tipoAmbito ta where ta.tipo ='Evaluaciones' and a.claseUady = clase and a.persona=persona ) group by clase.id,prof.id order by asig.nombre" )
	Page<Object[]> getClasesUADYPorInstitucionEP(Pageable pageable,@Param("id") List<Integer> id,@Param("idAnio") Integer idAnio,@Param("indicePeriodo") Short indicePeriodo,@Param("filtro") String filtro);

	
	/////////////////////////////////////////////////////////////////////
	@Query("select distinct cu, titular from ClaseUADY cu " +
				"join cu.sinodo titular "+
				"join cu.periodoCurso pc " +
				"join cu.institucion inst " +				
				"where titular.persona.id = :idTitular " +
				"and pc.indice=:indice and pc.anioEscolar.id=:idAnio and pc.interCurso='F' ")
	Page<Object[]> getClasesUADYOfDocente(Pageable pageable,@Param("idTitular") int id,@Param("idAnio") Integer idAnio,@Param("indice") Short indice);
	
	@Query("select distinct alumno from ClaseUADY clase " +
			"join clase.movimientosInscripcionGrupo grupo "
			+ "join grupo.alumnoUADYMatriculado alumno "+
			"where clase.id=:id ")
	List<AlumnoUADYMatriculado> getMovimientosInscripcionClase(@Param("id") int id);
	
	@Query("select a,alu from Ambito a, AlumnoUADYMatriculado alu " +
			"join a.cuestionariosResueltos cu "
			+ "join cu.personaEncuestada per "+
			"where a.id=:idAmbito "+
			"and per.id=alu.persona.id ")
	List<Object[]> getRelacionAlumnoAmbitosParaReporte(@Param("idAmbito") int idAmbito);
	
//	@Query(value="select  distinct clase, prof from ClaseUADY clase "
//			+ "join clase.movimientosInscripcionGrupo grupo  "
//			+ "join clase.asignaturaBase asig "
//			+ "join grupo.planDeEstudios pl "
//			+ "join pl.programaEducativo pr "
//			+ "join pr.tipoNivel nivel "
//			+ "join clase.periodoCurso pc "
//			+ "join clase.sinodo prof "
//			+ "join clase.institucion inst "
//			+ "where  inst.id in (:id) and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
//			+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo  order by asig.nombre ", countQuery=
//			"select count(clase.id) from ClaseUADY clase "
//					+ "join clase.movimientosInscripcionGrupo grupo "
//					+ "join clase.asignaturaBase asig "
//					+ "join grupo.planDeEstudios pl "
//					+ "join pl.programaEducativo pr "
//					+ "join pr.tipoNivel nivel "
//					+ "join clase.periodoCurso pc "
//					+ "join clase.sinodo prof "
//					+ "join clase.institucion inst "
//					+ "where  inst.id = :id and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
//					+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo  group by clase.id,prof.id order by asig.nombre" )
//	Page<Object[]> getClasesUADYPorInstitucion(Pageable pageable,@Param("id") Integer idCentro,@Param("idAnio") Integer idAnio,@Param("indicePeriodo") Short indicePeriodo);
	
	@Query(value="select  distinct clase, prof from ClaseUADY clase "
			+ "join clase.movimientosInscripcionGrupo grupo  "
			+ "join clase.asignaturaBase asig "
			+ "join clase.grupo g "
			+ "join grupo.planDeEstudios pl "
			+ "join pl.programaEducativo pr "
			+ "join pr.tipoNivel nivel "
			+ "join clase.periodoCurso pc "
			+ "join clase.sinodo prof "
			+ "join prof.persona persona "
			+ "join clase.institucion inst "
			+ "where  inst.id = :id and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
			+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo "
			+ "and ( "
			+ "asig.nombre like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoPaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoMaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.nombres like CONCAT('%',:filtro,'%')"
			+ "or g.grupo like CONCAT('%',:filtro,'%') "
			+ ") "
			+ "order by asig.nombre", countQuery= 
			"select count(clase.id) from ClaseUADY clase "
					+ "join clase.movimientosInscripcionGrupo grupo "
					+ "join clase.asignaturaBase asig "
					+ "join clase.grupo g "
					+ "join grupo.planDeEstudios pl "
					+ "join pl.programaEducativo pr "
					+ "join pr.tipoNivel nivel "
					+ "join clase.periodoCurso pc "
					+ "join clase.sinodo prof "
					+ "join prof.persona persona "
					+ "join clase.institucion inst "
					+ "where  inst.id =:id and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
					+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo and ( asig.nombre like CONCAT('%',:filtro,'%') or persona.apellidoPaterno like CONCAT('%',:filtro,'%') or persona.apellidoMaterno like CONCAT('%',:filtro,'%') or persona.nombres like CONCAT('%',:filtro,'%') or g.grupo like CONCAT('%',:filtro,'%') ) group by clase.id,prof.id order by asig.nombre" )
	Page<Object[]> getClasesUADYPorInstitucion(Pageable pageable,@Param("id") Integer idCentro,@Param("idAnio") Integer idAnio,@Param("indicePeriodo") Short indicePeriodo,@Param("filtro") String filtro);
	
	
	////////////////////////////////////////////////////////////////////
	@Query(value="select  distinct clase, prof from ClaseUADY clase "
			+ "join clase.movimientosInscripcionGrupo grupo  "
			+ "join clase.asignaturaBase asig "
			+ "join clase.grupo g "
			+ "join grupo.planDeEstudios pl "
			+ "join pl.programaEducativo pr "
			+ "join pr.tipoNivel nivel "
			+ "join clase.periodoCurso pc "
			+ "join clase.sinodo prof "
			+ "join prof.persona persona "
			+ "join clase.institucion inst "
			+ "where  inst.id =:id and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
			+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo "
			+ "and ( "
			+ "asig.nombre like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoPaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.apellidoMaterno like CONCAT('%',:filtro,'%') "
			+ "or persona.nombres like CONCAT('%',:filtro,'%') "
			+ "or g.grupo like CONCAT('%',:filtro,'%') "
			+ ") "
			+ "and null=(select ta.tipo from Ambito a join a.tipoAmbito ta where ta.tipo ='Evaluaciones' and a.claseUady = clase and a.persona=persona ) "
			//
			+ "order by asig.nombre", countQuery= 
			"select count(clase.id) from ClaseUADY clase "
					+ "join clase.movimientosInscripcionGrupo grupo "
					+ "join clase.asignaturaBase asig "
					+ "join clase.grupo g "
					+ "join grupo.planDeEstudios pl "
					+ "join pl.programaEducativo pr "
					+ "join pr.tipoNivel nivel "
					+ "join clase.periodoCurso pc "
					+ "join clase.sinodo prof "
					+ "join prof.persona persona "
					+ "join clase.institucion inst "
					+ "where  inst.id = :id and clase.mefi='T' and nivel.id=2 and pc.interCurso='F' "
					+ "and pc.anioEscolar.id = :idAnio and pc.indice=:indicePeriodo and ( asig.nombre like CONCAT('%',:filtro,'%') or persona.apellidoPaterno like CONCAT('%',:filtro,'%') or persona.apellidoMaterno like CONCAT('%',:filtro,'%') or persona.nombres like CONCAT('%',:filtro,'%') or g.grupo like CONCAT('%',:filtro,'%') ) and null=(select ta.tipo from Ambito a join a.tipoAmbito ta where ta.tipo ='Evaluaciones' and a.claseUady = clase and a.persona=persona ) group by clase.id,prof.id order by asig.nombre" )
	Page<Object[]> getClasesUADYPorInstitucionEP(Pageable pageable,@Param("id") Integer idCentro,@Param("idAnio") Integer idAnio,@Param("indicePeriodo") Short indicePeriodo,@Param("filtro") String filtro);

	
	
	
	
	
	
	

}
