package zzz.com.micodeya.backend.core.dao.zk.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.dao.zk.UsuarioDao;
import zzz.com.micodeya.backend.core.dao.zk.UsuarioJpa;
import zzz.com.micodeya.backend.core.dao.zk.UsuarioRolDao;
import zzz.com.micodeya.backend.core.entities.zk.Usuario;
import zzz.com.micodeya.backend.core.exception.KwfAuthException;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.service.JeSecurityService;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;
import zzz.com.micodeya.backend.core.util.security.InicioSesionDto;
import zzz.com.micodeya.backend.core.util.security.UsuarioSesionInterno;
import java.util.Date;

@Slf4j
@Service
public class UsuarioDaoImpl extends GenericDAO<Usuario, Integer> implements UsuarioDao {

    @Autowired
    private UsuarioJpa jpa;
    
    public UsuarioDaoImpl() {
        referenceId = "idUsuario";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idUsuario,cuenta,usuario,alias,nombre,apellido,correoPrincipal,telefono,fechaNacimientoMask,timeOutSesion,contrasenha,estado,confirmarCorreo,confirmarTelefono,tipoRegistro,avatar,imagenPortada,fechaHoraSuspensionMask,anonimo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idUsuario","ID", "cuenta","Cuenta", "usuario","Usuario", "alias","Alias", "nombre","Nombre", "apellido","Apellido", "correoPrincipal","Correo", "telefono","Teléfono", "fechaNacimiento","Fecha Nacimiento", "haNacimientoMask","Fecha Nacimiento"));
		etiquetaAtributos.putAll(Map.of("timeOutSesion","Time Out Sesion", "contrasenha","Contraseña", "estado","Estado", "confirmarCorreo","Confirmar Correo", "confirmarTelefono","Confirmar Teléfono", "tipoRegistro","Tipo de Registro", "avatar","Foto Avatar", "imagenPortada","Imagen Portada", "fechaHoraSuspension","Fecha Hora Suspension", "haHoraSuspensionMask","Fecha Hora Suspension"));
		etiquetaAtributos.putAll(Map.of("anonimo","Anónimo"));
        
    }
    
    @Override
    protected Class<Usuario> getEntityBeanType() {
        return Usuario.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Usuario usuario) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = usuario.getIdUsuario() != null;

        if (usuario.getUsuario().contains(" ")) {
            errorValList.add("El usuario no debe contener espacio");
        }

        if (usuario.getUsuario().length() < 3) {
            errorValList.add("El usuario  debe tener al menos 3 caracteres");
        }

        if (usuario.getUsuario().equalsIgnoreCase("base")) {
            errorValList.add("Usuario base ya existe");
        }
        if (usuario.getUsuario().equalsIgnoreCase("admin")) {
            errorValList.add("Usuario admin ya existe");
        }

        if (usuario.getUsuario().equalsIgnoreCase("administrador")) {
            errorValList.add("Usuario administrador ya existe");
        }

        if (usuario.getUsuario().equalsIgnoreCase("visitante")) {
            errorValList.add("Usuario visitante ya existe");
        }

        if(!StringUtils.isEmpty(usuario.getCorreoPrincipal())){
            if ((!modificar && jpa.countByCorreoPrincipalIgnoreCase(usuario.getCorreoPrincipal()) > 0)
                    || (modificar && jpa.countByIdUsuarioNotAndCorreoPrincipalIgnoreCase(
                            usuario.getIdUsuario(), usuario.getCorreoPrincipal()) > 0)) {
                errorValList.add("Correo ya se encuentra registrado en otra cuenta, elige otro.");
            }
        }

