package com.myapp.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "statusalumno")
public class StatusAlumno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final HashMap INSTANCES = new HashMap();
	
	public static final StatusAlumno ACTIVO = new StatusAlumno(new Integer(1), "Activo");
	 
	public static final StatusAlumno INACTIVO_BAJA_TEMPORAL = new StatusAlumno(new Integer(2), "Baja temporal");
	 
	public static final StatusAlumno INACTIVO_BAJA_DEFINITIVA = new StatusAlumno(new Integer(3), "Baja definitiva");
	 
	public static final StatusAlumno INACTIVO = new StatusAlumno(new Integer(4), "Inactivo");
	
	//Falta implementar la Lógica de Negocio. 
	//Creado a raiz de una duda de Romel con Marcos. 
	//public static final StatusAlumno INACTIVO_BAJA_DEFINITIVA_POR_RECONOCIMIENTO = new StatusAlumno(new Integer(5), "Baja definitiva por Reconocimiento");
	
	//Nuevos tipos. El POR_RECONOCIMIENTO equivale a POR_CAMBIO_PLAN
	
	//¿Porque es necesario separar esta Baja definitiva por cambio de plan de 
	//la Baja definitiva? Porque es la unica baja definitiva que te permite
	//reingresar al mismo Programa en distinta version(plan), si no existiera 
	//no podriamos identificar los casos para aplicar esta lógica particular
	public static final StatusAlumno INACTIVO_BAJA_DEFINITIVA_POR_CAMBIO_PLAN = 
		new StatusAlumno(new Integer(5), "Inactivo baja definitiva por cambio " +
				"de Plan (versión o programa)");
	public static final StatusAlumno EGRESADO = new StatusAlumno(new Integer(6), "Egresado");
	public static final StatusAlumno CERTIFICADO = new StatusAlumno(new Integer(7), "Certificado");
	public static final StatusAlumno TITULADO = new StatusAlumno(new Integer(8), "Titulado");


	/*
	 * static { INSTANCES.put(FIJO.getId(), FIJO); INSTANCES.put(MOVIL.getId(),
	 * MOVIL); }
	 */

	public static StatusAlumno getInstance(Integer id) {
		StatusAlumno statusAlumno = null;

		Object obj = INSTANCES.get(id);

		if (obj != null)
			statusAlumno = (StatusAlumno) obj;

		return statusAlumno;
	}

	public static Collection getConstants() {
		return INSTANCES.values();
	}
	
	public static List<StatusAlumno> BAJAS_DEFINITIVAS = new ArrayList<StatusAlumno>();
	public static List<StatusAlumno> NO_ES_ALUMNO = new ArrayList<StatusAlumno>();
	public static List<StatusAlumno> BAJAS = new ArrayList<StatusAlumno>();
	static {
		BAJAS_DEFINITIVAS.add(StatusAlumno.INACTIVO_BAJA_DEFINITIVA);
		BAJAS_DEFINITIVAS.add(StatusAlumno.INACTIVO_BAJA_DEFINITIVA_POR_CAMBIO_PLAN);
		
		NO_ES_ALUMNO.addAll(BAJAS_DEFINITIVAS);
		NO_ES_ALUMNO.add(StatusAlumno.EGRESADO);
		NO_ES_ALUMNO.add(StatusAlumno.TITULADO);
		
		BAJAS.add(StatusAlumno.INACTIVO_BAJA_TEMPORAL);
		BAJAS.addAll(BAJAS_DEFINITIVAS);		
	}
	
	@Id
	private Integer id;
	
	@Column(name = "status")
	private String status;

	public StatusAlumno() {
	}

	/**
	 * @param id
	 * @param tipo
	 */
	private StatusAlumno(Integer id, String status) {
		this.id = id;
		this.status = status;
		INSTANCES.put(this.getId(), this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
