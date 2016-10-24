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
@Table(name = "asignaturabase")
public class AsignaturaBase  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre; // No se implementa toUpperCase ya que los
	                       // nombres de las asignaturas oficiales consta de mayusculas y minusculas

	/**
	 * Identificador de la asignatura que la hace única dentro del universo de
	 * asignaturas
	 */
	@Column(name = "clave")
	private String clave; // Se implementa toUpperCase

	/**
	 * Numero de créditos asignados a la asignatura, estos se tomaran en cuenta
	 * si no se especifica el valor en créditos dentro del plan de estudios
	 */
	@Column(name = "creditos")
	private short creditos;

	/**
	 * Indica si la asignatura esta activa o si ya no esta en uso
	 */
	@Column(name = "activa")
	private boolean activa;

	 @OneToOne()
	 @JoinColumn (name = "idinstitucion") 
	private Institucion institucion;
	

	@Column(name = "mefi")
	private boolean mefi;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public short getCreditos() {
		return creditos;
	}


	public void setCreditos(short creditos) {
		this.creditos = creditos;
	}


	public boolean isActiva() {
		return activa;
	}


	public void setActiva(boolean activa) {
		this.activa = activa;
	}


	public Institucion getInstitucion() {
		return institucion;
	}


	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


	public boolean isMefi() {
		return mefi;
	}


	public void setMefi(boolean mefi) {
		this.mefi = mefi;
	}

	
	
}
