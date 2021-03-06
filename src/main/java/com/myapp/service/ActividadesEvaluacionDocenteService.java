package com.myapp.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

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
	        
	        Calendar now = Calendar.getInstance();
	        Calendar fin=actividad.getFin();
	        now.set(Calendar.DAY_OF_MONTH,fin.get(Calendar.DAY_OF_MONTH));
	        now.set(Calendar.MONTH,fin.get(Calendar.MONTH));
	        now.set(Calendar.YEAR,fin.get(Calendar.YEAR));
	        now.set(Calendar.HOUR_OF_DAY,23);
	        now.set(Calendar.MINUTE,59);
	        now.set(Calendar.SECOND,58);
	        now.set(Calendar.MILLISECOND,0);
	        actividad.setFin(now);	
	                
	        ActividadesEvaluacionDocente result = actividadesEvaDoceRepository.save(actividad);
	        return result;
	    }
	 @Transactional(readOnly = true) 
	public ActividadesEvaluacionDocente findOne(Integer id) {
		return actividadesEvaDoceRepository.findOne(id);
	}
	@Transactional(readOnly = true) 
	public List<TipoActividadEvaluacionDocente> getTiposActividades() {
		
		return actividadesEvaDoceRepository.getTipoActividades();
	}
	@Transactional(readOnly = true) 
	public List<AnioEscolar> getAniosEscolares() {
		// TODO Auto-generated method stub
		return anioEscolarRepository.getAllDesc();
	}

	@Transactional(readOnly = true) 
	public ActividadesEvaluacionDocente getActividadActualByTipo(Integer idClase, Integer idTipoActividadEva,
			Integer indicePeriodo,Integer idAnioEscolar) {
		
		return actividadesEvaDoceRepository.getActividadActualByTipo(idClase,idTipoActividadEva,
				indicePeriodo,idAnioEscolar);
		
	}
	@Transactional(readOnly = true) 
	public boolean validarNuevaActividad(ActividadesEvaluacionDocente actividad) {
		
		ActividadesEvaluacionDocente aux=actividadesEvaDoceRepository.existActividad(actividad.getAnioEscolar().getId(),
				actividad.getIndicePeriodo(),actividad.getInstitucion().getId(),actividad.getTipoActividad().getId());
		return aux!=null;
	}

}
