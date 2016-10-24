package com.myapp.domain;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cursoacompanamiento")
@PrimaryKeyJoinColumn(name="id")
public class CursoAcompanamiento extends ClaseUADY{

}
