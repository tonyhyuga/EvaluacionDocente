package com.myapp.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
@Entity
@Table(name = "periodotiempo")
@Inheritance(strategy=InheritanceType.JOINED)
public class PeriodoTiempo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "calendarinicio")
	private Calendar calendarInicio;
	
	@Column(name = "calendarfin")
	private Calendar calendarFin;
	
	public PeriodoTiempo() {
		this.id = null;
		this.calendarInicio = null;
		this.calendarFin = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getCalendarInicio() {
		return calendarInicio;
	}

	public void setCalendarInicio(Calendar calendarInicio) {
		this.calendarInicio = calendarInicio;
	}

	public Calendar getCalendarFin() {
		return calendarFin;
	}

	public void setCalendarFin(Calendar calendarFin) {
		this.calendarFin = calendarFin;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PeriodoTiempo pt = (PeriodoTiempo) o;
        if(pt.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, pt.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
