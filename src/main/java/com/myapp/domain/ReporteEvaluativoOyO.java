package com.myapp.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "reporteevaluativooyo")
@PrimaryKeyJoinColumn(name="id")
@DiscriminatorValue("OO")
public class ReporteEvaluativoOyO extends ReporteEvaluativo {
	
	private static final long serialVersionUID = 1L;
	@Column(name = "guiaaprenderindependientep")
	private Float guiaAprenderIndependienteP;
	@Column(name = "guiaaprenderindependientee")
	private Float guiaAprenderIndependienteE;
	
	@Column(name = "eticaprofesor")
	private Float eticaProfesor;
	@Column(name = "eticaestudiantes")
	private Float eticaEstudiantes;
	
	@Column(name = "usoplataformasprofesor")
	private Float usoPlataformasProfesor;
	@Column(name = "usoplataformasestudiantes")
	private Float usoPlataformasEstudiantes;
	
	@Column(name = "actividadescomplementariasp")
	private Float actividadesComplementariasP;
	@Column(name = "actividadescomplementariase")
	private Float actividadesComplementariasE;
	
	@Column(name = "desarrollocompetenciasp")
	private Float desarrolloCompetenciasP;
	@Column(name = "desarrollocompetenciase")
	private Float desarrolloCompetenciasE;
	
	@Column(name = "promueveanalisisprofesor")
	private Float promueveAnalisisProfesor;
	@Column(name = "promueveanalisisestudiantes")
	private Float promueveAnalisisEstudiantes;
	
	@Column(name = "integraconocimientoprofesor")
	private Float integraConocimientoProfesor;
	@Column(name = "integraconocimientoestudiante")
	private Float integraConocimientoEstudiante;
	
	@Column(name = "diferentesformasevaluacionp")	
	private Float diferentesFormasEvaluacionP;
	@Column(name = "diferentesformasevaluacione")
	private Float diferentesFormasEvaluacionE;
	
	@Column(name = "eticoprofesor")
	private Float eticoProfesor;
	@Column(name = "eticoestudiantes")
	private Float eticoEstudiantes;
	
	@Column(name = "honestoprofesor")
	private Float honestoProfesor;
	@Column(name = "honestoestudiantes")
	private Float honestoEstudiantes;
	
	@Column(name = "responsablesocialprofesor")
	private Float responsableSocialProfesor;
	@Column(name = "responsablesocialestudiantes")
	private Float responsableSocialEstudiantes;
	
	@Column(name = "toleranteprofesor")
	private Float toleranteProfesor;
	@Column(name = "toleranteestudiantes")
	private Float toleranteEstudiantes;
	
	@Column(name = "legalprofesor")
	private Float legalProfesor;
	@Column(name = "legalestudiantes")
	private Float legalEstudiantes;
	
	@Column(name = "respetuosoprofesor")
	private Float respetuosoProfesor;
	@Column(name = "respetuosoestudiantes")
	private Float respetuosoEstudiantes;
	
	@Column(name = "humildeprofesor")
	private Float humildeProfesor;
	@Column(name = "humildeestudiantes")
	private Float humildeEstudiantes;
	
	@Column(name = "equitativoprofesor")
	private Float equitativoProfesor;
	@Column(name = "equitativoestudiantes")
	private Float equitativoEstudiantes;
	
	@Column(name = "rigoracademicoprofesor")
	private Float rigorAcademicoProfesor;
	@Column(name = "rigoeracademicoestudiantes")
	private Float rigoerAcademicoEstudiantes;
	
	@Column(name = "valoractividadesdocente")
	private String valorActividadesDocente;



	public Float getGuiaAprenderIndependienteP() {
		return guiaAprenderIndependienteP;
	}



	public void setGuiaAprenderIndependienteP(Float guiaAprenderIndependienteP) {
		this.guiaAprenderIndependienteP = guiaAprenderIndependienteP;
	}



	public Float getGuiaAprenderIndependienteE() {
		return guiaAprenderIndependienteE;
	}



	public void setGuiaAprenderIndependienteE(Float guiaAprenderIndependienteE) {
		this.guiaAprenderIndependienteE = guiaAprenderIndependienteE;
	}



