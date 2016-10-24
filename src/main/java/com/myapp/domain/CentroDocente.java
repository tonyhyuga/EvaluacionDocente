package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "centrodocente")
@PrimaryKeyJoinColumn(name="id")
public class CentroDocente extends Institucion{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "clavecgse")
	private Integer claveCGSE;

	public Integer getClaveCGSE() {
		return claveCGSE;
	}

	public void setClaveCGSE(Integer claveCGSE) {
		this.claveCGSE = claveCGSE;
	}
	
	
}
