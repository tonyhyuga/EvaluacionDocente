package com.myapp.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "movimientoinscripciongrupo")
@PrimaryKeyJoinColumn(name="id")
public class MovimientoInscripcionGrupo extends MovimientoAlumno{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "oportunidad")
	private short oportunidad;
	 
	@Column(name = "derecho")
	private boolean derecho;
	 
	@Column(name = "calificacionEstablecida")
	private boolean calificacionEstablecida;
	//Atributo claseUADY se bajo a MovimientoSolicitudExamenOrd ya que se especializo 
	//la clase dependiendo del TipoExamen
	//private ClaseUADY claseUADY;
	 
	

	/**
	 * Se agrego esta liga para hacerlo mas natural al dominio, ya que toda Solicitud es
	 * sobre una AsignaturaBase. Ademas esta liga facilita las busquedas sobre las diversas
	 * especializaciones (Ord, Extra). Se establecerá en el caso de Ord al momento de establecerle
	 * la ClaseUADY ya que ahi se tiene la AsignaturaBase, en el caso del Extra cuando se tenga
	 * la GrupoInscripcionExtraordinario. 
	 */
	@OneToOne()
	@JoinColumn (name = "idasignaturabase")
	private AsignaturaBase asignaturaBase;
	
	
	/**
	 * Conjunto de GruposAlumnos (GrupoInscripcionExtraordinario, ListaExtraordinario, ListaExtraordinarioCalificable) 
	 * en los que se encuentra el MovimientoInscripcionExamenExtra
	 */
	@ManyToMany(mappedBy="movimientosInscripcionGrupo")
	private Set<GrupoAlumnos> gruposAlumnos;
	
	
	
	/**
	 * Relación (* -- 0..1).
	 * Identifica el {@link ContenedorOptativa} que se relaciona con el {@link ContenedorElegible}
	 * que se relaciona con la {@link AsignaturaBase} 
	 */
	@OneToOne()
	@JoinColumn (name = "idcontenedoroptativa")
	private ContenedorOptativa contenedorOptativa;
	/**
	 * Relación Nunca es null (* -- 1).
	 * Identifica el {@link ContenedorAsignatura} de tipo ContenedorDirecta 
	 * o ContenedorElegible que se relaciona con la {@link AsignaturaBase}.
	 * Ahora se utilizará esta relación en lugar de obtener el Contenedor a partir de la AsignaturaBase. 
	 */
	@OneToOne()
	@JoinColumn (name = "idcontenedorasignatura")
	private ContenedorAsignatura contenedorAsignatura;
	/**
	 * Nueva relación. Creada para facilitar y agilizar búsquedas y procesamiento.
	 * Por el momento permitirá null en lo que romel termina un fix para acompletar
	 * los datos.
	 */
	@OneToOne()
	@JoinColumn (name = "idplandeestudios")
	private PlanDeEstudios planDeEstudios;
	
	
	public short getOportunidad() {
		return oportunidad;
	}
	public void setOportunidad(short oportunidad) {
		this.oportunidad = oportunidad;
	}
	public boolean isDerecho() {
		return derecho;
	}
	public void setDerecho(boolean derecho) {
		this.derecho = derecho;
	}
	public boolean isCalificacionEstablecida() {
		return calificacionEstablecida;
	}
	public void setCalificacionEstablecida(boolean calificacionEstablecida) {
		this.calificacionEstablecida = calificacionEstablecida;
	}
	public AsignaturaBase getAsignaturaBase() {
		return asignaturaBase;
	}
	public void setAsignaturaBase(AsignaturaBase asignaturaBase) {
		this.asignaturaBase = asignaturaBase;
	}
	public Set<GrupoAlumnos> getGruposAlumnos() {
		return gruposAlumnos;
	}
	public void setGruposAlumnos(Set<GrupoAlumnos> gruposAlumnos) {
		this.gruposAlumnos = gruposAlumnos;
	}
	public ContenedorOptativa getContenedorOptativa() {
		return contenedorOptativa;
	}
	public void setContenedorOptativa(ContenedorOptativa contenedorOptativa) {
		this.contenedorOptativa = contenedorOptativa;
	}
	public ContenedorAsignatura getContenedorAsignatura() {
		return contenedorAsignatura;
	}
	public void setContenedorAsignatura(ContenedorAsignatura contenedorAsignatura) {
		this.contenedorAsignatura = contenedorAsignatura;
	}
	public PlanDeEstudios getPlanDeEstudios() {
		return planDeEstudios;
	}
	public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
		this.planDeEstudios = planDeEstudios;
	}
	
	
}
