package com.myapp.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actividadesdeperiodocurso")
public class ActividadesDePeriodoCurso {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne
	@JoinColumn (name = "idplandeestudios")
	private PlanDeEstudios planDeEstudios;
	
	@OneToOne
	@JoinColumn (name = "idperiodocurso")
	private PeriodoCurso periodoCurso;
	
	@ManyToMany(cascade=CascadeType.ALL)  
	@JoinTable(name="liga_actividadesdepc_actividad", 
	joinColumns=@JoinColumn(name="idactividadesdeperiodocurso"), 
	inverseJoinColumns=@JoinColumn(name="idactividad"))  
	private Set<Actividad> actividades;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public PlanDeEstudios getPlanDeEstudios() {
		return planDeEstudios;
	}
	public void setPlanDeEstudios(PlanDeEstudios planDeEstudios) {
		this.planDeEstudios = planDeEstudios;
	}
	public PeriodoCurso getPeriodoCurso() {
		return periodoCurso;
	}
	public void setPeriodoCurso(PeriodoCurso periodoCurso) {
		this.periodoCurso = periodoCurso;
	}
	public Set<Actividad> getActividades() {
		return actividades;
	}
	public void setActividades(Set<Actividad> actividades) {
		this.actividades = actividades;
	}
	
	
	
}
