package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Utilizador;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface UtilizadorDao extends GenericDAOInterface<Utilizador, Integer>{

  	//lectura
	public Utilizador getById(Integer idUtilizador);
	public Utilizador getByExample(Utilizador utilizador);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Utilizador utilizador);

    //transaccion
    public Utilizador agregar(InfoAuditoria infoAuditoria, Utilizador utilizador);
    public Utilizador modificar(InfoAuditoria infoAuditoria, Utilizador utilizador);
    public Utilizador eliminarPorId(InfoAuditoria infoAuditoria, Integer idUtilizador);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
