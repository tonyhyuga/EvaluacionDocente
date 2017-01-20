package com.myapp.domain.wrapper;

import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Persona;
import com.myapp.domain.encuestas.Ambito;

public class ClaseUADYAlumnoWrapper {
	
	private Integer id; 
	private ClaseUADY clase;
	private Persona profesor;
	

	private Ambito evaluacion;
	private boolean hayevaluaciones;

	
	public ClaseUADYAlumnoWrapper(){
		
	}
	public ClaseUADYAlumnoWrapper(ClaseUADY clase,Persona profesor,Integer id){
		this.clase = clase;
		this.profesor = profesor;
		this.id = id;
	}
	public ClaseUADY getClase() {
		return clase;
	}
	public void setClase(ClaseUADY clase) {
		this.clase = clase;
	}
	public Persona getProfesor() {
		return profesor;
	}
	public void setProfesor(Persona profesor) {
		this.profesor = profesor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Ambito getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Ambito evaluacion) {
		this.evaluacion = evaluacion;
	}
	
	public boolean getHayevaluaciones() {
		return hayevaluaciones;
	}
	public void setHayevaluaciones(boolean hayevaluaciones) {
		this.hayevaluaciones = hayevaluaciones;
	}
	
	
	public String getNombreCompletoAAN(){
		String nombre=(profesor.getApellidoPaterno()!=null?profesor.getApellidoPaterno()+" ":"")+" "+
				(profesor.getApellidoMaterno()!=null?profesor.getApellidoMaterno()+" ":"")+" "+
				(profesor.getNombres()!=null?profesor.getNombres():"");
		return nombre;
	}
	public String getNombreCompletoNAA(){
		return (profesor.getNombres()!=null?profesor.getNombres()+" ":"")+""+
				(profesor.getApellidoPaterno()!=null?profesor.getApellidoPaterno()+" ":"")+" "+
				(profesor.getApellidoMaterno()!=null?profesor.getApellidoMaterno():"");
	}

}
