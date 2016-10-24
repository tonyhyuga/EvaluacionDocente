package com.myapp.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "periodocurso")
@PrimaryKeyJoinColumn(name="id")
public class PeriodoCurso extends PeriodoTiempo{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "indice")
	private short indice;
	
	@Column(name = "intercurso")
	private boolean interCurso;
	
	@OneToOne()
	@JoinColumn (name = "idinstitucion")
	private Institucion institucion;
	
	@Column(name = "periodolectivoini")
	private Calendar periodoLectivoIni;
	
	@Column(name = "periodolectivofin")
	private Calendar periodoLectivoFin;
	
	//private Set actividades;
	@OneToOne()
	@JoinColumn (name = "idanioescolar")
	private AnioEscolar anioEscolar;
	
	@Column(name = "indiceaniocivil")
	private Short indiceAnioCivil;
	
	//Agregado para identificar los períodos de verano e invierno,
	@Column(name = "indiceintercurso")
	private short indiceIntercurso;
	//Agregado para identificar períodos MEyA, MEFI, o posgrado
	@Column(name = "etiqueta")
	private String etiqueta;
	
	
	public short getIndice() {
		return indice;
	}
	public void setIndice(short indice) {
		this.indice = indice;
	}
	public boolean isInterCurso() {
		return interCurso;
	}
	public void setInterCurso(boolean interCurso) {
		this.interCurso = interCurso;
	}
	public Institucion getInstitucion() {
		return institucion;
	}
	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}
	public Calendar getPeriodoLectivoIni() {
		return periodoLectivoIni;
	}
	public void setPeriodoLectivoIni(Calendar periodoLectivoIni) {
		this.periodoLectivoIni = periodoLectivoIni;
	}
	public Calendar getPeriodoLectivoFin() {
		return periodoLectivoFin;
	}
	public void setPeriodoLectivoFin(Calendar periodoLectivoFin) {
		this.periodoLectivoFin = periodoLectivoFin;
	}
	public AnioEscolar getAnioEscolar() {
		return anioEscolar;
	}
	public void setAnioEscolar(AnioEscolar anioEscolar) {
		this.anioEscolar = anioEscolar;
	}
	public Short getIndiceAnioCivil() {
		return indiceAnioCivil;
	}
	public void setIndiceAnioCivil(Short indiceAnioCivil) {
		this.indiceAnioCivil = indiceAnioCivil;
	}
	public short getIndiceIntercurso() {
		return indiceIntercurso;
	}
	public void setIndiceIntercurso(short indiceIntercurso) {
		this.indiceIntercurso = indiceIntercurso;
	}
	public String getEtiqueta() {
		return etiqueta;
	}
	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}
	
	
}
