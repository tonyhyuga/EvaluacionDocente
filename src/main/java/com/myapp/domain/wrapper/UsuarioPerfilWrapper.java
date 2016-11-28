package com.myapp.domain.wrapper;

import com.myapp.domain.Perfil;
import com.myapp.domain.Persona;

public class UsuarioPerfilWrapper {
	
	private Perfil perfil;
	private Persona persona;
	private Integer id;
	
	public UsuarioPerfilWrapper(Integer id,Perfil perfil,Persona persona){
		this.id=id;
		this.perfil=perfil;
		this.persona=persona;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombreCompletoNAA(){
		return (persona.getNombres()!=null?persona.getNombres()+" ":"")+""+
				(persona.getApellidoPaterno()!=null?persona.getApellidoPaterno()+" ":"")+" "+
				(persona.getApellidoMaterno()!=null?persona.getApellidoMaterno():"");
	}
	
	

}
