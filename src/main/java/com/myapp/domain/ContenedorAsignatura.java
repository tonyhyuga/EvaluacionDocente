package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "contenedorasignatura")
@PrimaryKeyJoinColumn(name="id")
public class ContenedorAsignatura extends PuntoDeAcreditacion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "nombre")
	private String nombre; // No se implementa toUpperCase
	/**
	 * Numero de créditos asignados a la asignatura, si no están definidos se
	 * tomaran en cuenta el numero de créditos definidos por la asignatura base.
	 */
	@Column(name = "creditos")
	private short creditos;
	/**
	 * Por default la Clave de la AsignaturaBase en caso de ser 
	 * ContenedorElegible o ContenedorDirecta.
	 */
	@Column(name = "clave")
	private String clave;

	/**
	 * Indica si la asignatura esta activa o si ya no esta en uso
	 */
	@Column(name = "activo")
	private boolean activo;

	/**
	 * contiene objetos ContenedorAsignatura que son los antecesores de este
	 * mismo
	 */

	/**
	 * plan de estudios al que pertenece
	 */
	 @OneToOne()
	 @JoinColumn (name = "idplandeestudios")
	private PlanDeEstudios planDeEstudios;
	/**
	 * Usado en modulo de tabla de rango para semestre equivalente.
	 * {@code true} No cuenta los creditos para avance
	 * {@code false} Cuenta los creditos para avance
	 */
	@Column(name = "inhabilitadoparacalculosemestre")
	private boolean inhabilitadoParaCalculoSemestre;
	/**
	 * Si se toma en cuenta la calificacion para el cálculo del promedio. 
	 * Default es true. Si el MovimientoInscripcionGrupo NO tiene
	 * ContenedorAsignatura (casos de migracion) por default cuentaEnPromedio.
	 * {@code true} Cuenta la calificacion para el promedio
	 * {@code false} No cuenta la calificacion para el promedio
	 */
	@Column(name = "cuentaenpromedio")
	private boolean cuentaEnPromedio = true;
	
	/**
	 * Representa el número de horas presenciales que se deben impartir de la asignatura. Aplica para MEyA y MEFI.
	 */
	@Column(name = "numerohoraspresenciales")
	private float numeroHorasPresenciales;
	/**
	 * Representa el número de horas presenciales que se deben impartir de la asignatura. Aplica solo para MEFI.
	 */
	@Column(name = "numerohorasnopresenciales")
	private float numeroHorasNoPresenciales;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public short getCreditos() {
		return creditos;
	}
	public void setCreditos(short creditos) {
		this.creditos = creditos;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public PlanDeEstudios getPlanDeEstudios() {
		return planDeEstudios;
	}
	public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
		this.planDeEstudios = planDeEstudios;
	}
	public boolean isInhabilitadoParaCalculoSemestre() {
		return inhabilitadoParaCalculoSemestre;
	}
	public void setInhabilitadoParaCalculoSemestre(boolean inhabilitadoParaCalculoSemestre) {
		this.inhabilitadoParaCalculoSemestre = inhabilitadoParaCalculoSemestre;
	}
	public boolean isCuentaEnPromedio() {
		return cuentaEnPromedio;
	}
	public void setCuentaEnPromedio(boolean cuentaEnPromedio) {
		this.cuentaEnPromedio = cuentaEnPromedio;
	}
	public float getNumeroHorasPresenciales() {
		return numeroHorasPresenciales;
	}
	public void setNumeroHorasPresenciales(float numeroHorasPresenciales) {
		this.numeroHorasPresenciales = numeroHorasPresenciales;
	}
	public float getNumeroHorasNoPresenciales() {
		return numeroHorasNoPresenciales;
	}
	public void setNumeroHorasNoPresenciales(float numeroHorasNoPresenciales) {
		this.numeroHorasNoPresenciales = numeroHorasNoPresenciales;
	}	
	
	
}
