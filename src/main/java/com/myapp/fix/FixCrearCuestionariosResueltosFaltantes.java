package com.myapp.fix;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myapp.domain.Actividad;
import com.myapp.domain.ActividadesDePeriodoCurso;
import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.AnioEscolar;
import com.myapp.domain.AsignaturaBase;
import com.myapp.domain.CentroDocente;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.ContenedorAsignatura;
import com.myapp.domain.ContenedorElegible;
import com.myapp.domain.ContenedorOptativa;
import com.myapp.domain.CursoAcompanamiento;
import com.myapp.domain.Empleado;
import com.myapp.domain.Grupo;
import com.myapp.domain.GrupoAlumnos;
import com.myapp.domain.Institucion;
import com.myapp.domain.Movimiento;
import com.myapp.domain.MovimientoAlumno;
import com.myapp.domain.MovimientoInscripcionGrupo;
import com.myapp.domain.Perfil;
import com.myapp.domain.PeriodoCurso;
import com.myapp.domain.PeriodoTiempo;
import com.myapp.domain.Persona;
import com.myapp.domain.PlanDeEstudios;
import com.myapp.domain.ProgramaEducativo;
import com.myapp.domain.PuntoDeAcreditacion;
import com.myapp.domain.Rol;
import com.myapp.domain.StatusAlumno;
import com.myapp.domain.StatusAlumnoPlanDeEstudios;
import com.myapp.domain.TipoActividad;
import com.myapp.domain.TipoElegible;
import com.myapp.domain.TipoGrupoAlumnos;
import com.myapp.domain.TipoNivel;
import com.myapp.domain.TipoTurno;
import com.myapp.domain.Usuario;
import com.myapp.domain.UsuarioAlumno;
import com.myapp.domain.UsuarioEmpleado;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.Cuestionario;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.GrupoPreguntas;
import com.myapp.domain.encuestas.OpcionPregunta;
import com.myapp.domain.encuestas.Pregunta;
import com.myapp.domain.encuestas.PreguntaHecha;
import com.myapp.domain.encuestas.RespuestaPregunta;
import com.myapp.domain.encuestas.TipoAmbito;
import com.myapp.domain.encuestas.TipoPregunta;

