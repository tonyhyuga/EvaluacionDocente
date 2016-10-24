package com.myapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "programaeducativo")
public class ProgramaEducativo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "clavecgse")
	private Long claveCGSE;
	
	@Column(name = "nemotecnico")
	private String nemotecnico;

	/**
	 * Nombre con el cual se hace referencia al programa de estudios
	 */
	//  No se implemento toUpperCase, los nombres de Programas consideran mayusculas y minusculas
	@Column(name = "nombre")
	private String nombre;

	/**
	 * Abreviatura con la cual se hace referencia al programa de estudios
	 */
	@Column(name = "abreviatura")
	private String abreviatura; 	// Se implemento toUpperCase
	
	@Column(name = "activo")
	private boolean activo;
	
	@OneToOne()
	@JoinColumn (name = "idcentrodocente")
	private CentroDocente centroDocente;
//	private Set planesDeEstudio;
	
	@OneToOne()
	@JoinColumn (name = "idtiponivel")
	private TipoNivel tipoNivel;
	
	@Column(name = "genero")
	private String genero; //M Masculino, F Femenino

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Long getClaveCGSE() {
		return claveCGSE;
	}

	public void setClaveCGSE(Long claveCGSE) {
		this.claveCGSE = claveCGSE;
	}

	public String getNemotecnico() {
		return nemotecnico;
	}

	public void setNemotecnico(String nemotecnico) {
		this.nemotecnico = nemotecnico;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public CentroDocente getCentroDocente() {
		return centroDocente;
	}

	public void setCentroDocente(CentroDocente centroDocente) {
		this.centroDocente = centroDocente;
	}

	public TipoNivel getTipoNivel() {
		return tipoNivel;
	}

	public void setTipoNivel(TipoNivel tipoNivel) {
		this.tipoNivel = tipoNivel;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}



	
}
