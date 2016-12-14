package com.myapp.web.reportes;

import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.Persona;

public class WrapperAlumnosCuestionariosResueltos {
	private Persona persona;
	private AlumnoUADYMatriculado alumnoUsady;
	private boolean respondio;
	
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public AlumnoUADYMatriculado getAlumnoUsady() {
		return alumnoUsady;
	}
	public void setAlumnoUsady(AlumnoUADYMatriculado alumnoUsady) {
		this.alumnoUsady = alumnoUsady;
	}
	public boolean isRespondio() {
		return respondio;
	}
	public void setRespondio(boolean respondio) {
		this.respondio = respondio;
	}
	
	
}
