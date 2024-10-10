package zzz.com.micodeya.backend.core.rest.zk;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
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

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.zk.UsuarioDao;
import zzz.com.micodeya.backend.core.dto.CodigoOtpDto;
import zzz.com.micodeya.backend.core.dto.MensajeMailDto;
import zzz.com.micodeya.backend.core.entities.zk.Rol;
import zzz.com.micodeya.backend.core.entities.zk.Usuario;
import zzz.com.micodeya.backend.core.entities.zk.UsuarioRol;
import zzz.com.micodeya.backend.core.exception.KwfAuthException;
import zzz.com.micodeya.backend.core.exception.MyFileNotFoundException;
import zzz.com.micodeya.backend.core.service.FileStorageService;
import zzz.com.micodeya.backend.core.service.JeSecurityService;
import zzz.com.micodeya.backend.core.service.mail.JeMailService;
import zzz.com.micodeya.backend.core.service.mail.plantillas.GeneralMailPlantilla;
import zzz.com.micodeya.backend.core.util.ArchivoDatoExtra;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.JeResponse;
import zzz.com.micodeya.backend.core.util.PaginacionAux;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;
import zzz.com.micodeya.backend.core.util.security.SesionJwt;
import zzz.com.micodeya.backend.core.util.security.UsuarioSesionInterno;

@Slf4j
@RestController
public class UsuarioCuentaRest {

    private final String BASE_API = "/api/zk/usuarioCuenta";

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private FileStorageService fileStorageService;

    
    @PostMapping(value = { BASE_API + "/info",  BASE_API + "/infoMiCuenta", "/api/zk/usuario/info"})
    public Map<String, Object> info(
            HttpServletRequest request) {

                Integer[] idRecursoPermisoArray = { 9 };
                JeResponse jeResponse = new JeResponse("Listado correcto de información de usuario", "Error grave al listar información del Usuario");
                UsuarioSesionInterno userSession = null;
        
                try {
        
                    userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
        
                    
                    if (jeResponse.sinErrorValidacion()) {
        
                        String atributos="idUsuario,cuenta,usuario,alias,nombre,apellido,correoPrincipal,fechaNacimientoMask,avatar,imagenPortada";
        
                        jeResponse.putResultadoListar(
                                usuarioDao.listarAtributosPorPagina(new Usuario(userSession.getIdUsuario()),
                                        atributos, new PaginacionAux("idUsuario asc")));
                    }
        
                    /* AUTOGENERADO _KGC_ */
                    jeResponse.prepararRetornoMap();
                } catch (Exception e) {
                    jeResponse.prepararRetornoErrorMap(e);
                    if (!jeResponse.isWarning())
                        log.error(jeResponse.getErrorForLog(), e);
                }
        
                return jeResponse.getRetornoMap();
    }

    // informacion de otra cuenta
    @PostMapping(BASE_API + "/infoPerfil/{cuenta}")
    public Map<String, Object> infoPerfil(
            HttpServletRequest request,
            @PathVariable(required = true) String cuenta) {

                Integer[] idRecursoPermisoArray = { 9 };
                JeResponse jeResponse = new JeResponse("Listado correcto de información de usuario", "Error grave al listar información del Usuario");
                UsuarioSesionInterno userSession = null;
        
                try {
        
                    userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
        
                    
                    if (jeResponse.sinErrorValidacion()) {
        
                        String atributos="idUsuario,cuenta,usuario,alias,nombre,apellido,correoPrincipal,fechaNacimientoMask,avatar,imagenPortada";

                        Usuario ejemplo = new Usuario();
                        ejemplo.setCuenta(cuenta);
        
                        jeResponse.putResultadoListar(
                                usuarioDao.listarAtributosPorPagina(ejemplo,
                                        atributos, new PaginacionAux("idUsuario asc")));
                    }
        
                    /* AUTOGENERADO _KGC_ */
                    jeResponse.prepararRetornoMap();
                } catch (Exception e) {
                    jeResponse.prepararRetornoErrorMap(e);
                    if (!jeResponse.isWarning())
                        log.error(jeResponse.getErrorForLog(), e);
                }
        
                return jeResponse.getRetornoMap();
    }

    
    //modificar usuario
    @PostMapping(BASE_API + "/modificar")
    public Map<String, Object> modificar(
            HttpServletRequest request,
            @RequestParam(name = "avatarFiles", required = false) MultipartFile[] avatarFiles,
            @RequestParam(name = "imagenPortadaFiles", required = false) MultipartFile[] imagenPortadaFiles,
            @RequestParam(name = "model", required = true) String model) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Datos de la cuenta modificados exitosamente", "Error grave al modificar datos de la cuenta");
        UsuarioSesionInterno userSession = null;

