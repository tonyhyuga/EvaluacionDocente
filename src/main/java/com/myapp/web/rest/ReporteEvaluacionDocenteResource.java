package com.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.myapp.domain.Empleado;
import com.myapp.domain.ReporteEvaluacionDocente;
import com.myapp.domain.ReporteEvaluativo;
import com.myapp.domain.ReporteEvaluativoLibre;
import com.myapp.domain.ReporteEvaluativoOyO;
import com.myapp.domain.encuestas.Ambito;
import com.myapp.domain.wrapper.ReporteEvaluativoWrapper;
import com.myapp.domain.wrapper.ReporteLibreWrapper;
import com.myapp.domain.wrapper.ReporteOOWrapper;
import com.myapp.repository.ReporteEvaluacionDocenteRepository;
import com.myapp.web.rest.util.HeaderUtil;
import com.myapp.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/apo")
public class ReporteEvaluacionDocenteResource {

	private final Logger log = LoggerFactory.getLogger(ReporteEvaluacionDocenteResource.class);
	
	@Inject
	private ReporteEvaluacionDocenteRepository reporteRepository;
	
	 @RequestMapping(value = "/guardarreporteoo",
			  method = RequestMethod.POST,
			  produces = MediaType.APPLICATION_JSON_VALUE)
			@Timed
public ResponseEntity<ReporteEvaluacionDocente> saveReporteOO(@RequestBody ReporteOOWrapper reportew) throws URISyntaxException {
				  log.debug("REST request to save Reporte OB y OP : {}", reportew.getReporte().getId());
				  ReporteEvaluativoOyO reporte = reportew.getReporte();
				  ReporteEvaluacionDocente result=null;
				  if(reporte.getId()==null)
					  result = reporteRepository.save(reporte);
				  else{
					  System.out.println("actualizado");
					  result = reporte;
				  }
					 
		 return ResponseEntity.created(new URI("/apo/guardarreporteoo/" + result.getId()))
			      .headers(HeaderUtil.createEntityCreationAlert("reporte", result.getId().toString()))
			      .body(result);
	}
	 
	 @RequestMapping(value = "/guardarreporteoo",
			  method = RequestMethod.PUT,
			  produces = MediaType.APPLICATION_JSON_VALUE)
			@Timed
public ResponseEntity<ReporteEvaluacionDocente> updateReporteOO(@RequestBody ReporteOOWrapper reporte) throws URISyntaxException {
				  log.debug("REST request to update Reporte OB y OP : {}", reporte);
		ReporteEvaluacionDocente result = reporteRepository.save(reporte.getReporte());
		
		 return ResponseEntity.created(new URI("/apo/guardarreporteoo/" + result.getId()))
			      .headers(HeaderUtil.createEntityCreationAlert("reporte", result.getId().toString()))
			      .body(result);
	}
	 
