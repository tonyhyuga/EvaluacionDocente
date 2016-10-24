package com.myapp.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "usuarioempleado")
@PrimaryKeyJoinColumn(name="id")
public class UsuarioEmpleado extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToOne()
	 @JoinColumn (name = "idempleado") 
	private Empleado empleado;

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	
}
