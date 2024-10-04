package com.micodeya.controlmercaderia.backend.rest.cs;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.micodeya.controlmercaderia.backend.dao.cs.UtilizadorDao;
import com.micodeya.controlmercaderia.backend.entities.cs.Utilizador;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dto.PuntoGeoDto;
import zzz.com.micodeya.backend.core.exception.KwfAuthException;
import zzz.com.micodeya.backend.core.exception.MyFileNotFoundException;
import zzz.com.micodeya.backend.core.service.FileStorageService;
import zzz.com.micodeya.backend.core.util.ArchivoDatoExtra;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.JeResponse;
import zzz.com.micodeya.backend.core.util.PaginacionAux;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;
import zzz.com.micodeya.backend.core.util.security.SesionJwt;
import zzz.com.micodeya.backend.core.util.security.UsuarioSesionInterno;

@Slf4j
@RestController
public class UtilizadorRest {

    private final String BASE_API = "/api/cs/utilizador";

    @Autowired
    private UtilizadorDao utilizadorDao;
    
	@Autowired
	private FileStorageService fileStorageService;
    
    // KGC-NOREPLACE-OTROS-INI

    private void transformacionAdicional(InfoAuditoria infoAuditoria, Utilizador utilizador) {
        boolean modificar = utilizador.getIdUtilizador() != null;
    }

    // KGC-NOREPLACE-OTROS-FIN
    
     
    @PostMapping(value = { BASE_API + "/listar",  BASE_API + "/listar/c/{cuentaCore}"})
    public Map<String, Object> listar(
            HttpServletRequest request,
            @PathVariable(required = false) String cuentaCore,
            @RequestBody(required = false) PaginacionAux paginacionAux
            ) {

        Integer[] idRecursoPermisoArray = { 6, 96 };
        JeResponse jeResponse=new JeResponse("Listado correcto de Usuario","Error grave al listar Usuario");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            // KGC-NOREPLACE-PRE-LISTAR-INI
            // JeBoot.verificarCuentaNula(cuentaCore, true);
            // userSession.esMismaCuenta(cuentaCore, true);
            // KGC-NOREPLACE-PRE-LISTAR-FIN
            if (jeResponse.sinErrorValidacion()) {
                jeResponse.putResultadoListar(utilizadorDao.listarAtributosPorPagina(new Utilizador(cuentaCore), paginacionAux.getAtributos(), paginacionAux));
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
				@RequestParam(name = "imagenPortadaFiles", required = false) MultipartFile[] imagenPortadaFiles,
				@RequestParam(name = "model", required = true) String model
            ) {

        Integer[] idRecursoPermisoArray = { 97 };
        JeResponse jeResponse=new JeResponse("Usuario creado correctamente","Error grave al crear Usuario");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
			Utilizador utilizador = new ObjectMapper().readValue(model, Utilizador.class);
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), utilizador);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(utilizadorDao.verificacionBasica(userSession.getInfoAuditoria(), utilizador));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
			if (jeResponse.sinErrorValidacion()) {
				List<ArchivoDatoExtra> adjuntoDatoExtraList = fileStorageService.guardarArchivoUploadResourceMultipart(
						imagenPortadaFiles, utilizador.getImagenPortada(), userSession.getInfoAuditoria(), BASE_API + "/imagenPortada");
			}


            if (jeResponse.sinErrorValidacion()) {
                utilizadorDao.agregar(userSession.getInfoAuditoria(), utilizador);
                jeResponse.putResultado("idUtilizador", utilizador.getIdUtilizador());
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
				@RequestParam(name = "imagenPortadaFiles", required = false) MultipartFile[] imagenPortadaFiles,
				@RequestParam(name = "model", required = true) String model
            ) {

        Integer[] idRecursoPermisoArray = { 98 };
        JeResponse jeResponse=new JeResponse("Usuario modificado correctamente","Error grave al modificar Usuario");
        UsuarioSesionInterno userSession = null;

        try {
        
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            
			Utilizador utilizador = new ObjectMapper().readValue(model, Utilizador.class);
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), utilizador);
            /** VERIFICACION BASICA */
            if(utilizador.getIdUtilizador()==null) jeResponse.addErrorValidacion("Usuario sin id para modificar");
            jeResponse.addErrorValidacion(utilizadorDao.verificacionBasica(userSession.getInfoAuditoria(), utilizador));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }
            
			if (jeResponse.sinErrorValidacion()) {
				List<ArchivoDatoExtra> adjuntoDatoExtraList = fileStorageService.guardarArchivoUploadResourceMultipart(
						imagenPortadaFiles, utilizador.getImagenPortada(), userSession.getInfoAuditoria(), BASE_API + "/imagenPortada");
			}

            
            if (jeResponse.sinErrorValidacion()) {
                utilizadorDao.modificar(userSession.getInfoAuditoria(), utilizador);
                jeResponse.putResultado("idUtilizador", utilizador.getIdUtilizador());
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
                @RequestBody Utilizador utilizador
            ) {

        Integer[] idRecursoPermisoArray = { 99 };
        JeResponse jeResponse=new JeResponse("Usuario eliminado correctamente","Error grave al elimimar Usuario");
        UsuarioSesionInterno userSession = null;

        try {
            
            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {
                utilizadorDao.eliminarPorId(userSession.getInfoAuditoria(), utilizador.getIdUtilizador());
                jeResponse.putResultado("idUtilizador", utilizador.getIdUtilizador());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

   
    
    
    @GetMapping(BASE_API + "/img/imagenPortada/{fileName:.+}")
    public ResponseEntity<Resource> imagenPortadaDownload(
            HttpServletRequest request, @PathVariable String fileName,
            @RequestParam(value = "thumb", required = false) String thumbFile) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Archivo Imagen Portada descargado correctamente",
                "Error grave al descargar archivo Imagen Portada");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {

                Resource resource = fileStorageService.recuperarArchivoUploadResource(BASE_API + "/imagenPortada", fileName, thumbFile);

                String contentType = JeBoot.getResourceMimeType(request,resource);

                String inlineOrAttachment = fileName.endsWith(".down") ? "attachment" : "inline";

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, inlineOrAttachment + "; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate")
                        .body(resource);

            }

        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            log.error(jeResponse.getErrorForLog(), e);
            if (e instanceof KwfAuthException) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            } else if (e instanceof MyFileNotFoundException) {
                return ResponseEntity.notFound().build();
            }
        }

        return ResponseEntity.internalServerError().build();
    }


    private void transformacionBasica(InfoAuditoria infoAuditoria, Utilizador utilizador) throws JsonMappingException, JsonProcessingException {
        boolean modificar = utilizador.getIdUtilizador() != null;



        transformacionAdicional(infoAuditoria, utilizador);

    }


}
