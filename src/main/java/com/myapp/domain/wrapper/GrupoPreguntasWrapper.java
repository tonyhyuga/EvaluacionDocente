package com.myapp.domain.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.myapp.domain.encuestas.GrupoPreguntas;
import com.myapp.domain.encuestas.Pregunta;

public class GrupoPreguntasWrapper {

	GrupoPreguntas grupoPreguntas;
	Set<PreguntaWrapper> preguntasWrapper;


	public Set<PreguntaWrapper> getPreguntasWrapper() {
		return preguntasWrapper;
	}

	public void setPreguntasWrapper(Set<PreguntaWrapper> preguntasWrapper) {
		this.preguntasWrapper = preguntasWrapper;
	}

	public GrupoPreguntas getGrupoPreguntas() {
		return grupoPreguntas;
	}

	public void setGrupoPreguntas(GrupoPreguntas grupoPreguntas) {
		this.grupoPreguntas = grupoPreguntas;
	}
	
	public List<PreguntaWrapper> getPreguntasWrapperForUI() {
		List<PreguntaWrapper> gs = new ArrayList<PreguntaWrapper>(preguntasWrapper);
		
		Collections.sort(gs, new Comparator<PreguntaWrapper>() {

			@Override
			public int compare(PreguntaWrapper o1, PreguntaWrapper o2) {
				return o1.pregunta.getIndiceOrden().compareTo(o2.pregunta.getIndiceOrden());
			}
		});
		
		return gs;
	}	
	
		
}
