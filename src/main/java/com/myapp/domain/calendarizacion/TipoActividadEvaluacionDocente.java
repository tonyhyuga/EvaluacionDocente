package com.myapp.domain.calendarizacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipoactividadevaluaciondocente")
public class TipoActividadEvaluacionDocente {

	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="descripcion")
	private String tipo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
