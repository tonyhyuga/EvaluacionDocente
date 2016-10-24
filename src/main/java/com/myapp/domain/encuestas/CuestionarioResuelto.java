package com.myapp.domain.encuestas;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.domain.Persona;

@Entity
@Table(name="cuestionarioresuelto")
public class CuestionarioResuelto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn (name = "idcuestionario") 
	@JsonIgnore
	private Cuestionario cuestionario;
	
	@ManyToOne()
	@JoinColumn(name="idambito")
	@JsonIgnore
	private Ambito  ambito;
	
	@OneToMany(mappedBy="cuestionarioResuelto",cascade = {CascadeType.ALL})
//	@JoinColumn(name="idcuestionarioresuelto")
	private Set<RespuestaPregunta> respuestasPregunta;
	

	@Column(name="completado")
	private Boolean completado;
	
	@OneToOne
	@JoinColumn (name = "idpersonaencuestada") 
	Persona personaEncuestada;

	public Set<RespuestaPregunta> getRespuestasPregunta() {
		return respuestasPregunta;
	}

	public void setRespuestasPregunta(Set<RespuestaPregunta> respuestasPregunta) {
		this.respuestasPregunta = respuestasPregunta;
	}

	public Boolean getCompletado() {
		return completado;
	}

	public void setCompletado(Boolean completado) {
		this.completado = completado;
	}

	public Persona getPersonaEncuestada() {
		return personaEncuestada;
	}

	public void setPersonaEncuestada(Persona persona) {
		this.personaEncuestada = persona;
	}



	public Ambito getAmbito() {
		return ambito;
	}

	public void setAmbito(Ambito ambito) {
		this.ambito = ambito;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cuestionario getCuestionario() {
		return cuestionario;
	}

	public void setCuestionario(Cuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}



}
