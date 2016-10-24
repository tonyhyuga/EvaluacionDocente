package com.myapp.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empleado")
public class Empleado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "titulo")
	private String titulo;
    
	@Column(name = "activo")
	private boolean activo;
    
	@Column(name = "porhonorarios")
	private boolean porHonorarios;
    
	@Column(name = "titulocompleto")
	private String tituloCompleto;
    
	@ManyToMany()
	@JoinTable(name="liga_institucion_empleados", 
				joinColumns={@JoinColumn(name="idempleado")}, 
				inverseJoinColumns={@JoinColumn(name="idinstitucion")})
	private Set<Institucion> instituciones;
    
    /************************************************************************
	 * Atributo al transformar herencia de Persona a Relacion Empleado -> Persona 
	 *******************************************************************/
    @OneToOne()
	@JoinColumn (name = "idpersona")
	private Persona persona;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public boolean isPorHonorarios() {
		return porHonorarios;
	}


	public void setPorHonorarios(boolean porHonorarios) {
		this.porHonorarios = porHonorarios;
	}


	public String getTituloCompleto() {
		return tituloCompleto;
	}


	public void setTituloCompleto(String tituloCompleto) {
		this.tituloCompleto = tituloCompleto;
	}


	public Persona getPersona() {
		return persona;
	}


	public void setPersona(Persona persona) {
		this.persona = persona;
	}
    
}
