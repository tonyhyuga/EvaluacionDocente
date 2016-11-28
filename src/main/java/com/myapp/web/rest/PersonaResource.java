package com.myapp.web.rest;

import java.net.URISyntaxException;
import java.util.List;
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
import com.myapp.domain.Persona;
import com.myapp.service.PersonaService;

@RestController
@RequestMapping("/apo")
public class PersonaResource {
	
	private final Logger log = LoggerFactory.getLogger(PersonaResource.class);
	
	@Inject
	private PersonaService personaService;
	
	@RequestMapping(value = "/buscarpersona/{criterio}",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<List<Persona>> buscarPersona(@PathVariable String criterio)
			throws URISyntaxException {
		log.debug("REST request to buscando Empleado {}",criterio);
		System.out.println(criterio);
		List<Persona> dependencias = personaService.buscarEmpleado(criterio);
		
		
		return Optional.ofNullable(dependencias)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
