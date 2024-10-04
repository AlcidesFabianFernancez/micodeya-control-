package com.micodeya.controlmercaderia.backend.rest.cs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.micodeya.controlmercaderia.backend.dao.cs.MovimientoStockDao;
import com.micodeya.controlmercaderia.backend.entities.cs.MovimientoStock;
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
public class MovimientoStockRest {

    private final String BASE_API = "/api/cs/movimientoStock";

    @Autowired
    private MovimientoStockDao movimientoStockDao;
    
    
    // KGC-NOREPLACE-OTROS-INI

    private void transformacionAdicional(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock) {
        boolean modificar = movimientoStock.getIdMovimientoStock() != null;
    }

    // KGC-NOREPLACE-OTROS-FIN
    
     
    @PostMapping(value = { BASE_API + "/listar",  BASE_API + "/listar/c/{cuentaCore}"})
    public Map<String, Object> listar(
            HttpServletRequest request,
            @PathVariable(required = false) String cuentaCore,
            @RequestBody(required = false) PaginacionAux paginacionAux
            ) {

        Integer[] idRecursoPermisoArray = { 6, 96 };
        JeResponse jeResponse=new JeResponse("Listado correcto de Movimiento Stock","Error grave al listar Movimiento Stock");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            // KGC-NOREPLACE-PRE-LISTAR-INI
            // JeBoot.verificarCuentaNula(cuentaCore, true);
            // userSession.esMismaCuenta(cuentaCore, true);
            // KGC-NOREPLACE-PRE-LISTAR-FIN
            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultadoListar(movimientoStockDao.listarAtributosPorPagina(new MovimientoStock(cuentaCore), paginacionAux.getAtributos(), paginacionAux));
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
				@RequestBody MovimientoStock movimientoStock
            ) {

        Integer[] idRecursoPermisoArray = { 97 };
        JeResponse jeResponse=new JeResponse("Movimiento Stock creado correctamente","Error grave al crear Movimiento Stock");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), movimientoStock);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(movimientoStockDao.verificacionBasica(userSession.getInfoAuditoria(), movimientoStock));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            

            if (jeResponse.sinErrorValidacion()) {
                movimientoStockDao.agregar(userSession.getInfoAuditoria(), movimientoStock);
                jeResponse.putResultado("idMovimientoStock", movimientoStock.getIdMovimientoStock());
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
				@RequestBody MovimientoStock movimientoStock
            ) {

        Integer[] idRecursoPermisoArray = { 98 };
        JeResponse jeResponse=new JeResponse("Movimiento Stock modificado correctamente","Error grave al modificar Movimiento Stock");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), movimientoStock);
            /** VERIFICACION BASICA */
            if(movimientoStock.getIdMovimientoStock()==null) jeResponse.addErrorValidacion("Movimiento Stock sin id para modificar");
            jeResponse.addErrorValidacion(movimientoStockDao.verificacionBasica(userSession.getInfoAuditoria(), movimientoStock));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
            
            if (jeResponse.sinErrorValidacion()) {
                movimientoStockDao.modificar(userSession.getInfoAuditoria(), movimientoStock);
                jeResponse.putResultado("idMovimientoStock", movimientoStock.getIdMovimientoStock());
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
                @RequestBody MovimientoStock movimientoStock
            ) {

        Integer[] idRecursoPermisoArray = { 99 };
        JeResponse jeResponse=new JeResponse("Movimiento Stock eliminado correctamente","Error grave al elimimar Movimiento Stock");
        UsuarioSesionInterno userSession = null;

        try {
            
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {
                movimientoStockDao.eliminarPorId(userSession.getInfoAuditoria(), movimientoStock.getIdMovimientoStock());
                jeResponse.putResultado("idMovimientoStock", movimientoStock.getIdMovimientoStock());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

   
    
    

    private void transformacionBasica(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock) throws JsonMappingException, JsonProcessingException {
        boolean modificar = movimientoStock.getIdMovimientoStock() != null;



        transformacionAdicional(infoAuditoria, movimientoStock);

    }


}
