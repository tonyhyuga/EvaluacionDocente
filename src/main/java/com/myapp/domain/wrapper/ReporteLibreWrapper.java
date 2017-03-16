package com.myapp.domain.wrapper;

import java.sql.Blob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import com.myapp.domain.ReporteEvaluativoLibre;

public class ReporteLibreWrapper {

	private ReporteEvaluativoLibre reporte;
	/**
	 * 1-Admin_Evaluacion
	 * 2-Gestor
	 * 3-Profesor
	 * */
	private int tipoUser;
	
	private String comentario1;
	private String comentario2;
	private String comentario3;
	private String comentario4;
	private String comentario5;
	private String comentario6;
	private String comentario7;
	
	public ReporteLibreWrapper(){}
	
	public ReporteLibreWrapper(ReporteEvaluativoLibre reporte,int tipo){
		this.reporte=reporte;
		this.tipoUser=tipo;
		comentario1=convertBlobToString(reporte.getRazonSi());
		comentario2=convertBlobToString(reporte.getRazonNo());
		comentario3=convertBlobToString(reporte.getFortalezasProfesor());
		comentario4=convertBlobToString(reporte.getSugerenciasProfesor());
		comentario5=convertBlobToString(reporte.getFortalezasEstudiantes());
		comentario6=convertBlobToString(reporte.getSugerenciasEstudiantes());
		comentario7=convertBlobToString(reporte.getComentariosEstudiantes());
	}
	public ReporteEvaluativoLibre getReporte() {
		return reporte;
	}
	public void setReporte(ReporteEvaluativoLibre reporte) {
		this.reporte = reporte;
	}
	public int getTipoUser() {
		return tipoUser;
	}
	public void setTipoUser(int tipoUser) {
		this.tipoUser = tipoUser;
	}
	
	public String getComentario1() {
		return comentario1;
	}
	public void setComentario1(String comentario1) {
		this.comentario1 = comentario1;
		this.reporte.setRazonSi(convertStringToBlob(comentario1)); 
	}
	public String getComentario2() {
		return comentario2;
	}
	public void setComentario2(String comentario2) {
		this.comentario2 = comentario2;
		this.reporte.setRazonNo(convertStringToBlob(comentario2)); 
	}
	public String getComentario3() {
		return comentario3;
	}
	public void setComentario3(String comentario3) {
		this.comentario3 = comentario3;
		this.reporte.setFortalezasProfesor(convertStringToBlob(comentario3));
	}
	public String getComentario4() {
		return comentario4;
	}
	public void setComentario4(String comentario4) {
		this.comentario4 = comentario4;
		this.reporte.setSugerenciasProfesor(convertStringToBlob(comentario4));
	}
	public String getComentario5() {
		return comentario5;
	}
	public void setComentario5(String comentario5) {
		this.comentario5 = comentario5;
		this.reporte.setFortalezasEstudiantes(convertStringToBlob(comentario5)); 
	}
	public String getComentario6() {
		return comentario6;
	}
	public void setComentario6(String comentario6) {
		this.comentario6 = comentario6;
		this.reporte.setSugerenciasEstudiantes(convertStringToBlob(comentario6));
	}
	public String getComentario7() {
		return comentario7;
	}
	public void setComentario7(String comentario7) {
		this.comentario7 = comentario7;
		this.reporte.setComentariosEstudiantes(convertStringToBlob(comentario7)); 
	}
	
	
//	public String getRazonSi() {
//		return convertBlobToString(reporte.getRazonSi());
//	}
//	public void setRazonSi(String razonSi) {
//		this.reporte.setRazonSi(convertStringToBlob(razonSi)); 
//	}
//	public String getRazonNo() {
//		return convertBlobToString(reporte.getRazonNo());
//	}
//	public void setRazonNo(String razonNo) {
//		this.reporte.setRazonNo(convertStringToBlob(razonNo)); 
//	}
//	public String getFortalezasProfesor() {
//		return convertBlobToString(reporte.getFortalezasProfesor());
//	}
//	public void setFortalezasProfesor(String fortalezasProfesor) {
//		this.reporte.setFortalezasProfesor(convertStringToBlob(fortalezasProfesor)); 
//	}
//	public String getSugerenciasProfesor() {
//		return convertBlobToString(reporte.getSugerenciasProfesor());
//	}
//	public void setSugerenciasProfesor(String sugerenciasProfesor) {
//		this.reporte.setSugerenciasProfesor(convertStringToBlob(sugerenciasProfesor)); 
//	}
//	public String getFortalezasEstudiantes() {
//		return convertBlobToString(reporte.getFortalezasEstudiantes());
//	}
//	public void setFortalezasEstudiantes(String fortalezasEstudiantes) {
//		this.reporte.setFortalezasEstudiantes(convertStringToBlob(fortalezasEstudiantes)); 
//	}
//	public String getSugerenciasEstudiantes() {
//		return convertBlobToString(reporte.getSugerenciasEstudiantes());
//	}
//	public void setSugerenciasEstudiantes(String sugerenciasEstudiantes) {
//		this.reporte.setSugerenciasEstudiantes(convertStringToBlob(sugerenciasEstudiantes)); 
//	}
//	
//	public String getComentariosEstudiantes() {
//		return convertBlobToString(reporte.getComentariosEstudiantes());
//	}
//	
//	public void setComentariosEstudiantes(String comentariosEstudiantes) {
//
//		this.reporte.setComentariosEstudiantes(convertStringToBlob(comentariosEstudiantes)); 
//	}
	
	
	private String convertBlobToString(Blob blob){
		String str="";
		
		try {
			if(blob!=null && ((int)blob.length())>0)
			str = new String(blob.getBytes(1l, (int) blob.length()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return str;
	}
	private Blob convertStringToBlob(String value){
		Blob blob=null;
		if(value!=null){
		byte[] buff = value.getBytes();
		
		try {
			blob = new SerialBlob(buff);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return blob;
	}
	
}
