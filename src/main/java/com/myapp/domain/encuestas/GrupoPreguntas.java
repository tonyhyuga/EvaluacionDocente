package com.myapp.domain.encuestas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.IndexColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="grupopreguntas")
public class GrupoPreguntas {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="instrucciones")
	private String instrucciones;

	@ManyToMany(mappedBy="grupopreguntas", fetch = FetchType.LAZY)
	@JsonIgnore
	@Transient
	private Set<Cuestionario> cuestionarios;
	
	@Column(name="estilovisual")
	 private String estiloVisual;
	
	@Column(name="indiceorden")
	 private Integer orden;
	
	@Column(name="cabecera")
	 private String cabecera;
	

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	@ManyToMany(cascade = {CascadeType.ALL})
	@OrderBy (value="indiceOrden")
	@JoinTable(name="liga_grupopreguntas_pregunta", 
	joinColumns={@JoinColumn(name="idgrupopreguntas", referencedColumnName="id")}, 
	inverseJoinColumns={@JoinColumn(name="idpregunta",referencedColumnName="id")})
	private Set<Pregunta> preguntas;
	

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}
	
	
	public String getEstiloVisual() {
		return estiloVisual;
	}

	public void setEstiloVisual(String estiloVisual) {
		this.estiloVisual = estiloVisual;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public Set<Pregunta> setPreguntas() {
		return preguntas;
	}	
	
//	public List<Pregunta> getPreguntasForUI() {
//		List<Pregunta> gs = new ArrayList<Pregunta>(preguntas);
//		
//		Collections.sort(gs, new Comparator<Pregunta>() {
//
//			public int compare(Pregunta o1, Pregunta o2) {
//				return o1.getPreguntaString().compareTo(o2.getPreguntaString());
//			}
//		});
//		
//		return gs;
//	}	
//	
//	public void setPreguntasForUI(List<Pregunta> pregunta) {
//		
//	}
	
	public Set<Cuestionario> getCuestionarios() {
		return cuestionarios;
	}

	public void setCuestionarios(Set<Cuestionario> cuestionarios) {
		this.cuestionarios = cuestionarios;
	}

	public String getInstrucciones() {
		return instrucciones;
	}

	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
	
	

}
