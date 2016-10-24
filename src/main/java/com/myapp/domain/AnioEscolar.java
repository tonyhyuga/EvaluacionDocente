package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "anioescolar")
@PrimaryKeyJoinColumn(name="id")
public class AnioEscolar extends PeriodoTiempo{


	private static final long serialVersionUID = 1L;

	
	@Column(name = "descripcion")
	private String descripcion;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn (name = "idanterior")
	private AnioEscolar anterior;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public AnioEscolar getAnterior() {
		return anterior;
	}

	public void setAnterior(AnioEscolar anterior) {
		this.anterior = anterior;
	}
	
	
}
