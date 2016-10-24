package com.myapp.domain.encuestas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preguntahecha")
public class PreguntaHecha {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="preguntastring")
	private String preguntaString;
	
	@Column(name="clave")
	private String clave;
	
	@Column(name="version")
	private Integer version;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPreguntaString() {
		return preguntaString;
	}

	public void setPreguntaString(String preguntaString) {
		this.preguntaString = preguntaString;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
