package com.myapp.web.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Persona;

public class ReporteCuestionarios {
	//String pathFile="/usr/local/tomcat8/temp/";//servidor
	String pathFile="C:/Users/rodrigo.denis/Desktop/respaldos/";//local
	private XSSFFont fontEncabezado;
	private XSSFCellStyle styleTitulo;
	
	public File writeXLSXFile(ClaseUADY clase,Persona profesor,
			ArrayList<Object[]> listaR,ArrayList<Object[]> listaP,String tipo) throws IOException {
		

		
		//String sheetName = "Respuestas Alumnos";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(tipo) ;
		fontEncabezado = wb.createFont();
		fontEncabezado.setFontName("Arial");
		fontEncabezado.setFontHeightInPoints((short)11);
		fontEncabezado.setBold(true);
		styleTitulo = wb.createCellStyle();
		styleTitulo.setFont(fontEncabezado);
		
		Integer currentRow=0;
		currentRow=writeCabecera(clase,profesor,sheet,currentRow);
		currentRow++;//espacio en blanco
		currentRow=writeNameColumn(listaP,sheet,currentRow);
		currentRow=writeBody(listaR,sheet,currentRow);
		

		
			
		pathFile+=tipo+".xlsx";
		

//		FileOutputStream fileOut = new FileOutputStream(
//				pathFile+clase.getAsignaturaBase().getNombre()+"-"+
//						getNombreProf(profesor)+".xlsx");
		FileOutputStream fileOut = new FileOutputStream(
				pathFile);
	

		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		File excelFile= new File(pathFile);
		
		return excelFile;
	}

	
	
	private Integer writeBody(ArrayList<Object[]> lista, XSSFSheet sheet,Integer currentRow) {
		Integer pivote=0;
		XSSFRow row = sheet.createRow(currentRow);
		int indice=0;
		for(int index=0; index<lista.size(); index++){
			
			Object[] datos= lista.get(index);
			
			if(pivote.intValue()!=((Integer)datos[2]).intValue()){
				pivote=(Integer)datos[2];
				currentRow++;
				indice=0;
				row = sheet.createRow(currentRow);
			}
     		XSSFCell celldato = row.createCell(indice);
			celldato.setCellValue(String.valueOf(datos[0]));
			indice++;
			
		
		}
		return currentRow;
	}
	
	private Integer writeNameColumn(ArrayList<Object[]> lista, XSSFSheet sheet,Integer currentRow) {
		XSSFRow row = sheet.createRow(currentRow);

		for(int index=0; index<lista.size(); index++){
			
			Object[] datos= lista.get(index);
//			for(int indice=0; indice<datos.length;indice++){
				XSSFCell celldato = row.createCell(index);
				celldato.setCellValue(String.valueOf(datos[0]));
				celldato.setCellStyle(styleTitulo);
//			}
		
		}
		currentRow++;
		return currentRow;
	}



	private Integer writeCabecera(ClaseUADY clase, Persona prof, XSSFSheet sheet, Integer currentRow) {
		XSSFRow asignaturaH = sheet.createRow(currentRow);
		currentRow++;
		XSSFRow profesorH = sheet.createRow(currentRow);
		currentRow++;
		XSSFRow grupoH = sheet.createRow(currentRow);
		XSSFCell asignaturaL = asignaturaH.createCell(0);
		XSSFCell profesorL = profesorH.createCell(0);
		XSSFCell grupoL = grupoH.createCell(0);
		XSSFCell asignatura = asignaturaH.createCell(1);
		XSSFCell profesor = profesorH.createCell(1);
		XSSFCell grupo = grupoH.createCell(1);
	    
				
	    asignaturaL.setCellValue("Asignatura:");
	    profesorL.setCellValue("Profesor:");
	    grupoL.setCellValue("Grupo:");
	    asignatura.setCellValue(clase.getAsignaturaBase().getNombre());
	    asignaturaL.setCellStyle(styleTitulo);
	    profesor.setCellValue(getNombreProf(prof));
	    profesorL.setCellStyle(styleTitulo);
	    grupo.setCellValue(clase.getGrupo().getGrupo());
	    grupoL.setCellStyle(styleTitulo);
	    currentRow++;
	   return currentRow;
	}




	private String getNombreProf(Persona profesor) {
		return (profesor.getNombres()!=null?profesor.getNombres()+" ":"")+""+
				(profesor.getApellidoPaterno()!=null?profesor.getApellidoPaterno()+" ":"")+" "+
				(profesor.getApellidoMaterno()!=null?profesor.getApellidoMaterno():"");
	
}



	public File createExcelFile(ClaseUADY clase, Persona profesor,ArrayList listaR,ArrayList listaP,String tipo){	
		try {
			return writeXLSXFile(clase,profesor,listaR,listaP,tipo);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	/*
	public static void main(String[] args){
		GeneradorReporteCuestionariosResueltos generador = new GeneradorReporteCuestionariosResueltos();
		try {
			ArrayList<WrapperAlumnosCuestionariosResueltos> wrappers = new ArrayList<WrapperAlumnosCuestionariosResueltos>();
			

			
			generador.writeXLSXFile(wrappers);
		} catch (IOException e) {// verificarl el error de permisos------
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	

}
