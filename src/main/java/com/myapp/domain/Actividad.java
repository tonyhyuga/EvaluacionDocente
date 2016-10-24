package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "actividad")
@PrimaryKeyJoinColumn(name="id")
public class Actividad extends PeriodoTiempo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "actividad")
	private String actividad;
	
	@OneToOne()
	@JoinColumn (name = "idtipoactividad")
	private TipoActividad tipoActividad;
	
	@OneToOne()
	@JoinColumn (name = "idperiodocurso")
	private PeriodoCurso periodoCurso;
	
	
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public TipoActividad getTipoActividad() {
		return tipoActividad;
	}
	public void setTipoActividad(TipoActividad tipoActividad) {
		this.tipoActividad = tipoActividad;
	}
	public PeriodoCurso getPeriodoCurso() {
		return periodoCurso;
	}
	public void setPeriodoCurso(PeriodoCurso periodoCurso) {
		this.periodoCurso = periodoCurso;
	}
	
	
}
