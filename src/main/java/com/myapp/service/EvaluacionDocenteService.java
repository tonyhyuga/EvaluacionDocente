package com.myapp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.Persona;
import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.Cuestionario;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.GrupoPreguntas;
import com.myapp.domain.encuestas.Pregunta;
import com.myapp.domain.encuestas.PreguntaHecha;
import com.myapp.domain.encuestas.RespuestaPregunta;
import com.myapp.domain.encuestas.TipoAmbito;
import com.myapp.domain.wrapper.AlumnoCuestionarioContestadoWrapper;
import com.myapp.domain.wrapper.ClaseUADYAlumnoWrapper;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.domain.wrapper.CuestionarioResueltoWrapper;
import com.myapp.domain.wrapper.GrupoPreguntasWrapper;
import com.myapp.domain.wrapper.PreguntaWrapper;
import com.myapp.repository.AmbitoRepository;
import com.myapp.repository.ClaseUADYRepository;
import com.myapp.repository.CuestionarioRepository;
import com.myapp.repository.CuestionarioResueltoRepository;
import com.myapp.repository.EmpleadoRepository;
import com.myapp.web.reportes.GeneradorReporteCuestionariosResueltos;
import com.myapp.web.reportes.ReporteCuestionarios;
import com.myapp.web.reportes.WrapperAlumnosCuestionariosResueltos;

@Service
@Transactional
public class EvaluacionDocenteService {
	private final Logger log = LoggerFactory.getLogger(EvaluacionDocenteService.class);

	@Inject
	private ClaseUADYRepository claseRepository;
	
	@Inject
	private EmpleadoRepository empleadoRepository;
	
	@Inject
	private CuestionarioResueltoRepository cuestionarioResueltoRepository;
	
	@Inject
	private PeridoCursoService pcService;
	
	@Inject
	private ActividadesEvaluacionDocenteService actividadesService;
	
	@Inject
	private AmbitoRepository ambitoRepository;
	
	@Inject
	private CuestionarioRepository cuestionarioRepository;


	@Transactional(readOnly = true) 	
	public Page<ClaseUADYDocenteWrapper> findClasesByDocente(Pageable pageable, int id,Integer anio, Short indice) {
		log.debug("Request to get all clases con paginacion");
		//PeriodoCurso pc = pcService.getPeriodoCursoActualPlanDeEstudios(plan.getId());
		//Calendar fechaBD=Calendar.getInstance();
		Page<Object[]> clasestotal = claseRepository.getClasesUADYOfDocente(pageable, id, anio,indice);
		//return claseRepository.getClasesUADYByProfesor(pageable,id);

		List<ClaseUADYDocenteWrapper> wrappers = new ArrayList<ClaseUADYDocenteWrapper>();
		List<Object[]> clases = clasestotal.getContent();		
		int idwrapper = 0;
		for(int i=0; i<clases.size(); i++){
			idwrapper++;
			ClaseUADY clase = (ClaseUADY)clases.get(i)[0];
			Empleado sinodo = (Empleado)clases.get(i)[1];
			ClaseUADYDocenteWrapper wraper = new ClaseUADYDocenteWrapper(clase, sinodo, idwrapper);
		
			Ambito ambitoObservaciones = ambitoRepository.getAmbito(clase.getId(), id,"Observaciones");
			Ambito ambitoEvaluacion = ambitoRepository.getAmbito(clase.getId(), id,"Evaluaciones");
			Ambito ambitoEvidencias = ambitoRepository.getAmbito(clase.getId(), id,"Evidencias");;
			wraper.setObservaciones(ambitoObservaciones);
			wraper.setEvaluacion(ambitoEvaluacion);
			wraper.setEvidencias(ambitoEvidencias);
			wrappers.add(wraper);
		}
		Page<ClaseUADYDocenteWrapper> page = new PageImpl<ClaseUADYDocenteWrapper>(wrappers, pageable, clasestotal.getTotalElements());
		return page;		
	}
	
