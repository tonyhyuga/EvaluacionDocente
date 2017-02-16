package com.myapp.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.repository.AmbitoRepository;
import com.myapp.repository.ClaseUADYRepository;

@Service
@Transactional
public class AdminEvaluacionDocenteService {
	
	private final Logger log = LoggerFactory.getLogger(AdminEvaluacionDocenteService.class);

	@Inject
	private ClaseUADYRepository claseRepository;
	
	@Inject
	private AmbitoRepository ambitoRepository;
	
	public Page<ClaseUADYDocenteWrapper> findClasesProfesor(Pageable pageable, Integer idAnio,Short indicePeriodo,Integer centroDocente,String search,boolean porEvaluar) {
		log.debug("Request to get all emeplados con paginacion");
		
		//Calendar fecha = Calendar.getInstance();
		Page<Object[]> clasest= null;

		if(porEvaluar)
		{
			clasest= claseRepository.getClasesUADYPorInstitucionEP(pageable,centroDocente,idAnio,indicePeriodo,search);
		}
		else
		{
			clasest= claseRepository.getClasesUADYPorInstitucion(pageable,centroDocente,idAnio,indicePeriodo,search);
		}	
		
		System.out.println("clases total sin sinodos: "+clasest.getTotalElements());

		List<ClaseUADYDocenteWrapper> wrappers= new ArrayList<ClaseUADYDocenteWrapper>();
		List<Object[]> clases = clasest.getContent();
		int idwrapper=0;
		for(int i=0;i<clases.size();i++){
			idwrapper++;
			ClaseUADY clase=(ClaseUADY)clases.get(i)[0];
			Empleado sinodo = (Empleado)clases.get(i)[1];
			ClaseUADYDocenteWrapper wraper= new ClaseUADYDocenteWrapper(clase,sinodo,idwrapper);
		
			Ambito ambitoObservaciones=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Observaciones");
			Ambito ambitoEvaluacion=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evaluaciones");
			if(ambitoEvaluacion==null)
			{
				wraper.setHayevaluaciones(false);
			}
			else
			{
				wraper.setHayevaluaciones(true);
			}
			Ambito ambitoEvidencias=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evidencias");;
			wraper.setObservaciones(ambitoObservaciones);
			wraper.setEvaluacion(ambitoEvaluacion);
			wraper.setEvidencias(ambitoEvidencias);
			wrappers.add(wraper);
		}
		Page<ClaseUADYDocenteWrapper> page = new PageImpl<ClaseUADYDocenteWrapper>(wrappers, pageable,clasest.getTotalElements());
		
		return page;
	}
}
