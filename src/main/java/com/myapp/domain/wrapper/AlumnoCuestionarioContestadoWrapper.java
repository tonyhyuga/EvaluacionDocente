package com.myapp.domain.wrapper;

public class AlumnoCuestionarioContestadoWrapper {
	private String matriculaParteInvariable;
	private String nombreAlumno;
	private String cuestionarioContestado;
	
	public String getCuestionarioContestado() {
		return cuestionarioContestado;
	}
	public void setCuestionarioContestado(String cuestionarioContestado) {
		this.cuestionarioContestado = cuestionarioContestado;
	}
	public String getMatriculaParteInvariable() {
		return matriculaParteInvariable;
	}
	public void setMatriculaParteInvariable(String matriculaParteInvariable) {
		this.matriculaParteInvariable = matriculaParteInvariable;
	}
	public String getNombreAlumno() {
		return nombreAlumno;
	}
	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}
}
