package com.micodeya.controlmercaderia.backend.dao.cs;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micodeya.controlmercaderia.backend.entities.cs.Empleado;

import scala.math.BigDecimal;

public interface EmpleadoJpa extends JpaRepository<Empleado, Integer>{

    /** Docs referencia
    * https://www.baeldung.com/spring-data-derived-queries
    * https://www.baeldung.com/spring-data-jpa-query
    */
    
    long countByNumeroDocumento(BigDecimal numeroDocumento);
	long countByIdEmpleadoNotAndNumeroDocumento(Integer idEmpleado, BigDecimal numeroDocumento);
    int countByNumeroDocumento(java.math.BigDecimal numeroDocumento);
    int countByIdEmpleadoNotAndNumeroDocumento(Integer idEmpleado, java.math.BigDecimal numeroDocumento);


    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
