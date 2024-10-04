package com.micodeya.controlmercaderia.backend.rest.cs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.micodeya.controlmercaderia.backend.dao.cs.CargoDao;
import com.micodeya.controlmercaderia.backend.entities.cs.Cargo;
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
public class CargoRest {

    private final String BASE_API = "/api/cs/cargo";

    @Autowired
    private CargoDao cargoDao;
    
    
    // KGC-NOREPLACE-OTROS-INI

    private void transformacionAdicional(InfoAuditoria infoAuditoria, Cargo cargo) {
        boolean modificar = cargo.getIdCargo() != null;
    }

    // KGC-NOREPLACE-OTROS-FIN
    
     
    @PostMapping(value = { BASE_API + "/listar",  BASE_API + "/listar/c/{cuentaCore}"})
    public Map<String, Object> listar(
            HttpServletRequest request,
            @PathVariable(required = false) String cuentaCore,
            @RequestBody(required = false) PaginacionAux paginacionAux
            ) {

        Integer[] idRecursoPermisoArray = { 6, 96 };
        JeResponse jeResponse=new JeResponse("Listado correcto de Cargo","Error grave al listar Cargo");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            // KGC-NOREPLACE-PRE-LISTAR-INI
            // JeBoot.verificarCuentaNula(cuentaCore, true);
            // userSession.esMismaCuenta(cuentaCore, true);
            // KGC-NOREPLACE-PRE-LISTAR-FIN
            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultadoListar(cargoDao.listarAtributosPorPagina(new Cargo(cuentaCore), paginacionAux.getAtributos(), paginacionAux));
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
				@RequestBody Cargo cargo
            ) {

        Integer[] idRecursoPermisoArray = { 97 };
        JeResponse jeResponse=new JeResponse("Cargo creado correctamente","Error grave al crear Cargo");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), cargo);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(cargoDao.verificacionBasica(userSession.getInfoAuditoria(), cargo));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            

            if (jeResponse.sinErrorValidacion()) {
                cargoDao.agregar(userSession.getInfoAuditoria(), cargo);
                jeResponse.putResultado("idCargo", cargo.getIdCargo());
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
				@RequestBody Cargo cargo
            ) {

        Integer[] idRecursoPermisoArray = { 98 };
        JeResponse jeResponse=new JeResponse("Cargo modificado correctamente","Error grave al modificar Cargo");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), cargo);
            /** VERIFICACION BASICA */
            if(cargo.getIdCargo()==null) jeResponse.addErrorValidacion("Cargo sin id para modificar");
            jeResponse.addErrorValidacion(cargoDao.verificacionBasica(userSession.getInfoAuditoria(), cargo));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
            
            if (jeResponse.sinErrorValidacion()) {
                cargoDao.modificar(userSession.getInfoAuditoria(), cargo);
                jeResponse.putResultado("idCargo", cargo.getIdCargo());
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
                @RequestBody Cargo cargo
            ) {

        Integer[] idRecursoPermisoArray = { 99 };
        JeResponse jeResponse=new JeResponse("Cargo eliminado correctamente","Error grave al elimimar Cargo");
        UsuarioSesionInterno userSession = null;

        try {
            
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {
                cargoDao.eliminarPorId(userSession.getInfoAuditoria(), cargo.getIdCargo());
                jeResponse.putResultado("idCargo", cargo.getIdCargo());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

   
    
    

    private void transformacionBasica(InfoAuditoria infoAuditoria, Cargo cargo) throws JsonMappingException, JsonProcessingException {
        boolean modificar = cargo.getIdCargo() != null;



        transformacionAdicional(infoAuditoria, cargo);

    }


}
