package com.myapp.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "movimientodeinscripcion")
@PrimaryKeyJoinColumn(name="id")
public class MovimientoDeInscripcion extends MovimientoAlumno{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne()
	@JoinColumn (name = "idplandeestudios")
	private PlanDeEstudios planDeEstudios;
	
	
	public PlanDeEstudios getPlanDeEstudios() {
		return planDeEstudios;
	}
	public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
		this.planDeEstudios = planDeEstudios;
	}
	
	
	
}
