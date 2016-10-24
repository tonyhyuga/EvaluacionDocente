package com.myapp.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "movimientoalumno")
//@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="id")
public class MovimientoAlumno extends Movimiento{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne()
	@JoinColumn (name = "idalumnouadymatriculado")
	private AlumnoUADYMatriculado alumnoUADYMatriculado;
	
	// Se convirtio en una List
	//private AlumnoUADYMatriculado alumnoUADYMatriculadoDeFusion;
	@OneToOne()
	@JoinColumn (name = "idactividad") 
	private Actividad actividad;

	public AlumnoUADYMatriculado getAlumnoUADYMatriculado() {
		return alumnoUADYMatriculado;
	}

	public void setAlumnoUADYMatriculado(AlumnoUADYMatriculado alumnoUADYMatriculado) {
		this.alumnoUADYMatriculado = alumnoUADYMatriculado;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}
	
	
}
