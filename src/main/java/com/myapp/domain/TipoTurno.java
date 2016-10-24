package com.myapp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoturno")
public class TipoTurno implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final HashMap INSTANCES = new HashMap();
	
	public static final TipoTurno MATUTINO = new TipoTurno(new Integer(1), "Matutino");
	
	public static final TipoTurno VESPERTINO = new TipoTurno(new Integer(2), "Vespertino");
	
	public static final TipoTurno NOCTURNO = new TipoTurno(new Integer(3), "Nocturno");
	
	public static final TipoTurno MIXTO = new TipoTurno(new Integer(4), "Mixto");
	
	public static final TipoTurno NOESPECIFICADO = new TipoTurno(new Integer(5), "No especificado");
	
	public static TipoTurno getInstance(Integer id) {
		TipoTurno tipoTurno = null;

		Object obj = INSTANCES.get(id);

		if (obj != null)
			tipoTurno = (TipoTurno) obj;

		return tipoTurno;
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}

	public TipoTurno() {
	}

	private TipoTurno(Integer id, String tipoTurno) {
		this.id = id;
		this.tipo = tipoTurno;
		INSTANCES.put(this.getId(), this); 
	}

	@Id
	private Integer id;
	
	@Column(name = "tipo")
	private String tipo;
	
	
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
	 * @return the turno
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param turno the turno to set
	 */
	public void setTipo(String turno) {
		this.tipo = turno;
	}
	 	 
	public Class getClassDomain() {
		return TipoTurno.class;
	}
}
