package com.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy=InheritanceType.JOINED)
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "activo")
    private String activo;

    @OneToMany(mappedBy = "usuario")
    private Set<Perfil> perfiles;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public Usuario login(String login) {
        this.login = login;
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public Usuario password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivo() {
        return activo;
    }

    public Usuario activo(String activo) {
        this.activo = activo;
        return this;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    

    public Set<Perfil> getPerfiles() {
		return perfiles;
	}
    public Set<Perfil> getPerfilesActivos() {
    	Set<Perfil> perfilesActivos =new HashSet<Perfil>();
    	Iterator<Perfil> itert = perfiles.iterator();
    	while(itert.hasNext()){
    		Perfil perfil=itert.next();
    		if(perfil.isActivo()){
    			perfilesActivos.add(perfil);
    		}
    	}
		return perfilesActivos;
	}

	public void setPerfiles(Set<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        if(usuario.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", login='" + login + "'" +
            ", password='" + password + "'" +
            ", activo='" + activo + "'" +
            '}';
    }
}