	public Float getEticaProfesor() {
		return eticaProfesor;
	}



	public void setEticaProfesor(Float eticaProfesor) {
		this.eticaProfesor = eticaProfesor;
	}



	public Float getEticaEstudiantes() {
		return eticaEstudiantes;
	}



	public void setEticaEstudiantes(Float eticaEstudiantes) {
		this.eticaEstudiantes = eticaEstudiantes;
	}



	public Float getUsoPlataformasProfesor() {
		return usoPlataformasProfesor;
	}



	public void setUsoPlataformasProfesor(Float usoPlataformasProfesor) {
		this.usoPlataformasProfesor = usoPlataformasProfesor;
	}



	public Float getUsoPlataformasEstudiantes() {
		return usoPlataformasEstudiantes;
	}



	public void setUsoPlataformasEstudiantes(Float usoPlataformasEstudiantes) {
		this.usoPlataformasEstudiantes = usoPlataformasEstudiantes;
	}



	public Float getActividadesComplementariasP() {
		return actividadesComplementariasP;
	}



	public void setActividadesComplementariasP(Float actividadesComplementariasP) {
		this.actividadesComplementariasP = actividadesComplementariasP;
	}



	public Float getActividadesComplementariasE() {
		return actividadesComplementariasE;
	}



	public void setActividadesComplementariasE(Float actividadesComplementariasE) {
		this.actividadesComplementariasE = actividadesComplementariasE;
	}



	public Float getDesarrolloCompetenciasP() {
		return desarrolloCompetenciasP;
	}



	public void setDesarrolloCompetenciasP(Float desarrolloCompetenciasP) {
		this.desarrolloCompetenciasP = desarrolloCompetenciasP;
	}



	public Float getDesarrolloCompetenciasE() {
		return desarrolloCompetenciasE;
	}



	public void setDesarrolloCompetenciasE(Float desarrolloCompetenciasE) {
		this.desarrolloCompetenciasE = desarrolloCompetenciasE;
	}



	public Float getPromueveAnalisisProfesor() {
		return promueveAnalisisProfesor;
	}



	public void setPromueveAnalisisProfesor(Float promueveAnalisisProfesor) {
		this.promueveAnalisisProfesor = promueveAnalisisProfesor;
	}



	public Float getPromueveAnalisisEstudiantes() {
		return promueveAnalisisEstudiantes;
	}



	public void setPromueveAnalisisEstudiantes(Float promueveAnalisisEstudiantes) {
		this.promueveAnalisisEstudiantes = promueveAnalisisEstudiantes;
	}



	public Float getIntegraConocimientoProfesor() {
		return integraConocimientoProfesor;
	}



	public void setIntegraConocimientoProfesor(Float integraConocimientoProfesor) {
		this.integraConocimientoProfesor = integraConocimientoProfesor;
	}



	public Float getIntegraConocimientoEstudiante() {
		return integraConocimientoEstudiante;
	}



	public void setIntegraConocimientoEstudiante(Float integraConocimientoEstudiante) {
		this.integraConocimientoEstudiante = integraConocimientoEstudiante;
	}



	public Float getDiferentesFormasEvaluacionP() {
		return diferentesFormasEvaluacionP;
	}



	public void setDiferentesFormasEvaluacionP(Float diferentesFormasEvaluacionP) {
		this.diferentesFormasEvaluacionP = diferentesFormasEvaluacionP;
	}



	public Float getDiferentesFormasEvaluacionE() {
		return diferentesFormasEvaluacionE;
	}



	public void setDiferentesFormasEvaluacionE(Float diferentesFormasEvaluacionE) {
		this.diferentesFormasEvaluacionE = diferentesFormasEvaluacionE;
	}



	public Float getEticoProfesor() {
		return eticoProfesor;
	}



	public void setEticoProfesor(Float eticoProfesor) {
		this.eticoProfesor = eticoProfesor;
	}



	public Float getEticoEstudiantes() {
		return eticoEstudiantes;
	}



