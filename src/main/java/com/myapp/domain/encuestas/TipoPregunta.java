package com.myapp.domain.encuestas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="tipopregunta")
public class TipoPregunta {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="tipopregunta")
	private String tipoPregunta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoPregunta() {
		return tipoPregunta;
	}
	public void setTipoPregunta(String tipoPregunta) {
		this.tipoPregunta = tipoPregunta;
	}
}
