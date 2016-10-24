package com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.UsuarioEmpleado;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.RespuestaPregunta;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.domain.wrapper.CuestionarioResueltoWrapper;
import com.myapp.service.EvaluacionDocenteService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class DocenteResource {


	private final Logger log = LoggerFactory.getLogger(DocenteResource.class);

	@Inject
	private EvaluacionDocenteService evaDoceService; 

	@RequestMapping(value = "/clasesdocente",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ClaseUADYDocenteWrapper>> getclasesDocente(Pageable pageable,HttpSession session)
			throws URISyntaxException {
		log.debug("REST request to get a page of Usuarios");
		int id=89774;
		Empleado docente = (Empleado)session.getAttribute("Empleado");
		Page<ClaseUADYDocenteWrapper> page = evaDoceService.findClasesByDocente(pageable,docente.getId());
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/clasesdocente");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/autoevaluacion/{ambito}/{profesor}",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<CuestionarioResueltoWrapper> getQuestionarie(@PathVariable Integer ambito, @PathVariable Integer profesor)
			throws URISyntaxException {
		log.debug("REST request to get a Cuestionario para crear {}", ambito, profesor);

		CuestionarioResueltoWrapper resuelto = evaDoceService.findCuestionarioResuelto(ambito, profesor);
		return Optional.ofNullable(resuelto)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	 @RequestMapping(value = "/autoevaluacion",
			  method = RequestMethod.POST,
			  produces = MediaType.APPLICATION_JSON_VALUE)
			@Timed
			public ResponseEntity<CuestionarioResuelto> createUsuario(@RequestBody CuestionarioResuelto wraper) throws URISyntaxException {
			  log.debug("REST request to save Ambito : {}", wraper);
			  //evaDoceService.getC
			  CuestionarioResuelto cuestionario = wraper;
//			  if (cuestionario.getId() != null) {
//			      return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ambito", "idexists", "A new ambito cannot already have an ID")).body(null);
//			  }
//			  Set<RespuestaPregunta> respuestas = cuestionario.getRespuestasPregunta();
//			  Iterator<RespuestaPregunta> respues = respuestas.iterator();
//			  while(respues.hasNext()){
//				  RespuestaPregunta r = respues.next();
//				  System.out.println("->"+r.getOpcion()+" id: "+r.getId());
//			  }
			  
			  CuestionarioResuelto result = evaDoceService.actualizar(cuestionario);
			  return ResponseEntity.created(new URI("/apo/gestoracademico/" + result.getId()))
			      .headers(HeaderUtil.createEntityCreationAlert("Cuestionario Resuelto", result.getId().toString()))
			      .body(result);
			}
}
