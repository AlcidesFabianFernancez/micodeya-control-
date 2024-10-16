package zzz.com.micodeya.backend.core.dao.zk;

import org.springframework.data.jpa.repository.JpaRepository;

import zzz.com.micodeya.backend.core.entities.zk.NotificacionEnviada;

public interface NotificacionEnviadaJpa extends JpaRepository<NotificacionEnviada, Integer>{

    /** Docs referencia
    * https://www.baeldung.com/spring-data-derived-queries
    * https://www.baeldung.com/spring-data-jpa-query
    */
    
    

    // KGC-NOREPLACE-OTROS-INI

    long countByCuentaAndVisto(String cuenta, Boolean visto);
    // KGC-NOREPLACE-OTROS-FIN

}
