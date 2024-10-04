package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Cargo;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface CargoDao extends GenericDAOInterface<Cargo, Integer>{

  	//lectura
	public Cargo getById(Integer idCargo);
	public Cargo getByExample(Cargo cargo);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Cargo cargo);

    //transaccion
    public Cargo agregar(InfoAuditoria infoAuditoria, Cargo cargo);
    public Cargo modificar(InfoAuditoria infoAuditoria, Cargo cargo);
    public Cargo eliminarPorId(InfoAuditoria infoAuditoria, Integer idCargo);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
