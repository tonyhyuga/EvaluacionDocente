package com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

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
import com.myapp.domain.AnioEscolar;
import com.myapp.domain.Institucion;
import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;
import com.myapp.service.ActividadesEvaluacionDocenteService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class ActividadEvaluacionDocenteResource {
	
	private final Logger log = LoggerFactory.getLogger(ActividadEvaluacionDocenteResource.class);

	@Inject
	private ActividadesEvaluacionDocenteService actividadesEvaluacionDocenteService;
	
	
	@RequestMapping(value = "/actividades",
	        method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	    @Timed
	public ResponseEntity<List<ActividadesEvaluacionDocente>> getActividades(Pageable pageable)
			throws URISyntaxException {
		log.debug("REST request to get a page of ActividadesEvaluacionDocente ");
		
	
		 Page<ActividadesEvaluacionDocente> page = actividadesEvaluacionDocenteService.getAllActividadesEvaluacionDocente(pageable);
	     HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/actividades");
	     return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
  @RequestMapping(value = "/actividades",
  method = RequestMethod.POST,
  produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<ActividadesEvaluacionDocente> createActividad(@RequestBody ActividadesEvaluacionDocente actividad) throws URISyntaxException {
  log.debug("REST request to save ActividadesEvaluacionDocente : {}", actividad.getId());
  if(actividad.getInicio().after(actividad.getFin())){
	  return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("actividad", "noValid", "La fecha inicial debe ser menor a la fecha final")).body(null);
  } 
  if(actividadesEvaluacionDocenteService.validarNuevaActividad(actividad)){
	  return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("actividad", "idexists", "Ya existe una actividad similar en el año")).body(null);
  }
  if (actividad.getId()==0 || actividad.getId() == null ){ 
	  ActividadesEvaluacionDocente result = actividadesEvaluacionDocenteService.save(actividad);
	  return ResponseEntity.created(new URI("/apo/actividades/" + result.getId()))
	      .headers(HeaderUtil.createEntityCreationAlert("actividad", result.getId().toString()))
	      .body(result);
  }else{
	  return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("actividad", "idexists", "Ya existe una actividad similar en el año")).body(null);
  }
}

/**
* PUT  /usuarios : Updates an existing usuario.
*
* @param ActividadesEvaluacionDocente the actividad to update
* @return the ResponseEntity with status 200 (OK) and with body the updated usuario,
* or with status 400 (Bad Request) if the usuario is not valid,
* or with status 500 (Internal Server Error) if the usuario couldnt be updated
* @throws URISyntaxException if the Location URI syntax is incorrect
*/
@RequestMapping(value = "/actividades",
  method = RequestMethod.PUT,
  produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<ActividadesEvaluacionDocente> updateActividad(@RequestBody ActividadesEvaluacionDocente actividad) throws URISyntaxException {
  log.debug("REST request to update Actividad : {}", actividad);
  if (actividad.getId() == null) {
      return createActividad(actividad);
  }
  if(actividad.getInicio().after(actividad.getFin())){
	  return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("actividad", "noValid", "La fecha inicial debe ser menor a la fecha final")).body(null);
  } 
  ActividadesEvaluacionDocente result = actividadesEvaluacionDocenteService.save(actividad);
  return ResponseEntity.ok()
      .headers(HeaderUtil.createEntityUpdateAlert("ActividadesEvaluacionDocente", actividad.getId().toString()))
      .body(result);
}

@RequestMapping(value = "/actividades/{id}",
method = RequestMethod.GET,
produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<ActividadesEvaluacionDocente> getActividadEvalucion(@PathVariable Integer id) {
log.debug("REST request to get Actividad : {}", id);
ActividadesEvaluacionDocente usuario = actividadesEvaluacionDocenteService.findOne(id);
return Optional.ofNullable(usuario)
  .map(result -> new ResponseEntity<>(
      result,
      HttpStatus.OK))
  .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

@RequestMapping(value = "/tiposActividades",
method = RequestMethod.GET,
produces = MediaType.ALL_VALUE)
@Timed
public ResponseEntity<List<TipoActividadEvaluacionDocente>> getTiposActividades()
throws URISyntaxException {
log.debug("REST request to get tipos de Actividades {}");

List<TipoActividadEvaluacionDocente> tipos = actividadesEvaluacionDocenteService.getTiposActividades();

//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(null, "/apo/admonusuarios");
return Optional.ofNullable(tipos)
    .map(result -> new ResponseEntity<>(
  result,
  HttpStatus.OK))
.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}
@RequestMapping(value = "/aniosEscolares",
method = RequestMethod.GET,
produces = MediaType.ALL_VALUE)
@Timed
public ResponseEntity<List<AnioEscolar>> getAniosEscolares()
throws URISyntaxException {
log.debug("REST request to get Años Escolares{}");

List<AnioEscolar> aniosEscolares = actividadesEvaluacionDocenteService.getAniosEscolares();

//HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(null, "/apo/admonusuarios");
return Optional.ofNullable(aniosEscolares)
    .map(result -> new ResponseEntity<>(
  result,
  HttpStatus.OK))
.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

}
