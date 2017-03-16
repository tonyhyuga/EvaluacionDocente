package com.myapp.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.AsignaturaBase;
import com.myapp.domain.ClaseUADY;
import com.myapp.domain.Empleado;
import com.myapp.domain.Persona;
import com.myapp.domain.ReporteEvaluativo;
import com.myapp.domain.ReporteEvaluativoLibre;
import com.myapp.domain.ReporteEvaluativoOyO;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.RespuestaPregunta;
import com.myapp.domain.wrapper.ClaseUADYDocenteWrapper;
import com.myapp.domain.wrapper.ReporteEvaluativoWrapper;
import com.myapp.repository.AmbitoRepository;
import com.myapp.repository.ClaseUADYRepository;
import com.myapp.repository.ReporteEvaluacionDocenteRepository;

@Service
@Transactional
public class AdminEvaluacionDocenteService {
	
	 final Logger log = LoggerFactory.getLogger(AdminEvaluacionDocenteService.class);

	@Inject
	 ClaseUADYRepository claseRepository;
	
	@Inject
	 AmbitoRepository ambitoRepository;
	@Inject
	ReporteEvaluacionDocenteService reporteEVService;
	@Inject
	ReporteEvaluacionDocenteRepository reporteRepository;
	
	public Page<ClaseUADYDocenteWrapper> findClasesProfesor(Pageable pageable, Integer idAnio,Short indicePeriodo,Integer centroDocente,String search,boolean porEvaluar) {
		log.debug("Request to get all emeplados con paginacion");
		
		//Calendar fecha = Calendar.getInstance();
		Page<Object[]> clasest= null;

		if(porEvaluar)
		{
			clasest= claseRepository.getClasesUADYPorInstitucionEP(pageable,centroDocente,idAnio,indicePeriodo,search);
		}
		else
		{
			clasest= claseRepository.getClasesUADYPorInstitucion(pageable,centroDocente,idAnio,indicePeriodo,search);
		}	
		
		System.out.println("clases total sin sinodos: "+clasest.getTotalElements());

		List<ClaseUADYDocenteWrapper> wrappers= new ArrayList<ClaseUADYDocenteWrapper>();
		List<Object[]> clases = clasest.getContent();
		int idwrapper=0;
		for(int i=0;i<clases.size();i++){
			idwrapper++;
			ClaseUADY clase=(ClaseUADY)clases.get(i)[0];
			Empleado sinodo = (Empleado)clases.get(i)[1];
			ClaseUADYDocenteWrapper wraper= new ClaseUADYDocenteWrapper(clase,sinodo,idwrapper);
		
			Ambito ambitoObservaciones=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Observaciones");
			Ambito ambitoEvaluacion=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evaluaciones");
			if(ambitoEvaluacion==null)
			{
				wraper.setHayevaluaciones(false);
			}
			else
			{
				wraper.setHayevaluaciones(true);
			}
			Ambito ambitoEvidencias=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evidencias");;
			wraper.setObservaciones(ambitoObservaciones);
			wraper.setEvaluacion(ambitoEvaluacion);
			wraper.setEvidencias(ambitoEvidencias);
			wrappers.add(wraper);
		}
		Page<ClaseUADYDocenteWrapper> page = new PageImpl<ClaseUADYDocenteWrapper>(wrappers, pageable,clasest.getTotalElements());
		
		return page;
	}
	
