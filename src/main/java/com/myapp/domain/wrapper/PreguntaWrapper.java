package com.myapp.domain.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.myapp.domain.encuestas.OpcionPregunta;
import com.myapp.domain.encuestas.Pregunta;
import com.myapp.domain.encuestas.RespuestaPregunta;

public class PreguntaWrapper {

	Pregunta pregunta;
	RespuestaPregunta respuestaPregunta;	
	
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public RespuestaPregunta getRespuesta() {
		return respuestaPregunta;
	}
	public void setRespuesta(RespuestaPregunta respuesta) {
		this.respuestaPregunta = respuesta;
	}
	public List<OpcionPregunta> getRespuestasForUI() {
	List<OpcionPregunta> gs = new ArrayList<OpcionPregunta>(pregunta.getRespuestas());
	
	Collections.sort(gs, new Comparator<OpcionPregunta>() {

		public int compare(OpcionPregunta o1, OpcionPregunta o2) {
			return o1.getRespuestaString().compareTo(o2.getRespuestaString());
		}
	});
	
	return gs;
	}	
}
