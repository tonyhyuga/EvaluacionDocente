package com.myapp.fix;

import java.util.Iterator;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.myapp.domain.Actividad;
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

public class FixEliminarAmbito {
	private static final SessionFactory concreteSessionFactory;
	static {
		try {
			Properties prop= new Properties();
			prop.setProperty("hibernate.connection.url", "jdbc:mysql://148.209.12.14:3306/base_soporte?useOldAliasMetadataBehavior=true");
			prop.setProperty("hibernate.connection.username", "ileon");
			prop.setProperty("hibernate.connection.password", "w36b3c4r10");
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
	
	
	public Ambito getAmbito(int ambitoId){
		Session session=getSession();
		String query="select a from Ambito a "
				+ "join a.cuestionariosResueltos cue "
				+ "join cue.respuestasPregunta resp "
				+ "where a.id=:ambitoId";
		
		Ambito amb=	(Ambito)session.createQuery(query).setInteger("ambitoId", ambitoId).uniqueResult();
		return amb;
	}
	
	public static void main(String[] args){
		FixEliminarAmbito fix = new FixEliminarAmbito();
		Ambito a =	fix.getAmbito(4);
		fix.eliminarAmbito(a);
	}
	
	public void eliminarAmbito(Ambito ambito){
		
		if(ambito!=null){
			Session session=getSession();
			Iterator it = ambito.getCuestionariosResueltos().iterator();
			
			while(it.hasNext()){
				CuestionarioResuelto cuestion= (CuestionarioResuelto)it.next();
	
				Iterator itRespuestaPregunta = cuestion.getRespuestasPregunta().iterator();
				while(itRespuestaPregunta.hasNext()){
					RespuestaPregunta resp = (RespuestaPregunta)itRespuestaPregunta.next();
					session.delete(resp.getPreguntaHecha());
					session.delete(resp);
					
				}
				
				session.delete(cuestion);
				
			}
			session.delete(ambito);
			
	
		        session.getTransaction().commit();
				
				session.close();
				System.exit(0);
		}else{
			System.out.println("el ambito que se proporciono es nulo");
		}
		
	}
}
