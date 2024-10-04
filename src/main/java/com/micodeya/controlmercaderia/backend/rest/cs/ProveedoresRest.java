package com.micodeya.controlmercaderia.backend.rest.cs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.micodeya.controlmercaderia.backend.dao.cs.ProveedoresDao;
import com.micodeya.controlmercaderia.backend.entities.cs.Proveedores;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dto.PuntoGeoDto;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.JeResponse;
import zzz.com.micodeya.backend.core.util.PaginacionAux;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;
import zzz.com.micodeya.backend.core.util.security.SesionJwt;
import zzz.com.micodeya.backend.core.util.security.UsuarioSesionInterno;

@Slf4j
@RestController
public class ProveedoresRest {

    private final String BASE_API = "/api/cs/proveedores";

    @Autowired
    private ProveedoresDao proveedoresDao;
    
    
    // KGC-NOREPLACE-OTROS-INI

    private void transformacionAdicional(InfoAuditoria infoAuditoria, Proveedores proveedores) {
        boolean modificar = proveedores.getIdProveedores() != null;
    }

    // KGC-NOREPLACE-OTROS-FIN
    
     
    @PostMapping(value = { BASE_API + "/listar",  BASE_API + "/listar/c/{cuentaCore}"})
    public Map<String, Object> listar(
            HttpServletRequest request,
            @PathVariable(required = false) String cuentaCore,
            @RequestBody(required = false) PaginacionAux paginacionAux
            ) {

        Integer[] idRecursoPermisoArray = { 6, 96 };
        JeResponse jeResponse=new JeResponse("Listado correcto de Proveedores","Error grave al listar Proveedores");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            // KGC-NOREPLACE-PRE-LISTAR-INI
            // JeBoot.verificarCuentaNula(cuentaCore, true);
            // userSession.esMismaCuenta(cuentaCore, true);
            // KGC-NOREPLACE-PRE-LISTAR-FIN
            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultadoListar(proveedoresDao.listarAtributosPorPagina(new Proveedores(cuentaCore), paginacionAux.getAtributos(), paginacionAux));
            }
            // KGC-NOREPLACE-POS-LISTAR-INI
            // KGC-NOREPLACE-POS-LISTAR-FIN

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }



    
    @PostMapping( BASE_API + "/agregar" )
    public Map<String, Object> agregar(
                HttpServletRequest request,
				@RequestBody Proveedores proveedores
            ) {

        Integer[] idRecursoPermisoArray = { 97 };
        JeResponse jeResponse=new JeResponse("Proveedores creado correctamente","Error grave al crear Proveedores");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), proveedores);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(proveedoresDao.verificacionBasica(userSession.getInfoAuditoria(), proveedores));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            

            if (jeResponse.sinErrorValidacion()) {
                proveedoresDao.agregar(userSession.getInfoAuditoria(), proveedores);
                jeResponse.putResultado("idProveedores", proveedores.getIdProveedores());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

    
        
    @PostMapping( BASE_API + "/modificar" )
    public Map<String, Object> modificar(
                HttpServletRequest request,
				@RequestBody Proveedores proveedores
            ) {

        Integer[] idRecursoPermisoArray = { 98 };
        JeResponse jeResponse=new JeResponse("Proveedores modificado correctamente","Error grave al modificar Proveedores");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), proveedores);
            /** VERIFICACION BASICA */
            if(proveedores.getIdProveedores()==null) jeResponse.addErrorValidacion("Proveedores sin id para modificar");
            jeResponse.addErrorValidacion(proveedoresDao.verificacionBasica(userSession.getInfoAuditoria(), proveedores));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
            
            if (jeResponse.sinErrorValidacion()) {
                proveedoresDao.modificar(userSession.getInfoAuditoria(), proveedores);
                jeResponse.putResultado("idProveedores", proveedores.getIdProveedores());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

    
       
    @PostMapping( BASE_API + "/eliminar" )
    public Map<String, Object> eliminar(
                HttpServletRequest request,
                @RequestBody Proveedores proveedores
            ) {

        Integer[] idRecursoPermisoArray = { 99 };
        JeResponse jeResponse=new JeResponse("Proveedores eliminado correctamente","Error grave al elimimar Proveedores");
        UsuarioSesionInterno userSession = null;

        try {
            
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {
                proveedoresDao.eliminarPorId(userSession.getInfoAuditoria(), proveedores.getIdProveedores());
                jeResponse.putResultado("idProveedores", proveedores.getIdProveedores());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

   
    
    

    private void transformacionBasica(InfoAuditoria infoAuditoria, Proveedores proveedores) throws JsonMappingException, JsonProcessingException {
        boolean modificar = proveedores.getIdProveedores() != null;



        transformacionAdicional(infoAuditoria, proveedores);

    }


}
