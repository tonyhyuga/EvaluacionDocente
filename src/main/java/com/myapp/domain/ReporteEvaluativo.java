package com.myapp.domain;

import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "reporteevaluativo")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="id")
@DiscriminatorColumn(name="discriminador", discriminatorType = DiscriminatorType.STRING)
public class ReporteEvaluativo extends ReporteEvaluacionDocente{
	
	@Column(name = "totalalumnos")
	protected Float numEstudiantes;
	
	@Column(name = "muestrarespetoprofesor")
	protected Float muestraRespetoProfesor;
	@Column(name = "muestrarespetoestudiantes")
	protected Float muestraRespetoEstudiantes;
	
	@Column(name = "congruenciaprofesor")
	protected Float congruenciaProfesor;
	@Column(name = "congruenciaestudiantes")
	protected Float congruenciaEstudiantes;
	
	@Column(name = "intercambioconocimientop")
	protected Float intercambioConocimientoP;
	@Column(name = "intercambioconocimientoe")
	protected Float intercambioConocimientoE;
	
	@Column(name = "optimizatiempoprofesor")
	protected Float optimizaTiempoProfesor;
	@Column(name = "optimizatiempoestudiante")
	protected Float optimizaTiempoEstudiante;
	
	@Column(name = "respetuosootrosdocentes")
	protected Float respetuosoOtrosDocentes;
	
	@Column(name = "comprometido")
	protected Float comprometido;
	
	@Column(name = "interesaprendizajeprofesor")
	protected Float interesAprendizajeProfesor;
	@Column(name = "interesaprendizajeestudiante")
	protected Float interesAprendizajeEstudiante;
	
	@Column(name = "establecepuntajeprofesor")
	protected Float establecePuntajeProfesor;
	@Column(name = "establecepuntajeestudiante")
	protected Float establecePuntajeEstudiante;
	
	@Column(name = "informapuntuacionprofesor")
	protected Float informaPuntuacionProfesor;
	@Column(name = "informapuntuacionestudiante")
	protected Float informaPuntuacionEstudiante;
	
	@Column(name = "actividadesrelacionadovistop")
	protected Float actividadesRelacionadoVistoP;
	@Column(name = "actividadesrelacionadovistoe")
	protected Float actividadesRelacionadoVistoE;
	
	@Column(name = "retroalimentoprofesor")
	protected Float retroalimentoProfesor;
	@Column(name = "retroalimentoestudiante")
	protected Float retroalimentoEstudiante;
	
	@Column(name = "insatisfechocompetencias")
	protected Float insatisfechoCompetencias;
	@Column(name = "satisfechocompetencias")
	protected Float satisfechoCompetencias;
	@Column(name = "muysatisfechocompetencias")
	protected Float muySatisfechoCompetencias;
	@Column(name = "valorcompetenciasprofesor")
	protected String valorCompetenciasProfesor;
	
	@Column(name = "insatisfechodesempenio")
	protected Float insatisfechoDesempenio;
	@Column(name = "satisfechodesempenio")
	protected Float satisfechoDesempenio;
	@Column(name = "muysatisfechodesempenio")
	protected Float muySatisfechoDesempenio;
	@Column(name = "valordesempenioprofesor")
	protected String valorDesempenioProfesor;
	
