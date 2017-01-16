package com.myapp.web.rest;

import java.net.URISyntaxException;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.myapp.domain.Usuario;
import com.myapp.service.EvaluacionDocenteService;
import com.myapp.service.UsuarioService;

@RestController
@RequestMapping("/apo")
public class AmbitoResource {
	
	private final Logger log = LoggerFactory.getLogger(AmbitoResource.class);

	@Inject
	private EvaluacionDocenteService evaDoceService;
	
	@Inject
	private UsuarioService usuarioService; 
	
	@RequestMapping(value = "/ambito/{id}/{idp}",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<Usuario> getAmbito(@PathVariable Long clase, @PathVariable Long profesor)
			throws URISyntaxException {
		log.debug("REST request to get Ambito {}", clase,profesor);
		
	
		Usuario o = usuarioService.findOne(clase);
		//ClaseUADYDocenteWrapper claseUADYDocenteWrapper=
		return Optional.ofNullable(o)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}
