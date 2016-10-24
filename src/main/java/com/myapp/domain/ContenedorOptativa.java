package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "contenedoroptativa")
@PrimaryKeyJoinColumn(name="id")
public class ContenedorOptativa extends ContenedorAsignatura{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "comprometible")
	private boolean comprometible;

	public boolean isComprometible() {
		return comprometible;
	}

	public void setComprometible(boolean comprometible) {
		this.comprometible = comprometible;
	}
	
	
}
