package com.myapp.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "institucion")
@Inheritance(strategy=InheritanceType.JOINED)
public class Institucion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "abreviatura") 
	private String abreviatura;
	 
	/**
	 *Un identificador de la universidad llamada:
	 *Clave de Centro de Trabajo.
	 */
	 @Column(name = "clave")
	private String clave;
	
	@ManyToMany(mappedBy="instituciones")
	 private Set<Empleado> empleados; 
	 
	 @OneToOne()
	 @JoinColumn (name = "idinstitucionpadre") 
	private Institucion padre;

	 @OneToOne()
	 @JoinColumn (name = "idtipoinstitucion") 
	 private TipoInstitucion tipoInstitucion;
	 
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

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Institucion getPadre() {
		return padre;
	}

	public void setPadre(Institucion padre) {
		this.padre = padre;
	}

	public TipoInstitucion getTipoInstitucion() {
		return tipoInstitucion;
	}

	public void setTipoInstitucion(TipoInstitucion tipoInstitucion) {
		this.tipoInstitucion = tipoInstitucion;
	}

	public Set<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	
	
	
}
