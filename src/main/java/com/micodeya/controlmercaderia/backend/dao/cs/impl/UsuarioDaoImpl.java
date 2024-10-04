package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.UsuarioDao;
import com.micodeya.controlmercaderia.backend.dao.cs.UsuarioJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Usuario;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class UsuarioDaoImpl extends GenericDAO<Usuario, Integer> implements UsuarioDao {

    @Autowired
    private UsuarioJpa jpa;
    
    public UsuarioDaoImpl() {
        referenceId = "idUsuario";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idUsuario,usuario,contrasenha,cargo.idCargo,cargo.nombreCargo,empleado.idEmpleado,empleado.nombre,empleado.apellido,imagenPortada,activo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idUsuario","ID", "usuario","Nombre Usuario", "contrasenha","Contraseñaa", "cargo","Cargo", "empleado","Empleado", "imagenPortada","Imagen Portada", "activo","Estado"));
        
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
        List<FilterAux> filterList = null;


        return errorValList;
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

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, usuario));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Usuario usuario){

        usuario.setImagenPortada(JeBoot.nvl(usuario.getImagenPortada(),"[]"));
		usuario.setActivo(JeBoot.nvl(usuario.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Usuario usuarioDto, Usuario usuarioExistente){

        usuarioExistente.setUsuario(usuarioDto.getUsuario());
		usuarioExistente.setContrasenha(usuarioDto.getContrasenha());
		usuarioExistente.setCargo(usuarioDto.getCargo());
		usuarioExistente.setEmpleado(usuarioDto.getEmpleado());
		usuarioExistente.setImagenPortada(usuarioDto.getImagenPortada());
		usuarioExistente.setActivo(usuarioDto.getActivo());

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
