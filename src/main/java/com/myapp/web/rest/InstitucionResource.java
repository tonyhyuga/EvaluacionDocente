package com.myapp.web.rest;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.myapp.domain.Institucion;
import com.myapp.service.InstitucionService;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class InstitucionResource {

	private final Logger log = LoggerFactory.getLogger(InstitucionResource.class);
	
	@Inject
	private InstitucionService institucionService;
	
	@RequestMapping(value = "/instituciones",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<List<Institucion>> getInstituciones()
			throws URISyntaxException {
		log.debug("REST request to get a Intituciones del tipo {}");
		
		List<Institucion> dependencias = institucionService.getDependencias();
		
		//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(null, "/apo/admonusuarios");
		return Optional.ofNullable(dependencias)
	            .map(result -> new ResponseEntity<>(
              result,
              HttpStatus.OK))
          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