	@Transactional(readOnly = true) 
	public CuestionarioResueltoWrapper findCuestionarioResuelto(int ambito,int profesor) {
		log.debug("Request to get Ambito {}",ambito,profesor);
		CuestionarioResuelto result = cuestionarioResueltoRepository.getCuestionarioResuelto(ambito, profesor);		
		CuestionarioResueltoWrapper wrapper = new CuestionarioResueltoWrapper();
		wrapper.setCuestionarioResuelto(result);
		ArrayList<GrupoPreguntasWrapper> wrappergp = new ArrayList<GrupoPreguntasWrapper>();
		Cuestionario instrumento = result.getCuestionario();
		Set<GrupoPreguntas> grupoP = instrumento.getGrupopreguntas();
		Iterator<GrupoPreguntas> grupo = grupoP.iterator();
		while(grupo.hasNext()){
			GrupoPreguntas g = grupo.next();
			GrupoPreguntasWrapper gp=new GrupoPreguntasWrapper();
			gp.setGrupoPreguntas(g);
			wrappergp.add(gp);
			Set<Pregunta> preguntas = g.getPreguntas();
			Iterator<Pregunta> itpreg = preguntas.iterator();
			Set<PreguntaWrapper> preguntasW = new HashSet<PreguntaWrapper>();
			while(itpreg.hasNext()){
				Pregunta pregunta = itpreg.next();
				PreguntaWrapper preWrap = new PreguntaWrapper();
				preWrap.setPregunta(pregunta);
				Set<RespuestaPregunta> respuestas = result.getRespuestasPregunta();
				Iterator<RespuestaPregunta> resultados = respuestas.iterator();
				while(resultados.hasNext()){
					RespuestaPregunta respuesta = resultados.next();
					if(respuesta.getPregunta().getId().intValue()==pregunta.getId().intValue()){
						preWrap.setRespuesta(respuesta);
						break;
					}
				}
				preguntasW.add(preWrap);
			}
			gp.setPreguntasWrapper(preguntasW);
		}
		wrapper.setGrupoPreguntasWrapper(wrappergp);
		return wrapper;
	}
	
	
	@Transactional(readOnly = true) 	
	public Page<ClaseUADYAlumnoWrapper> findClasesByAlumno(Pageable pageable, int idAlumno) {
		log.debug("Request to get all clases con paginacion");

		List<Integer> ids = cuestionarioRepository.getIdsCuestionariosActuales(idAlumno);
		if(ids==null || ids.size()==0){
			return null;
		}
		Page<CuestionarioResuelto> cuestionarios= claseRepository.getClasesConCuestionariosNoResueltosByAlumno(pageable,idAlumno,ids);


		List<ClaseUADYAlumnoWrapper> wrappers= new ArrayList<ClaseUADYAlumnoWrapper>();
		List<CuestionarioResuelto> cuestionario = cuestionarios.getContent();
		int idwrapper=0;
		for(int i=0;i<cuestionario.size();i++){
			idwrapper++;
			Ambito ambitoEvaluacion = cuestionario.get(i).getAmbito();
			ClaseUADY clase=ambitoEvaluacion.getClaseUady();
			Persona sinodo = ambitoEvaluacion.getPersona();
			ClaseUADYAlumnoWrapper wraper= new ClaseUADYAlumnoWrapper(clase,sinodo,idwrapper);
			wraper.setEvaluacion(ambitoEvaluacion);
			wrappers.add(wraper);
		}
		Page<ClaseUADYAlumnoWrapper> page = new PageImpl<ClaseUADYAlumnoWrapper>(wrappers, pageable,cuestionarios.getTotalElements());
		
		return page;
	
	
	}
	

