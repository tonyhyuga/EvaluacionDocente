package com.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.Institucion;
import com.myapp.repository.InstitucionRepository;

/**
 * Service Implementation for managing Usuario.
 */
@Service
@Transactional
public class InstitucionService {

    private final Logger log = LoggerFactory.getLogger(InstitucionService.class);
    
    @Inject
    private InstitucionRepository institucionRepository;
    
   

    /**
     * Save a usuario.
     *
     * @param usuario the entity to save
     * @return the persisted entity
     */
    public Institucion save(Institucion institucion) {
        log.debug("Request to save Institucion : {}", institucion);
        Institucion result = institucionRepository.save(institucion);
        return result;
    }

    /**
     *  Get all the usuarios.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Institucion> findAll(Pageable pageable) {
        log.debug("Request to get all Instituciones");
        Page<Institucion> result = institucionRepository.findAll(pageable);
        //AnioEscolar result2 = anioEscolarRepository.getAnioActual();
       // PlanDeEstudios result2= planRepository.getPlanDeEstudios(7221);
      
        return result;
    }

    /**
     *  Get one usuario by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Institucion findOne(Integer id) {
        log.debug("Request to get Institucion : {}", id);
        Institucion institucion = institucionRepository.findOne(id);
        return institucion;
    }

    /**
     *  Delete the  usuario by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Integer id) {
        log.debug("Request to delete Intitucion : {}", id);
        institucionRepository.delete(id);
    }
    
    public List<Institucion> getDependencias() {
        log.debug("Request to get  Institucion : {}");
       return institucionRepository.getDependencias();
    }
    

}
