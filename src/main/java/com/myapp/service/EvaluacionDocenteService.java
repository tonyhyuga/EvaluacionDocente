package com.myapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.myapp.domain.AnioEscolar;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.MovimientoInscripcionGrupo;
import com.myapp.domain.PeriodoCurso;
import com.myapp.domain.PeriodoTiempo;
import com.myapp.domain.Persona;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.Cuestionario;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.GrupoPreguntas;
import com.myapp.domain.encuestas.Pregunta;
import com.myapp.domain.encuestas.PreguntaHecha;
import com.myapp.domain.encuestas.RespuestaPregunta;
import com.myapp.domain.encuestas.TipoAmbito;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.domain.wrapper.CuestionarioResueltoWrapper;
import com.myapp.domain.wrapper.GrupoPreguntasWrapper;
import com.myapp.domain.wrapper.PreguntaWrapper;
import com.myapp.repository.AmbitoRepository;
import com.myapp.repository.AnioEscolarRepository;
import com.myapp.repository.ClaseUADYRepository;
import com.myapp.repository.CuestionarioRepository;
import com.myapp.repository.CuestionarioResueltoRepository;
import com.myapp.repository.EmpleadoRepository;

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
	private AmbitoRepository ambitoRepository;
	
	@Inject
	private CuestionarioRepository cuestionarioRepository;

	@Transactional(readOnly = true) 
	public List<ClaseUADY> findClasesByDocente(Integer iddocente){
		log.debug("Request to get all clases sin paginacion ");
		return  claseRepository.getClasesUADYByProfesor(iddocente);
	}
	@Transactional(readOnly = true) 	
	public Page<ClaseUADYDocenteWrapper> findClasesByDocente(Pageable pageable, int id) {
		log.debug("Request to get all clases con paginacion");
		//PeriodoCurso pc = pcService.getPeriodoCursoActualPlanDeEstudios(plan.getId());
		Calendar fechaBD=Calendar.getInstance();
		Page<Object[]> clasestotal = claseRepository.getClasesUADYOfDocente(pageable, id, fechaBD);
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
	public Page<ClaseUADYDocenteWrapper> findClasesByAlumno(Pageable pageable, int idAlumno,int idPeriodocurso) {
		log.debug("Request to get all clases con paginacion");

		Page<Object[]> clasest= claseRepository.getClasesConCuestionariosNoResueltosByAlumno(pageable,idAlumno);

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
	public List<ClaseUADY> findClasesByAlumno(int id) {
		log.debug("Request to get all clases con paginacion");
		List<ClaseUADY> result = claseRepository.getClaseUADYByAlumno("");
		return result;

	}
	public Page<ClaseUADYDocenteWrapper> findDocentesByInstitucion(Pageable pageable, List<Integer> ids) {
		log.debug("Request to get all emeplados con paginacion");
		
		Calendar fecha = Calendar.getInstance();
		Page<Object[]> clasest= claseRepository.getClasesUADYPorInstitucion(pageable,ids,fecha);

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
	
	
}