	public void setEticoEstudiantes(Float eticoEstudiantes) {
		this.eticoEstudiantes = eticoEstudiantes;
	}



	public Float getHonestoProfesor() {
		return honestoProfesor;
	}



	public void setHonestoProfesor(Float honestoProfesor) {
		this.honestoProfesor = honestoProfesor;
	}



	public Float getHonestoEstudiantes() {
		return honestoEstudiantes;
	}



	public void setHonestoEstudiantes(Float honestoEstudiantes) {
		this.honestoEstudiantes = honestoEstudiantes;
	}



	public Float getResponsableSocialProfesor() {
		return responsableSocialProfesor;
	}



	public void setResponsableSocialProfesor(Float responsableSocialProfesor) {
		this.responsableSocialProfesor = responsableSocialProfesor;
	}



	public Float getResponsableSocialEstudiantes() {
		return responsableSocialEstudiantes;
	}



	public void setResponsableSocialEstudiantes(Float responsableSocialEstudiantes) {
		this.responsableSocialEstudiantes = responsableSocialEstudiantes;
	}



	public Float getToleranteProfesor() {
		return toleranteProfesor;
	}



	public void setToleranteProfesor(Float toleranteProfesor) {
		this.toleranteProfesor = toleranteProfesor;
	}



	public Float getToleranteEstudiantes() {
		return toleranteEstudiantes;
	}



	public void setToleranteEstudiantes(Float toleranteEstudiantes) {
		this.toleranteEstudiantes = toleranteEstudiantes;
	}



	public Float getLegalProfesor() {
		return legalProfesor;
	}



	public void setLegalProfesor(Float legalProfesor) {
		this.legalProfesor = legalProfesor;
	}



	public Float getLegalEstudiantes() {
		return legalEstudiantes;
	}



	public void setLegalEstudiantes(Float legalEstudiantes) {
		this.legalEstudiantes = legalEstudiantes;
	}



	public Float getRespetuosoProfesor() {
		return respetuosoProfesor;
	}



	public void setRespetuosoProfesor(Float respetuosoProfesor) {
		this.respetuosoProfesor = respetuosoProfesor;
	}



	public Float getRespetuosoEstudiantes() {
		return respetuosoEstudiantes;
	}



	public void setRespetuosoEstudiantes(Float respetuosoEstudiantes) {
		this.respetuosoEstudiantes = respetuosoEstudiantes;
	}



	public Float getHumildeProfesor() {
		return humildeProfesor;
	}



	public void setHumildeProfesor(Float humildeProfesor) {
		this.humildeProfesor = humildeProfesor;
	}



	public Float getHumildeEstudiantes() {
		return humildeEstudiantes;
	}



	public void setHumildeEstudiantes(Float humildeEstudiantes) {
		this.humildeEstudiantes = humildeEstudiantes;
	}



	public Float getEquitativoProfesor() {
		return equitativoProfesor;
	}



	public void setEquitativoProfesor(Float equitativoProfesor) {
		this.equitativoProfesor = equitativoProfesor;
	}



	public Float getEquitativoEstudiantes() {
		return equitativoEstudiantes;
	}



	public void setEquitativoEstudiantes(Float equitativoEstudiantes) {
		this.equitativoEstudiantes = equitativoEstudiantes;
	}



	public Float getRigorAcademicoProfesor() {
		return rigorAcademicoProfesor;
	}



	public void setRigorAcademicoProfesor(Float rigorAcademicoProfesor) {
		this.rigorAcademicoProfesor = rigorAcademicoProfesor;
	}



	public Float getRigoerAcademicoEstudiantes() {
		return rigoerAcademicoEstudiantes;
	}



	public void setRigoerAcademicoEstudiantes(Float rigoerAcademicoEstudiantes) {
		this.rigoerAcademicoEstudiantes = rigoerAcademicoEstudiantes;
	}



	public String getValorActividadesDocente() {
		return valorActividadesDocente;
	}



	public void setValorActividadesDocente(String valorActividadesDocente) {
		this.valorActividadesDocente = valorActividadesDocente;
	}
	
	
	
	
	
	
	
	
	
	
	

}
