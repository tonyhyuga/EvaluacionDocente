package com.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.calendarizacion.ActividadesEvaluacionDocente;
import com.myapp.domain.calendarizacion.TipoActividadEvaluacionDocente;
import com.myapp.repository.ActividadesEvaluacionDocenteRepository;
import com.myapp.repository.AnioEscolarRepository;

@Service
@Transactional
public class ActividadesEvaluacionDocenteService {
	private final Logger log = LoggerFactory.getLogger(ActividadesEvaluacionDocenteService.class);
	
	@Inject
	private ActividadesEvaluacionDocenteRepository actividadesEvaDoceRepository;
	
	@Inject
	private AnioEscolarRepository anioEscolarRepository;
	
	/**obtiene todas las actividades de evaluacion docente*/
	@Transactional(readOnly = true) 
	public Page<ActividadesEvaluacionDocente> getAllActividadesEvaluacionDocente(Pageable page){
	
		Page<ActividadesEvaluacionDocente> actividades = actividadesEvaDoceRepository.findAll(page);
		return actividades;
	}
	
	@Transactional(readOnly = true) 
	public Page<ActividadesEvaluacionDocente> getAllActividadesEvaluacionDocente(Pageable page,int tipoActividad){
	
		Page<ActividadesEvaluacionDocente> actividades = actividadesEvaDoceRepository.findAllByTipo(page,tipoActividad);
		return actividades;
	}
	
	 public ActividadesEvaluacionDocente save(ActividadesEvaluacionDocente actividad) {
	        log.debug("Request to save actividad : {}", actividad);
	        ActividadesEvaluacionDocente result = actividadesEvaDoceRepository.save(actividad);
	        return result;
	    }

	public ActividadesEvaluacionDocente findOne(Integer id) {
		return actividadesEvaDoceRepository.findOne(id);
	}

	public List<TipoActividadEvaluacionDocente> getTiposActividades() {
		
		return actividadesEvaDoceRepository.getTipoActividades();
	}

	public List<AnioEscolar> getAniosEscolares() {
		// TODO Auto-generated method stub
		return anioEscolarRepository.getAllDesc();
	}
}