package com.myapp.domain.encuestas;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Institucion;
import com.myapp.domain.PeriodoCurso;
import com.myapp.domain.Persona;

@Entity
@Table(name="ambito")
public class Ambito {
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne
	@JoinColumn (name = "idclaseuady") 
	private ClaseUADY claseUady;
	@OneToOne
	@JoinColumn (name = "idpersona") 
	Persona persona;
	@OneToOne
	@JoinColumn (name = "idperiodocurso") 
	PeriodoCurso periodoCurso;
	@OneToOne
	@JoinColumn (name = "idinstitucion") 
	Institucion institucion;
	
	@Column(name="formadeevaluar")
	private String formaDeEvaluar;
//anterior------------------------

//	@ManyToMany(mappedBy="ambitos")
//	private Set<Cuestionario> cuestionarios;
	
	@OneToOne
	@JoinColumn (name = "idtipoambito") 
	private TipoAmbito tipoAmbito;
	
	@OneToMany(mappedBy="ambito",cascade = {CascadeType.ALL},fetch=FetchType.EAGER)
	//@JoinColumn (name = "idambito")
	//@Transient
	@JsonIgnore
	private Set<CuestionarioResuelto> cuestionariosResueltos;
	
	@OneToMany(mappedBy="ambito")
	//@JoinColumn (name = "idambito")
	@Transient
	@JsonIgnore
	private Set<RespuestaPregunta> RespuestasPreguntas;
	
	


	public ClaseUADY getClaseUady() {
		return claseUady;
	}

	public void setClaseUady(ClaseUADY claseUady) {
		this.claseUady = claseUady;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public PeriodoCurso getPeriodoCurso() {
		return periodoCurso;
	}

	public void setPeriodoCurso(PeriodoCurso periodoCurso) {
		this.periodoCurso = periodoCurso;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Set<CuestionarioResuelto> getCuestionariosResueltos() {
		return cuestionariosResueltos;
	}

	public void setCuestionariosResueltos(Set<CuestionarioResuelto> cuestionariosResueltos) {
		this.cuestionariosResueltos = cuestionariosResueltos;
	}

	public Set<RespuestaPregunta> getRespuestasPreguntas() {
		return RespuestasPreguntas;
	}

	public void setRespuestasPreguntas(Set<RespuestaPregunta> respuestasPreguntas) {
		RespuestasPreguntas = respuestasPreguntas;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	



	public String getFormaDeEvaluar() {
		return formaDeEvaluar;
	}

	public void setFormaDeEvaluar(String formaDeEvaluar) {
		this.formaDeEvaluar = formaDeEvaluar;
	}

	public TipoAmbito getTipoAmbito() {
		return tipoAmbito;
	}

	public void setTipoAmbito(TipoAmbito tipoAmbito) {
		this.tipoAmbito = tipoAmbito;
	}
	
}
