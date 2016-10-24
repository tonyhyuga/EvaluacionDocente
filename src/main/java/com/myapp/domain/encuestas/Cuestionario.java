package com.myapp.domain.encuestas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
@Table(name="cuestionario")
public class Cuestionario {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.ALL})
	@Fetch(FetchMode.JOIN)
	@JoinTable(name="liga_cuestionario_grupopreguntas", 
	joinColumns={@JoinColumn(name="idcuestionario", referencedColumnName="id")}, 
	inverseJoinColumns={@JoinColumn(name="idgrupopreguntas",referencedColumnName="id")})
	private Set<GrupoPreguntas> grupopreguntas;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="tipo_cuestionario")
	private Integer tipoinstrumento;
	
	@Column(name="estilovisual")
	 private String estiloVisual;
	

	
	
//	@ManyToMany(cascade = {CascadeType.ALL})
//	@JoinTable(name="liga_cuestionario_ambito", 
//	joinColumns={@JoinColumn(name="idcuestionario", referencedColumnName="id")}, 
//	inverseJoinColumns={@JoinColumn(name="idambito",referencedColumnName="id")})
//	private Set<Ambito> ambitos;
	
	

	

	public String getEstiloVisual() {
		return estiloVisual;
	}
	public void setEstiloVisual(String estiloVisual) {
		this.estiloVisual = estiloVisual;
	}
	public Set<GrupoPreguntas> getGrupopreguntas() {
		return grupopreguntas;
	}
	
	public List<GrupoPreguntas> getGrupoForUI() {
		List<GrupoPreguntas> gs = new ArrayList<GrupoPreguntas>(grupopreguntas);
		
		Collections.sort(gs, new Comparator<GrupoPreguntas>() {

			public int compare(GrupoPreguntas o1, GrupoPreguntas o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		
		return gs;
	}
	public void setGrupopreguntas(Set<GrupoPreguntas> grupopreguntas) {
		this.grupopreguntas = grupopreguntas;
	}
//	public Set<GrupoPreguntas> getGrupopreguntasOrdendas() {
//
//		List<GrupoPreguntas> gs = new ArrayList<GrupoPreguntas>(grupopreguntas);
//		
//		Collections.sort(gs, new Comparator<GrupoPreguntas>() {
//
//			public int compare(GrupoPreguntas o1, GrupoPreguntas o2) {
//				return o1.getId().compareTo(o2.getId());
//			}
//		});
//		
//		return grupopreguntas;
//	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getTipoinstrumento() {
		return tipoinstrumento;
	}
	public void setTipoinstrumento(Integer tipoinstrumento) {
		this.tipoinstrumento = tipoinstrumento;
	}
	
	
}
