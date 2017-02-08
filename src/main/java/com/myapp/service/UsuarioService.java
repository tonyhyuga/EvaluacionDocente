package com.myapp.service;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Institucion;
import com.myapp.domain.PlanDeEstudios;
import com.myapp.domain.Usuario;
import com.myapp.repository.AnioEscolarRepository;
import com.myapp.repository.ClaseUADYRepository;
import com.myapp.repository.PlanDeEstudiosRepository;
import com.myapp.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Usuario.
 */
@Service
@Transactional
public class UsuarioService {

    private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    
    @Inject
    private UsuarioRepository usuarioRepository;
    
    @Inject
    private AnioEscolarRepository anioEscolarRepository;
    @Inject
    private PlanDeEstudiosRepository planRepository;
    @Inject
    private ClaseUADYRepository claseRepository;

    /**
     * Save a usuario.
     *
     * @param usuario the entity to save
     * @return the persisted entity
     */
    public Usuario save(Usuario usuario) {
        log.debug("Request to save Usuario : {}", usuario);
        Usuario result = usuarioRepository.save(usuario);
        return result;
    }


    /**
     *  Get one usuario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Usuario findOne(Long id) {
        log.debug("Request to get Usuario : {}", id);
        Usuario usuario = usuarioRepository.findOne(id);
        return usuario;
    }

    /**
     *  Delete the  usuario by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Usuario : {}", id);
        usuarioRepository.delete(id);
    }
    
    public List<Integer> getInstitucionByRol(Integer idpersona,String rol){
    	 log.debug("Request to get Intitucion con rol: {}",idpersona, rol);
    	 List<Integer> instituciones=usuarioRepository.getInstitucionesByRol(idpersona,rol);
    	return instituciones;
    }
}
