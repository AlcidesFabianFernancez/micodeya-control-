package com.micodeya.controlmercaderia.backend.dao.cs;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micodeya.controlmercaderia.backend.entities.cs.Empleado;

public interface EmpleadoJpa extends JpaRepository<Empleado, Integer>{

    /** Docs referencia
    * https://www.baeldung.com/spring-data-derived-queries
    * https://www.baeldung.com/spring-data-jpa-query
    */
    
    long countByNumeroDocumento(BigDecimal numeroDocumento);
	long countByIdEmpleadoNotAndNumeroDocumento(Integer idEmpleado, BigDecimal numeroDocumento);


    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
