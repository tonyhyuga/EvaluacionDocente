package com.myapp.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "anioescolar")
public class TipoActividad implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private static final HashMap INSTANCES = new HashMap();
	
	public static final TipoActividad OFERTA_ACADEMICA = new TipoActividad(
			new Integer(1), "Oferta Académica");
	
	public static final TipoActividad INSCRIPCIONES = new TipoActividad(
			new Integer(2), "Inscripciones");

	public static final TipoActividad CARGA_ACADEMICA = new TipoActividad(
			new Integer(3), "Carga Académica");

	public static final TipoActividad CARGA_ACADEMICA_WEB = new TipoActividad(
			new Integer(4), "Carga Académica en Web");

	public static final TipoActividad SOLICITUD_EXTRAORDINARIOS = new TipoActividad(
			new Integer(5), "Solicitud de Exámenes Extraordinarios");

	public static final TipoActividad SOLICITUD_ESPECIALES = new TipoActividad(
			new Integer(6), "Solicitud de Exámenes Especiales");

	public static final TipoActividad REGISTRO_CALIFICACIONES_ORDINARIOS = new TipoActividad(
			new Integer(7), "Registro de Calificaciones de Exámenes Ordinarios");

	public static final TipoActividad REGISTRO_CALIFICACIONES_EXTRAORDINARIOS = new TipoActividad(
			new Integer(8), "Registro de Calificaciones de Exámenes Extraordinarios");

	public static final TipoActividad REGISTRO_CALIFICACIONES_ESPECIALES = new TipoActividad(
			new Integer(9), "Registro de Calificaciones de Exámenes Especiales");

	public static final TipoActividad BAJAS = new TipoActividad(
			new Integer(10),	"Bajas");

	public static final TipoActividad EQUIVALENCIAS = new TipoActividad(
			new Integer(11), "Equivalencias");
	
	public static final TipoActividad REGISTRO_CALIFICACIONES_ORDINARIOS_WEB = new TipoActividad(
			new Integer(12), "Registro de Calificaciones de Exámenes Ordinarios en Web");

	public static final TipoActividad REGISTRO_CALIFICACIONES_EXTRAORDINARIOS_WEB = new TipoActividad(
			new Integer(13), "Registro de Calificaciones de Exámenes Extraordinarios en Web");

	public static final TipoActividad REGISTRO_CALIFICACIONES_ESPECIALES_WEB = new TipoActividad(
			new Integer(14), "Registro de Calificaciones de Exámenes Especiales en Web");
	
	public static final TipoActividad EGRESOS = new TipoActividad(
			new Integer(15), "Egresos");
	
	public static final TipoActividad EXAMENES_DE_TITULACION = new TipoActividad(
			new Integer(16), "Exámenes profesionales");
	
	public static final TipoActividad PREINSCRIPCIONES_EXTRAORDINARIOS_WEB = new TipoActividad(
			new Integer(17), "Preinscripciones a Exámenes Extraordinarios en Web");
	
	public static final TipoActividad CERTIFICADO = new TipoActividad(
			new Integer(18), "Impresión de Certificado");

	public static final TipoActividad REGISTRO_CALIFICACIONES_CURSO_REGULAR = new TipoActividad(
			new Integer(19), "Registro de Calificaciones de Cursos Regulares"); 
	
	public static final TipoActividad REGISTRO_CALIFICACIONES_CURSO_REGULAR_WEB = new TipoActividad(
			new Integer(20), "Registro de Calificaciones de Cursos Regulares en Web");
	
	public static final TipoActividad REGISTRO_CALIFICACIONES_CURSO_ACOMPANAMIENTO = new TipoActividad(
			new Integer(21), "Registro de Calificaciones de Cursos de Acompañamiento");
	
	public static final TipoActividad REGISTRO_CALIFICACIONES_CURSO_ACOMPANAMIENTO_WEB = new TipoActividad(
			new Integer(22), "Registro de Calificaciones de Cursos de Acompañamiento en Web");		
	public static final TipoActividad CARGA_ACADEMICA_ASIGNATURAS_MOVILIDAD= new TipoActividad(
			new Integer(23), "Carga academica de movilidad");	
	public static final TipoActividad SOLICITUDES_DE_BECAS= new TipoActividad(
			new Integer(24), "Solicitudes de Beca");	

	
	/*
	 * static { INSTANCES.put(FIJO.getId(), FIJO); INSTANCES.put(MOVIL.getId(),
	 * MOVIL); }
	 */

	public static TipoActividad getInstance(Integer id) {
		TipoActividad tipoActividad = null;

		Object obj = INSTANCES.get(id);

		if (obj != null)
			tipoActividad = (TipoActividad) obj;

		return tipoActividad;
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}

	@Id
	private Integer id;
	// No se implementa toUpperCase por ser atributo de Catalogo
	@Column(name = "tipo")
	private String tipo;

	public TipoActividad() {
	}

	/**
	 * @param id
	 * @param tipo
	 */
	private TipoActividad(Integer id, String tipo) {
		this.id = id;
		this.tipo = tipo;
		INSTANCES.put(this.getId(), this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
