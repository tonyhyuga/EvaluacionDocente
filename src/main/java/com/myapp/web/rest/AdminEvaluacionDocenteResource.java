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
import com.myapp.domain.ReporteEvaluativo;
import com.myapp.domain.ReporteEvaluativoLibre;
import com.myapp.domain.ReporteEvaluativoOyO;
import com.myapp.domain.Usuario;
import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.wrapper.AlumnoCuestionarioContestadoWrapper;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.domain.wrapper.ReporteEvaluativoWrapper;
import com.myapp.domain.wrapper.ReporteLibreWrapper;
import com.myapp.domain.wrapper.ReporteOOWrapper;
import com.myapp.service.ActividadesEvaluacionDocenteService;
import com.myapp.service.AdminEvaluacionDocenteService;
import com.myapp.service.EvaluacionDocenteService;
import com.myapp.service.ReporteEvaluacionDocenteService;
import com.myapp.service.UsuarioService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class AdminEvaluacionDocenteResource {

	private final Logger log = LoggerFactory.getLogger(AdminEvaluacionDocenteResource.class);


	
	
	@Inject
	private AdminEvaluacionDocenteService adminEvaluacionDocenteService;
	@Inject
	private ReporteEvaluacionDocenteService reporteService;
	

	@RequestMapping(value = "/diie{search},{type},{indice},{anio},{centro},{ep}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ClaseUADYDocenteWrapper>> getClasesDocentes(Pageable pageable,HttpSession httpsession,
			@PathVariable String search,@PathVariable Integer type,@PathVariable Short indice,@PathVariable Integer anio,@PathVariable Integer centro,@PathVariable boolean ep)
			throws URISyntaxException {
		log.debug("REST request to get a page of Clases Para DIIE {}",type,search,indice,anio,centro);
		
//		Empleado empleado = (Empleado)httpsession.getAttribute("Empleado");
//		List<Integer> inst=usuarioService.getInstitucionByRol(empleado.getPersona().getId(),"GESTOR ACADEMICO");
		Page<ClaseUADYDocenteWrapper> page = adminEvaluacionDocenteService.findClasesProfesor(pageable,anio,indice,centro,search,ep);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/diie");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/reporteEvaluativo{search},{type},{indice},{anio},{centro},{ep}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ReporteEvaluativoWrapper>> getAsignaturaProfesor(Pageable pageable,HttpSession httpsession,
			@PathVariable String search,@PathVariable Integer type,@PathVariable Integer indice,@PathVariable Integer anio,@PathVariable Integer centro,@PathVariable boolean ep)
			throws URISyntaxException {
		log.debug("REST request to get a page of asiganturas Para Reporte DIIE {}",type,search,indice,anio,centro);
		
//		Empleado empleado = (Empleado)httpsession.getAttribute("Empleado");
//		List<Integer> inst=usuarioService.getInstitucionByRol(empleado.getPersona().getId(),"GESTOR ACADEMICO");
		Page<ReporteEvaluativoWrapper> page = adminEvaluacionDocenteService.findAsignaturaProfesor(pageable,anio,indice,centro,search,ep);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/reporteEvaluativo");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reporteEvaluativo/reporteOO{idA},{idP},{idAnio},{idIndice},{tipoEvaluacion},{idDependencia}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<ReporteOOWrapper> getReporteEvaluativoOO(@PathVariable Integer idA,@PathVariable Integer idP,
			@PathVariable Integer idAnio,@PathVariable Integer idIndice,@PathVariable String tipoEvaluacion,@PathVariable Integer idDependencia)
			throws URISyntaxException {
		log.debug("REST request to get a Reporte O y O {}",idA,idP,idAnio,idIndice,idDependencia,tipoEvaluacion);
		
		ReporteEvaluativo reporte = reporteService.findReporte(idA,idP,idDependencia,idAnio,idIndice,tipoEvaluacion);
		ReporteEvaluativoOyO reporteOO=null;
		if(reporte==null)
			reporteOO = adminEvaluacionDocenteService.generarReporteEvaluativoOO(idA,idP,idAnio,idIndice,tipoEvaluacion,idDependencia);
		else
		if(reporte instanceof ReporteEvaluativoOyO)
			reporteOO=(ReporteEvaluativoOyO)reporte;
		
		ReporteOOWrapper wraper=new ReporteOOWrapper(reporteOO,1);
		
		return Optional.ofNullable(wraper)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "/reporteEvaluativo/reporteLI{idA},{idP},{idAnio},{idIndice},{tipoEvaluacion},{idDependencia}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<ReporteLibreWrapper> getReporteEvaluativoLI(@PathVariable Integer idA,@PathVariable Integer idP,
			@PathVariable Integer idAnio,@PathVariable Integer idIndice,@PathVariable String tipoEvaluacion,@PathVariable Integer idDependencia)
			throws URISyntaxException {
		log.debug("REST request to get Reporte Li {}",idA,idP,idAnio,idIndice,idDependencia,tipoEvaluacion);
		
		ReporteEvaluativo reporte = reporteService.findReporte(idA,idP,idDependencia,idAnio,idIndice,tipoEvaluacion);
		ReporteEvaluativoLibre reporteLi=null;
		if(reporte==null)
			 reporteLi=adminEvaluacionDocenteService.generarReporteEvaluativoLI(idA,idP,idAnio,idIndice,tipoEvaluacion,idDependencia);
		if(reporte instanceof ReporteEvaluativoLibre)
			reporteLi=(ReporteEvaluativoLibre)reporte;
		
		ReporteLibreWrapper wrapper= new ReporteLibreWrapper(reporteLi, 1);
		
		return Optional.ofNullable(wrapper)
	            .map(result -> new ResponseEntity<>(
	                    result,
	                    HttpStatus.OK))
	                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
