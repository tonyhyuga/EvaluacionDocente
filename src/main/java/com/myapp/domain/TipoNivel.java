package com.myapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tiponivel")
public class TipoNivel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final HashMap INSTANCES = new HashMap();
	private static List<TipoNivel> POSGRADOS = null;

	public static final TipoNivel BACHILLER = new TipoNivel(new Integer(1), "Bachiller");	

	public static final TipoNivel LICENCIATURA = new TipoNivel(new Integer(2), "Licenciatura");

	public static final TipoNivel ESPECIALIZACION = 
		new TipoNivel(new Integer(3), "Especialización");

	public static final TipoNivel MAESTRIA = new TipoNivel(new Integer(4), "Maestría");

	public static final TipoNivel DOCTORADO = new TipoNivel(new Integer(5), "Doctorado");	
	
	public static final TipoNivel TECNICO_SUPERIOR_UNIVERSITARIO = 
		new TipoNivel(new Integer(6), "Técnico Superior Universitario");
	
	public static final TipoNivel DIPLOMADO = new TipoNivel(new Integer(7), "Diplomado");

	public static TipoNivel getInstance(Integer id) {
		TipoNivel tipoNivel = null;

		Object obj = INSTANCES.get(id);

		if (obj != null)
			tipoNivel = (TipoNivel) obj;

		return tipoNivel;
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}
	public static List<TipoNivel> getPosgrados(){
		if(POSGRADOS == null){
			POSGRADOS = new ArrayList();
			POSGRADOS.add(TipoNivel.DOCTORADO);
			POSGRADOS.add(TipoNivel.ESPECIALIZACION);
			POSGRADOS.add(TipoNivel.MAESTRIA);
		}
		return POSGRADOS;
	}

	public TipoNivel() {
	}

	private TipoNivel(Integer id, String tipoNivel) {
		this.id = id;
		this.tipo = tipoNivel;
		INSTANCES.put(this.getId(), this); 
	}

	@Id
	private Integer id;
	
	@Column(name = "tipo")
	private String tipo;
	
	@Column(name = "claveniveldgp")
	private Integer claveNivelDGP;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipoNivel) {
		this.tipo = tipoNivel;
	}

	public Class getClassDomain() {
		return TipoNivel.class;
	}
	public Integer getClaveNivelDGP() {
		return claveNivelDGP;
	}

	public void setClaveNivelDGP(Integer claveNivelDGP) {
		this.claveNivelDGP = claveNivelDGP;
	}
}
