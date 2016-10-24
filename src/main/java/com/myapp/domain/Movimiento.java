package com.myapp.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "movimiento")
@Inheritance(strategy=InheritanceType.JOINED)
public class Movimiento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * Tiempo del Sistema en que se crea el Movimiento.
	 * Puede ser en fechaDeRecalendarizacion o fecha de BD
	 * Se obtiene de web.PlanDeEstudiosDAO.getFechaActualOfPlanDeEstudios(Integer planDeEstudiosId, Session session)
	 */
	@Column(name = "tiempomovimiento")
	private Calendar tiempoMovimiento;
	/**
	 * Tiempo Real en que se crea el Movimiento.
	 * Se obtiene de PeriodoTiempoDAO.getInstance().getSysDate(session)
	 */
	@Column(name = "tiemporealmovimiento")
	private Calendar tiempoRealMovimiento;
	
	@Column(name = "cancelado")
	private boolean cancelado;

	 @OneToOne()
	 @JoinColumn (name = "idperiodocurso") 
	private PeriodoCurso periodoCurso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getTiempoMovimiento() {
		return tiempoMovimiento;
	}

	public void setTiempoMovimiento(Calendar tiempoMovimiento) {
		this.tiempoMovimiento = tiempoMovimiento;
	}

	public Calendar getTiempoRealMovimiento() {
		return tiempoRealMovimiento;
	}

	public void setTiempoRealMovimiento(Calendar tiempoRealMovimiento) {
		this.tiempoRealMovimiento = tiempoRealMovimiento;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public PeriodoCurso getPeriodoCurso() {
		return periodoCurso;
	}

	public void setPeriodoCurso(PeriodoCurso periodoCurso) {
		this.periodoCurso = periodoCurso;
	}

	 
}
