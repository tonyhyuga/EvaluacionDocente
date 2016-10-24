package com.myapp.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "plandeestudios")
@PrimaryKeyJoinColumn(name="id")
public class PlanDeEstudios extends PuntoDeAcreditacion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "version")
	private Integer version;
	
	@Column(name = "fechadeaprobacion")
	private Calendar fechaDeAprobacion;

	@Column(name = "abreviatura")
	private String abreviatura;

	@Column(name = "activo")
	private boolean activo;

	@OneToOne()
	@JoinColumn (name = "idprogramaeducativo")
	private ProgramaEducativo programaEducativo;



	//private Set actividadesDePeriodosCurso;


	/**
	 * periodoCursoActual. Esta asociacion NO esta incluida en el mapeo de Hibernate. Es solo para trabajar en
	 * memoria y mantener una referencia al PeriodoCurso Actual del PlanDeEstudios.
	 * El PeriodoCurso Actual se está obteniendo con el DAO PlanDeEstudiosDAO.getPeriodoCursoActualAndVerificaActividadActualFromPlanDeEstudios
	 */	
	//private PeriodoCurso periodoCursoActual;
	@Column(name = "fechaderecalendarizacion")
	private Calendar fechaDeRecalendarizacion;
	
	//private TablaDeRangoParaSemestreEquivalente tablaDeRangoParaSemestreEquivalente;
	

	@Column(name = "mefi")
	private boolean mefi;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Calendar getFechaDeAprobacion() {
		return fechaDeAprobacion;
	}

	public void setFechaDeAprobacion(Calendar fechaDeAprobacion) {
		this.fechaDeAprobacion = fechaDeAprobacion;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public ProgramaEducativo getProgramaEducativo() {
		return programaEducativo;
	}

	public void setProgramaEducativo(ProgramaEducativo programaEducativo) {
		this.programaEducativo = programaEducativo;
	}

	public Calendar getFechaDeRecalendarizacion() {
		return fechaDeRecalendarizacion;
	}

	public void setFechaDeRecalendarizacion(Calendar fechaDeRecalendarizacion) {
		this.fechaDeRecalendarizacion = fechaDeRecalendarizacion;
	}

	public boolean isMefi() {
		return mefi;
	}

	public void setMefi(boolean mefi) {
		this.mefi = mefi;
	}
	
}
