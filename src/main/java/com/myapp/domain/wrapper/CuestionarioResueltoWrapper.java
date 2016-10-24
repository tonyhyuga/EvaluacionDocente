package com.myapp.domain.wrapper;

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
}
