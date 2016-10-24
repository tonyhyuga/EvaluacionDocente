package com.myapp.domain.encuestas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="opcionpregunta")
public class OpcionPregunta {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="respuestastring")
	private String respuestaString;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Transient
	@JsonIgnore
	@JoinColumn(name="idpregunta")
	private Pregunta pregunta;
	
	@Column(name="ponderacion")
	private int ponderacion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRespuestaString() {
		return respuestaString;
	}
	public void setRespuestaString(String respuestaString) {
		this.respuestaString = respuestaString;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}	
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public int getPonderacion() {
		return ponderacion;
	}
	public void setPonderacion(int ponderacion) {
		this.ponderacion = ponderacion;
	}
}
