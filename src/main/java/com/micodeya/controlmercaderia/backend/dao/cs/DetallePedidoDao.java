package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.DetallePedido;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface DetallePedidoDao extends GenericDAOInterface<DetallePedido, Integer>{

  	//lectura
	public DetallePedido getById(Integer idDetallePedido);
	public DetallePedido getByExample(DetallePedido detallePedido);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, DetallePedido detallePedido);

    //transaccion
    public DetallePedido agregar(InfoAuditoria infoAuditoria, DetallePedido detallePedido);
    public DetallePedido modificar(InfoAuditoria infoAuditoria, DetallePedido detallePedido);
    public DetallePedido eliminarPorId(InfoAuditoria infoAuditoria, Integer idDetallePedido);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
