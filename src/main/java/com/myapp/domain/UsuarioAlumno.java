package com.myapp.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "usuarioalumno")
@PrimaryKeyJoinColumn(name="id")
public class UsuarioAlumno extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne()
	 @JoinColumn (name = "idalumnouadymatriculado") 
	private AlumnoUADYMatriculado alumnoUADYMatriculado;

	public AlumnoUADYMatriculado getAlumnoUADYMatriculado() {
		return alumnoUADYMatriculado;
	}

	public void setAlumnoUADYMatriculado(AlumnoUADYMatriculado alumnoUADYMatriculado) {
		this.alumnoUADYMatriculado = alumnoUADYMatriculado;
	}
	
	
}
