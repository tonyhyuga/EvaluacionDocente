package com.myapp.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alumnouadymatriculado")
public class AlumnoUADYMatriculado implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
     * Set que indica el Status que tiene el {@link AlumnoUADYMatriculado} en cada {@link PlanDeEstudios}
     * al que se encuentra inscrito (en el caso en que este inscrito en mas de uno).
     */
	@OneToMany(mappedBy="alumnoUADYMatriculado")
    private Set<StatusAlumnoPlanDeEstudios> statusAlumnoPlanDeEstudios;
    /**
	 * Bandera que utiliza el componente 'Validador de Identidad'
	 */
    @Column(name = "validado")
    private boolean validado;
    
    /**
	 * Bandera que indica una asociacion entre un AlumnoUADYMatriculado  y otro 
	 *  AlumnoUADYMatriculado existente 
	 */
    @Column(name = "fusionado")
	private boolean fusionado;
	
    @Column(name = "curpvalidada")
	private boolean curpValidada;
	
	/**
	 * Atributo al transformar herencia de Persona a Relacion AlumnoUADYMAtriculado -> Persona 
	 */
    @OneToOne()
	@JoinColumn (name = "idpersona")
	private Persona persona;
	
	// ************************************
    
	
   // En la Matricula no se aplica el ToUpperCase 
	@Column(name = "matriculapartevariable")
	private String matriculaParteVariable;
	
	@Column(name = "matriculaparteinvariable")
	private String matriculaParteInvariable;

	public Set<StatusAlumnoPlanDeEstudios> getStatusAlumnoPlanDeEstudios() {
		return statusAlumnoPlanDeEstudios;
	}

	public void setStatusAlumnoPlanDeEstudios(Set<StatusAlumnoPlanDeEstudios> statusAlumnoPlanDeEstudios) {
		this.statusAlumnoPlanDeEstudios = statusAlumnoPlanDeEstudios;
	}

	public boolean isValidado() {
		return validado;
	}

	public void setValidado(boolean validado) {
		this.validado = validado;
	}

	public boolean isFusionado() {
		return fusionado;
	}

	public void setFusionado(boolean fusionado) {
		this.fusionado = fusionado;
	}

	public boolean isCurpValidada() {
		return curpValidada;
	}

	public void setCurpValidada(boolean curpValidada) {
		this.curpValidada = curpValidada;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getMatriculaParteVariable() {
		return matriculaParteVariable;
	}

	public void setMatriculaParteVariable(String matriculaParteVariable) {
		this.matriculaParteVariable = matriculaParteVariable;
	}

	public String getMatriculaParteInvariable() {
		return matriculaParteInvariable;
	}

	public void setMatriculaParteInvariable(String matriculaParteInvariable) {
		this.matriculaParteInvariable = matriculaParteInvariable;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	 
	
	

}
