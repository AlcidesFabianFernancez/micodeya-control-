package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.MovimientoStock;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface MovimientoStockDao extends GenericDAOInterface<MovimientoStock, Integer>{

  	//lectura
	public MovimientoStock getById(Integer idMovimientoStock);
	public MovimientoStock getByExample(MovimientoStock movimientoStock);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock);

    //transaccion
    public MovimientoStock agregar(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock);
    public MovimientoStock modificar(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock);
    public MovimientoStock eliminarPorId(InfoAuditoria infoAuditoria, Integer idMovimientoStock);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