public class FixCrearCuestionariosResueltosFaltantes {
	private static final SessionFactory concreteSessionFactory;
	static {
		try {
			Properties prop= new Properties();
			prop.setProperty("hibernate.connection.url", "jdbc:mysql://148.209.12.14:3306/dev2_evaluaciondocente?useOldAliasMetadataBehavior=true");
			prop.setProperty("hibernate.connection.username", "");
			prop.setProperty("hibernate.connection.password", "");
			prop.setProperty("dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			prop.setProperty("hibernate.cache.use_second_level_cache", "false");
			
			//concreteSessionFactory = 
					
			Configuration confi=new Configuration();
		  confi.addPackage("com.myapp.domain.encuestas");
		  confi.addProperties(prop);
			
			confi.addAnnotatedClass(TipoAmbito.class);
			confi.addAnnotatedClass(TipoPregunta.class);
			confi.addAnnotatedClass(RespuestaPregunta.class);
			confi.addAnnotatedClass(PreguntaHecha.class);
			confi.addAnnotatedClass(Pregunta.class);
			confi.addAnnotatedClass(OpcionPregunta.class);
			confi.addAnnotatedClass(GrupoPreguntas.class);
			confi.addAnnotatedClass(CuestionarioResuelto.class);
			confi.addAnnotatedClass(Cuestionario.class);
			confi.addAnnotatedClass(Ambito.class);
			
			//------------------------------------------
			confi.addAnnotatedClass(UsuarioEmpleado.class);
			confi.addAnnotatedClass(UsuarioAlumno.class);
			confi.addAnnotatedClass(Usuario.class);
			confi.addAnnotatedClass(TipoTurno.class);
			confi.addAnnotatedClass(TipoNivel.class);
			confi.addAnnotatedClass(TipoGrupoAlumnos.class);
			confi.addAnnotatedClass(TipoElegible.class);
			confi.addAnnotatedClass(TipoActividad.class);
			confi.addAnnotatedClass(StatusAlumnoPlanDeEstudios.class);
			confi.addAnnotatedClass(StatusAlumno.class);
			confi.addAnnotatedClass(Rol.class);
			confi.addAnnotatedClass(PuntoDeAcreditacion.class);
			confi.addAnnotatedClass(ProgramaEducativo.class);
			confi.addAnnotatedClass(PlanDeEstudios.class);
			confi.addAnnotatedClass(Persona.class);
			confi.addAnnotatedClass(PeriodoTiempo.class);
			confi.addAnnotatedClass(PeriodoCurso.class);
			confi.addAnnotatedClass(Perfil.class);
			confi.addAnnotatedClass(MovimientoInscripcionGrupo.class);
			confi.addAnnotatedClass(MovimientoAlumno.class);
			confi.addAnnotatedClass(Movimiento.class);
			confi.addAnnotatedClass(Institucion.class);
			confi.addAnnotatedClass(GrupoAlumnos.class);
			confi.addAnnotatedClass(Grupo.class);
			confi.addAnnotatedClass(Empleado.class);
			confi.addAnnotatedClass(CursoAcompanamiento.class);
			confi.addAnnotatedClass(ContenedorOptativa.class);
			confi.addAnnotatedClass(ContenedorElegible.class);
			confi.addAnnotatedClass(ContenedorAsignatura.class);
			confi.addAnnotatedClass(ClaseUADY.class);
			confi.addAnnotatedClass(CentroDocente.class);
			confi.addAnnotatedClass(AsignaturaBase.class);
			confi.addAnnotatedClass(AnioEscolar.class);
			confi.addAnnotatedClass(AlumnoUADYMatriculado.class);
			confi.addAnnotatedClass(Actividad.class);
			confi.addAnnotatedClass(ActividadesDePeriodoCurso.class);

			
			

			
			concreteSessionFactory=confi.buildSessionFactory();
			
			//((AnnotationConfiguration)concreteSessionFactory).addAnnotatedClass(annotatedClass);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}
	

	private static Session se;
	public static Session getSession()
			throws HibernateException {
		if(se==null){
			se=concreteSessionFactory.openSession();
			se.beginTransaction();
		}
		return se;
	}
	
	public CuestionarioResuelto crearCuestionarioResuelto(Ambito ambito,Persona persona,Cuestionario instrumento){
		
		CuestionarioResuelto cuestionario= new CuestionarioResuelto();
		cuestionario.setAmbito(ambito);
		cuestionario.setCompletado(false);
		cuestionario.setPersonaEncuestada(persona);
		cuestionario.setCuestionario(instrumento);
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
				 respuesta.setPersonaEncuestada(ambito.getPersona());
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
		 cuestionario.setRespuestasPregunta(respuestas);
		return cuestionario;
		
	}
	
	public Ambito getAmbito(int ambitoId){
		Session session=getSession();
		String query="select a from Ambito a "
				+ "join a.cuestionariosResueltos cue "
				+ "join cue.respuestasPregunta resp "
				+ "where a.id=:ambitoId";
		
		Ambito amb=	(Ambito)session.createQuery(query).setInteger("ambitoId", ambitoId).uniqueResult();
		return amb;
	}
	
	public Cuestionario getCuestionario(int ambitoId){
		Session session=getSession();
		String query="select c from Cuestionario c "
				+ "join c.grupopreguntas g "
				+ "join g.preguntas p "
				+ "where c.id=:ambitoId";
		
		Cuestionario amb=	(Cuestionario)session.createQuery(query).setInteger("ambitoId", ambitoId).uniqueResult();
		return amb;
	}
	
	public Persona getPersona(int ambitoId){
		Session session=getSession();
		String query="select p from Persona p "
				+ "where p.id=:ambitoId";
		
		Persona amb=	(Persona)session.createQuery(query).setInteger("ambitoId", ambitoId).uniqueResult();
		return amb;
	}
	
	public static void main(String[] args){
		
		//*crea los cuestionarios faltantes del alumno 07001514 que son 3*/
		//El alumno tiene 4 asignaturas pero uno si fue creado por el sieddo, descomentar las lineas //
		///si esto no es asi.//
		FixCrearCuestionariosResueltosFaltantes fix = new FixCrearCuestionariosResueltosFaltantes();
		Persona alumno=fix.getPersona(26277);//id alumno
		Cuestionario cuestionario=fix.getCuestionario(1);//estudiante oblig y opta
		//Ambito ambito4=fix.getAmbito(1338);// ambito(claseuady y profesor) ya fue creado.
		Ambito ambito1=fix.getAmbito(1137);// ambito(claseuady y profesor)
		Ambito ambito2=fix.getAmbito(1147);// ambito(claseuady y profesor)
		Ambito ambito3=fix.getAmbito(1154);// ambito(claseuady y profesor)
		CuestionarioResuelto c1 = fix.crearCuestionarioResuelto(ambito1, alumno, cuestionario);
		CuestionarioResuelto c2=fix.crearCuestionarioResuelto(ambito2, alumno, cuestionario);
		CuestionarioResuelto c3=fix.crearCuestionarioResuelto(ambito3, alumno, cuestionario);
		//CuestionarioResuelto c4 = fix.crearCuestionarioResuelto(ambito4, alumno, cuestionario);// ya esta creado
		fix.salvar(c1);
		fix.salvar(c2);
		fix.salvar(c3);
		//fix.salvar(c4); ya esta guardado
		Session session = getSession();
		 session.getTransaction().commit();
			
			session.close();
		System.out.println("Finalizado....");
	}
	public void salvar(CuestionarioResuelto cuestionario){
		Session session=getSession();
		if(cuestionario!=null)
		session.save(cuestionario);
	}
//	public void eliminarAmbito(Ambito ambito){
//		
//		if(ambito!=null){
//			Session session=getSession();
//			Iterator it = ambito.getCuestionariosResueltos().iterator();
//			
//			while(it.hasNext()){
//				CuestionarioResuelto cuestion= (CuestionarioResuelto)it.next();
//	
//				Iterator itRespuestaPregunta = cuestion.getRespuestasPregunta().iterator();
//				while(itRespuestaPregunta.hasNext()){
//					RespuestaPregunta resp = (RespuestaPregunta)itRespuestaPregunta.next();
//					session.delete(resp.getPreguntaHecha());
//					session.delete(resp);
//					
//				}
//				
//				session.delete(cuestion);
//				
//			}
//			session.delete(ambito);
//			
//	
//		        session.getTransaction().commit();
//				
//				session.close();
//				System.exit(0);
//		}else{
//			System.out.println("el ambito que se proporciono es nulo");
//		}
//		
//	}
}
