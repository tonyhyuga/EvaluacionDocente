package com.myapp.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.Institucion;
import com.myapp.domain.Persona;
import com.myapp.domain.Usuario;
import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.wrapper.AlumnoCuestionarioContestadoWrapper;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.service.ActividadesEvaluacionDocenteService;
import com.myapp.service.EvaluacionDocenteService;
import com.myapp.service.UsuarioService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class GestorAcademicoResource {

	private final Logger log = LoggerFactory.getLogger(GestorAcademicoResource.class);

	@Inject
	private EvaluacionDocenteService evaDoceService;
	
	@Inject
	private ActividadesEvaluacionDocenteService actividadesService;
	
	@Inject
	private UsuarioService usuarioService; 

	@RequestMapping(value = "/profesores{search},{type},{indice},{anio},{ep}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ClaseUADYDocenteWrapper>> getDocentes(Pageable pageable,HttpSession httpsession,
			@PathVariable String search,@PathVariable Integer type,@PathVariable Short indice,@PathVariable Integer anio,@PathVariable boolean ep)
			throws URISyntaxException {
		log.debug("REST request to get a page of Asignaturas de la institucion {}",type,search,indice,anio,search,ep);
		
		Empleado empleado = (Empleado)httpsession.getAttribute("Empleado");
		List<Integer> inst=usuarioService.getInstitucionByRol(empleado.getPersona().getId(),"GESTOR ACADEMICO");
		Page<ClaseUADYDocenteWrapper> page = evaDoceService.findDocentesByInstitucion(pageable,inst,anio,indice,search,ep);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/docentesgestor");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/profesores/{id}/{idp}",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<Ambito> getAmbito(@PathVariable Integer id, @PathVariable Integer idp)
			throws URISyntaxException {
		log.debug("REST request to get a Ambito para crear {}", id,idp);
		
		if(!evaDoceService.validarActividad(id,TipoActividadEvaluacionDocente.REGISTRAR_EVALUACION.getId())){
			return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ambito", "noActividad", "No hay Actividad "
					+TipoActividadEvaluacionDocente.REGISTRAR_EVALUACION.getTipo()+ " vigente.")).body(null);
		}
		Ambito ambito=evaDoceService.findAmbito(id, idp);
		//return new ResponseEntity<>(ambito, HttpStatus.OK);
		return Optional.ofNullable(ambito)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
  @RequestMapping(value = "/profesores",
  method = RequestMethod.POST,
  produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<Ambito> createUsuario(@RequestBody Ambito ambito) throws URISyntaxException {
  log.debug("REST request to save Ambito : {}", ambito);
  if (ambito.getId() != null) {
      return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ambito", "idexists", "A new ambito cannot already have an ID")).body(null);
  }
  Ambito result = evaDoceService.crearAmbito(ambito);
  return ResponseEntity.created(new URI("/apo/gestoracademico/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert("usuario", result.getId().toString()))
      .body(result);
}
  
  
	@RequestMapping(value = "/alumosquecontestaron/{ambito}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<AlumnoCuestionarioContestadoWrapper>> getAlumosQueContestaron(@PathVariable Integer ambito,Pageable pageable,HttpSession httpsession)
			throws URISyntaxException {
		

		List<AlumnoCuestionarioContestadoWrapper> listaWrappers= evaDoceService.getWrappersForDownloadPage(ambito);
		
		Page<AlumnoCuestionarioContestadoWrapper> page = new PageImpl<AlumnoCuestionarioContestadoWrapper>(listaWrappers, pageable,listaWrappers.size());
		
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/docentesgestor");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	 @RequestMapping(value = "/reporteclasealumno/{idAmbito}", 
	    		method = RequestMethod.GET,
	    		produces= MediaType.ALL_VALUE)
	 public void reporteAlumnos(@PathVariable int idAmbito, final HttpServletRequest request, final HttpServletResponse response) {
	    	    	
		 	Ambito ambito=evaDoceService.getAmbito(idAmbito);
	    	File file=evaDoceService.getReporteAlumnos(ambito);
	        log.trace("Write response...");
	        try (InputStream fileInputStream = new FileInputStream(file);
	                OutputStream output = response.getOutputStream();) {

	            response.reset();

	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + createPath(ambito,"Evaluacion") + "\"");

	            IOUtils.copyLarge(fileInputStream, output);
	            output.flush();
	            
	            if(file.delete()){
		        	  System.out.println("borrado");
		          }else{
		        	  System.out.println("No borrado");
		          }
	            file.deleteOnExit();
	        } catch (IOException e) {
	            log.error(e.getMessage(), e);
	        }

	    }
	 
	 @RequestMapping(value = "/reporteclaseprofesor/{idAmbito}", 
	    		method = RequestMethod.GET,
	    		produces= MediaType.ALL_VALUE)
	    public void reporteProfesor(@PathVariable int idAmbito, final HttpServletRequest request, final HttpServletResponse response) {
	    	    	
		 Ambito ambito=evaDoceService.getAmbito(idAmbito);
	    	File file=evaDoceService.getReporteProfesor(ambito);
	        log.trace("Write response...");
	        try (InputStream fileInputStream = new FileInputStream(file);
	                OutputStream output = response.getOutputStream();) {

	            response.reset();

	            response.setContentType("application/octet-stream");
	            response.setHeader("Content-Disposition", "attachment; filename=\"" + createPath(ambito,"Autoevaluacion") + "\"");

	            IOUtils.copyLarge(fileInputStream, output);
	            output.flush();
	            
	          if(file.delete()){
	        	  System.out.println("borrado");
	          }else{
	        	  System.out.println("No borrado");
	          }
	        } catch (IOException e) {
	            log.error(e.getMessage(), e);
	        }

	    }
	 
	 private String createPath(Ambito ambito,String tipo){
		 String name=tipo;
		 Persona profesor=ambito.getPersona();
		 ClaseUADY clase=ambito.getClaseUady();
		 name+="-"+clase.getAsignaturaBase().getNombre();
		 name+="-"+(profesor.getNombres()!=null?profesor.getNombres()+" ":"")+""+
						(profesor.getApellidoPaterno()!=null?profesor.getApellidoPaterno()+" ":"")+" "+
						(profesor.getApellidoMaterno()!=null?profesor.getApellidoMaterno():"");
			
		 name+=".xlsx";
		 return name;
	 }
}
