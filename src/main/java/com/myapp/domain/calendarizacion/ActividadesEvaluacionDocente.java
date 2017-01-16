package com.myapp.domain.calendarizacion;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.Institucion;

@Entity
@Table(name="actividadesevaluaciondocente")
public class ActividadesEvaluacionDocente {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="inicio")
	private Calendar inicio;
	
	@Column(name="fin")
	private Calendar fin;
	
	@Column(name="indiceperiodo")
	private int indicePeriodo;
	
	@OneToOne
	@JoinColumn(name="idanioescolar")
	private AnioEscolar anioEscolar;
	
	@OneToOne
	@JoinColumn(name="idtipoactividadevaluaciondocente")
	private TipoActividadEvaluacionDocente tipoActividad;
	
	@OneToOne
	@JoinColumn(name="idinstitucion")
	private Institucion institucion;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getInicio() {
		return inicio;
	}

	public void setInicio(Calendar inicio) {
		this.inicio = inicio;
	}

	public Calendar getFin() {
		return fin;
	}

	public void setFin(Calendar fin) {
		this.fin = fin;
	}

	public int getIndicePeriodo() {
		return indicePeriodo;
	}

	public void setIndicePeriodo(int indicePeriodo) {
		this.indicePeriodo = indicePeriodo;
	}

	public AnioEscolar getAnioEscolar() {
		return anioEscolar;
	}

	public void setAnioEscolar(AnioEscolar anioEscolar) {
		this.anioEscolar = anioEscolar;
	}

	public TipoActividadEvaluacionDocente getTipoActividad() {
		return tipoActividad;
	}

	public void setTipoActividad(TipoActividadEvaluacionDocente tipoActividad) {
		this.tipoActividad = tipoActividad;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	
	
	
	
	
}
