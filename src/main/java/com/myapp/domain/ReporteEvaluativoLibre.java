package com.myapp.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "ReporteEvaluativoLibre")
@Table(name = "reporteevaluativolibre")
@PrimaryKeyJoinColumn(name="id")
@DiscriminatorValue("LI")
public class ReporteEvaluativoLibre extends ReporteEvaluativo {
	private static final long serialVersionUID = 1L;
}