	public Page<ClaseUADYDocenteWrapper> findDocentesByInstitucion(Pageable pageable, List<Integer> ids,Integer idAnio,Short indicePeriodo,String search,boolean ep) {
		log.debug("Request to get all emeplados con paginacion");
		
		//Calendar fecha = Calendar.getInstance();
		Page<Object[]> clasest = null;
		if(ep)
		{
			clasest= claseRepository.getClasesUADYPorInstitucionEP(pageable,ids,idAnio,indicePeriodo,search);
		}
		else
		{
			clasest= claseRepository.getClasesUADYPorInstitucion(pageable,ids,idAnio,indicePeriodo,search);
		}	

		System.out.println("clases total sin sinodos: "+clasest.getTotalElements());

		List<ClaseUADYDocenteWrapper> wrappers= new ArrayList<ClaseUADYDocenteWrapper>();
		List<Object[]> clases = clasest.getContent();
		int idwrapper=0;
		for(int i=0;i<clases.size();i++){
			idwrapper++;
			ClaseUADY clase=(ClaseUADY)clases.get(i)[0];
			Empleado sinodo = (Empleado)clases.get(i)[1];
			ClaseUADYDocenteWrapper wraper= new ClaseUADYDocenteWrapper(clase,sinodo,idwrapper);
		
			Ambito ambitoObservaciones=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Observaciones");
			Ambito ambitoEvaluacion=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evaluaciones");
			if(ambitoEvaluacion==null)
			{
				wraper.setHayevaluaciones(false);
			}
			else
			{
				wraper.setHayevaluaciones(true);
			}
			Ambito ambitoEvidencias=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evidencias");;
			wraper.setObservaciones(ambitoObservaciones);
			wraper.setEvaluacion(ambitoEvaluacion);
			wraper.setEvidencias(ambitoEvidencias);
			wrappers.add(wraper);
		}
		Page<ClaseUADYDocenteWrapper> page = new PageImpl<ClaseUADYDocenteWrapper>(wrappers, pageable,clasest.getTotalElements());
		
		return page;
	}
	
	@Transactional(readOnly = true) 	
	public Ambito findAmbito(int idclase,int idProf) {
		log.debug("Request to get Ambito {}",idclase,idProf);
		Ambito result = ambitoRepository.getAmbito(idclase,idProf);
		if(result==null){
			result= new Ambito();
			ClaseUADY clase=claseRepository.findOne(idclase);
			Empleado persona=empleadoRepository.getEmpleadoByPersona(idProf);
			TipoAmbito tipo=ambitoRepository.getTipoAmbito(2);
			result.setClaseUady(clase);
			result.setPersona(persona.getPersona());
			result.setInstitucion(clase.getInstitucion());
			result.setPeriodoCurso(clase.getPeriodoCurso());
			result.setTipoAmbito(tipo);
		
		}
		return result;
	}	
	
