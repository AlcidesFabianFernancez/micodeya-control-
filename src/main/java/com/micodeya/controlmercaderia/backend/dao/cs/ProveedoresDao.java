package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Proveedores;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface ProveedoresDao extends GenericDAOInterface<Proveedores, Integer>{

  	//lectura
	public Proveedores getById(Integer idProveedores);
	public Proveedores getByExample(Proveedores proveedores);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Proveedores proveedores);

    //transaccion
    public Proveedores agregar(InfoAuditoria infoAuditoria, Proveedores proveedores);
    public Proveedores modificar(InfoAuditoria infoAuditoria, Proveedores proveedores);
    public Proveedores eliminarPorId(InfoAuditoria infoAuditoria, Integer idProveedores);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
