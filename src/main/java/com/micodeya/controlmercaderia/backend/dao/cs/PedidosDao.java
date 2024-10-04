package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Pedidos;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface PedidosDao extends GenericDAOInterface<Pedidos, Integer>{

  	//lectura
	public Pedidos getById(Integer idPedidos);
	public Pedidos getByExample(Pedidos pedidos);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Pedidos pedidos);

    //transaccion
    public Pedidos agregar(InfoAuditoria infoAuditoria, Pedidos pedidos);
    public Pedidos modificar(InfoAuditoria infoAuditoria, Pedidos pedidos);
    public Pedidos eliminarPorId(InfoAuditoria infoAuditoria, Integer idPedidos);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
