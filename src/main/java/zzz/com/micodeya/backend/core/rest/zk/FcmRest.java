package zzz.com.micodeya.backend.core.rest.zk;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

import zzz.com.micodeya.backend.core.util.PaginacionAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.JeResponse;
import zzz.com.micodeya.backend.core.util.security.UsuarioSesionInterno;
import zzz.com.micodeya.backend.core.util.security.SesionJwt;

import zzz.com.micodeya.backend.core.entities.zk.Rol;
import zzz.com.micodeya.backend.core.service.fcm.FirebaseMessagingService;
import zzz.com.micodeya.backend.core.service.fcm.dto.SendOneFcmDto;
import zzz.com.micodeya.backend.core.dao.zk.RolDao;


@Slf4j
@RestController
@RequestMapping("/api/zk/fcm")
public class FcmRest {

    @Autowired
    private RolDao rolDao;

    @Autowired
    private FirebaseMessagingService firebaseService;


    @PostMapping("/toToken")
    public Map<String, Object> toToken(@RequestBody SendOneFcmDto note) throws FirebaseMessagingException {

        // Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("PUSH enviado correctamente",
                "Error grave al enviar PUSH");
        // UsuarioSesionInterno userSession = null;

        try {

            // userSession = SesionMap.getUserSesion(request, idRecursoPermisoArray);

            /*** AREA VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultado("messageId", firebaseService.sendOneNotification(note));
            }

            /* AUTOGENERADO _KWF_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

     
 	@PostMapping(value = {"/listar"})
    public Map<String, Object> listar(
            HttpServletRequest request,
            @RequestBody(required = false) PaginacionAux paginacionAux
            ) {

        Integer[] idRecursoPermisoArray = { 16 };
        JeResponse jeResponse=new JeResponse("Listado correcto de Rol","Error grave al listar Rol");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultadoListar(rolDao.listarAtributosPorPagina(new Rol(), paginacionAux.getAtributos(), paginacionAux));
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }


        return jeResponse.getRetornoMap();
    }


    
    @PostMapping("/agregar")
    public Map<String, Object> agregar(
            HttpServletRequest request,
            @RequestBody Rol rol
            ) {


        Integer[] idRecursoPermisoArray = { 17 };
        JeResponse jeResponse=new JeResponse("Rol creado correctamente","Error grave al crear Rol");
        UsuarioSesionInterno userSession = null;
        


        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(rolDao.verificacionBasica(userSession.getInfoAuditoria(), rol));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            

            if (jeResponse.sinErrorValidacion()) {

                

                jeResponse.putResultado("idRol", rolDao.agregar(userSession.getInfoAuditoria(), rol).getIdRol());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }


        return jeResponse.getRetornoMap();
    }

    
        
    @PostMapping("/modificar")
    public Map<String, Object> modificar(
                HttpServletRequest request,
                @RequestBody Rol rol
            ) {


        Integer[] idRecursoPermisoArray = { 18 };
        JeResponse jeResponse=new JeResponse("Rol modificado correctamente","Error grave al modificar Rol");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
            /** VERIFICACION BASICA */
            if(rol.getIdRol()==null) jeResponse.addErrorValidacion("Rol sin id para modificar");
            jeResponse.addErrorValidacion(rolDao.verificacionBasica(userSession.getInfoAuditoria(), rol));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
            if (jeResponse.sinErrorValidacion()) {

                

                jeResponse.putResultado("idRol", rolDao.modificar(userSession.getInfoAuditoria(), rol).getIdRol());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }


        return jeResponse.getRetornoMap();
    }

    
       
    @PostMapping("/eliminar")
    public Map<String, Object> eliminar(
                HttpServletRequest request,
                @RequestBody Rol rol
            ) {

        Integer[] idRecursoPermisoArray = { 19 };
        JeResponse jeResponse=new JeResponse("Rol eliminado correctamente","Error grave al elimimar Rol");
        UsuarioSesionInterno userSession = null;


        try {
            
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);


            if (jeResponse.sinErrorValidacion()) {

                rolDao.eliminarPorId(userSession.getInfoAuditoria(), rol.getIdRol());

            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }


        return jeResponse.getRetornoMap();
    }

   
   
    


}
