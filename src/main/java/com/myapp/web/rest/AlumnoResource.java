package com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.PeriodoCurso;
import com.myapp.domain.PlanDeEstudios;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.domain.wrapper.CuestionarioResueltoWrapper;
import com.myapp.service.EvaluacionDocenteService;
import com.myapp.service.PeridoCursoService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class AlumnoResource {

	private final Logger log = LoggerFactory.getLogger(AlumnoResource.class);

	@Inject
	private EvaluacionDocenteService evaDoceService;
	
	@Inject
	private PeridoCursoService pcService;
	

	@RequestMapping(value = "/clasesalumno",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ClaseUADYDocenteWrapper>> getclasesAlumno(Pageable pageable, HttpSession session)
			throws URISyntaxException {
		log.debug("REST request to get a page of Clases Alumno");

		AlumnoUADYMatriculado alumno = (AlumnoUADYMatriculado)session.getAttribute("Alumno");
		PlanDeEstudios plan = (PlanDeEstudios)session.getAttribute("PlanDeEstudios");
		if(alumno!=null && plan!=null){
			PeriodoCurso pc = pcService.getPeriodoCursoActualPlanDeEstudios(plan.getId());
			Page<ClaseUADYDocenteWrapper> page = evaDoceService.findClasesByAlumno(pageable,alumno.getPersona().getId(),pc.getId());
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/clasesalumno");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/evaluacion/{ambito}",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<CuestionarioResueltoWrapper> getQuestionarie(@PathVariable Integer ambito,HttpSession sesion)
			throws URISyntaxException {
		log.debug("REST request to get a Cuestionario para crear {}", ambito);

		AlumnoUADYMatriculado alumno = (AlumnoUADYMatriculado)sesion.getAttribute("Alumno");
		CuestionarioResueltoWrapper resuelto = evaDoceService.findCuestionarioResuelto(ambito, alumno.getPersona().getId());
		return Optional.ofNullable(resuelto)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	 @RequestMapping(value = "/evaluacion",
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
