package com.myapp.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "grupoalumnos")
@Inheritance(strategy=InheritanceType.JOINED)
public class GrupoAlumnos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	/**
	 * Atributo que indica la fecha-hr asignada en la cual se programará el 
	 * Examen (ya sea Ordinario o Extraordinario) de la {@link ClaseUADY}
	 */
	@Column(name = "fechahora")
	private Calendar fechaHora;
	/**
	 * Fecha Límite que tiene el titular para registrar la Calificación
	 */
	@Column(name = "fechalimite")
	private Calendar fechaLimite;
	
	@OneToOne()
	@JoinColumn (name = "idasignaturabase")
	private AsignaturaBase asignaturaBase;
	
	@OneToOne()
	@JoinColumn (name = "idinstitucion")
	private Institucion institucion;
	
	@OneToMany
	 @JoinTable(name = "liga_grupoalumnos_titulares",
     joinColumns = @JoinColumn(name="idgrupoalumnos", referencedColumnName="ID"),
     inverseJoinColumns = @JoinColumn(name="idempleado", referencedColumnName="ID"))
	private Set<Empleado> sinodo;
	/**
	 * Antes movimientosSolicitudExamenOrd
	 * Set que contiene las Solicitudes de Examenes Ordinarios  
	 * (MovimientoSolicitudExamenOrd) realizadas a los alumnos de la ClaseUADY
	 */
	
	@OneToMany
	 @JoinTable(name = "liga_grupoalumnos_movinscrgrupo",
    joinColumns = @JoinColumn(name="idgrupoalumnos", referencedColumnName="ID"),
    inverseJoinColumns = @JoinColumn(name="idmovimientoinscripciongrupo", referencedColumnName="ID"))
	private Set<MovimientoInscripcionGrupo> movimientosInscripcionGrupo;
	
	/*private FormaDeCalificar formaDeCalificar;*/
	
	
	@OneToOne()
	@JoinColumn (name = "idperiodocurso")
	private PeriodoCurso periodoCurso;
	
	@OneToOne()
	@JoinColumn (name = "idtipogrupoalumnos")
	private TipoGrupoAlumnos tipoGrupoAlumnos;

	@Column(name = "sellado")
	private boolean sellado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Calendar getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Calendar fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public AsignaturaBase getAsignaturaBase() {
		return asignaturaBase;
	}

	public void setAsignaturaBase(AsignaturaBase asignaturaBase) {
		this.asignaturaBase = asignaturaBase;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Set getSinodo() {
		return sinodo;
	}

	public void setSinodo(Set sinodo) {
		this.sinodo = sinodo;
	}

	public Set getMovimientosInscripcionGrupo() {
		return movimientosInscripcionGrupo;
	}

	public void setMovimientosInscripcionGrupo(Set movimientosInscripcionGrupo) {
		this.movimientosInscripcionGrupo = movimientosInscripcionGrupo;
	}

	public PeriodoCurso getPeriodoCurso() {
		return periodoCurso;
	}

	public void setPeriodoCurso(PeriodoCurso periodoCurso) {
		this.periodoCurso = periodoCurso;
	}

	public TipoGrupoAlumnos getTipoGrupoAlumnos() {
		return tipoGrupoAlumnos;
	}

	public void setTipoGrupoAlumnos(TipoGrupoAlumnos tipoGrupoAlumnos) {
		this.tipoGrupoAlumnos = tipoGrupoAlumnos;
	}

	public boolean isSellado() {
		return sellado;
	}

	public void setSellado(boolean sellado) {
		this.sellado = sellado;
	}
	
	
	
	
}
