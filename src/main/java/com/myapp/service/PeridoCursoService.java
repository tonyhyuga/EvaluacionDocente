package com.myapp.service;

import java.util.Calendar;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.PeriodoCurso;
import com.myapp.repository.PeriodoCursoRepository;

@Service
@Transactional
public class PeridoCursoService {
	private final Logger log = LoggerFactory.getLogger(PeridoCursoService.class);
	
	@Inject
	private PeriodoCursoRepository pcRepository;
	
	/**obtiene el perido curso actual del plan apartir de la fecha actual, 
	 * no toma en cuenta la recalendarizacion del plan.*/
	@Transactional(readOnly = true) 
	public PeriodoCurso getPeriodoCursoActualPlanDeEstudios(Integer idPlan){
		Calendar hoy= Calendar.getInstance();
		PeriodoCurso pc = pcRepository.getPeridoCursoActual(idPlan, hoy);
		return pc;
	}
}
