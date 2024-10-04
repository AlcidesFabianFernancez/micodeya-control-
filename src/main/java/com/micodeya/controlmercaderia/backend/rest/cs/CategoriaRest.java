package com.micodeya.controlmercaderia.backend.rest.cs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.micodeya.controlmercaderia.backend.dao.cs.CategoriaDao;
import com.micodeya.controlmercaderia.backend.entities.cs.Categoria;
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
public class CategoriaRest {

    private final String BASE_API = "/api/cs/categoria";

    @Autowired
    private CategoriaDao categoriaDao;
    
    
    // KGC-NOREPLACE-OTROS-INI

    private void transformacionAdicional(InfoAuditoria infoAuditoria, Categoria categoria) {
        boolean modificar = categoria.getIdCategoria() != null;
    }

    // KGC-NOREPLACE-OTROS-FIN
    
     
    @PostMapping(value = { BASE_API + "/listar",  BASE_API + "/listar/c/{cuentaCore}"})
    public Map<String, Object> listar(
            HttpServletRequest request,
            @PathVariable(required = false) String cuentaCore,
            @RequestBody(required = false) PaginacionAux paginacionAux
            ) {

        Integer[] idRecursoPermisoArray = { 6, 96 };
        JeResponse jeResponse=new JeResponse("Listado correcto de Categoria","Error grave al listar Categoria");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            // KGC-NOREPLACE-PRE-LISTAR-INI
            // JeBoot.verificarCuentaNula(cuentaCore, true);
            // userSession.esMismaCuenta(cuentaCore, true);
            // KGC-NOREPLACE-PRE-LISTAR-FIN
            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultadoListar(categoriaDao.listarAtributosPorPagina(new Categoria(cuentaCore), paginacionAux.getAtributos(), paginacionAux));
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
				@RequestBody Categoria categoria
            ) {

        Integer[] idRecursoPermisoArray = { 97 };
        JeResponse jeResponse=new JeResponse("Categoria creada correctamente","Error grave al crear Categoria");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), categoria);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(categoriaDao.verificacionBasica(userSession.getInfoAuditoria(), categoria));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            

            if (jeResponse.sinErrorValidacion()) {
                categoriaDao.agregar(userSession.getInfoAuditoria(), categoria);
                jeResponse.putResultado("idCategoria", categoria.getIdCategoria());
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
				@RequestBody Categoria categoria
            ) {

        Integer[] idRecursoPermisoArray = { 98 };
        JeResponse jeResponse=new JeResponse("Categoria modificada correctamente","Error grave al modificar Categoria");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), categoria);
            /** VERIFICACION BASICA */
            if(categoria.getIdCategoria()==null) jeResponse.addErrorValidacion("Categoria sin id para modificar");
            jeResponse.addErrorValidacion(categoriaDao.verificacionBasica(userSession.getInfoAuditoria(), categoria));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
            
            if (jeResponse.sinErrorValidacion()) {
                categoriaDao.modificar(userSession.getInfoAuditoria(), categoria);
                jeResponse.putResultado("idCategoria", categoria.getIdCategoria());
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
                @RequestBody Categoria categoria
            ) {

        Integer[] idRecursoPermisoArray = { 99 };
        JeResponse jeResponse=new JeResponse("Categoria eliminada correctamente","Error grave al elimimar Categoria");
        UsuarioSesionInterno userSession = null;

        try {
            
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {
                categoriaDao.eliminarPorId(userSession.getInfoAuditoria(), categoria.getIdCategoria());
                jeResponse.putResultado("idCategoria", categoria.getIdCategoria());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

   
    
    

    private void transformacionBasica(InfoAuditoria infoAuditoria, Categoria categoria) throws JsonMappingException, JsonProcessingException {
        boolean modificar = categoria.getIdCategoria() != null;



        transformacionAdicional(infoAuditoria, categoria);

    }


}
