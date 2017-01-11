package com.myapp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tipoinstitucion")
public class TipoInstitucion implements Serializable{
	
	private static final HashMap INSTANCES = new HashMap();

	public static final TipoInstitucion UNIVERSIDAD = new TipoInstitucion(new Integer(1),"Universidad");

	public static final TipoInstitucion CAMPUS = new TipoInstitucion(new Integer(2),"Campus");

	public static final TipoInstitucion CENTRO_DOCENTE = new TipoInstitucion(new Integer(3),"Centro Docente");



	public static TipoInstitucion getInstance(Integer id) {
		TipoInstitucion TipoInstitucion = null;

		Object obj = INSTANCES.get(id);

		if (obj != null)
			TipoInstitucion = (TipoInstitucion) obj;

		return TipoInstitucion;
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "tipo")
	private String tipo;
	//private Set roles;

	public TipoInstitucion() {
	}

	/**
	 * @param id
	 * @param tipo
	 */
	 private TipoInstitucion(Integer id, String tipo) {
		 this.id = id;
		 this.tipo = tipo;
		// this.roles = null;
		 INSTANCES.put(this.getId(), this); 
	 }

	 public Integer getId() {
		 return id;
	 }

	 public void setId(Integer id) {
		 this.id = id;
	 }

	 public String getTipo() {
		 return tipo;
	 }

	 public void setTipo(String tipo) {
		 this.tipo = tipo;
	 }
//
//	 /**
//		 * @return the rol
//	 */
//	public Set getRoles() {
//		return roles;
//	}
//
//	/**
//	 * @param rol the rol to set
//	 */
//	public void setRoles(Set roles) {
//		this.roles = roles;
//	}
//	
//	/**
//	 * 
//	 * @param rol
//	 */
//	public void addRol(Rol rol) {
//		if (getRoles() == null ) {
//			setRoles (new HashSet() );
//		}
//		
//		getRoles().add( rol );
//		
//	}
	
	public Class getClassDomain() {
		return TipoInstitucion.class;
	}
}