	public Ambito crearAmbito(Ambito ambito) {
		
		List<AlumnoUADYMatriculado> alumnos = claseRepository.getMovimientosInscripcionClase(ambito.getClaseUady().getId());
		
		Iterator<AlumnoUADYMatriculado> alummov = alumnos.iterator();
		/**se obtienen los instrumentos del ambito*/
		Cuestionario instrumentoDoce = getInstrumentoDocente(ambito.getFormaDeEvaluar());
		Cuestionario instrumentoAlumno = getInstrumentoAlumno(ambito.getFormaDeEvaluar());
		
		Set<CuestionarioResuelto> cuestionariosAlumno= new HashSet<CuestionarioResuelto>();
		/**se crea la hoja de respuestas del instrumento del profesor*/
		CuestionarioResuelto cuestionarioProf= new CuestionarioResuelto();
		cuestionarioProf.setAmbito(ambito);
		cuestionarioProf.setPersonaEncuestada(ambito.getPersona());
		cuestionarioProf.setCompletado(false);
		cuestionarioProf.setCuestionario(instrumentoDoce);
		
		Set<RespuestaPregunta> lista = generarRespuetasOfPreguntas(instrumentoDoce,ambito,ambito.getPersona(),cuestionarioProf);
		cuestionarioProf.setRespuestasPregunta(lista);
		cuestionariosAlumno.add(cuestionarioProf);
		/**se crean las hojas de respuesta por cada alumno que intrega la clase */
		Ambito newAmbito = ambitoRepository.save(ambito);
		//cuestionarioResueltoRepository.save(cuestionarioProf);
		while(alummov.hasNext()){
			AlumnoUADYMatriculado alumnmo = alummov.next();
			CuestionarioResuelto cuestionario= new CuestionarioResuelto();
			cuestionario.setAmbito(ambito);
			cuestionario.setCompletado(false);
			cuestionario.setPersonaEncuestada(alumnmo.getPersona());
			Set<RespuestaPregunta> respuestas = generarRespuetasOfPreguntas(instrumentoAlumno,ambito,
					alumnmo.getPersona(),cuestionario);
			cuestionario.setCuestionario(instrumentoAlumno);
			cuestionario.setRespuestasPregunta(respuestas);
			cuestionarioResueltoRepository.save(cuestionario);
			cuestionariosAlumno.add(cuestionario);
		}
		ambito.setCuestionariosResueltos(cuestionariosAlumno);
		return newAmbito;
	}
	/**crea un conjunto de respuestas por cada alumno, por instrumento */
	private Set<RespuestaPregunta> generarRespuetasOfPreguntas(Cuestionario instrumento,Ambito ambito,
			Persona evaluador,CuestionarioResuelto cuestionario){
		Set<RespuestaPregunta> respuestas=new HashSet<RespuestaPregunta>();
		 Set<GrupoPreguntas> contenedor = instrumento.getGrupopreguntas();
		 Iterator<GrupoPreguntas> grupopreguntas = contenedor.iterator();
		 while(grupopreguntas.hasNext()){
			 GrupoPreguntas grupo = grupopreguntas.next();
			 Set<Pregunta> preguntas = grupo.getPreguntas();
			 Iterator<Pregunta> iterator = preguntas.iterator();
			 while(iterator.hasNext()){
				 Pregunta pregunta=iterator.next();
				 RespuestaPregunta respuesta= new RespuestaPregunta();
				 respuesta.setAmbito(ambito);
				 respuesta.setCuestionarioResuelto(cuestionario);
				 respuesta.setPersonaEncuestada(evaluador);
				 respuesta.setPregunta(pregunta);
				 PreguntaHecha preguntaHecha=new PreguntaHecha();
				 preguntaHecha.setPreguntaString(pregunta.getPreguntaString());
				 preguntaHecha.setVersion(pregunta.getVersion());
				 preguntaHecha.setClave(pregunta.getClave());
				 respuesta.setPreguntaHecha(preguntaHecha);
				 respuesta.setOpcion(0);
				// respuesta.setOpcionrespuesta(1);
				 respuestas.add(respuesta);
				 
			 }
		 }
		return respuestas;
	}
	
	private Cuestionario getInstrumentoAlumno(String tipo){
		Cuestionario instrumento=null;
		if(tipo.equals("Libre")){
			instrumento=cuestionarioRepository.getInstumento(5);
		}
		if(tipo.equals("Optativa")||tipo.equals("Obligatoria")){
			instrumento=cuestionarioRepository.getInstumento(4);
		}
		if(tipo.equals("Profesionalizante")){
			
		}
		return instrumento;
	}
	private Cuestionario getInstrumentoDocente(String tipo){
		Cuestionario instrumento=null;
		if(tipo.equals("Libre")){
			instrumento=cuestionarioRepository.getInstumento(8);
		}
		if(tipo.equals("Optativa")||tipo.equals("Obligatoria")){
			instrumento=cuestionarioRepository.getInstumento(7);
		}
		if(tipo.equals("Profesionalizante")){
			
		}
		return instrumento;
	}
	public CuestionarioResuelto actualizar(CuestionarioResuelto cuestionario) {
		// TODO Auto-generated method stub
		CuestionarioResuelto cuestionarioResuelto = cuestionarioResueltoRepository.findOne(cuestionario.getId());
		machRespuestas(cuestionarioResuelto,cuestionario);
		cuestionarioResuelto.setCompletado(true);
		return cuestionarioResueltoRepository.save(cuestionarioResuelto);
	}
	
