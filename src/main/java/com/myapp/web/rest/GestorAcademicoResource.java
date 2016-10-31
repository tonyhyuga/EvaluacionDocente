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
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.Institucion;
import com.myapp.domain.Usuario;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
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
	private UsuarioService usuarioService; 

	@RequestMapping(value = "/profesores",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	public ResponseEntity<List<ClaseUADYDocenteWrapper>> getDocentes(Pageable pageable,HttpSession httpsession)
			throws URISyntaxException {
		log.debug("REST request to get a page of Asignaturas de la institucion");
		Empleado empleado = (Empleado)httpsession.getAttribute("Empleado");
		List<Integer> inst=usuarioService.getInstitucionByRol(empleado.getPersona().getId(),"GESTOR ACADEMICO");
		Page<ClaseUADYDocenteWrapper> page = evaDoceService.findDocentesByInstitucion(pageable,inst);
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
}