	public Page<ReporteEvaluativoWrapper> findAsignaturaProfesor(Pageable pageable, Integer idAnio,Integer indicePeriodo,Integer centroDocente,String search,boolean porEvaluar) {
		log.debug("Request to get all asiganturas con paginacion reporte");
		
		//Calendar fecha = Calendar.getInstance();
		Page<Object[]> clasest= null;

//		if(porEvaluar)
//		{
//			clasest= claseRepository.getClasesUADYPorInstitucionEP(pageable,centroDocente,idAnio,indicePeriodo,search);
//		}
//		else
//		{
		Short indice=0;
		boolean intercurso=false;
		Short indiceIntercurso=0;
		if(indicePeriodo==1){//primer periodo
		indice=1;	
		}
		if(indicePeriodo==2){//segundo periodo
			indice=2;		
		}
		if(indicePeriodo==3){//verano
			 intercurso=true;
			 indiceIntercurso=2;
		}
		if(indicePeriodo==4){//invierno
			 intercurso=true;
			 indiceIntercurso=1;
		}
		
			clasest= claseRepository.getAsignaturasPorInstitucion(pageable,centroDocente,idAnio,indice,intercurso,indiceIntercurso,search);
		//}	
		
		System.out.println("clases total sin sinodos: "+clasest.getTotalElements());

		List<ReporteEvaluativoWrapper> wrappers= new ArrayList<ReporteEvaluativoWrapper>();
		List<Object[]> clases = clasest.getContent();
		int idwrapper=0;
		for(int i=0;i<clases.size();i++){
			idwrapper++;
			AsignaturaBase asigantura=(AsignaturaBase)clases.get(i)[0];
			Persona sinodo = (Persona)clases.get(i)[1];
			String tipo = (String)clases.get(i)[2];
			ReporteEvaluativoWrapper wraper= new ReporteEvaluativoWrapper(idwrapper,asigantura,sinodo,tipo);
			
			ReporteEvaluativo reporte=null;
			if(tipo.equals("Obligatoria")||tipo.equals("Optativa"))
				 reporte=reporteRepository.getReporteOO(asigantura.getId(),sinodo.getId(),centroDocente,idAnio,indicePeriodo,tipo);
			if(tipo.equals("Libre"))
				reporte=reporteRepository.getReporteLI(asigantura.getId(),sinodo.getId(),centroDocente,idAnio,indicePeriodo,tipo);
			//Ambito ambitoEvaluacion=ambitoRepository.getAmbito(clase.getId(), sinodo.getPersona().getId(),"Evaluaciones");
			if(reporte==null)
			{
				wraper.setHasReporte(false);
			}
			else
			{
				wraper.setHasReporte(true);
			}
		
			wrappers.add(wraper);
		}
		Page<ReporteEvaluativoWrapper> page = new PageImpl<ReporteEvaluativoWrapper>(wrappers, pageable,clasest.getTotalElements());
		
		return page;
	}
	
	
	public ReporteEvaluativoOyO generarReporteEvaluativoOO(Integer idAsignatura,Integer idPersona,Integer idAnio,Integer indice,String tipo,Integer idDependencia){
		Short indicePeriodo=0;
		boolean intercurso=false;
		Short indiceIntercurso=0;
		if(indice==1){//primer periodo
			indicePeriodo=1;	
		}
		if(indice==2){//segundo periodo
			indicePeriodo=2;		
		}
		if(indice==3){//verano
			 intercurso=true;
			 indiceIntercurso=2;
		}
		if(indice==4){//invierno
			 intercurso=true;
			 indiceIntercurso=1;
		}
		List<CuestionarioResuelto> cuestionarios = ambitoRepository.getAmbitosToReporte(idAsignatura,idPersona,idAnio,indicePeriodo,intercurso,indiceIntercurso,tipo,idDependencia);
		System.out.println(cuestionarios.size());
		ReporteEvaluativoOyO reporte = reporteEVService.generarReporteEvaluativoOO(cuestionarios,idAsignatura, idPersona, idAnio, indice, tipo, idDependencia);
		//reporteRepository.save(reporte);
		return reporte;
	}
	
	public ReporteEvaluativoLibre generarReporteEvaluativoLI(Integer idAsignatura,Integer idPersona,Integer idAnio,Integer indice,String tipo,Integer idDependencia){
		Short indicePeriodo=0;
		boolean intercurso=false;
		Short indiceIntercurso=0;
		if(indice==1){//primer periodo
			indicePeriodo=1;	
		}
		if(indice==2){//segundo periodo
			indicePeriodo=2;		
		}
		if(indice==3){//verano
			 intercurso=true;
			 indiceIntercurso=2;
		}
		if(indice==4){//invierno
			 intercurso=true;
			 indiceIntercurso=1;
		}
		List<CuestionarioResuelto> cuestionarios = ambitoRepository.getAmbitosToReporte(idAsignatura,idPersona,idAnio,indicePeriodo,intercurso,indiceIntercurso,tipo,idDependencia);
		System.out.println(cuestionarios.size());
		ReporteEvaluativoLibre reporte = reporteEVService.generarReporteEvaluativoLI(cuestionarios,idAsignatura, idPersona, idAnio, indice, tipo, idDependencia);
		//reporteRepository.save(reporte);
		return reporte;
	}
	
}