	private void machRespuestas(CuestionarioResuelto cuestionarioBD,CuestionarioResuelto view){
		Set<RespuestaPregunta> listaBD = cuestionarioBD.getRespuestasPregunta();
		Set<RespuestaPregunta> listaView = view.getRespuestasPregunta();
		Iterator<RespuestaPregunta> respuestasBD = listaBD.iterator();
		
		
		while(respuestasBD.hasNext()){
			RespuestaPregunta respuesta = respuestasBD.next();
			Iterator<RespuestaPregunta> respuestasView = listaView.iterator();
			while(respuestasView.hasNext()){
				 RespuestaPregunta respuestaView = respuestasView.next();
				 if(respuesta.getPregunta().getId().intValue()==respuestaView.getPregunta().getId().intValue()){
					// respuesta.setOpcion(respuestaView.getOpcion());
					 respuesta.setRespuestaSeleccionada(respuestaView.getRespuestaSeleccionada());
					 break;
				 }
			}
		}
	}
	
	
	public List<AlumnoCuestionarioContestadoWrapper> getWrappersForDownloadPage(int ambito){
		
		List<Object[]> objects=claseRepository.getRelacionAlumnoAmbitosParaReporte(ambito);
		ArrayList<AlumnoCuestionarioContestadoWrapper> wrapperAlumnos = new ArrayList<AlumnoCuestionarioContestadoWrapper>();
		for(int index=0; index<objects.size(); index++){
			AlumnoCuestionarioContestadoWrapper wrapper= new AlumnoCuestionarioContestadoWrapper();
			wrapper.setMatriculaParteInvariable(((AlumnoUADYMatriculado)objects.get(index)[1]).getMatriculaParteInvariable());
			Persona per =((AlumnoUADYMatriculado)objects.get(index)[1]).getPersona();
			wrapper.setNombreAlumno(per.getNombres()+" "+per.getApellidoPaterno()+" "+per.getApellidoMaterno());
			
			Set<CuestionarioResuelto> cuestionarios = ((Ambito)objects.get(index)[0]).getCuestionariosResueltos();
			
			Iterator  cuestionariosIt = cuestionarios.iterator();
			while(cuestionariosIt.hasNext()){
				CuestionarioResuelto cuestionario= (CuestionarioResuelto)cuestionariosIt.next();
				if(cuestionario.getPersonaEncuestada().getId().intValue()==per.getId().intValue()){
					if(cuestionario.getCompletado()){
						wrapper.setCuestionarioContestado("Si");	
					}else{
						wrapper.setCuestionarioContestado("No");
					}
					break;
					}
			}
			wrapperAlumnos.add(wrapper);
			
			
		}
		
		return wrapperAlumnos;
	}
	
	
	@Transactional(readOnly = true) 
	public File getRelacionAlumnoAmbitosParaReporte(int ambito) {
		List<Object[]> objects=claseRepository.getRelacionAlumnoAmbitosParaReporte(ambito);
		
		ArrayList<WrapperAlumnosCuestionariosResueltos> wrapperAlumnos = new ArrayList<WrapperAlumnosCuestionariosResueltos>();
	
		for(int index=0; index<objects.size(); index++){
			WrapperAlumnosCuestionariosResueltos wrapper= new WrapperAlumnosCuestionariosResueltos();
			wrapper.setAlumnoUsady((AlumnoUADYMatriculado)objects.get(index)[1]);
			Persona per =((AlumnoUADYMatriculado)objects.get(index)[1]).getPersona();
			wrapper.setPersona(per);
			
			Set<CuestionarioResuelto> cuestionarios = ((Ambito)objects.get(index)[0]).getCuestionariosResueltos();
			
			Iterator  cuestionariosIt = cuestionarios.iterator();
			while(cuestionariosIt.hasNext()){
				CuestionarioResuelto cuestionario= (CuestionarioResuelto)cuestionariosIt.next();
				if(cuestionario.getPersonaEncuestada().getId().intValue()==per.getId().intValue()){
					wrapper.setRespondio(cuestionario.getCompletado());
				
					break;
					}
			}
			wrapperAlumnos.add(wrapper);
			
			
		}
		GeneradorReporteCuestionariosResueltos generador = new GeneradorReporteCuestionariosResueltos();
		return generador.createExcelFile(wrapperAlumnos);
	}


