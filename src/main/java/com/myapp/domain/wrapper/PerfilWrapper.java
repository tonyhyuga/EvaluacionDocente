package com.myapp.domain.wrapper;

import com.myapp.domain.Institucion;
import com.myapp.domain.Persona;
import com.myapp.domain.Usuario;

public class PerfilWrapper {
	
	private Persona persona;
	private Institucion institucion;
	private Integer id;
	private Integer rol;
	
	public PerfilWrapper(){}
	
	
	
	public PerfilWrapper(Persona persona,Institucion institucion,Integer rol){
		this.institucion=institucion;
		this.persona=persona;
		this.rol=rol;
	}


	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	

	public Integer getRol() {
		return rol;
	}



	public void setRol(Integer rol) {
		this.rol = rol;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
