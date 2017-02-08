package com.myapp.domain.calendarizacion;

import java.util.Collection;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myapp.domain.TipoActividad;

@Entity
@Table(name="tipoactividadevaluaciondocente")
public class TipoActividadEvaluacionDocente {

	@Id
	@GeneratedValue
	private int id;

	@Column(name="descripcion")
	private String tipo;


	private static final long serialVersionUID = 1L;

	private static final HashMap INSTANCES = new HashMap();

	public static final TipoActividadEvaluacionDocente REGISTRAR_EVALUACION = new TipoActividadEvaluacionDocente(
			new Integer(1), "Registro de Evaluaciones");

	public static final TipoActividadEvaluacionDocente EVALUAR_PROFESORES = new TipoActividadEvaluacionDocente(
			new Integer(2), "Evaluación de Profesores");

	public static final TipoActividadEvaluacionDocente AUTOEVALUAR_PROFESORES = new TipoActividadEvaluacionDocente(
			new Integer(3), "Autoevaluación de Profesores");


	public static TipoActividadEvaluacionDocente getInstance(Integer id) {
		TipoActividadEvaluacionDocente tipoActividad = null;

		Object obj = INSTANCES.get(id);

		if (obj != null)
			tipoActividad = (TipoActividadEvaluacionDocente) obj;

		return tipoActividad;
	}

	public TipoActividadEvaluacionDocente(){
		
	}
	public static Collection getConstants() {
		return INSTANCES.values();
	}
	private TipoActividadEvaluacionDocente(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
		INSTANCES.put(this.getId(), this);
	}

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
