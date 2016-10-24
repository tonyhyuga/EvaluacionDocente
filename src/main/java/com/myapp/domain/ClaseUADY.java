package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "claseuady")
@PrimaryKeyJoinColumn(name="id")
public class ClaseUADY extends GrupoAlumnos{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "activa")
	private boolean activa;
	/**
	 * Si temporal es true la clase no tendra sesionesAcademicas (para prepas) y el
	 * web debe mostrarlas (a no ser que activa=F) y permitir al alumno agregarla
	 * a su carga
	 */
	@Column(name = "temporal")
	private boolean temporal;
	 

	 @OneToOne()
	 @JoinColumn (name = "idgrupo") 
	private Grupo grupo;
	
	 @OneToOne()
	 @JoinColumn (name = "idtipoturno") 
	private TipoTurno tipoTurno;

	@Column(name = "mefi")
	private boolean mefi;

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public boolean isTemporal() {
		return temporal;
	}

	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public TipoTurno getTipoTurno() {
		return tipoTurno;
	}

	public void setTipoTurno(TipoTurno tipoTurno) {
		this.tipoTurno = tipoTurno;
	}

	public boolean isMefi() {
		return mefi;
	}

	public void setMefi(boolean mefi) {
		this.mefi = mefi;
	}
	

}
