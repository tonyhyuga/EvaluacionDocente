package com.myapp.domain.encuestas;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pregunta")
public class Pregunta {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToMany(mappedBy="preguntas", fetch = FetchType.LAZY)
	@Transient
	@JsonIgnore
	private Set<GrupoPreguntas> grupoPreguntas;
	
	@Column(name="preguntastring")
	private String preguntaString;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="version")
	private Integer version;
	
	@Column(name="obligatoria")
	private Boolean obligatoria;
	
	@Column(name="comportamientopreguntasdependientes")
	 private Boolean comportamientoPreguntasDependientes;
	
	@Column(name="estilovisual")
	 private String estiloVisual;
	
	@Column(name="indiceorden")
	 private Integer indiceOrden;
	

	public Integer getIndiceOrden() {
		return indiceOrden;
	}
	public void setIndiceOrden(Integer indiceOrden) {
		this.indiceOrden = indiceOrden;
	}
	@ManyToOne
	@JoinColumn(name="idpreguntaactivador")
	
	private Pregunta preguntaActivador;
	

	@OneToMany
	@JoinColumn(name="idpregunta")
	private Set<OpcionPregunta> respuestas;
	
	
	@OneToMany(mappedBy="preguntaActivador")
	@Transient
	@JsonIgnore
	 private Set<Pregunta> preguntasDependientes;
	
	@OneToOne
	@JoinColumn (name = "idtipopregunta") 
	private TipoPregunta tipoPregunta;

	public TipoPregunta getTipoPregunta() {
		return tipoPregunta;
	}
	
	public void setTipoPregunta(TipoPregunta tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
	public Set<Pregunta> getPreguntasDependientes() {
		return preguntasDependientes;
	}
	public void setPreguntasDependientes(Set<Pregunta> preguntasDependientes) {
		this.preguntasDependientes = preguntasDependientes;
	}
	public Set<GrupoPreguntas> getGrupoPreguntas() {
		return grupoPreguntas;
	}
	public void setGrupoPreguntas(Set<GrupoPreguntas> grupoPreguntas) {
		this.grupoPreguntas = grupoPreguntas;
	}
	public String getPreguntaString() {
		return preguntaString;
	}
	public void setPreguntaString(String preguntaString) {
		this.preguntaString = preguntaString;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public Boolean getObligatoria() {
		return obligatoria;
	}
	public void setObligatoria(Boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public Boolean getComportamientoPreguntasDependientes() {
		return comportamientoPreguntasDependientes;
	}
	public void setComportamientoPreguntasDependientes(Boolean comportamientoPreguntasDependientes) {
		this.comportamientoPreguntasDependientes = comportamientoPreguntasDependientes;
	}
	public String getEstiloVisual() {
		return estiloVisual;
	}
	public void setEstiloVisual(String estiloVisual) {
		this.estiloVisual = estiloVisual;
	}
	public Pregunta getPreguntaActivador() {
		return preguntaActivador;
	}
	public void setPreguntaActivador(Pregunta preguntaActivador) {
		this.preguntaActivador = preguntaActivador;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Set<OpcionPregunta> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(Set<OpcionPregunta> respuestas) {
		this.respuestas = respuestas;
	}
	
//	public List<OpcionPregunta> getRespuestasForUI() {
//		List<OpcionPregunta> gs = new ArrayList<OpcionPregunta>(respuestas);
//		
//		Collections.sort(gs, new Comparator<OpcionPregunta>() {
//
//			public int compare(OpcionPregunta o1, OpcionPregunta o2) {
//				return o1.getRespuestaString().compareTo(o2.getRespuestaString());
//			}
//		});
//		
//		return gs;
//	}	
//	public void setRespuestasForUI(List<OpcionPregunta> opcionPreguntas){
//		
//	}
	
}
