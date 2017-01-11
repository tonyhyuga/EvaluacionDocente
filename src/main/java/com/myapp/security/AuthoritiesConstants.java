package com.myapp.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";
    
    public static final String ALUMNO = "ALUMNO";
    public static final String PROFESOR = "PROFESOR";
    public static final String GESTOR_ACADEMICO = "GESTOR_ACADEMICO";
    public static final String ATI = "ATI_EVALUACION_DOCENTE";
    public static final String ADMINISTRADOR_EVALUACION = "ADMTVO_EVALUACION_DOCENTE";
    
    
    private AuthoritiesConstants() {
    }
}