        try {

            // System.out.println("\n\n\n\n\n\n\n######################");
            // System.out.println("ENTRO ACAAAAAAAAAAAAAAAAA");

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            Usuario usuario = new ObjectMapper().readValue(model, Usuario.class);
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), usuario);

            /** VERIFICACION BASICA */
            if (usuario.getIdUsuario() == null)
                jeResponse.addErrorValidacion("Usuario sin id para modificar");
            //jeResponse.addErrorValidacion(usuarioDao.verificacionBasica(userSession.getInfoAuditoria(), usuario));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }

            if (jeResponse.sinErrorValidacion()) {
                List<ArchivoDatoExtra> adjuntoDatoExtraList = fileStorageService.guardarArchivoUploadResourceMultipart(
                        avatarFiles, usuario.getAvatar(), userSession.getInfoAuditoria(), BASE_API + "/avatar");

                //Guardar con nombre fijo
                if(avatarFiles!=null && avatarFiles.length==1){
                    //Eliminar archivos previos
                    String originalFileName = avatarFiles[0].getOriginalFilename();
                    if(originalFileName!=null && originalFileName.equals("ya-estoy-en-el-server.png")){

                    }else{
                        //eliminar crear imagen por usuario solo si cambio el avatar
                        fileStorageService.eliminarArchivoUploadResource(BASE_API + "/avatar", userSession.getUsuario()+".jpg");
                        fileStorageService.eliminarArchivoUploadResource(BASE_API + "/avatar", userSession.getUsuario()+".png");
                        fileStorageService.eliminarArchivoUploadResource(BASE_API + "/avatar", userSession.getUsuario()+".jpeg");

                        
                        List<ArchivoDatoExtra> adjuntoDatoExtraNombreEspecificoList = fileStorageService.guardarArchivoUploadResourceMultipart(
                            avatarFiles, usuario.getAvatar(), userSession.getInfoAuditoria(), BASE_API + "/avatar", userSession.getUsuario());
                    }

                    
                }
                        

            }

            if (jeResponse.sinErrorValidacion()) {
                List<ArchivoDatoExtra> adjuntoDatoExtraList = fileStorageService.guardarArchivoUploadResourceMultipart(
                        imagenPortadaFiles, usuario.getImagenPortada(), userSession.getInfoAuditoria(),
                        BASE_API + "/imagenPortada");
            }

            if (jeResponse.sinErrorValidacion()) {

                jeResponse.putResultado("idUsuario",
                        usuarioDao.modificarDatosCuenta(userSession.getInfoAuditoria(), usuario).getIdUsuario());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }



    @PostMapping(BASE_API + "/agregarAvatarDirecto")
    public Map<String, Object> agregarAvatarDirecto(
            HttpServletRequest request,
            @RequestParam(name = "avatarFiles", required = false) MultipartFile[] avatarFiles,
            @RequestParam(name = "model", required = true) String model) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Avatar agregado exitosamente", "Error grave al agregar avatar de la cuenta");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            Usuario usuario = new ObjectMapper().readValue(model, Usuario.class);
            /** TRANSFORMACION BASICA */
            transformacionBasica(userSession.getInfoAuditoria(), usuario);

            /** VERIFICACION BASICA */
            // if (usuario.getIdUsuario() == null)
            //     jeResponse.addErrorValidacion("Usuario sin id para modificar");
            //jeResponse.addErrorValidacion(usuarioDao.verificacionBasica(userSession.getInfoAuditoria(), usuario));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {
                if(usuarioDao.verificarTieneAvatar(usuario.getUsuario())){
                    jeResponse.addErrorValidacion("Usuario ya tiene avatar");
                }
            }

            if (jeResponse.sinErrorValidacion()) {
                
                //String usuarioCuenta=userSession.getUsuario();
                String usuarioCuenta=usuario.getUsuario();

                List<ArchivoDatoExtra> adjuntoDatoExtraList = fileStorageService.guardarArchivoUploadResourceMultipart(
                        avatarFiles, usuario.getAvatar(), userSession.getInfoAuditoria(), BASE_API + "/avatar");

                //Guardar con nombre fijo
                if(avatarFiles!=null && avatarFiles.length==1){
                    //Eliminar archivos previos
                    String originalFileName = avatarFiles[0].getOriginalFilename();
                    if(originalFileName!=null && originalFileName.equals("ya-estoy-en-el-server.png")){

                    }else{
                        //eliminar crear imagen por usuario solo si cambio el avatar
                        fileStorageService.eliminarArchivoUploadResource(BASE_API + "/avatar", usuarioCuenta+".jpg");
                        fileStorageService.eliminarArchivoUploadResource(BASE_API + "/avatar", usuarioCuenta+".png");
                        fileStorageService.eliminarArchivoUploadResource(BASE_API + "/avatar", usuarioCuenta+".jpeg");

                        
                        List<ArchivoDatoExtra> adjuntoDatoExtraNombreEspecificoList = fileStorageService.guardarArchivoUploadResourceMultipart(
                            avatarFiles, usuario.getAvatar(), userSession.getInfoAuditoria(), BASE_API + "/avatar", usuarioCuenta);
                    }

                    
                }
                        

            }

           
            if (jeResponse.sinErrorValidacion()) {

                jeResponse.putResultado("idUsuario",
                        usuarioDao.modificarAvatarCuenta(userSession.getInfoAuditoria(), usuario).getIdUsuario());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }


    // recuperar avatar
    @GetMapping(BASE_API + "/img/avatar/{fileName:.+}")
    public ResponseEntity<Resource> avatarDownload(
            HttpServletRequest request, @PathVariable String fileName) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Archivo Avatar descargado correctamente",
                "Error grave al descargar archivo Avatar");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {

                Resource resource = fileStorageService.recuperarArchivoUploadResource(BASE_API + "/avatar", fileName);

                String contentType = JeBoot.getResourceMimeType(request, resource);

                String inlineOrAttachment = fileName.endsWith(".down") ? "attachment" : "inline";

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                inlineOrAttachment + "; filename=\"" + resource.getFilename() + "\"")
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


    // recuperar avatar
    @GetMapping(BASE_API + "/img/avatarCuenta/{fileName}")
    public ResponseEntity<Resource> avatarCuenta(
            HttpServletRequest request, @PathVariable String fileName) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Archivo Avatar descargado correctamente",
                "Error grave al descargar archivo Avatar");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {

                //Verificar cual existe del usuario
                String nombreArchivo= fileName;

                String[] extensionesPosiblesArray={".jpg",".jpeg", ".png"};

                for (int i = 0; i < extensionesPosiblesArray.length; i++) {
                    nombreArchivo = fileName + extensionesPosiblesArray[i];
                    if (fileStorageService.existeArchivoUploadResource(BASE_API + "/avatar", nombreArchivo)) {
                        break;
                    }
                }


                Resource resource = fileStorageService.recuperarArchivoUploadResource(BASE_API + "/avatar", nombreArchivo);

                String contentType = JeBoot.getResourceMimeType(request, resource);

                String inlineOrAttachment = fileName.endsWith(".down") ? "attachment" : "inline";

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                inlineOrAttachment + "; filename=\"" + resource.getFilename() + "\"")
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


    @GetMapping(BASE_API + "/img/imagenPortada/{fileName:.+}")
    public ResponseEntity<Resource> imagenPortadaDownload(
            HttpServletRequest request, @PathVariable String fileName) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Archivo Avatar descargado correctamente",
                "Error grave al descargar archivo Avatar");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            if (jeResponse.sinErrorValidacion()) {

                Resource resource = fileStorageService.recuperarArchivoUploadResource(BASE_API + "/imagenPortada",
                        fileName);

                String contentType = JeBoot.getResourceMimeType(request, resource);

                String inlineOrAttachment = fileName.endsWith(".down") ? "attachment" : "inline";

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                inlineOrAttachment + "; filename=\"" + resource.getFilename() + "\"")
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

    private void transformacionBasica(InfoAuditoria infoAuditoria, Usuario usuario) throws JsonMappingException, JsonProcessingException {
        boolean modificar = usuario.getIdUsuario() != null;

		usuario.setFechaNacimiento(JeBoot.getFecha(usuario.getFechaNacimientoMask()));
		usuario.setFechaHoraSuspension(JeBoot.getFechaHHMMSS(usuario.getFechaHoraSuspensionMask()));

        transformacionAdicional(infoAuditoria, usuario);

    }

    @Autowired
    JeSecurityService securityService;

    @Autowired
    JeMailService mailService;

    @Autowired
    GeneralMailPlantilla mailPlantilla;

    private static Map<String, CodigoOtpDto> OTP_EMAIL_AUTORREGISTR0_MAP = new HashMap<String, CodigoOtpDto>();
    private static Map<String, CodigoOtpDto> OTP_EMAIL_RECUPERAR_PASSWORD_MAP = new HashMap<String, CodigoOtpDto>();

    private void transformacionAdicional(InfoAuditoria infoAuditoria, Usuario usuario) {
        boolean modificar = usuario.getIdUsuario() != null;

        String usuarioSinModificar=usuario.getUsuario();
        // normalizamos el nombre del usuario
        usuario.setUsuario(JeBoot.normalizarUsuario(usuario.getUsuario()));
        usuario.setCuenta(usuario.getUsuario());
        
        // si no tiene alias, usar el usuario
        if(StringUtils.isEmpty(usuario.getAlias())){
            usuario.setAlias(usuarioSinModificar);
        }
        // si no tiene nombre, usar ----
        if(StringUtils.isEmpty(usuario.getNombre())){
            usuario.setNombre("----");
        }
        // si no tiene apellido, usar ----
        if(StringUtils.isEmpty(usuario.getApellido())){
            usuario.setApellido("----");
        }
        
        //modificar usuario
        if (modificar){ 
            // solo si hay contraseña
            
            if(!StringUtils.isEmpty(usuario.getContrasenha())) {
                //verificamos el password
                securityService.verificarPassword(usuario.getContrasenha());
                usuario.setContrasenha(securityService.encriptar(usuario.getContrasenha(), usuario.getUsuario()));
            }else{
                usuario.setContrasenha(null);
            }
        } else {
            // es nuevo
            securityService.verificarPassword(usuario.getContrasenha());
            usuario.setContrasenha(securityService.encriptar(usuario.getContrasenha(), usuario.getUsuario()));

        }

    }

  

    @PostMapping(BASE_API + "/modificarPassword")
    public Map<String, Object> modificarPassword(
            HttpServletRequest request,
            @RequestBody ModificarPasswordDto modificarPasswordDto) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Contraseña modificada correctamente",
                "Error grave al modificar Contraseña");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            /** TRANSFORMACION BASICA */

            /** VERIFICACION BASICA */

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {
                if (!modificarPasswordDto.getPasswordNuevo().equals(modificarPasswordDto.getPasswordNuevoRepetir())) {
                    jeResponse.addErrorValidacion("Contraseña nueva no son iguales");
                }
            }

            String newPassword = null;

            if (jeResponse.sinErrorValidacion()) {
                securityService.verificarPassword(modificarPasswordDto.getPasswordNuevo());

                newPassword = securityService.encriptar(modificarPasswordDto.getPasswordNuevo(),
                        userSession.getUsuario().toLowerCase());
            }

            if (jeResponse.sinErrorValidacion()) {

                jeResponse.putResultado("idUsuario",
                        usuarioDao.modificarPassword(userSession.getInfoAuditoria(), userSession.getIdUsuario(),
                                modificarPasswordDto.getPassword(),
                                newPassword).getIdUsuario());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

    @PostMapping(BASE_API + "/suspenderCuenta")
    public Map<String, Object> suspenderCuenta(
            HttpServletRequest request,
            @RequestBody SuspenderCuentaDto suspenderCuentaDto) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Cuenta de usuario suspendido exitosamente",
                "Error grave al suspender cuenta");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            // InfoAuditoria infoAuditoria = new InfoAuditoria(true);

            /** TRANSFORMACION BASICA */

            /** VERIFICACION BASICA */

            /** OTRAS VERIFICACIONES */

            if (jeResponse.sinErrorValidacion()) {

            }

            if (jeResponse.sinErrorValidacion()) {

                jeResponse.putResultado("idUsuario",
                        usuarioDao.suspenderCuenta(userSession.getInfoAuditoria(), userSession.getIdUsuario(),
                                suspenderCuentaDto.getPassword()).getIdUsuario());

            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }




    @PostMapping("/api/public/registro/porMail")
    public Map<String, Object> autoregistroPorMail(
            HttpServletRequest request,
            @RequestBody Usuario usuario) {

        // Integer[] idRecursoPermisoArray = { 12 };
        JeResponse jeResponse = new JeResponse("Usuario creado correctamente", "Error grave al crear Usuario");
        UsuarioSesionInterno userSession = null;

        try {
            // se crea uno ya que no hay sesion
            InfoAuditoria infoAuditoria = new InfoAuditoria(true);

            /** TRANSFORMACION BASICA */
            transformacionBasica(infoAuditoria, usuario);

            usuario.setTimeOutSesion(30);
            usuario.setEstado("ACT");
            usuario.setConfirmarCorreo("S");
            usuario.setConfirmarTelefono("N");
            usuario.setTipoRegistro("AAP");
            usuario.setAvatar("[]");
            usuario.setImagenPortada("[]");

            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(usuarioDao.verificacionBasica(infoAuditoria, usuario));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {
                if (StringUtils.isEmpty(usuario.getCorreoPrincipal())) {
                    jeResponse.addErrorValidacion("Correo no debe estar vacío");
                }
            }

            if (jeResponse.sinErrorValidacion()) {

                String keyMapOtp = usuario.getUsuario() + "-" + usuario.getCorreoPrincipal();

                // enviar codigo
                if (usuario.getSolicitudOtpEmail() != null && usuario.getSolicitudOtpEmail()) {

                    String valorCodigo = String.valueOf((new Random().nextInt(9999 - 1000) + 1000));
                    // System.out.println("valorCodigo: "+ valorCodigo);
                    OTP_EMAIL_AUTORREGISTR0_MAP.put(keyMapOtp, new CodigoOtpDto(valorCodigo));

                    MensajeMailDto mensaje = mailPlantilla.mensajeVerificarEmailAutorregistro(valorCodigo);
                    mensaje.setPara(usuario.getCorreoPrincipal());

                    mailService.enviarMail(mensaje);

                    jeResponse.replaceMensajeExito("Correo enviado con un código único.");
                    jeResponse.putResultado("codigoOtpEnviado", true);

                } else {
                    // verificar codigo y guardar

                    String valorCodigo = usuario.getCodigoOtpEmail();

                    if (!OTP_EMAIL_AUTORREGISTR0_MAP.containsKey(keyMapOtp)) {
                        jeResponse.addErrorValidacion("Código no encontrado");
                    }

                    if (jeResponse.sinErrorValidacion()) {
                        if (StringUtils.isEmpty(valorCodigo)) {
                            jeResponse.addErrorValidacion("Código ingresado está vacío");
                        }
                    }
                    if (jeResponse.sinErrorValidacion()) {
                        CodigoOtpDto codigoOtpDto = OTP_EMAIL_AUTORREGISTR0_MAP.get(keyMapOtp);
                        if (!codigoOtpDto.verificarCodigo(valorCodigo)) {
                            jeResponse.addErrorValidacion("Código ingresado incorrecto");
                        }
                    }

                    // guardar en la BD
                    if (jeResponse.sinErrorValidacion()) {

                        usuario.setUsuarioRolList(getRolBasicoList(infoAuditoria));

                        jeResponse.putResultado("idUsuario",
                                usuarioDao.agregar(infoAuditoria, usuario).getIdUsuario());
                        jeResponse.putResultado("codigoOtpEnviado", false);
                    }

                }
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

    @PostMapping("/api/public/registro/recuperarPasswordPorMail")
    public Map<String, Object> recuperarPassword(
            HttpServletRequest request,
            @RequestBody RecuperarPasswordPorMailDto recuperarPasswordPorMailDto) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Contraseña recuperada correctamente",
                "Error grave al recuperar Contraseña");
        UsuarioSesionInterno userSession = null;

        try {

            // userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            InfoAuditoria infoAuditoria = new InfoAuditoria(true);

            /** TRANSFORMACION BASICA */

            /** VERIFICACION BASICA */

            /** OTRAS VERIFICACIONES */

            if (jeResponse.sinErrorValidacion()) {
                if (recuperarPasswordPorMailDto.getDatoBuscar().equals("usuario")
                        && Strings.isBlank(recuperarPasswordPorMailDto.getUsuario())) {
                    jeResponse.addErrorValidacion("Usuario está vacio");
                }
            }

            if (jeResponse.sinErrorValidacion()) {
                if (recuperarPasswordPorMailDto.getDatoBuscar().equals("correo")
                        && Strings.isBlank(recuperarPasswordPorMailDto.getCorreo())) {
                    jeResponse.addErrorValidacion("Correo está vacio");
                }
            }

            if (jeResponse.sinErrorValidacion()) {
                if (Strings.isBlank(recuperarPasswordPorMailDto.getUsuario())
                        && Strings.isBlank(recuperarPasswordPorMailDto.getCorreo())) {
                    jeResponse.addErrorValidacion("Usuario o correo vacio");
                }
            }
            // buscar por usuario o correo

            if (jeResponse.sinErrorValidacion()) {
                Usuario usuarioEjemplo = new Usuario();
                usuarioEjemplo.setUsuario(
                        recuperarPasswordPorMailDto.getUsuario() != null
                                ? recuperarPasswordPorMailDto.getUsuario().toLowerCase().trim()
                                : null);
                usuarioEjemplo.setCorreoPrincipal(
                        recuperarPasswordPorMailDto.getCorreo() != null
                                ? recuperarPasswordPorMailDto.getCorreo().toLowerCase().trim()
                                : null);

                PaginacionAux result = usuarioDao.listarAtributosPorPagina(usuarioEjemplo,
                        "idUsuario,usuario,correoPrincipal,estado",
                        new PaginacionAux("usuario asc"));

                if (result.getList().size() == 0) {
                    if (recuperarPasswordPorMailDto.getDatoBuscar().equals("usuario")) {
                        jeResponse.addErrorValidacion(
                                "Usuario '" + recuperarPasswordPorMailDto.getUsuario() + "' no encontrado");
                    }
                    if (recuperarPasswordPorMailDto.getDatoBuscar().equals("correo")) {
                        jeResponse.addErrorValidacion(
                                "Correo '" + recuperarPasswordPorMailDto.getUsuario() + "' no encontrado");
                    }

                } else if (result.getList().size() > 1) {
                    jeResponse.addErrorValidacion(
                            "Se encontró más de un Usuario '" + recuperarPasswordPorMailDto.getUsuario() + "'");
                    if (recuperarPasswordPorMailDto.getDatoBuscar().equals("usuario")) {
                        jeResponse.addErrorValidacion(
                                "Se encontró más de un Usuario '" + recuperarPasswordPorMailDto.getUsuario() + "'");
                    }
                    if (recuperarPasswordPorMailDto.getDatoBuscar().equals("correo")) {
                        jeResponse.addErrorValidacion(
                                "Se encontró más de un Correo '" + recuperarPasswordPorMailDto.getUsuario() + "'");
                    }
                } else {

                    if(!result.getList().get(0).get("estado").toString().equals("ACT")){
                        jeResponse.addErrorValidacion("Usuario inactivo");
                    }

                    recuperarPasswordPorMailDto
                            .setIdUsuario(Integer.parseInt(result.getList().get(0).get("idUsuario").toString()));
                    recuperarPasswordPorMailDto.setUsuario(result.getList().get(0).get("usuario").toString());
                    recuperarPasswordPorMailDto.setCorreo(result.getList().get(0).get("correoPrincipal").toString());
                }

            }

            // enviar mail con OTP
            String keyMapOtp = recuperarPasswordPorMailDto.getUsuario() + "-" + recuperarPasswordPorMailDto.getCorreo();
            if (jeResponse.sinErrorValidacion() && recuperarPasswordPorMailDto.getSolicitudOtpEmail()) {

                String valorCodigo = String.valueOf((new Random().nextInt(9999 - 1000) + 1000));
                // System.out.println("valorCodigo: "+ valorCodigo);
                OTP_EMAIL_RECUPERAR_PASSWORD_MAP.put(keyMapOtp, new CodigoOtpDto(valorCodigo));

                MensajeMailDto mensaje = mailPlantilla.mensajeRecuperarPasswordPorEmailOtp(valorCodigo);
                mensaje.setPara(recuperarPasswordPorMailDto.getCorreo());

                mailService.enviarMail(mensaje);

                jeResponse.replaceMensajeExito("Correo enviado con un código único.");
                jeResponse.putResultado("correoEncontrado", JeBoot.ocultarCorreo(recuperarPasswordPorMailDto.getCorreo()));
                jeResponse.putResultado("codigoOtpEnviado", true);
            }

            // verificar codigo y continuar
            if (jeResponse.sinErrorValidacion() && !recuperarPasswordPorMailDto.getSolicitudOtpEmail()) {

                String valorCodigo = recuperarPasswordPorMailDto.getCodigoOtpEmail();

                if (!OTP_EMAIL_RECUPERAR_PASSWORD_MAP.containsKey(keyMapOtp)) {
                    jeResponse.addErrorValidacion("Código no encontrado");
                }

                if (jeResponse.sinErrorValidacion()) {
                    if (StringUtils.isEmpty(valorCodigo)) {
                        jeResponse.addErrorValidacion("Código ingresado está vacío");
                    }
                }
                if (jeResponse.sinErrorValidacion()) {
                    CodigoOtpDto codigoOtpDto = OTP_EMAIL_RECUPERAR_PASSWORD_MAP.get(keyMapOtp);
                    if (!codigoOtpDto.verificarCodigo(valorCodigo)) {
                        jeResponse.addErrorValidacion("Código ingresado incorrecto");
                    }
                }

                if (jeResponse.sinErrorValidacion()) {
                    if (!recuperarPasswordPorMailDto.getPasswordNuevo()
                            .equals(recuperarPasswordPorMailDto.getPasswordNuevoRepetir())) {
                        jeResponse.addErrorValidacion("Contraseña nueva no son iguales");
                    }
                }

                if (jeResponse.sinErrorValidacion()) {
                    securityService.verificarPassword(recuperarPasswordPorMailDto.getPasswordNuevo());
                }
                // guardar en la BD
                if (jeResponse.sinErrorValidacion()) {

                    String newPassword = securityService.encriptar(recuperarPasswordPorMailDto.getPasswordNuevo(),
                            recuperarPasswordPorMailDto.getUsuario().toLowerCase());

                    // usuario.setUsuarioRolList(usuarioRolList);

                    jeResponse.putResultado("idUsuario",
                            usuarioDao.recuperarPassword(infoAuditoria, recuperarPasswordPorMailDto.getIdUsuario(),
                                    newPassword).getIdUsuario());
                    jeResponse.putResultado("codigoOtpEnviado", false);
                }

            }

            if (jeResponse.sinErrorValidacion()) {

            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }


    @PostMapping("/api/public/registro/verificarExistenciaUsuario")
    public Map<String, Object> verificarExistenciaUsuario(
            HttpServletRequest request,
            @RequestBody ExistenciaUsuarioDto existenciaUsuarioDto) {

        Integer[] idRecursoPermisoArray = { 9 };
        JeResponse jeResponse = new JeResponse("Usuario disponible",
                "Error grave al verificar existencia de usuario");
        UsuarioSesionInterno userSession = null;

        try {

            // userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);

            /** TRANSFORMACION BASICA */

            /** VERIFICACION BASICA */

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }

            Usuario usuario = new Usuario();
            usuario.setUsuario( JeBoot.normalizarUsuario(existenciaUsuarioDto.getUser()) );
            usuario.setCuenta( JeBoot.normalizarUsuario(existenciaUsuarioDto.getUser()) );
            jeResponse.addErrorValidacion(usuarioDao.verificacionBasica(null, usuario));

            if (jeResponse.sinErrorValidacion()) {
                // jeResponse.putResultado("idUsuario",);
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }

    @PostMapping(BASE_API + "/agregarDirecto")
    public Map<String, Object> agregarDirecto(
            HttpServletRequest request,
            @RequestBody Usuario usuario) {

        Integer[] idRecursoPermisoArray = { 12 };
        JeResponse jeResponse = new JeResponse("Usuario creado correctamente", "Error grave al crear Usuario");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            InfoAuditoria infoAuditoriaDefautl = userSession.getInfoAuditoria();
            // InfoAuditoria infoAuditoriaDefautl=new InfoAuditoria(true);

            // Usuario usuario = new ObjectMapper().readValue(model, Usuario.class);
            /** TRANSFORMACION BASICA */
            transformacionBasica(infoAuditoriaDefautl, usuario);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(usuarioDao.verificacionBasica(infoAuditoriaDefautl, usuario));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }

            if (jeResponse.sinErrorValidacion()) {
    
                usuario.setTimeOutSesion(30);
                usuario.setEstado("ACT");
                usuario.setConfirmarCorreo("N");
                usuario.setConfirmarTelefono("N");
                usuario.setTipoRegistro("PSI");
                usuario.setAvatar("[]");
                usuario.setImagenPortada("[]");

                usuario.setUsuarioRolList(getRolBasicoList(infoAuditoriaDefautl));

                jeResponse.putResultado("idUsuario",
                        usuarioDao.agregar(infoAuditoriaDefautl, usuario).getIdUsuario());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }


  


    @PostMapping("/api/public/registro/anonimo")
    public Map<String, Object> anonimo(
            HttpServletRequest request,
            @RequestBody Usuario usuario) {

        Integer[] idRecursoPermisoArray = { 12 };
        JeResponse jeResponse = new JeResponse("Usuario creado correctamente", "Error grave al crear Usuario");
        UsuarioSesionInterno userSession = null;

        try {

            userSession = SesionJwt.getUserSesion(request, idRecursoPermisoArray);
            // InfoAuditoria infoAuditoriaDefautl = userSession.getInfoAuditoria();
            InfoAuditoria infoAuditoriaDefautl=new InfoAuditoria(true);

            // Usuario usuario = new ObjectMapper().readValue(model, Usuario.class);
            /** TRANSFORMACION BASICA */
            transformacionBasica(infoAuditoriaDefautl, usuario);
            /** VERIFICACION BASICA */
            jeResponse.addErrorValidacion(usuarioDao.verificacionBasica(infoAuditoriaDefautl, usuario));

            /** OTRAS VERIFICACIONES */
            if (jeResponse.sinErrorValidacion()) {

            }

            if (jeResponse.sinErrorValidacion()) {
    
                usuario.setTimeOutSesion(30);
                usuario.setEstado("ACT");
                usuario.setConfirmarCorreo("N");
                usuario.setConfirmarTelefono("N");
                usuario.setTipoRegistro("PSI");
                usuario.setAvatar("[]");
                usuario.setImagenPortada("[]");
                usuario.setAnonimo(true);

                usuario.setUsuarioRolList(getRolBasicoList(infoAuditoriaDefautl));

                jeResponse.putResultado("idUsuario",
                        usuarioDao.agregar(infoAuditoriaDefautl, usuario).getIdUsuario());
                jeResponse.putResultado("usuario",usuario.getUsuario());
            }

            /* AUTOGENERADO _KGC_ */
            jeResponse.prepararRetornoMap();
        } catch (Exception e) {
            jeResponse.prepararRetornoErrorMap(e);
            if (!jeResponse.isWarning())
                log.error(jeResponse.getErrorForLog(), e);
        }

        return jeResponse.getRetornoMap();
    }


  



     private List<UsuarioRol> getRolBasicoList(InfoAuditoria infoAuditoria){

        
        List<UsuarioRol> usuarioRolList = new LinkedList<UsuarioRol>();

        // Acceso basico Web
        UsuarioRol usuarioRol98 = new UsuarioRol();
        usuarioRol98.setActivo(true);
        usuarioRol98.setRol(new Rol(98));
        usuarioRol98.setCodigoEmpresaCore(infoAuditoria.getEmpresaCore());
        usuarioRolList.add(usuarioRol98);

        // Acceso basico App
        UsuarioRol usuarioRol97 = new UsuarioRol();
        usuarioRol97.setActivo(true);
        usuarioRol97.setRol(new Rol(97));
        usuarioRol97.setCodigoEmpresaCore(infoAuditoria.getEmpresaCore());
        usuarioRolList.add(usuarioRol97);

        return usuarioRolList;
    }


}

@Data
class ModificarPasswordDto {
    @NotEmpty(message = "Contraseña Actual: no debe estar vacio")
    private String password;

    @NotEmpty(message = "Contraseña Nueva: no debe estar vacio")
    private String passwordNuevo;

    @NotEmpty(message = "Contraseña Nueva Repetir: no debe estar vacio")
    private String passwordNuevoRepetir;
}

@Data
class SuspenderCuentaDto {
    @NotEmpty(message = "Contraseña Actual: no debe estar vacio")
    private String password;

}

@Data
class RecuperarPasswordPorMailDto {
    private Integer idUsuario; // se carga al recuperar
    private String datoBuscar; // usuario o correo
    private String usuario;
    private String correo;
    private String passwordNuevo;
    private String passwordNuevoRepetir;

    private Boolean solicitudOtpEmail;
    private String codigoOtpEmail;
}


@Data
class ExistenciaUsuarioDto {
    private String user;
}

