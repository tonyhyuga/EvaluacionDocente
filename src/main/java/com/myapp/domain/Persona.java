package com.myapp.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "persona")
public class Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "apellidopaterno")
    private String apellidoPaterno = "";
	
	@Column(name = "apellidomaterno")
    private String apellidoMaterno = "";
    
	@Column(name = "nombres")
	private String nombres = "";
    
	@Column(name = "curp")
	private String curp;
    
	@Column(name = "sexo")
    private String sexo; // H - Hombre, M - Mujer
    
	@Column(name = "email")
	private String email;
	
    
	@Column(name = "fechadenacimiento")
    private Calendar fechaDeNacimiento;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getApellidoPaterno() {
		return apellidoPaterno;
	}


	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}


	public String getApellidoMaterno() {
		return apellidoMaterno;
	}


	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}


	public String getNombres() {
		return nombres;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public String getCurp() {
		return curp;
	}


	public void setCurp(String curp) {
		this.curp = curp;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Calendar getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}


	public void setFechaDeNacimiento(Calendar fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}
    
  
	

}
