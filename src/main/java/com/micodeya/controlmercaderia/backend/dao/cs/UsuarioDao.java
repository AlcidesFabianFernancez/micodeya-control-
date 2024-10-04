package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Usuario;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface UsuarioDao extends GenericDAOInterface<Usuario, Integer>{

  	//lectura
	public Usuario getById(Integer idUsuario);
	public Usuario getByExample(Usuario usuario);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Usuario usuario);

    //transaccion
    public Usuario agregar(InfoAuditoria infoAuditoria, Usuario usuario);
    public Usuario modificar(InfoAuditoria infoAuditoria, Usuario usuario);
    public Usuario eliminarPorId(InfoAuditoria infoAuditoria, Integer idUsuario);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
