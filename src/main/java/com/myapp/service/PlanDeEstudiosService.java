package com.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.PlanDeEstudios;
import com.myapp.repository.PlanDeEstudiosRepository;

@Service
@Transactional
public class PlanDeEstudiosService {
	
	@Inject
	private PlanDeEstudiosRepository planRepository;
	
	@Transactional(readOnly = true) 
	public PlanDeEstudios getPlanInscrito(Integer idalumno){
		List<PlanDeEstudios> planes = planRepository.getPlanesDeEstudiosInscritosAlumno(idalumno);
		if(planes!=null && planes.size()>0)
			return planes.get(0);
		else 
			return null;
	}
}
