package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Categoria;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface CategoriaDao extends GenericDAOInterface<Categoria, Integer>{

  	//lectura
	public Categoria getById(Integer idCategoria);
	public Categoria getByExample(Categoria categoria);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Categoria categoria);

    //transaccion
    public Categoria agregar(InfoAuditoria infoAuditoria, Categoria categoria);
    public Categoria modificar(InfoAuditoria infoAuditoria, Categoria categoria);
    public Categoria eliminarPorId(InfoAuditoria infoAuditoria, Integer idCategoria);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
