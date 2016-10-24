package com.myapp.domain.encuestas;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.domain.Persona;

@Entity
@Table(name="respuestapregunta")
public class RespuestaPregunta implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@Column(name="respuestaseleccionada")
	private String respuestaSeleccionada;
	
	@Column(name="opcion")
	private int opcion;
	
	@Column(name="idopcionpregunta")
	private int opcionrespuesta;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn (name = "idpregunta") 
	private Pregunta pregunta;
	
	@OneToOne (cascade = {CascadeType.ALL})
	@JoinColumn (name = "idpreguntahecha") 
	private PreguntaHecha preguntaHecha;

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonIgnore
	@JoinColumn(name="idcuestionarioresuelto")
	private CuestionarioResuelto cuestionarioResuelto;
	
	@OneToOne
	@JoinColumn (name = "idpersonaencuestada") 
	private Persona personaEncuestada;
	
	@ManyToOne
	@JoinColumn(name="idambito")
	@JsonIgnore
	private Ambito  ambito;
	

	public Ambito getAmbito() {
		return ambito;
	}
	public void setAmbito(Ambito ambito) {
		this.ambito = ambito;
	}
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public CuestionarioResuelto getCuestionarioResuelto() {
		return cuestionarioResuelto;
	}
	public void setCuestionarioResuelto(CuestionarioResuelto cuestionarioResuelto) {
		this.cuestionarioResuelto = cuestionarioResuelto;
	}
	public Persona getPersonaEncuestada() {
		return personaEncuestada;
	}
	public void setPersonaEncuestada(Persona personaEncuestada) {
		this.personaEncuestada = personaEncuestada;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRespuestaSeleccionada() {
		return respuestaSeleccionada;
	}
	public void setRespuestaSeleccionada(String respuestaSeleccionada) {
		this.respuestaSeleccionada = respuestaSeleccionada;
	}
	public int getOpcion() {
		return opcion;
	}
	public void setOpcion(int opcion) {
		this.opcion = opcion;
	}
//	public int getPonderacion() {
//		return ponderacion;
//	}
//	public void setPonderacion(int ponderacion) {
//		this.ponderacion = ponderacion;
//	}
	public PreguntaHecha getPreguntaHecha() {
		return preguntaHecha;
	}
	public void setPreguntaHecha(PreguntaHecha preguntaHecha) {
		this.preguntaHecha = preguntaHecha;
	}
	public int getOpcionrespuesta() {
		return opcionrespuesta;
	}
	public void setOpcionrespuesta(int opcionrespuesta) {
		this.opcionrespuesta = opcionrespuesta;
	}

	public String toString(){
		return "RespuestaPregunta{" +
            "id=" + id +
            ", opcion='" + opcion + "'" +
            '}';
	}
	
}
