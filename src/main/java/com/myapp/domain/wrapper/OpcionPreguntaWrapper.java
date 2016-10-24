package com.myapp.domain.wrapper;

import java.util.Set;

import com.myapp.domain.encuestas.OpcionPregunta;

public class OpcionPreguntaWrapper {
	
	private Set<OpcionPregunta> opciones;

	public Set<OpcionPregunta> getOpciones() {
		return opciones;
	}

	public void setOpciones(Set<OpcionPregunta> opciones) {
		this.opciones = opciones;
	}
	
	

}