	 @RequestMapping(value = "/guardarreporteli",
			  method = RequestMethod.POST,
			  produces = MediaType.APPLICATION_JSON_VALUE)
			@Timed
public ResponseEntity<ReporteEvaluacionDocente> saveReporteLI(@RequestBody ReporteLibreWrapper reportew) throws URISyntaxException {
				  log.debug("REST request to save Reporte Libre : {}", reportew.getReporte().getId());
		ReporteEvaluacionDocente result=null;
		ReporteEvaluativoLibre reporte = reportew.getReporte();
		if(reporte.getId()==null)
			  result = reporteRepository.save(reporte);
		  else{
			  System.out.println("actualizado");
			  result = reporte;
		  }
		 return ResponseEntity.created(new URI("/apo/guardarreporteli/" + result.getId()))
			      .headers(HeaderUtil.createEntityCreationAlert("reporte", result.getId().toString()))
			      .body(result);
	}
	 
	 
	 @RequestMapping(value = "/guardarreporteli",
			  method = RequestMethod.PUT,
			  produces = MediaType.APPLICATION_JSON_VALUE)
			@Timed
public ResponseEntity<ReporteEvaluacionDocente> updateReporteLI(@RequestBody ReporteLibreWrapper reporte) throws URISyntaxException {
				  log.debug("REST request to update Reporte Libre : {}", reporte.getReporte().getId());
		ReporteEvaluacionDocente result = reporteRepository.save(reporte.getReporte());
		 return ResponseEntity.created(new URI("/apo/guardarreporteli/" + result.getId()))
			      .headers(HeaderUtil.createEntityCreationAlert("reporte", result.getId().toString()))
			      .body(result);
	}
	 
	 
	 @RequestMapping(value = "/misreportes{search},{type},{indice},{anio},{centro},{ep}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		@Timed
		public ResponseEntity<List<ReporteEvaluativo>> getMisReportes(Pageable pageable,HttpSession httpsession,
				@PathVariable String search,@PathVariable Integer type,@PathVariable Integer indice,@PathVariable Integer anio,@PathVariable Integer centro,@PathVariable boolean ep)
				throws URISyntaxException {
			log.debug("REST request to get a page of Mis Reportes {}",type,search,indice,anio,centro);
			
			Empleado docente = (Empleado)httpsession.getAttribute("Empleado");
			Page<ReporteEvaluativo> page =reporteRepository.getMisReportes(pageable,docente.getPersona().getId(),indice,anio,centro);// adminEvaluacionDocenteService.findAsignaturaProfesor(pageable,anio,indice,centro,search,ep);
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/reporteEvaluativo");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		} 
	 
	 
	 @RequestMapping(value = "/revisionreporteoo{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		@Timed
		public ResponseEntity<ReporteOOWrapper> getReporteOO(@PathVariable Integer id)
				throws URISyntaxException {
			log.debug("REST request to get a page of Mis Reportes {}",id);
			
			ReporteEvaluativoOyO reporte = reporteRepository.getReporteOO(id);
			
			ReporteOOWrapper wraper=new ReporteOOWrapper(reporte,2);
			
			return Optional.ofNullable(wraper)
		            .map(result -> new ResponseEntity<>(
	              result,
	              HttpStatus.OK))
	          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
			
		} 
	 
	 
	 @RequestMapping(value = "/revisionreporteli{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		@Timed
		public ResponseEntity<ReporteLibreWrapper> getReporteLI(@PathVariable Integer id)
				throws URISyntaxException {
			log.debug("REST request to get a page of Mis Reportes {}",id);
			
			ReporteEvaluativoLibre reporte = reporteRepository.getReporteLibre(id);
			
			ReporteLibreWrapper wrapper= new ReporteLibreWrapper(reporte,2);
			return Optional.ofNullable(wrapper)
		            .map(result -> new ResponseEntity<>(
		                    result,
		                    HttpStatus.OK))
		                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} 
	 
	 
	 @RequestMapping(value = "/verreporteoo{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		@Timed
		public ResponseEntity<ReporteOOWrapper> getReporteDocenteOO(@PathVariable Integer id)
				throws URISyntaxException {
			log.debug("REST request to get a page of Mis Reportes {}",id);
			
			 ReporteEvaluativoOyO reporte = reporteRepository.getReporteOO(id);
			
			ReporteOOWrapper wraper=new ReporteOOWrapper(reporte,3);
			
			return Optional.ofNullable(wraper)
		            .map(result -> new ResponseEntity<>(
	              result,
	              HttpStatus.OK))
	          .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
			
		} 
	 
	 
	 @RequestMapping(value = "/verreporteli{id}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		@Timed
		public ResponseEntity<ReporteLibreWrapper> getReporteDocenteLI(@PathVariable Integer id)
				throws URISyntaxException {
			log.debug("REST request to get a page of Mis Reportes {}",id);
			
			ReporteEvaluativoLibre reporte = reporteRepository.getReporteLibre(id);
			ReporteLibreWrapper wrapper= new ReporteLibreWrapper(reporte, 3);
			return Optional.ofNullable(wrapper)
		            .map(result -> new ResponseEntity<>(
		                    result,
		                    HttpStatus.OK))
		                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		} 
	 
	 
	 @RequestMapping(value = "/revisionreportes{search},{type},{indice},{anio},{centro},{ep}",
				method = RequestMethod.GET,
				produces = MediaType.APPLICATION_JSON_VALUE)
		@Timed
		public ResponseEntity<List<ReporteEvaluativo>> getReportesRevision(Pageable pageable,HttpSession httpsession,
				@PathVariable String search,@PathVariable Integer type,@PathVariable Integer indice,@PathVariable Integer anio,@PathVariable Integer centro,@PathVariable boolean ep)
				throws URISyntaxException {
			log.debug("REST request to get a page of Mis Reportes {}",type,search,indice,anio,centro);
			
			Empleado docente = (Empleado)httpsession.getAttribute("Empleado");
			Page<ReporteEvaluativo> page =reporteRepository.getReportesRevision(pageable,indice,anio,centro);// adminEvaluacionDocenteService.findAsignaturaProfesor(pageable,anio,indice,centro,search,ep);
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/apo/reporteEvaluativo");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		} 
	 
}
