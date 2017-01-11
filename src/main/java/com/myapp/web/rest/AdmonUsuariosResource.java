package com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.myapp.domain.Institucion;
import com.myapp.domain.Perfil;
import com.myapp.domain.Persona;
import com.myapp.domain.Usuario;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.wrapper.PerfilWrapper;
import com.myapp.domain.wrapper.UsuarioPerfilWrapper;
import com.myapp.service.PerfilUsuarioService;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class AdmonUsuariosResource {

	private final Logger log = LoggerFactory.getLogger(AdmonUsuariosResource.class);
	
	@Inject
	private PerfilUsuarioService perfilService;
	
	@RequestMapping(value = "/admonusuarios/{tipo}",
			method = RequestMethod.GET,
			produces = MediaType.ALL_VALUE)
	@Timed
	public ResponseEntity<List<UsuarioPerfilWrapper>> getPerfiles(Pageable pageable,@PathVariable Integer tipo)
			throws URISyntaxException {
		log.debug("REST request to get a page of Perfiles del tipo {}", tipo);
		
		 Page<UsuarioPerfilWrapper> page = perfilService.getPerfiles(pageable,tipo);
		
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/admonusuarios");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	  @RequestMapping(value = "/admonusuarios",
			  method = RequestMethod.POST,
			  produces = MediaType.APPLICATION_JSON_VALUE)
			@Timed
			public ResponseEntity<Perfil> createPerfil(
					@RequestBody  PerfilWrapper wapper) throws URISyntaxException {
			  log.debug("REST request to save nuevo Perfil tipo {}",wapper.getRol());

			  Usuario usuario=perfilService.getUsuarioEmpleadobyPersona(wapper.getPersona().getId());
			  Perfil result = perfilService.crearPerfil(usuario, wapper.getInstitucion(), wapper.getRol());
			  return ResponseEntity.created(new URI("/apo/gestoracademico/" + result.getId()))
			      .headers(HeaderUtil.createEntityCreationAlert("usuario", result.getId().toString()))
			      .body(result);
			}
	  
	  
    @RequestMapping(value = "/admonusuarios/{id}/{tipo}",
    method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<Perfil> getPerfil(@PathVariable Integer id,@PathVariable Integer tipo) {
    log.debug("REST request to get Perfil : {}", id,tipo);
    Perfil usuario = perfilService.findOne(id);
    return Optional.ofNullable(usuario)
        .map(result -> new ResponseEntity<>(
            result,
            HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
}

/**
 * DELETE  /admonusuarios/:id : delete the "id" perfil.
 *
 * @param id the id of the perfil to delete
 * @return the ResponseEntity with status 200 (OK)
 */
@RequestMapping(value = "/admonusuarios/{id}",
    method = RequestMethod.DELETE,
    produces = MediaType.APPLICATION_JSON_VALUE)
@Timed
public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
    log.debug("REST request to delete perfil : {}", id);
    perfilService.delete(id);
    return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("perfil", id.toString())).build();
}
}
