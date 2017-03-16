package com.myapp.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myapp.domain.util.StatusReporte;

import com.myapp.domain.AnioEscolar;
import com.myapp.domain.AsignaturaBase;
import com.myapp.domain.Institucion;
import com.myapp.domain.Persona;
import com.myapp.domain.ReporteEvaluativo;
import com.myapp.domain.ReporteEvaluativoLibre;
import com.myapp.domain.ReporteEvaluativoOyO;
import com.myapp.domain.encuestas.CuestionarioResuelto;
import com.myapp.domain.encuestas.RespuestaPregunta;
import com.myapp.repository.AnioEscolarRepository;
import com.myapp.repository.AsignaturaBaseRepository;
import com.myapp.repository.InstitucionRepository;
import com.myapp.repository.PersonaRepository;
import com.myapp.repository.ReporteEvaluacionDocenteRepository;

@Service
@Transactional
public class ReporteEvaluacionDocenteService {

	private final Logger log = LoggerFactory.getLogger(ReporteEvaluacionDocenteService.class);
	@Inject
	private AsignaturaBaseRepository asignaturaRepository;
	@Inject
	private AnioEscolarRepository anioEscolarRepository;
	@Inject
	private InstitucionRepository institucionRepository;
	@Inject
	private PersonaRepository personaRepository;
	@Inject
	private ReporteEvaluacionDocenteRepository reporteRepository;
	
