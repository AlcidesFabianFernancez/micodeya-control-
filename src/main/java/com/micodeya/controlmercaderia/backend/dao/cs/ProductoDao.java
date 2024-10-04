package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Producto;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface ProductoDao extends GenericDAOInterface<Producto, Integer>{

  	//lectura
	public Producto getById(Integer idProducto);
	public Producto getByExample(Producto producto);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Producto producto);

    //transaccion
    public Producto agregar(InfoAuditoria infoAuditoria, Producto producto);
    public Producto modificar(InfoAuditoria infoAuditoria, Producto producto);
    public Producto eliminarPorId(InfoAuditoria infoAuditoria, Integer idProducto);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
