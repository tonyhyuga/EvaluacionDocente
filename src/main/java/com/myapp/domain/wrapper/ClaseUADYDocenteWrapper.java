package com.myapp.domain.wrapper;

import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.encuestas.Ambito;

public class ClaseUADYDocenteWrapper {
	
	private Integer id; 
	private ClaseUADY clase;
	private Empleado profesor;
	
	private Ambito observaciones;
	private Ambito evaluacion;
	private Ambito evidencias;
	
	public ClaseUADYDocenteWrapper(){
		
	}
	public ClaseUADYDocenteWrapper(ClaseUADY clase,Empleado profesor,Integer id){
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
	public Empleado getProfesor() {
		return profesor;
	}
	public void setProfesor(Empleado profesor) {
		this.profesor = profesor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Ambito getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(Ambito observaciones) {
		this.observaciones = observaciones;
	}
	public Ambito getEvaluacion() {
		return evaluacion;
	}
	public void setEvaluacion(Ambito evaluacion) {
		this.evaluacion = evaluacion;
	}
	public Ambito getEvidencias() {
		return evidencias;
	}
	public void setEvidencias(Ambito evidencias) {
		this.evidencias = evidencias;
	}
	
	public String getNombreCompletoAAN(){
		String nombre=(profesor.getPersona().getApellidoPaterno()!=null?profesor.getPersona().getApellidoPaterno()+" ":"")+" "+
				(profesor.getPersona().getApellidoMaterno()!=null?profesor.getPersona().getApellidoMaterno()+" ":"")+" "+
				(profesor.getPersona().getNombres()!=null?profesor.getPersona().getNombres():"");
		return nombre;
	}
	public String getNombreCompletoNAA(){
		return (profesor.getPersona().getNombres()!=null?profesor.getPersona().getNombres()+" ":"")+""+
				(profesor.getPersona().getApellidoPaterno()!=null?profesor.getPersona().getApellidoPaterno()+" ":"")+" "+
				(profesor.getPersona().getApellidoMaterno()!=null?profesor.getPersona().getApellidoMaterno():"");
	}

}