	public boolean validarActividad(Integer idclase,Integer idTipoActividadEva) {
		ClaseUADY clase=claseRepository.findOne(idclase);
		Integer indicePeriodo=new Integer(clase.getPeriodoCurso().getIndice());
		ActividadesEvaluacionDocente actividad =actividadesService.getActividadActualByTipo(clase.getInstitucion().getId(),
				idTipoActividadEva,indicePeriodo,clase.getPeriodoCurso().getAnioEscolar().getId());
		return actividad!=null;
	}
	
	public boolean validarActividadAutoEvaluacion(Integer idAmbito) {
		Ambito ambito=ambitoRepository.findOne(idAmbito);
		Integer indicePeriodo=new Integer(ambito.getClaseUady().getPeriodoCurso().getIndice());
		ActividadesEvaluacionDocente actividad =actividadesService.getActividadActualByTipo(ambito.getInstitucion().getId(),
				TipoActividadEvaluacionDocente.AUTOEVALUAR_PROFESORES.getId(),indicePeriodo,
				ambito.getClaseUady().getPeriodoCurso().getAnioEscolar().getId());
		return actividad!=null;
	}

	public File getReporteAlumnos(Ambito ambito) {
		//Ambito ambito = ambitoRepository.findOne(idAmbito);
		ArrayList<Object[]> preguntas=null;
		if(ambito.getFormaDeEvaluar().equals("Libre")){
			preguntas=cuestionarioRepository.getIdsPreguntas(5);
		}
		if(ambito.getFormaDeEvaluar().equals("Optativa")||ambito.getFormaDeEvaluar().equals("Obligatoria")){
			preguntas=cuestionarioRepository.getIdsPreguntas(4);
		}
		if(ambito.getFormaDeEvaluar().equals("Profesionalizante")){
			
		}
		ArrayList<Object[]> listaR = cuestionarioResueltoRepository.getCuestionariosAlumnosReporte(ambito.getId());
		ReporteCuestionarios reporte=new ReporteCuestionarios();
		return reporte.createExcelFile(ambito.getClaseUady(), ambito.getPersona(), listaR, preguntas,"Evaluacion");
		

	}

	public File getReporteProfesor(Ambito ambito) {
		//Ambito ambito = ambitoRepository.findOne(idAmbito);
		ArrayList<Object[]> preguntas=null;
		if(ambito.getFormaDeEvaluar().equals("Libre")){
			preguntas=cuestionarioRepository.getIdsPreguntas(8);
		}
		if(ambito.getFormaDeEvaluar().equals("Optativa")||ambito.getFormaDeEvaluar().equals("Obligatoria")){
			preguntas=cuestionarioRepository.getIdsPreguntas(7);
		}
		if(ambito.getFormaDeEvaluar().equals("Profesionalizante")){
			
		}
		ArrayList<Object[]> listaR =cuestionarioResueltoRepository.getCuestionariosProfesorReporte(ambito.getId());
		ReporteCuestionarios reporte=new ReporteCuestionarios();
		return reporte.createExcelFile(ambito.getClaseUady(), ambito.getPersona(), listaR, preguntas,"Autoevaluacion");
		
	}
	
	public Ambito getAmbito(int idAmbito) {
			Ambito ambito = ambitoRepository.findOne(idAmbito);
		
		return ambito;
		
	}
	
	
}
