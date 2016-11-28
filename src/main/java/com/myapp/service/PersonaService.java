package com.myapp.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.Persona;
import com.myapp.repository.PersonaRepository;

@Service
@Transactional
public class PersonaService {
	private final Logger log = LoggerFactory.getLogger(PersonaService.class);

	@Inject
	private PersonaRepository personaRepository;

	@Transactional(readOnly = true) 
	public List<Persona> buscarEmpleado(String criterio){
		List<Persona> planes = personaRepository.buscarEmpleado(criterio);
		
			return planes;
	}
}
