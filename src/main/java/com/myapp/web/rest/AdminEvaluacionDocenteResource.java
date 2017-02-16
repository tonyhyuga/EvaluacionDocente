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
import com.myapp.service.AdminEvaluacionDocenteService;
import com.myapp.service.EvaluacionDocenteService;
import com.myapp.service.UsuarioService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class AdminEvaluacionDocenteResource {

	private final Logger log = LoggerFactory.getLogger(AdminEvaluacionDocenteResource.class);


	
	
	@Inject
	private AdminEvaluacionDocenteService adminEvaluacionDocenteService;
	

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
	

}
