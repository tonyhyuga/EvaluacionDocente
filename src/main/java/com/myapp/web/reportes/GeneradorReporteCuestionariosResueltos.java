package com.myapp.web.reportes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.myapp.domain.AlumnoUADYMatriculado;
import com.myapp.domain.Persona;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.CuestionarioResuelto;

public class GeneradorReporteCuestionariosResueltos {
	String pathFile="/home/christian/opt/apache-tomcat-8.5.5/temp/Reporte de alumnos evaluados.xlsx";
	
	public File writeXLSXFile(ArrayList<WrapperAlumnosCuestionariosResueltos> wrappers) throws IOException {
		
		//String excelFileName = "C:/Test.xlsx";//name of excel file

		String sheetName = "Sheet1";//name of sheet

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName) ;
		
		int currentRow=0;
		XSSFRow cabeceras = sheet.createRow(0);
		XSSFCell matricula = cabeceras.createCell(0);
		XSSFCell nombre = cabeceras.createCell(1);
		XSSFCell respondio = cabeceras.createCell(2);
		currentRow++;
		
		
		matricula.setCellValue("Matricula");
		nombre.setCellValue("nombre");
		respondio.setCellValue("¿Completo la evaluacion?");

		
		//Set<CuestionarioResuelto> cuestionariosResueltos=  ambito.getCuestionariosResueltos();
		//ArrayList<CuestionarioResuelto> cuestionariosList = new ArrayList<>(cuestionariosResueltos);
		
		for(int index=0; index<wrappers.size(); index++){
			XSSFRow row = sheet.createRow(currentRow);
			XSSFCell cellMatricula = row.createCell(0);
			cellMatricula.setCellValue(wrappers.get(index).getAlumnoUsady().getMatriculaParteInvariable());
			
			XSSFCell cellNombres= row.createCell(1); 
			Persona per = wrappers.get(index).getPersona();
			cellNombres.setCellValue(per.getApellidoPaterno()+" "+per.getApellidoMaterno()+" "+per.getNombres());
			
			
			XSSFCell cellCuestionarioRespondido= row.createCell(2);
			String respondioString="No";
			if(wrappers.get(index).isRespondio()){
				respondioString="Si";
			}
			cellCuestionarioRespondido.setCellValue(respondioString);
			currentRow++;
			
		}
		
			
		
		

		FileOutputStream fileOut = new FileOutputStream(pathFile);
	

		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
		File excelFile= new File(pathFile);
		
		return excelFile;
	}

	
	
	public File createExcelFile(ArrayList<WrapperAlumnosCuestionariosResueltos> wrappers){	
		try {
			return writeXLSXFile(wrappers);
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
