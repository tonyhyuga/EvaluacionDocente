package com.myapp.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "statusalumnoplandeestudios")
public class StatusAlumnoPlanDeEstudios implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne()
	@JoinColumn (name = "idstatusalumno")
	private StatusAlumno statusAlumno;
	
	@OneToOne()
	@JoinColumn (name = "idplandeestudios")
	private PlanDeEstudios planDeEstudios;
	//private AlumnoUADY alumnoUADY; //TIKET_2939 Clase Deprecada
	
	@ManyToOne
	@JoinColumn(name="idalumnouadymatriculado")
	private AlumnoUADYMatriculado alumnoUADYMatriculado; //TIKET_2939
	
	@Column(name = "aplicaperfiles")
	private boolean aplicaPerfiles;
	
	@Column(name = "istutorado")
	private boolean isTutorado;
	
	@Column(name = "folioceneval")
	private String folioCeneval;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public StatusAlumno getStatusAlumno() {
		return statusAlumno;
	}

	public void setStatusAlumno(StatusAlumno statusAlumno) {
		this.statusAlumno = statusAlumno;
	}

	public PlanDeEstudios getPlanDeEstudios() {
		return planDeEstudios;
	}

	public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
		this.planDeEstudios = planDeEstudios;
	}

	public AlumnoUADYMatriculado getAlumnoUADYMatriculado() {
		return alumnoUADYMatriculado;
	}

	public void setAlumnoUADYMatriculado(AlumnoUADYMatriculado alumnoUADYMatriculado) {
		this.alumnoUADYMatriculado = alumnoUADYMatriculado;
	}

	public boolean isAplicaPerfiles() {
		return aplicaPerfiles;
	}

	public void setAplicaPerfiles(boolean aplicaPerfiles) {
		this.aplicaPerfiles = aplicaPerfiles;
	}

	public boolean isTutorado() {
		return isTutorado;
	}

	public void setTutorado(boolean isTutorado) {
		this.isTutorado = isTutorado;
	}

	public String getFolioCeneval() {
		return folioCeneval;
	}

	public void setFolioCeneval(String folioCeneval) {
		this.folioCeneval = folioCeneval;
	}
	
	
	
}
