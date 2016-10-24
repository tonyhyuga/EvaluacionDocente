package com.myapp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipogrupoalumnos")
public class TipoGrupoAlumnos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final HashMap INSTANCES = new HashMap();
	public static final TipoGrupoAlumnos NORMAL = new TipoGrupoAlumnos(new Integer(1), "Normal");
	public static final TipoGrupoAlumnos EQUIVALENCIA = new TipoGrupoAlumnos(new Integer(2), "Equivalencia");
	
	public static TipoGrupoAlumnos getInstance(Integer id) {
		TipoGrupoAlumnos tipoGrupoAlumnos = null;

		Object obj = INSTANCES.get(id);

		if(obj!=null)
			tipoGrupoAlumnos = (TipoGrupoAlumnos)obj;

		return tipoGrupoAlumnos;
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}
	
	@Id
	private Integer id;
	
	@Column(name = "tipo")
	private String tipo;
	
	protected TipoGrupoAlumnos(){}
	
	/**
	 * @param id
	 * @param tipo
	 */
	private TipoGrupoAlumnos(Integer id, String tipoAula) {
		this.id = id;
		this.tipo = tipoAula;
		INSTANCES.put(this.getId(), this);
	}



	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipoAula) {
		this.tipo = tipoAula;
	}
}
