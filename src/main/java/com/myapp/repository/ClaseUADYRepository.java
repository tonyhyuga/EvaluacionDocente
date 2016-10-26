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

@SuppressWarnings("unused")
public interface ClaseUADYRepository extends JpaRepository<ClaseUADY,Integer>{
	
	@Query("select clase from ClaseUADY clase join clase.movimientosInscripcionGrupo grupos "
			+ "join grupos.alumnoUADYMatriculado alumno "
			+ "join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "where alumno.matriculaParteInvariable =:matricula and anio.id=18025 ")
	Page<ClaseUADY> getClaseUADYByAlumno(Pageable pageable, @Param("matricula") String matricula);
	
	@Query("select clase from ClaseUADY clase join clase.movimientosInscripcionGrupo grupos "
			+ "join grupos.alumnoUADYMatriculado alumno "
			+ "join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "where alumno.matriculaParteInvariable =:matricula and anio.id=18025 ")
	List<ClaseUADY> getClaseUADYByAlumno( @Param("matricula") String matricula);
	
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
			
	@Query("select distinct clase, prof from CuestionarioResuelto resuelto "
	+ "join resuelto.ambito a "
	+ "join a.claseUady clase "
	+ "join clase.movimientosInscripcionGrupo grupos "
	+ "join grupos.alumnoUADYMatriculado alumno "
	+ "join clase.periodoCurso pc "
	+ "join clase.sinodo prof "
	+ "where alumno.id =:idAlumno and pc.id=:idPC and resuelto.completado = true")
	Page<Object[]> getClasesConCuestionariosNoResueltosByAlumno(Pageable pageable, @Param("idAlumno") Integer idAlumno,@Param("idPC")Integer idPeridocurso);
	
	@Query("select clase from ClaseUADY clase join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "join clase.sinodo prof where prof.id =:id and anio.id=18025 ")
	List<ClaseUADY> getClasesUADYByProfesor( @Param("id") Integer id);

	@Query("select clase from ClaseUADY clase join clase.periodoCurso pc "
			+ "join pc.anioEscolar anio "
			+ "join clase.sinodo prof where prof.id =:id and anio.id=18025 ")
	Page<ClaseUADY> getClasesUADYByProfesor(Pageable pageable,@Param("id") int id);
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

	@Query("select  distinct clase,prof from ClaseUADY clase "
			+ "join clase.movimientosInscripcionGrupo grupo  "
			+ "join grupo.planDeEstudios pl "
			+ "join pl.programaEducativo pr "
			+ "join pr.tipoNivel nivel "
			+ "join clase.periodoCurso pc "
			+ "join clase.sinodo prof "
			+ "join clase.institucion inst "
			+ "where  inst.id in (:id) and clase.mefi='T' and nivel.id=2 "
			+ "and pc.calendarInicio <= :fecha and pc.calendarFin >= :fecha")
	Page<Object[]> getClasesUADYPorInstitucion(Pageable pageable,@Param("id") List<Integer> id,@Param("fecha") Calendar fecha);

	
	
	/////////////////////////////////////////////////////////////////////
	@Query("select distinct cu, titular from ClaseUADY cu " +
				"join cu.sinodo titular "+
				"join cu.periodoCurso pc " +
				"join cu.institucion inst " +				
				"where titular.id = :idTitular " +
				"and ( :fechaBD between pc.calendarInicio and pc.calendarFin)")
	Page<Object[]> getClasesUADYOfDocente(Pageable pageable,@Param("idTitular") int id,@Param("fechaBD") Calendar fechaBD);
	
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

}