	@Transactional(readOnly = true) 
	public ReporteEvaluativoOyO generarReporteEvaluativoOO(List<CuestionarioResuelto> cuestionarios,
			Integer idAsignatura,Integer idPersona,Integer idAnio,Integer indice,
			String tipo,Integer idDependencia){
		Iterator<CuestionarioResuelto> iterator = cuestionarios.iterator();
		
		 float numEstudiantes = 0;
		 float muestraRespetoEstudiantes = 0;
		 float congruenciaEstudiantes = 0;
		 float intercambioConocimientoE = 0;
		 float optimizaTiempoEstudiante = 0;
		 float respetuosoOtrosDocentes = 0;
		 float comprometido = 0;
		 float interesAprendizajeEstudiante = 0;
		 float establecePuntajeEstudiante = 0;
		 float informaPuntuacionEstudiante = 0;
		 float actividadesRelacionadoVistoE = 0;
		 float retroalimentoEstudiante = 0;
		 float insatisfechoCompetencias = 0;
		 float satisfechoCompetencias = 0;
		 float muySatisfechoCompetencias = 0;
		 float insatisfechoDesempenio = 0;
		 float satisfechoDesempenio = 0;
		 float muySatisfechoDesempenio = 0;
		 float recomendarProfesorSi = 0;
		 float recomendarProfesorNo = 0;
		 String razonSi = "";
		 String razonNo = "";
		 String fortalezasEstudiantes = "";
		 String sugerenciasEstudiantes = "";
		 String comentariosEstudiantes = "";
		 float guiaAprenderIndependienteE = 0;
		 float eticaEstudiantes = 0;
		 float usoPlataformasEstudiantes = 0;
		 float actividadesComplementariasE = 0;
		 float desarrolloCompetenciasE = 0;
		 float promueveAnalisisEstudiantes = 0;
		 float integraConocimientoEstudiante = 0;
		 float diferentesFormasEvaluacionE = 0;
		 float eticoProfesor;
		 float eticoEstudiantes = 0;
		 float honestoEstudiantes = 0;
		 float responsableSocialEstudiantes = 0;
		 float toleranteEstudiantes = 0;
		 float legalEstudiantes = 0;
		 float respetuosoEstudiantes = 0;
		 float humildeEstudiantes = 0;
		 float equitativoEstudiantes = 0;
		 float rigoerAcademicoEstudiantes = 0;
		ReporteEvaluativoOyO reporte=new ReporteEvaluativoOyO();
		
		while(iterator.hasNext()){
			CuestionarioResuelto cuestionarioresuelto = iterator.next();
			if(cuestionarioresuelto.getPersonaEncuestada().getId().intValue()==idPersona){//profesor
				Iterator<RespuestaPregunta> iteratorrespuestas = cuestionarioresuelto.getRespuestasPregunta().iterator();
				while(iteratorrespuestas.hasNext()){
					RespuestaPregunta respuesta = iteratorrespuestas.next();
					if(respuesta.getPregunta().getId()==54){
						reporte.setMuestraRespetoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==55){
						reporte.setGuiaAprenderIndependienteP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==56){
						reporte.setCongruenciaProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==57){
						reporte.setEticaProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==58){
						reporte.setIntercambioConocimientoP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==59){
						reporte.setOptimizaTiempoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==60){
						reporte.setUsoPlataformasProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==61){
						reporte.setActividadesComplementariasP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==62){
						reporte.setDesarrolloCompetenciasP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==63){
						reporte.setPromueveAnalisisProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==64){
						reporte.setIntegraConocimientoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==65){
						reporte.setInteresAprendizajeProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==66){
						reporte.setEstablecePuntajeProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==67){
						reporte.setInformaPuntuacionProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==68){
						reporte.setActividadesRelacionadoVistoP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==69){
						reporte.setRetroalimentoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==70){
						reporte.setDiferentesFormasEvaluacionP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==71){
						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
						reporte.setEticoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==72){
						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
						reporte.setHonestoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==73){
						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
						reporte.setResponsableSocialProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==74){
						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
						reporte.setToleranteProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==75){
						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
						reporte.setLegalProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==76){
						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
						reporte.setRespetuosoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==77){
						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
						reporte.setHumildeProfesor(porcentaje(eticoProfesor,1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==78){
						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
						reporte.setEquitativoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==79){
						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
						reporte.setRigorAcademicoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
					}
					if(respuesta.getPregunta().getId()==80){
						reporte.setValorCompetenciasProfesor(interpretarSatisfaccion(getValueInteger(respuesta.getRespuestaSeleccionada())));
					}
					if(respuesta.getPregunta().getId()==81){
						reporte.setValorDesempenioProfesor(interpretarSatisfaccion(getValueInteger(respuesta.getRespuestaSeleccionada())));
					}
					if(respuesta.getPregunta().getId()==82){
						reporte.setValorActividadesDocente(interpretarSatisfaccion(getValueInteger(respuesta.getRespuestaSeleccionada())));
					}
					if(respuesta.getPregunta().getId()==83){
						reporte.setFortalezasProfesor(convertStringToBlob(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==84){
						reporte.setSugerenciasProfesor(convertStringToBlob(respuesta.getRespuestaSeleccionada()));
					}
				}
			}else{//alumnos
				numEstudiantes++;
				Iterator<RespuestaPregunta> iteratorrespuestas = cuestionarioresuelto.getRespuestasPregunta().iterator();
				Integer pivoteSINO=0;
				String auxRazon="";
				while(iteratorrespuestas.hasNext()){
					RespuestaPregunta respuesta = iteratorrespuestas.next();
					
					if(respuesta.getPregunta().getId()==1){
						respetuosoOtrosDocentes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==2){
						muestraRespetoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==3){
						guiaAprenderIndependienteE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==4){
						comprometido+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==5){
						congruenciaEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==6){
						eticaEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==7){
						intercambioConocimientoE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==8){
						optimizaTiempoEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==9){
						usoPlataformasEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==10){
						actividadesComplementariasE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==11){
						desarrolloCompetenciasE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==12){
						promueveAnalisisEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==13){
						integraConocimientoEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==14){
						interesAprendizajeEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==15){
						establecePuntajeEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==16){
						informaPuntuacionEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==17){
						actividadesRelacionadoVistoE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==18){
						retroalimentoEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==19){
						diferentesFormasEvaluacionE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==20){
						eticoEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==21){
						honestoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==22){
						responsableSocialEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==23){
						toleranteEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==24){
						legalEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==25){
						respetuosoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==26){
						humildeEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==27){
						equitativoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==28){
						rigoerAcademicoEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==29){
						Integer valor=getValueInteger(respuesta.getRespuestaSeleccionada());
						if(valor==1){
							insatisfechoCompetencias++;
						}
						if(valor==2){
							satisfechoCompetencias++;
						}
						if(valor==3){
							muySatisfechoCompetencias++;
						}
					}
					if(respuesta.getPregunta().getId()==30){
						Integer valor=getValueInteger(respuesta.getRespuestaSeleccionada());
						if(valor==1){
							insatisfechoDesempenio++;
						}
						if(valor==2){
							satisfechoDesempenio++;
						}
						if(valor==3){
							muySatisfechoDesempenio++;
						}
					}
					if(respuesta.getPregunta().getId()==31){
						String valor=getValueString(respuesta.getRespuestaSeleccionada());
						if(valor.equals("Si")||valor.equals("")){
							recomendarProfesorSi++;
							pivoteSINO=1;
						}
						if(valor.equals("No")){
							recomendarProfesorNo++;
							pivoteSINO=2;
						}
					}
					if(respuesta.getPregunta().getId()==32){
						auxRazon=respuesta.getRespuestaSeleccionada();
					}
					if(respuesta.getPregunta().getId()==33){
						fortalezasEstudiantes+=respuesta.getRespuestaSeleccionada()+"\n";
					}
					if(respuesta.getPregunta().getId()==34){
						sugerenciasEstudiantes+=respuesta.getRespuestaSeleccionada()+"\n";
					}
					if(respuesta.getPregunta().getId()==35){
						comentariosEstudiantes+=respuesta.getRespuestaSeleccionada()+"\n";
					}
				}
				if(pivoteSINO==1){
					razonSi+=auxRazon+"\n";
				}else{
					razonNo+=auxRazon+"\n";
				}
			}
		}
		reporte.setNumEstudiantes(numEstudiantes);
		reporte.setMuestraRespetoEstudiantes(porcentaje(muestraRespetoEstudiantes,numEstudiantes,4.0f));
		reporte.setGuiaAprenderIndependienteE(porcentaje(guiaAprenderIndependienteE,numEstudiantes,4.0f));
		reporte.setCongruenciaEstudiantes(porcentaje(congruenciaEstudiantes,numEstudiantes,4.0f));
		reporte.setEticaEstudiantes(porcentaje(eticaEstudiantes,numEstudiantes,4.0f));
		reporte.setIntercambioConocimientoE(porcentaje(intercambioConocimientoE,numEstudiantes,4.0f));
		reporte.setOptimizaTiempoEstudiante(porcentaje(optimizaTiempoEstudiante,numEstudiantes,4.0f));
		reporte.setRespetuosoOtrosDocentes(porcentaje(respetuosoOtrosDocentes,numEstudiantes,4.0f));
		reporte.setComprometido(porcentaje(comprometido,numEstudiantes,4.0f));
		reporte.setUsoPlataformasEstudiantes(porcentaje(usoPlataformasEstudiantes,numEstudiantes,4.0f));
		reporte.setActividadesComplementariasE(porcentaje(actividadesComplementariasE,numEstudiantes,4.0f));
		reporte.setDesarrolloCompetenciasE(porcentaje(desarrolloCompetenciasE,numEstudiantes,4.0f));
		reporte.setPromueveAnalisisEstudiantes(porcentaje(promueveAnalisisEstudiantes,numEstudiantes,4.0f));
		reporte.setIntegraConocimientoEstudiante(porcentaje(integraConocimientoEstudiante,numEstudiantes,4.0f));
		reporte.setInteresAprendizajeEstudiante(porcentaje(interesAprendizajeEstudiante,numEstudiantes,4.0f));
		reporte.setEstablecePuntajeEstudiante(porcentaje(establecePuntajeEstudiante,numEstudiantes,4.0f));
		reporte.setInformaPuntuacionEstudiante(porcentaje(informaPuntuacionEstudiante,numEstudiantes,4.0f));
		reporte.setActividadesRelacionadoVistoE(porcentaje(actividadesRelacionadoVistoE,numEstudiantes,4.0f));
		reporte.setRetroalimentoEstudiante(porcentaje(retroalimentoEstudiante,numEstudiantes,4.0f));
		reporte.setDiferentesFormasEvaluacionE(porcentaje(diferentesFormasEvaluacionE,numEstudiantes,4.0f));
		reporte.setEticoEstudiantes(porcentaje(eticoEstudiantes,numEstudiantes,7.0f));
		reporte.setHonestoEstudiantes(porcentaje(honestoEstudiantes,numEstudiantes,7.0f));
		reporte.setResponsableSocialEstudiantes(porcentaje(responsableSocialEstudiantes,numEstudiantes,7.0f));
		reporte.setToleranteEstudiantes(porcentaje(toleranteEstudiantes,numEstudiantes,7.0f));
		reporte.setLegalEstudiantes(porcentaje(legalEstudiantes,numEstudiantes,7.0f));
		reporte.setRespetuosoEstudiantes(porcentaje(respetuosoEstudiantes,numEstudiantes,7.0f));
		reporte.setHumildeEstudiantes(porcentaje(humildeEstudiantes,numEstudiantes,7.0f));
		reporte.setEquitativoEstudiantes(porcentaje(equitativoEstudiantes,numEstudiantes,7.0f));
		reporte.setRigoerAcademicoEstudiantes(porcentaje(rigoerAcademicoEstudiantes,numEstudiantes,7.0f));
		reporte.setInsatisfechoCompetencias(porcentaje(insatisfechoCompetencias,numEstudiantes,1.0f));
		reporte.setSatisfechoCompetencias(porcentaje(satisfechoCompetencias,numEstudiantes,1.0f));
		reporte.setMuySatisfechoCompetencias(porcentaje(muySatisfechoCompetencias,numEstudiantes,1.0f));
		reporte.setInsatisfechoDesempenio(porcentaje(insatisfechoDesempenio,numEstudiantes,1.0f));
		reporte.setSatisfechoDesempenio(porcentaje(satisfechoDesempenio,numEstudiantes,1.0f));
		reporte.setMuySatisfechoDesempenio(porcentaje(muySatisfechoDesempenio,numEstudiantes,1.0f));
		reporte.setRecomendarProfesorSi(porcentaje(recomendarProfesorSi,numEstudiantes,1.0f));
		reporte.setRecomendarProfesorNo(porcentaje(recomendarProfesorNo,numEstudiantes,1.0f));
		reporte.setRazonSi(convertStringToBlob(razonSi));
		reporte.setRazonNo(convertStringToBlob(razonNo));
		reporte.setFortalezasEstudiantes(convertStringToBlob(fortalezasEstudiantes));
		reporte.setSugerenciasEstudiantes(convertStringToBlob(sugerenciasEstudiantes));
		reporte.setComentariosEstudiantes(convertStringToBlob(comentariosEstudiantes));
		
		AsignaturaBase asignatura = asignaturaRepository.getOne(idAsignatura);
		AnioEscolar anio = anioEscolarRepository.getOne(idAnio);
		Institucion dependencia = institucionRepository.getOne(idDependencia);
		Persona profesor = personaRepository.getOne(idPersona);
		reporte.setStatus(StatusReporte.editar);
		reporte.setProfesor(profesor);
		reporte.setAnioEscolar(anio);
		reporte.setInstitucion(dependencia);
		reporte.setAsignatura(asignatura);
		reporte.setIndicePeriodoEvalaucion(indice);
		reporte.setFormaDeEvaluar(tipo);
		
		return reporte;
		
	}
	
	private Integer invertirValor(Integer valor){
		Integer newValor=0;
		switch(valor){
		case 1: newValor= 7;
		case 2: newValor= 6;
		case 3: newValor= 5;
		case 4: newValor= 4;
		case 5: newValor= 3;
		case 6: newValor= 2;
		case 7: newValor= 1;
		}
		return newValor;
	}
	private String interpretarSatisfaccion(Integer valor){
		String interpretacion="";
		switch(valor){
		case 1: interpretacion= "Insatisfecho";
		case 2: interpretacion= "Satisfecho";
		case 3: interpretacion= "Muy Satisfecho";
		}
		return interpretacion;
	}
	
	private Float porcentaje(Float sumatoria, Float total,Float base){
		if(total>0){
			return (sumatoria/total*100)/base;
		}
		return 0.0f;
	}
	
	private Float getValueFloat(String value){
		return (value==null||value=="")?0.0f:Float.valueOf(value);
	}
	
	private Integer getValueInteger(String value){
		return (value==null||value=="")?0:Integer.valueOf(value);
	}
	private String getValueString(String value){
		return (value==null||value=="")?"":value;
	}
	
	private String convertBlobToString(Blob blob){
		String str="";
		try {
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

	public ReporteEvaluativoLibre generarReporteEvaluativoLI(List<CuestionarioResuelto> cuestionarios,
			Integer idAsignatura, Integer idPersona, Integer idAnio, Integer indice, String tipo,
			Integer idDependencia) {
		
		Iterator<CuestionarioResuelto> iterator = cuestionarios.iterator();
		
		 float numEstudiantes = 0;
		 float muestraRespetoEstudiantes = 0;
		 float congruenciaEstudiantes = 0;
		 float intercambioConocimientoE = 0;
		 float optimizaTiempoEstudiante = 0;
		 float respetuosoOtrosDocentes = 0;
		 float comprometido = 0;
		 float interesAprendizajeEstudiante = 0;
		 float establecePuntajeEstudiante = 0;
		 float informaPuntuacionEstudiante = 0;
		 float actividadesRelacionadoVistoE = 0;
		 float retroalimentoEstudiante = 0;
		 float insatisfechoCompetencias = 0;
		 float satisfechoCompetencias = 0;
		 float muySatisfechoCompetencias = 0;
		 float insatisfechoDesempenio = 0;
		 float satisfechoDesempenio = 0;
		 float muySatisfechoDesempenio = 0;
		 float recomendarProfesorSi = 0;
		 float recomendarProfesorNo = 0;
		 String razonSi = "";
		 String razonNo = "";
		 String fortalezasEstudiantes = "";
		 String sugerenciasEstudiantes = "";
		 String comentariosEstudiantes = "";
//		 float guiaAprenderIndependienteE = 0;
//		 float eticaEstudiantes = 0;
//		 float usoPlataformasEstudiantes = 0;
//		 float actividadesComplementariasE = 0;
//		 float desarrolloCompetenciasE = 0;
//		 float promueveAnalisisEstudiantes = 0;
//		 float integraConocimientoEstudiante = 0;
//		 float diferentesFormasEvaluacionE = 0;
//		 float eticoProfesor;
//		 float eticoEstudiantes = 0;
//		 float honestoEstudiantes = 0;
//		 float responsableSocialEstudiantes = 0;
//		 float toleranteEstudiantes = 0;
//		 float legalEstudiantes = 0;
//		 float respetuosoEstudiantes = 0;
//		 float humildeEstudiantes = 0;
//		 float equitativoEstudiantes = 0;
//		 float rigoerAcademicoEstudiantes = 0;
		 ReporteEvaluativoLibre reporte= new ReporteEvaluativoLibre();
		
		while(iterator.hasNext()){
			CuestionarioResuelto cuestionarioresuelto = iterator.next();
			if(cuestionarioresuelto.getPersonaEncuestada().getId().intValue()==idPersona){//profesor
				Iterator<RespuestaPregunta> iteratorrespuestas = cuestionarioresuelto.getRespuestasPregunta().iterator();
				while(iteratorrespuestas.hasNext()){
					RespuestaPregunta respuesta = iteratorrespuestas.next();
					if(respuesta.getPregunta().getId()==85){
						reporte.setMuestraRespetoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
//					if(respuesta.getPregunta().getId()==55){
//						reporte.setGuiaAprenderIndependienteP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
					if(respuesta.getPregunta().getId()==86){
						reporte.setCongruenciaProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
//					if(respuesta.getPregunta().getId()==57){
//						reporte.setEticaProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
					if(respuesta.getPregunta().getId()==87){
						reporte.setIntercambioConocimientoP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==88){
						reporte.setOptimizaTiempoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
//					if(respuesta.getPregunta().getId()==60){
//						reporte.setUsoPlataformasProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
//					if(respuesta.getPregunta().getId()==61){
//						reporte.setActividadesComplementariasP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
//					if(respuesta.getPregunta().getId()==62){
//						reporte.setDesarrolloCompetenciasP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
//					if(respuesta.getPregunta().getId()==63){
//						reporte.setPromueveAnalisisProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
//					if(respuesta.getPregunta().getId()==64){
//						reporte.setIntegraConocimientoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
					if(respuesta.getPregunta().getId()==89){
						reporte.setInteresAprendizajeProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==90){
						reporte.setEstablecePuntajeProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==91){
						reporte.setInformaPuntuacionProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==92){
						reporte.setActividadesRelacionadoVistoP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
					if(respuesta.getPregunta().getId()==93){
						reporte.setRetroalimentoProfesor(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
					}
//					if(respuesta.getPregunta().getId()==70){
//						reporte.setDiferentesFormasEvaluacionP(porcentaje(getValueFloat(respuesta.getRespuestaSeleccionada()),1.0f,4.0f));
//					}
//					if(respuesta.getPregunta().getId()==71){
//						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//						reporte.setEticoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==72){
//						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
//						reporte.setHonestoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==73){
//						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//						reporte.setResponsableSocialProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==74){
//						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
//						reporte.setToleranteProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==75){
//						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//						reporte.setLegalProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==76){
//						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
//						reporte.setRespetuosoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==77){
//						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//						reporte.setHumildeProfesor(porcentaje(eticoProfesor,1.0f,4.0f));
//					}
//					if(respuesta.getPregunta().getId()==78){
//						eticoProfesor=getValueFloat(respuesta.getRespuestaSeleccionada());
//						reporte.setEquitativoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
//					if(respuesta.getPregunta().getId()==79){
//						eticoProfesor=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//						reporte.setRigorAcademicoProfesor(porcentaje(eticoProfesor,1.0f,7.0f));
//					}
					if(respuesta.getPregunta().getId()==94){
						reporte.setValorCompetenciasProfesor(interpretarSatisfaccion(getValueInteger(respuesta.getRespuestaSeleccionada())));
					}
					if(respuesta.getPregunta().getId()==95){
						reporte.setValorDesempenioProfesor(interpretarSatisfaccion(getValueInteger(respuesta.getRespuestaSeleccionada())));
					}
//					if(respuesta.getPregunta().getId()==82){
//						reporte.setValorActividadesDocente(interpretarSatisfaccion(getValueInteger(respuesta.getRespuestaSeleccionada())));
//					}
					if(respuesta.getPregunta().getId()==96){
						reporte.setFortalezasProfesor(convertStringToBlob(respuesta.getRespuestaSeleccionada()));
					}
					if(respuesta.getPregunta().getId()==97){
						reporte.setSugerenciasProfesor(convertStringToBlob(respuesta.getRespuestaSeleccionada()));
					}
				}
			}else{//alumnos
				numEstudiantes++;
				Iterator<RespuestaPregunta> iteratorrespuestas = cuestionarioresuelto.getRespuestasPregunta().iterator();
				Integer pivoteSINO=0;
				String auxRazon="";
				while(iteratorrespuestas.hasNext()){
					RespuestaPregunta respuesta = iteratorrespuestas.next();
					
					if(respuesta.getPregunta().getId()==36){
						respetuosoOtrosDocentes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==37){
						muestraRespetoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
//					if(respuesta.getPregunta().getId()==3){
//						guiaAprenderIndependienteE+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
					if(respuesta.getPregunta().getId()==38){
						comprometido+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==39){
						congruenciaEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
//					if(respuesta.getPregunta().getId()==6){
//						eticaEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
					if(respuesta.getPregunta().getId()==40){
						intercambioConocimientoE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==41){
						optimizaTiempoEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
//					if(respuesta.getPregunta().getId()==9){
//						usoPlataformasEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==10){
//						actividadesComplementariasE+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==11){
//						desarrolloCompetenciasE+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==12){
//						promueveAnalisisEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==13){
//						integraConocimientoEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
					if(respuesta.getPregunta().getId()==42){
						interesAprendizajeEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==43){
						establecePuntajeEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==44){
						informaPuntuacionEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==45){
						actividadesRelacionadoVistoE+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
					if(respuesta.getPregunta().getId()==46){
						retroalimentoEstudiante+=getValueFloat(respuesta.getRespuestaSeleccionada());
					}
//					if(respuesta.getPregunta().getId()==19){
//						diferentesFormasEvaluacionE+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==20){
//						eticoEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//					}
//					if(respuesta.getPregunta().getId()==21){
//						honestoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==22){
//						responsableSocialEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//					}
//					if(respuesta.getPregunta().getId()==23){
//						toleranteEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==24){
//						legalEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//					}
//					if(respuesta.getPregunta().getId()==25){
//						respetuosoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==26){
//						humildeEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//					}
//					if(respuesta.getPregunta().getId()==27){
//						equitativoEstudiantes+=getValueFloat(respuesta.getRespuestaSeleccionada());
//					}
//					if(respuesta.getPregunta().getId()==28){
//						rigoerAcademicoEstudiantes+=invertirValor(getValueInteger(respuesta.getRespuestaSeleccionada()));
//					}
					if(respuesta.getPregunta().getId()==47){
						Integer valor=getValueInteger(respuesta.getRespuestaSeleccionada());
						if(valor==1){
							insatisfechoCompetencias++;
						}
						if(valor==2){
							satisfechoCompetencias++;
						}
						if(valor==3){
							muySatisfechoCompetencias++;
						}
					}
					if(respuesta.getPregunta().getId()==48){
						Integer valor=getValueInteger(respuesta.getRespuestaSeleccionada());
						if(valor==1){
							insatisfechoDesempenio++;
						}
						if(valor==2){
							satisfechoDesempenio++;
						}
						if(valor==3){
							muySatisfechoDesempenio++;
						}
					}
					if(respuesta.getPregunta().getId()==49){
						String valor=getValueString(respuesta.getRespuestaSeleccionada());
						if(valor.equals("Si")||valor.equals("")){
							recomendarProfesorSi++;
							pivoteSINO=1;
						}
						if(valor.equals("No")){
							recomendarProfesorNo++;
							pivoteSINO=2;
						}
					}
					if(respuesta.getPregunta().getId()==50){
						auxRazon=respuesta.getRespuestaSeleccionada();
					}
					if(respuesta.getPregunta().getId()==51){
						fortalezasEstudiantes+=respuesta.getRespuestaSeleccionada()+"\n";
					}
					if(respuesta.getPregunta().getId()==52){
						sugerenciasEstudiantes+=respuesta.getRespuestaSeleccionada()+"\n";
					}
					if(respuesta.getPregunta().getId()==53){
						comentariosEstudiantes+=respuesta.getRespuestaSeleccionada()+"\n";
					}
				}
				if(pivoteSINO==1){
					razonSi+=auxRazon+"\n";
				}else{
					razonNo+=auxRazon+"\n";
				}
			}
		}
		reporte.setNumEstudiantes(numEstudiantes);
		reporte.setMuestraRespetoEstudiantes(porcentaje(muestraRespetoEstudiantes,numEstudiantes,4.0f));
		//reporte.setGuiaAprenderIndependienteE(porcentaje(guiaAprenderIndependienteE,numEstudiantes,4.0f));
		reporte.setCongruenciaEstudiantes(porcentaje(congruenciaEstudiantes,numEstudiantes,4.0f));
		//reporte.setEticaEstudiantes(porcentaje(eticaEstudiantes,numEstudiantes,4.0f));
		reporte.setIntercambioConocimientoE(porcentaje(intercambioConocimientoE,numEstudiantes,4.0f));
		reporte.setOptimizaTiempoEstudiante(porcentaje(optimizaTiempoEstudiante,numEstudiantes,4.0f));
		reporte.setRespetuosoOtrosDocentes(porcentaje(respetuosoOtrosDocentes,numEstudiantes,4.0f));
		reporte.setComprometido(porcentaje(comprometido,numEstudiantes,4.0f));
//		reporte.setUsoPlataformasEstudiantes(porcentaje(usoPlataformasEstudiantes,numEstudiantes,4.0f));
//		reporte.setActividadesComplementariasE(porcentaje(actividadesComplementariasE,numEstudiantes,4.0f));
//		reporte.setDesarrolloCompetenciasE(porcentaje(desarrolloCompetenciasE,numEstudiantes,4.0f));
//		reporte.setPromueveAnalisisEstudiantes(porcentaje(promueveAnalisisEstudiantes,numEstudiantes,4.0f));
//		reporte.setIntegraConocimientoEstudiante(porcentaje(integraConocimientoEstudiante,numEstudiantes,4.0f));
		reporte.setInteresAprendizajeEstudiante(porcentaje(interesAprendizajeEstudiante,numEstudiantes,4.0f));
		reporte.setEstablecePuntajeEstudiante(porcentaje(establecePuntajeEstudiante,numEstudiantes,4.0f));
		reporte.setInformaPuntuacionEstudiante(porcentaje(informaPuntuacionEstudiante,numEstudiantes,4.0f));
		reporte.setActividadesRelacionadoVistoE(porcentaje(actividadesRelacionadoVistoE,numEstudiantes,4.0f));
		reporte.setRetroalimentoEstudiante(porcentaje(retroalimentoEstudiante,numEstudiantes,4.0f));
//		reporte.setDiferentesFormasEvaluacionE(porcentaje(diferentesFormasEvaluacionE,numEstudiantes,4.0f));
//		reporte.setEticoEstudiantes(porcentaje(eticoEstudiantes,numEstudiantes,7.0f));
//		reporte.setHonestoEstudiantes(porcentaje(honestoEstudiantes,numEstudiantes,7.0f));
//		reporte.setResponsableSocialEstudiantes(porcentaje(responsableSocialEstudiantes,numEstudiantes,7.0f));
//		reporte.setToleranteEstudiantes(porcentaje(toleranteEstudiantes,numEstudiantes,7.0f));
//		reporte.setLegalEstudiantes(porcentaje(legalEstudiantes,numEstudiantes,7.0f));
//		reporte.setRespetuosoEstudiantes(porcentaje(respetuosoEstudiantes,numEstudiantes,7.0f));
//		reporte.setHumildeEstudiantes(porcentaje(humildeEstudiantes,numEstudiantes,7.0f));
//		reporte.setEquitativoEstudiantes(porcentaje(equitativoEstudiantes,numEstudiantes,7.0f));
//		reporte.setRigoerAcademicoEstudiantes(porcentaje(rigoerAcademicoEstudiantes,numEstudiantes,7.0f));
		reporte.setInsatisfechoCompetencias(porcentaje(insatisfechoCompetencias,numEstudiantes,1.0f));
		reporte.setSatisfechoCompetencias(porcentaje(satisfechoCompetencias,numEstudiantes,1.0f));
		reporte.setMuySatisfechoCompetencias(porcentaje(muySatisfechoCompetencias,numEstudiantes,1.0f));
		reporte.setInsatisfechoDesempenio(porcentaje(insatisfechoDesempenio,numEstudiantes,1.0f));
		reporte.setSatisfechoDesempenio(porcentaje(satisfechoDesempenio,numEstudiantes,1.0f));
		reporte.setMuySatisfechoDesempenio(porcentaje(muySatisfechoDesempenio,numEstudiantes,1.0f));
		reporte.setRecomendarProfesorSi(porcentaje(recomendarProfesorSi,numEstudiantes,1.0f));
		reporte.setRecomendarProfesorNo(porcentaje(recomendarProfesorNo,numEstudiantes,1.0f));
		reporte.setRazonSi(convertStringToBlob(razonSi));
		reporte.setRazonNo(convertStringToBlob(razonNo));
		reporte.setFortalezasEstudiantes(convertStringToBlob(fortalezasEstudiantes));
		reporte.setSugerenciasEstudiantes(convertStringToBlob(sugerenciasEstudiantes));
		reporte.setComentariosEstudiantes(convertStringToBlob(comentariosEstudiantes));
		
		AsignaturaBase asignatura = asignaturaRepository.getOne(idAsignatura);
		System.out.println(asignatura.getNombre());
		AnioEscolar anio = anioEscolarRepository.getOne(idAnio);
		System.out.println(anio.getDescripcion());
		Institucion dependencia = institucionRepository.getOne(idDependencia);
		System.out.println(dependencia.getNombre());
		Persona profesor = personaRepository.getOne(idPersona);
		System.out.println(profesor.getNombres());
		reporte.setStatus(StatusReporte.editar);
		reporte.setProfesor(profesor);
		reporte.setAnioEscolar(anio);
		reporte.setInstitucion(dependencia);
		reporte.setAsignatura(asignatura);
		reporte.setIndicePeriodoEvalaucion(indice);
		reporte.setFormaDeEvaluar(tipo);
		
		return reporte;
	}
	
	
	public ReporteEvaluativo findReporte(Integer idAsignatura, Integer idPersona, Integer idDependencia, Integer idAnio, Integer idIndice, String tipoEvaluacion){
		if(tipoEvaluacion.equals("Obligatoria")||tipoEvaluacion.equals("Optativa"))
		return reporteRepository.getReporteOO(idAsignatura, idPersona, idDependencia, idAnio, idIndice, tipoEvaluacion);
		if(tipoEvaluacion.equals("Libre"))
			return reporteRepository.getReporteLI(idAsignatura, idPersona, idDependencia, idAnio, idIndice, tipoEvaluacion);
		return null;
	}
}
