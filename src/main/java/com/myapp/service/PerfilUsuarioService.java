package com.myapp.service;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.domain.Institucion;
import com.myapp.domain.Perfil;
import com.myapp.domain.Persona;
import com.myapp.domain.Rol;
import com.myapp.domain.Usuario;
import com.myapp.domain.UsuarioAlumno;
import com.myapp.domain.UsuarioEmpleado;
import com.myapp.domain.wrapper.UsuarioPerfilWrapper;
import com.myapp.repository.PerfilRepository;
import com.myapp.repository.UsuarioRepository;

@Service
@Transactional
public class PerfilUsuarioService {
	private final Logger log = LoggerFactory.getLogger(PerfilUsuarioService.class);
	
	@Inject
	private PerfilRepository perfilRepository;
	
	@Inject
	private UsuarioRepository usuarioRepository;
	
	@PersistenceContext
    private EntityManager manager;
	
	
	@Transactional(readOnly = true) 
	public Page<UsuarioPerfilWrapper> getPerfiles(Pageable pageable, Integer idperfil){
		Page<Perfil> perfiles = perfilRepository.getPerfilesPageable(pageable,idperfil);
		List<UsuarioPerfilWrapper> wrappers= new ArrayList<UsuarioPerfilWrapper>();
		Iterator<Perfil> perfilesIte = perfiles.getContent().iterator();
		int id=0;
		while(perfilesIte.hasNext()){
			id++;
			Perfil perfil=perfilesIte.next();
			Persona p=null;
			if(perfil.getUsuario() instanceof UsuarioEmpleado){
				p=((UsuarioEmpleado)perfil.getUsuario()).getEmpleado().getPersona();
			}
			if(perfil.getUsuario() instanceof UsuarioAlumno){
				p=((UsuarioAlumno)perfil.getUsuario()).getAlumnoUADYMatriculado().getPersona();
			}
			UsuarioPerfilWrapper wrap= new UsuarioPerfilWrapper(id,perfil,p);
			wrap.setLogin(perfil.getUsuario().getLogin());
			wrappers.add(wrap);
		}
		Page<UsuarioPerfilWrapper> page = new PageImpl<UsuarioPerfilWrapper>(wrappers, pageable,perfiles.getTotalElements());
		return page;
	}
	
	@Transactional(readOnly = true) 
	public UsuarioEmpleado getUsuarioEmpleadobyPersona( Integer idPersona){
		return usuarioRepository.getUsuarioEmpleadoByPersona(idPersona);
	}
	
	
	public Perfil crearPerfil(Usuario usuario,Institucion institucion, Integer idRol){
		Rol rol=usuarioRepository.getRol(idRol);
		Perfil perfil= new Perfil();
		perfil.setActivo(true);
		perfil.setInstitucion(institucion);
		perfil.setUsuario(usuario);
		perfil.setRol(rol);
		return perfilRepository.save(perfil);
	}
	
	public boolean validarNuevoPerfil(Usuario usuario,Institucion institucion, Integer idRol){
		
		Perfil perfil=perfilRepository.validarUsuario(usuario.getId(),institucion.getId(),idRol);
		
		return perfil==null?true:false;
	}
	
	
	@Transactional(readOnly = false) 
	public void delete(Integer id) {
		
		manager.createNativeQuery("delete from liga_perfil_permisos where idperfil=:id ").setParameter("id",id).executeUpdate();
		manager.flush();
		perfilRepository.delete(id);
		
	}

	public Perfil findOne(Integer id) {
		// TODO Auto-generated method stub
		return perfilRepository.findOne(id);
	}
}
