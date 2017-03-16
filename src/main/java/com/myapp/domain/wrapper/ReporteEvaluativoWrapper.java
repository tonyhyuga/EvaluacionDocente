package com.myapp.domain.wrapper;

import com.myapp.domain.AsignaturaBase;
import com.myapp.domain.Persona;

public class ReporteEvaluativoWrapper {
	
	private Integer id; 
	private AsignaturaBase asignatura;
	private Persona profesor; 
	private String formaEvaluacion;
	private boolean hasReporte;
	
	public ReporteEvaluativoWrapper(Integer id,AsignaturaBase asignatura,Persona sinodo,String tipo){
		this.id=id;
		this.asignatura=asignatura;
		this.profesor=sinodo;
		this.formaEvaluacion=tipo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AsignaturaBase getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(AsignaturaBase asignatura) {
		this.asignatura = asignatura;
	}
	public Persona getProfesor() {
		return profesor;
	}
	public void setProfesor(Persona profesor) {
		this.profesor = profesor;
	}
	public String getFormaEvaluacion() {
		return formaEvaluacion;
	}
	public void setFormaEvaluacion(String formaEvaluacion) {
		this.formaEvaluacion = formaEvaluacion;
	}

	public boolean isHasReporte() {
		return hasReporte;
	}

	public void setHasReporte(boolean hasReporte) {
		this.hasReporte = hasReporte;
	}
	
	public String getNombreCompletoAAN(){
			String nombre=(profesor.getApellidoPaterno()!=null?profesor.getApellidoPaterno()+" ":"")+" "+
					(profesor.getApellidoMaterno()!=null?profesor.getApellidoMaterno()+" ":"")+" "+
					(profesor.getNombres()!=null?profesor.getNombres():"");
			return nombre;
		
	}
	
	

}
