package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.myapp.domain.util.StatusReporte;

@Entity
@Table(name = "reporteevaluaciondocente")
@Inheritance(strategy=InheritanceType.JOINED)
public class ReporteEvaluacionDocente {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	
	@OneToOne()
	@JoinColumn (name = "idpersona") 
	protected Persona profesor;
	 
	 @OneToOne()
	 @JoinColumn (name = "idasignaturabase") 
	protected AsignaturaBase asignatura;
	 
	 @OneToOne()
	 @JoinColumn (name = "idanioescolar") 
	protected AnioEscolar anioEscolar;

	 @Column (name = "indiceperiodoevadoce") 
	protected Integer indicePeriodoEvalaucion;
	 
	 @OneToOne()
	 @JoinColumn (name = "idinstitucion") 
	protected Institucion institucion;
	 
	@Column(name = "formaevaluacion")
	protected String formaDeEvaluar;
	
	@Column(name = "estatus")
	@Enumerated(EnumType.STRING)
	protected StatusReporte status;

	public Persona getProfesor() {
		return profesor;
	}

	public void setProfesor(Persona profesor) {
		this.profesor = profesor;
	}

	public AsignaturaBase getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(AsignaturaBase asignatura) {
		this.asignatura = asignatura;
	}

	public AnioEscolar getAnioEscolar() {
		return anioEscolar;
	}

	public void setAnioEscolar(AnioEscolar anioEscolar) {
		this.anioEscolar = anioEscolar;
	}

	public Integer getIndicePeriodoEvalaucion() {
		return indicePeriodoEvalaucion;
	}

	public void setIndicePeriodoEvalaucion(Integer indicePeriodoEvalaucion) {
		this.indicePeriodoEvalaucion = indicePeriodoEvalaucion;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public String getFormaDeEvaluar() {
		return formaDeEvaluar;
	}

	public void setFormaDeEvaluar(String formaDeEvaluar) {
		this.formaDeEvaluar = formaDeEvaluar;
	}

	public StatusReporte getStatus() {
		return status;
	}

	public void setStatus(StatusReporte status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	

}
