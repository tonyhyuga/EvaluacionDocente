package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "contenedorelegible")
@PrimaryKeyJoinColumn(name="id")
public class ContenedorElegible extends ContenedorAsignatura {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "comprometible")
	private boolean comprometible;

	@OneToOne()
	@JoinColumn (name = "idasignaturabase")
	private AsignaturaBase asignaturaBase;
	
	@OneToOne()
	@JoinColumn (name = "idtipoelegible")
	private TipoElegible tipoElegible;

	 //private Set gruposAsignaturasElegibles;
	 
	 
	public boolean isComprometible() {
		return comprometible;
	}

	public void setComprometible(boolean comprometible) {
		this.comprometible = comprometible;
	}

	public AsignaturaBase getAsignaturaBase() {
		return asignaturaBase;
	}

	public void setAsignaturaBase(AsignaturaBase asignaturaBase) {
		this.asignaturaBase = asignaturaBase;
	}

	public TipoElegible getTipoElegible() {
		return tipoElegible;
	}

	public void setTipoElegible(TipoElegible tipoElegible) {
		this.tipoElegible = tipoElegible;
	}
	

	 
	 
}
