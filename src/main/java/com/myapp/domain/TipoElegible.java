package com.myapp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoelegible")
public class TipoElegible implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final HashMap<Integer, TipoElegible> INSTANCES = new HashMap();
	
	public static final TipoElegible SIN_CLASIFICAR = new TipoElegible(new Integer(1),"Sin clasificar");

	public static final TipoElegible OPTATIVA = new TipoElegible(new Integer(2),"Optativa");

	public static final TipoElegible LIBRE = new TipoElegible(new Integer(3),"Libre");

	public static TipoElegible getInstance(Integer id) {		
		return INSTANCES.get(id);
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}

  @Id
	private Integer id;
  
  @Column(name = "descripcion")
	private String descripcion;
	
	/**
	 * 
	 */
	public TipoElegible() {
		this.id = null;
		this.descripcion = null;
	}
	
	/**
	 * @param id
	 * @param tipo
	 */
	private TipoElegible(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;		
		INSTANCES.put(this.getId(), this);
	}

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
}