        return errorValList;

    }


    @Autowired
    UsuarioRolDao usuarioRolDao;

    @Autowired
    JeSecurityService securityService;

    private UsuarioSesionInterno procesarUsuarioAcceso(HttpServletRequest request, Usuario usuario, String empresaCore) {
        UsuarioSesionInterno usuarioSesion = null;

        if (usuario.getEstado().equals("SUS")) {
            log.warn(KwfAuthException.CodigoEstado.USUARIO_SUSPENDIDO + " usuario=" + usuario.getUsuario()
                    + ", request=" + JeBoot.getHttpRequestInfo(request));
            throw new KwfAuthException("Usuario suspendido", true, KwfAuthException.CodigoEstado.USUARIO_SUSPENDIDO);
        }

        if (!usuario.getEstado().equals("ACT")) {
            log.warn(KwfAuthException.CodigoEstado.USUARIO_INACTIVO + " usuario=" + usuario.getUsuario()
                    + ", request=" + JeBoot.getHttpRequestInfo(request));
            throw new KwfAuthException("Usuario inactivo", true, KwfAuthException.CodigoEstado.USUARIO_INACTIVO);
        }

        empresaCore = empresaCore.toUpperCase();

        List<Integer> recursosPemitidosList = jpa.getRecursosPermitidosEmpresaCore(usuario, empresaCore);
        String idRecursosConcat = recursosPemitidosList.stream()
                .map(o -> o.toString()).collect(Collectors.joining(","));
        System.out.println("idRecursosConcat: " + idRecursosConcat);

        List<String> recursosPemitidosBase16List = jpa.getRecursosPermitidosEmpresaCoreBase16(usuario, empresaCore);
        String idRecursosBase16Concat = recursosPemitidosBase16List.stream()
                .map(o -> o.toString().toUpperCase()).collect(Collectors.joining(","));

        // System.out.println("idRecursosBase16Concat: " + idRecursosBase16Concat);

        if (recursosPemitidosList == null || recursosPemitidosList.size() == 0) {
            log.warn(KwfAuthException.CodigoEstado.USUARIO_SIN_PRIVILEGIO + " usuario=" + usuario.getUsuario()
                    + " empresaCore=" + empresaCore
                    + ", request=" + JeBoot.getHttpRequestInfo(request));
            throw new KwfAuthException("Usuario sin privilegios", true,
                    KwfAuthException.CodigoEstado.USUARIO_SIN_PRIVILEGIO);
        }

        usuarioSesion = new UsuarioSesionInterno();
        usuarioSesion.setCuenta(usuario.getCuenta());
        usuarioSesion.setEmpresaCore(empresaCore);
        usuarioSesion.setIdSesion(5);
        usuarioSesion.setIdUsuario(usuario.getIdUsuario());
        usuarioSesion.setUsuario(usuario.getUsuario());
        usuarioSesion.setAlias(usuario.getAlias());
        usuarioSesion.setNombre(usuario.getNombre());
        usuarioSesion.setApellido(usuario.getApellido());
        usuarioSesion.setEmail(usuario.getCorreoPrincipal());
        usuarioSesion.setEstado(usuario.getEstado());
        usuarioSesion.setIdRecursoConcat("," + idRecursosConcat + ",");
        usuarioSesion.setIdRecursoConcatBase16("," + idRecursosBase16Concat + ",");
        usuarioSesion.setTimeOutSesion(usuario.getTimeOutSesion());

        return usuarioSesion;

    }

    @Override
    @Transactional
    public UsuarioSesionInterno obtenerUsuarioSesion(HttpServletRequest request, InicioSesionDto datosInicioSesion) {

        Usuario usuarioEjemplo = new Usuario();

        //es un correo
        if(datosInicioSesion.getUser().contains("@")){
            usuarioEjemplo.setCorreoPrincipal(datosInicioSesion.getUser().toLowerCase().trim());
        }else{
            usuarioEjemplo.setUsuario(datosInicioSesion.getUser().toLowerCase().trim());
        }

        String empresaCore = datosInicioSesion.getEmpresaCore() == null ? "EMPKUAA" : datosInicioSesion.getEmpresaCore();

        Usuario usuario = getByExample(usuarioEjemplo);

        if (usuario == null) {
            log.warn(KwfAuthException.CodigoEstado.USUARIO_NO_ENCONTRADO + " usuario=" + datosInicioSesion.getUser()
                    + ", request=" + JeBoot.getHttpRequestInfo(request));
            throw new KwfAuthException("Usuario o contraseña incorrecta", true,
                    KwfAuthException.CodigoEstado.USUARIO_NO_ENCONTRADO);
        }

        if (!securityService.encriptarComparar(datosInicioSesion.getPassword(),
            usuario.getUsuario().toLowerCase(), usuario.getContrasenha())) {

            log.warn(KwfAuthException.CodigoEstado.PASWORD_INCORRECTO + " usuario=" + datosInicioSesion.getUser()
                    + ", request=" + JeBoot.getHttpRequestInfo(request));
            throw new KwfAuthException("Usuario o contraseña incorrecta", true,
                    KwfAuthException.CodigoEstado.PASWORD_INCORRECTO);
        }

        return procesarUsuarioAcceso(request, usuario, empresaCore);

    }

    @Override
    @Transactional
    public UsuarioSesionInterno obtenerUsuarioSesionPorUsuario(HttpServletRequest request, String nombreUsuario,
            String empresaCore) {

        Usuario ejemplo = new Usuario();
        ejemplo.setUsuario(nombreUsuario);
        Usuario usuario = getByExample(ejemplo);

        if (usuario == null) {
            log.warn(KwfAuthException.CodigoEstado.USUARIO_NO_ENCONTRADO + " usuario=" + nombreUsuario
                    + ", request=" + JeBoot.getHttpRequestInfo(request));
            throw new KwfAuthException("Usuario o contraseña incorrecta", true,
                    KwfAuthException.CodigoEstado.USUARIO_NO_ENCONTRADO);
        }

        return procesarUsuarioAcceso(request, usuario, empresaCore);
    }
 
    @Override
    @Transactional(readOnly = true)
    public boolean verificarTieneAvatar(String usuario) {

        Usuario usuarioEjemplo = new Usuario();
        usuarioEjemplo.setUsuario(usuario);
        Usuario usuarioExistente = getByExample(usuarioEjemplo);

        if(usuarioExistente==null){
            throw new ValidacionException("Usuario "+usuarioEjemplo.getUsuario()+" no existe");
        }
        
        //tiene avatar 
        if(usuarioExistente.getAvatar().length()>5){
            return true;
        }

        return false;

    }

    @Override
    @Transactional
    public Usuario modificarPassword(InfoAuditoria infoAuditoria, Integer idUsuario,String passwordActual, String passwordNuevo) {
        
        Usuario usuarioExistente = getById(idUsuario);

        //comparar si las contrasenas actuales coinciden
        if(!securityService.encriptarComparar(passwordActual, usuarioExistente.getUsuario(), usuarioExistente.getContrasenha())){
            throw new ValidacionException("Contraseña actual incorrrecta");
        }

        usuarioExistente.setContrasenha(passwordNuevo);
        jpa.save(this.preGuardado(usuarioExistente, infoAuditoria));

        return usuarioExistente;

    }

    @Override
    @Transactional
    public Usuario recuperarPassword(InfoAuditoria infoAuditoria, Integer idUsuario, String passwordNuevo) {
        
        Usuario usuarioExistente = getById(idUsuario);

        usuarioExistente.setContrasenha(passwordNuevo);
        jpa.save(this.preGuardado(usuarioExistente, infoAuditoria));

        return usuarioExistente;

    }

        
    @Override
    @Transactional
    public Usuario modificarDatosCuenta(InfoAuditoria infoAuditoria, Usuario usuario) {

        Usuario usuarioExistente = getById(usuario.getIdUsuario());
    
        //Verificar que el usaurio que se desea modificar es el mismo de la sesion
        if(!usuarioExistente.getIdUsuario().equals(infoAuditoria.getIdUsuario())){
            throw new ValidacionException("No se pudo comprobar que la sesión corresponde al usuario que desea modificar");
        }

        //setear solo algunos permitidos

        usuarioExistente.setAlias(usuario.getAlias());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setApellido(usuario.getApellido());
        //usuarioExistente.setCorreoPrincipal(usuario.getCorreoPrincipal());
        //usuarioExistente.setTelefono(usuario.getTelefono());
        usuarioExistente.setFechaNacimiento(usuario.getFechaNacimiento());
    
        usuarioExistente.setAvatar(usuario.getAvatar());
        usuarioExistente.setImagenPortada(usuario.getImagenPortada());

        setearDatosDefault(infoAuditoria, usuarioExistente);

        jpa.save(this.preGuardado(usuarioExistente, infoAuditoria));

        return usuarioExistente;

    }

    @Override
    @Transactional
    public Usuario modificarAvatarCuenta(InfoAuditoria infoAuditoria, Usuario usuario) {

        Usuario usuarioEjemplo = new Usuario();
        usuarioEjemplo.setUsuario(usuario.getUsuario());
        Usuario usuarioExistente = getByExample(usuarioEjemplo);


        if(usuarioExistente==null){
            throw new ValidacionException("Usuario "+usuarioEjemplo.getUsuario()+" no existe");
        }
    
        //Verificar que el usaurio que se desea modificar es el mismo de la sesion
        if(!usuarioExistente.getIdUsuario().equals(infoAuditoria.getIdUsuario())){
            //temporal, solo para importaciones masivas
            // throw new ValidacionException("No se pudo comprobar que la sesión corresponde al usuario que desea modificar");
        }

        //solo cuando no tiene avatar
        if(usuarioExistente.getAvatar().length()<5){
            usuarioExistente.setAvatar(usuario.getAvatar());
            jpa.save(this.preGuardado(usuarioExistente, infoAuditoria));
        }

        //setearDatosDefault(usuarioExistente);


        return usuarioExistente;

    }

    @Override
    @Transactional
    public Usuario suspenderCuenta(InfoAuditoria infoAuditoria, Integer idUsuario,String passwordActual) {
        
        Usuario usuarioExistente = getById(idUsuario);

        //comparar si las contrasenas actuales coinciden
        if(!securityService.encriptarComparar(passwordActual, usuarioExistente.getUsuario(), usuarioExistente.getContrasenha())){
            throw new ValidacionException("Contraseña incorrrecta");
        }

        usuarioExistente.setEstado("SUS");
        usuarioExistente.setFechaHoraSuspension(new Date());
        jpa.save(this.preGuardado(usuarioExistente, infoAuditoria));

        return usuarioExistente;

    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Usuario getById(Integer idUsuario) {
        return jpa.findById(idUsuario)
            .orElseThrow(() -> new ValidacionException("Usuario no encontrado", "idUsuario", idUsuario));
    }

    @Override
    @Transactional(readOnly = true) 
    public Usuario getByExample(Usuario usuario) {	
        return jpa.findOne(Example.of(usuario, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Usuario usuario) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = usuario.getIdUsuario()!=null;
        List<FilterAux> filterList = null;

        if ((!modificar && jpa.countByUsuarioIgnoreCase(usuario.getUsuario()) > 0)
				 || (modificar && jpa.countByIdUsuarioNotAndUsuarioIgnoreCase(
					usuario.getIdUsuario(), usuario.getUsuario()) > 0)) {
			errorValList.add("Mismo usuario ya existe, elige otro.");
		}
		if ((!modificar && jpa.countByCuentaIgnoreCase(usuario.getCuenta()) > 0)
				 || (modificar && jpa.countByIdUsuarioNotAndCuentaIgnoreCase(
					usuario.getIdUsuario(), usuario.getCuenta()) > 0)) {
			errorValList.add("Misma cuenta ya existe, elige otro.");
		}


        errorValList.addAll(verificacionAdicional(infoAuditoria, usuario));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Usuario usuario){

        usuario.setTimeOutSesion(JeBoot.nvl(usuario.getTimeOutSesion(),30));
		usuario.setEstado(JeBoot.nvl(usuario.getEstado(),"ACT"));
		usuario.setConfirmarCorreo(JeBoot.nvl(usuario.getConfirmarCorreo(),"N"));
		usuario.setConfirmarTelefono(JeBoot.nvl(usuario.getConfirmarTelefono(),"N"));
		usuario.setAvatar(JeBoot.nvl(usuario.getAvatar(),"[]"));
		usuario.setImagenPortada(JeBoot.nvl(usuario.getImagenPortada(),"[]"));

        if(usuario.getCorreoPrincipal()!=null){
            usuario.setCorreoPrincipal(usuario.getCorreoPrincipal().toLowerCase());
        }

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Usuario usuarioDto, Usuario usuarioExistente){

        //usuarioExistente.setCuenta(usuarioDto.getCuenta());
		//usuarioExistente.setUsuario(usuarioDto.getUsuario());
		usuarioExistente.setAlias(usuarioDto.getAlias());
		usuarioExistente.setNombre(usuarioDto.getNombre());
		usuarioExistente.setApellido(usuarioDto.getApellido());
		//usuarioExistente.setCorreoPrincipal(usuarioDto.getCorreoPrincipal());
		usuarioExistente.setTelefono(usuarioDto.getTelefono());
		usuarioExistente.setFechaNacimiento(usuarioDto.getFechaNacimiento());
		usuarioExistente.setTimeOutSesion(usuarioDto.getTimeOutSesion());
		//usuarioExistente.setContrasenha(usuarioDto.getContrasenha());
		usuarioExistente.setEstado(usuarioDto.getEstado());
		usuarioExistente.setConfirmarCorreo(usuarioDto.getConfirmarCorreo());
		usuarioExistente.setConfirmarTelefono(usuarioDto.getConfirmarTelefono());
		//usuarioExistente.setTipoRegistro(usuarioDto.getTipoRegistro());
		usuarioExistente.setAvatar(usuarioDto.getAvatar());
		usuarioExistente.setImagenPortada(usuarioDto.getImagenPortada());
		usuarioExistente.setFechaHoraSuspension(usuarioDto.getFechaHoraSuspension());
		usuarioExistente.setAnonimo(usuarioDto.getAnonimo());
        
        if (usuarioDto.getContrasenha() != null)
         usuarioExistente.setContrasenha(usuarioDto.getContrasenha());

        setearDatosDefault(infoAuditoria, usuarioExistente);

    }
        
    @Override
    @Transactional
    public Usuario agregar(InfoAuditoria infoAuditoria, Usuario usuario) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, usuario);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(usuario, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        usuarioRolDao.agregarModificar(infoAuditoria, usuario.getUsuarioRolList(), usuario);
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return usuario; 
    }

    @Override
    @Transactional
    public Usuario modificar(InfoAuditoria infoAuditoria, Usuario usuario) {

        Usuario usuarioExistente = getById(usuario.getIdUsuario());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, usuario, usuarioExistente);
        //preModificar(infoAuditoria, usuario, usuarioExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(usuarioExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        usuarioRolDao.agregarModificar(infoAuditoria, usuario.getUsuarioRolList(), usuario);
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return usuarioExistente;

    }
    
    @Override
    @Transactional
    public Usuario eliminarPorId(InfoAuditoria infoAuditoria, Integer idUsuario) {

        Usuario usuarioExistente = jpa.findById(idUsuario)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado para eliminar", "idUsuario", idUsuario));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idUsuario,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return usuarioExistente;
    }	

}