	@Column(name = "recomendarprofesorsi")
	protected Float recomendarProfesorSi;
	@Column(name = "recomendarprofesorno")
	protected Float recomendarProfesorNo;
	
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "razonsi")
	protected Blob razonSi;
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "razonno")
	protected Blob razonNo;
	
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "fortalezasprofesor")
	protected Blob fortalezasProfesor;
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "sugerenciasprofesor")
	protected Blob sugerenciasProfesor;
	
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "fortalezasestudiantes")
	protected Blob fortalezasEstudiantes;
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "sugerenciasestudiantes")
	protected Blob sugerenciasEstudiantes;
	
	@JsonIgnore
	@Lob @Basic(fetch=FetchType.LAZY)
	@Column(name = "comentariosestudiantes")
	protected Blob comentariosEstudiantes;

	
	
	
	public Float getNumEstudiantes() {
		return numEstudiantes;
	}
	public void setNumEstudiantes(Float numEstudiantes) {
		this.numEstudiantes = numEstudiantes;
	}
	public Float getMuestraRespetoProfesor() {
		return muestraRespetoProfesor;
	}
	public void setMuestraRespetoProfesor(Float muestraRespetoProfesor) {
		this.muestraRespetoProfesor = muestraRespetoProfesor;
	}
	public Float getMuestraRespetoEstudiantes() {
		return muestraRespetoEstudiantes;
	}
	public void setMuestraRespetoEstudiantes(Float muestraRespetoEstudiantes) {
		this.muestraRespetoEstudiantes = muestraRespetoEstudiantes;
	}
	public Float getCongruenciaProfesor() {
		return congruenciaProfesor;
	}
	public void setCongruenciaProfesor(Float congruenciaProfesor) {
		this.congruenciaProfesor = congruenciaProfesor;
	}
	public Float getCongruenciaEstudiantes() {
		return congruenciaEstudiantes;
	}
	public void setCongruenciaEstudiantes(Float congruenciaEstudiantes) {
		this.congruenciaEstudiantes = congruenciaEstudiantes;
	}
	public Float getIntercambioConocimientoP() {
		return intercambioConocimientoP;
	}
	public void setIntercambioConocimientoP(Float intercambioConocimientoP) {
		this.intercambioConocimientoP = intercambioConocimientoP;
	}
	public Float getIntercambioConocimientoE() {
		return intercambioConocimientoE;
	}
	public void setIntercambioConocimientoE(Float intercambioConocimientoE) {
		this.intercambioConocimientoE = intercambioConocimientoE;
	}
	public Float getOptimizaTiempoProfesor() {
		return optimizaTiempoProfesor;
	}
	public void setOptimizaTiempoProfesor(Float optimizaTiempoProfesor) {
		this.optimizaTiempoProfesor = optimizaTiempoProfesor;
	}
	public Float getOptimizaTiempoEstudiante() {
		return optimizaTiempoEstudiante;
	}
	public void setOptimizaTiempoEstudiante(Float optimizaTiempoEstudiante) {
		this.optimizaTiempoEstudiante = optimizaTiempoEstudiante;
	}
	public Float getRespetuosoOtrosDocentes() {
		return respetuosoOtrosDocentes;
	}
	public void setRespetuosoOtrosDocentes(Float respetuosoOtrosDocentes) {
		this.respetuosoOtrosDocentes = respetuosoOtrosDocentes;
	}
	public Float getComprometido() {
		return comprometido;
	}
	public void setComprometido(Float comprometido) {
		this.comprometido = comprometido;
	}
	public Float getInteresAprendizajeProfesor() {
		return interesAprendizajeProfesor;
	}
	public void setInteresAprendizajeProfesor(Float interesAprendizajeProfesor) {
		this.interesAprendizajeProfesor = interesAprendizajeProfesor;
	}
	public Float getInteresAprendizajeEstudiante() {
		return interesAprendizajeEstudiante;
	}
	public void setInteresAprendizajeEstudiante(Float interesAprendizajeEstudiante) {
		this.interesAprendizajeEstudiante = interesAprendizajeEstudiante;
	}
	public Float getEstablecePuntajeProfesor() {
		return establecePuntajeProfesor;
	}
	public void setEstablecePuntajeProfesor(Float establecePuntajeProfesor) {
		this.establecePuntajeProfesor = establecePuntajeProfesor;
	}
	public Float getEstablecePuntajeEstudiante() {
		return establecePuntajeEstudiante;
	}
	public void setEstablecePuntajeEstudiante(Float establecePuntajeEstudiante) {
		this.establecePuntajeEstudiante = establecePuntajeEstudiante;
	}
	public Float getInformaPuntuacionProfesor() {
		return informaPuntuacionProfesor;
	}
	public void setInformaPuntuacionProfesor(Float informaPuntuacionProfesor) {
		this.informaPuntuacionProfesor = informaPuntuacionProfesor;
	}
	public Float getInformaPuntuacionEstudiante() {
		return informaPuntuacionEstudiante;
	}
	public void setInformaPuntuacionEstudiante(Float informaPuntuacionEstudiante) {
		this.informaPuntuacionEstudiante = informaPuntuacionEstudiante;
	}
	public Float getActividadesRelacionadoVistoP() {
		return actividadesRelacionadoVistoP;
	}
	public void setActividadesRelacionadoVistoP(Float actividadesRelacionadoVistoP) {
		this.actividadesRelacionadoVistoP = actividadesRelacionadoVistoP;
	}
	public Float getActividadesRelacionadoVistoE() {
		return actividadesRelacionadoVistoE;
	}
	public void setActividadesRelacionadoVistoE(Float actividadesRelacionadoVistoE) {
		this.actividadesRelacionadoVistoE = actividadesRelacionadoVistoE;
	}
	public Float getRetroalimentoProfesor() {
		return retroalimentoProfesor;
	}
	public void setRetroalimentoProfesor(Float retroalimentoProfesor) {
		this.retroalimentoProfesor = retroalimentoProfesor;
	}
	public Float getRetroalimentoEstudiante() {
		return retroalimentoEstudiante;
	}
	public void setRetroalimentoEstudiante(Float retroalimentoEstudiante) {
		this.retroalimentoEstudiante = retroalimentoEstudiante;
	}
	public Float getInsatisfechoCompetencias() {
		return insatisfechoCompetencias;
	}
	public void setInsatisfechoCompetencias(Float insatisfechoCompetencias) {
		this.insatisfechoCompetencias = insatisfechoCompetencias;
	}
	public Float getSatisfechoCompetencias() {
		return satisfechoCompetencias;
	}
	public void setSatisfechoCompetencias(Float satisfechoCompetencias) {
		this.satisfechoCompetencias = satisfechoCompetencias;
	}
	public Float getMuySatisfechoCompetencias() {
		return muySatisfechoCompetencias;
	}
	public void setMuySatisfechoCompetencias(Float muySatisfechoCompetencias) {
		this.muySatisfechoCompetencias = muySatisfechoCompetencias;
	}
	public String getValorCompetenciasProfesor() {
		return valorCompetenciasProfesor;
	}
	public void setValorCompetenciasProfesor(String valorCompetenciasProfesor) {
		this.valorCompetenciasProfesor = valorCompetenciasProfesor;
	}
	public Float getInsatisfechoDesempenio() {
		return insatisfechoDesempenio;
	}
	public void setInsatisfechoDesempenio(Float insatisfechoDesempenio) {
		this.insatisfechoDesempenio = insatisfechoDesempenio;
	}
	public Float getSatisfechoDesempenio() {
		return satisfechoDesempenio;
	}
	public void setSatisfechoDesempenio(Float satisfechoDesempenio) {
		this.satisfechoDesempenio = satisfechoDesempenio;
	}
	public Float getMuySatisfechoDesempenio() {
		return muySatisfechoDesempenio;
	}
	public void setMuySatisfechoDesempenio(Float muySatisfechoDesempenio) {
		this.muySatisfechoDesempenio = muySatisfechoDesempenio;
	}
	public String getValorDesempenioProfesor() {
		return valorDesempenioProfesor;
	}
	public void setValorDesempenioProfesor(String valorDesempenioProfesor) {
		this.valorDesempenioProfesor = valorDesempenioProfesor;
	}
	public Float getRecomendarProfesorSi() {
		return recomendarProfesorSi;
	}
	public void setRecomendarProfesorSi(Float recomendarProfesorSi) {
		this.recomendarProfesorSi = recomendarProfesorSi;
	}
	public Float getRecomendarProfesorNo() {
		return recomendarProfesorNo;
	}
	public void setRecomendarProfesorNo(Float recomendarProfesorNo) {
		this.recomendarProfesorNo = recomendarProfesorNo;
	}
	public Blob getRazonSi() {
		return razonSi;
	}
	public void setRazonSi(Blob razonSi) {
		this.razonSi = razonSi;
	}
	public Blob getRazonNo() {
		return razonNo;
	}
	public void setRazonNo(Blob razonNo) {
		this.razonNo = razonNo;
	}
	public Blob getFortalezasProfesor() {
		return fortalezasProfesor;
	}
	public void setFortalezasProfesor(Blob fortalezasProfesor) {
		this.fortalezasProfesor = fortalezasProfesor;
	}
	public Blob getSugerenciasProfesor() {
		return sugerenciasProfesor;
	}
	public void setSugerenciasProfesor(Blob sugerenciasProfesor) {
		this.sugerenciasProfesor = sugerenciasProfesor;
	}
	public Blob getFortalezasEstudiantes() {
		return fortalezasEstudiantes;
	}
	public void setFortalezasEstudiantes(Blob fortalezasEstudiantes) {
		this.fortalezasEstudiantes = fortalezasEstudiantes;
	}
	public Blob getSugerenciasEstudiantes() {
		return sugerenciasEstudiantes;
	}
	public void setSugerenciasEstudiantes(Blob sugerenciasEstudiantes) {
		this.sugerenciasEstudiantes = sugerenciasEstudiantes;
	}
	public Blob getComentariosEstudiantes() {
		return this.comentariosEstudiantes;
	}
	public void setComentariosEstudiantes(Blob comentariosEstudiantes) {
	
		this.comentariosEstudiantes = comentariosEstudiantes;
	}

	
