package com.myapp.domain.wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.myapp.domain.encuestas.CuestionarioResuelto;

public class CuestionarioResueltoWrapper {

	private CuestionarioResuelto cuestionarioResuelto;
	private List<GrupoPreguntasWrapper> grupoPreguntasWrapper;
	
	public CuestionarioResuelto getCuestionarioResuelto() {
		return cuestionarioResuelto;
	}
	public void setCuestionarioResuelto(CuestionarioResuelto cuestionarioResuelto) {
		this.cuestionarioResuelto = cuestionarioResuelto;
	}
	
	public List<GrupoPreguntasWrapper> getGrupoPreguntasWrapper() {
		return grupoPreguntasWrapper;
	}
	public void setGrupoPreguntasWrapper(List<GrupoPreguntasWrapper> grupoPreguntasWrapper) {
		this.grupoPreguntasWrapper = grupoPreguntasWrapper;
	}
	
	public List<GrupoPreguntasWrapper> getGrupoPreguntasForUI() {
		List<GrupoPreguntasWrapper> gs = new ArrayList<GrupoPreguntasWrapper>(grupoPreguntasWrapper);
		
		Collections.sort(gs, new Comparator<GrupoPreguntasWrapper>() {

			@Override
			public int compare(GrupoPreguntasWrapper o1, GrupoPreguntasWrapper o2) {
				return o1.grupoPreguntas.getOrden().compareTo(o2.grupoPreguntas.getOrden());
			}
		});
		
		return gs;
	}	
}
