package com.micodeya.controlmercaderia.backend.dao.cs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micodeya.controlmercaderia.backend.entities.cs.Utilizador;

public interface UtilizadorJpa extends JpaRepository<Utilizador, Integer>{

    /** Docs referencia
    * https://www.baeldung.com/spring-data-derived-queries
    * https://www.baeldung.com/spring-data-jpa-query
    */
    
    

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