//	public String getRazonSi() {
//		return convertBlobToString(reporte.getRazonSi());
//	}
//	public void setRazonSi(String razonSi) {
//		this.setRazonSi(convertStringToBlob(razonSi)); 
//	}
//	public String getRazonNo() {
//		return convertBlobToString(reporte.getRazonNo());
//	}
//	public void setRazonNo(String razonNo) {
//		this.setRazonNo(convertStringToBlob(razonNo)); 
//	}
//	public String getFortalezasProfesor() {
//		return convertBlobToString(reporte.getFortalezasProfesor());
//	}
//	public void setFortalezasProfesor(String fortalezasProfesor) {
//		this.setFortalezasProfesor(convertStringToBlob(fortalezasProfesor)); 
//	}
//	public String getSugerenciasProfesor() {
//		return convertBlobToString(reporte.getSugerenciasProfesor());
//	}
//	public void setSugerenciasProfesor(String sugerenciasProfesor) {
//		this.setSugerenciasProfesor(convertStringToBlob(sugerenciasProfesor)); 
//	}
//	public String getFortalezasEstudiantes() {
//		return convertBlobToString(reporte.getFortalezasEstudiantes());
//	}
//	public void setFortalezasEstudiantes(String fortalezasEstudiantes) {
//		this.setFortalezasEstudiantes(convertStringToBlob(fortalezasEstudiantes)); 
//	}
//	public String getSugerenciasEstudiantes() {
//		return convertBlobToString(reporte.getSugerenciasEstudiantes());
//	}
//	public void setSugerenciasEstudiantes(String sugerenciasEstudiantes) {
//		this.setSugerenciasEstudiantes(convertStringToBlob(sugerenciasEstudiantes)); 
//	}
//	
//	public String getComentariosEstudiantes() {
//		return convertBlobToString(reporte.getComentariosEstudiantes());
//	}
//	
//	public void setComentariosEstudiantes(String comentariosEstudiantes) {
//
//		this.setComentariosEstudiantes(convertStringToBlob(comentariosEstudiantes)); 
//	}
//	
//	private String convertBlobToString(Blob blob){
//		String str="";
//		try {
//			str = new String(blob.getBytes(1l, (int) blob.length()));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch blockz
//			e.printStackTrace();
//		}
//		return str;
//	}
//	private Blob convertStringToBlob(String value){
//		byte[] buff = value.getBytes();
//		Blob blob=null;
//		try {
//			blob = new SerialBlob(buff);
//		} catch (SerialException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return blob;
//	}
	
}
