package com.micodeya.controlmercaderia.backend.dao.cs;

import java.util.List;

import com.micodeya.controlmercaderia.backend.entities.cs.Empleado;
import zzz.com.micodeya.backend.core.dao.GenericDAOInterface;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

public interface EmpleadoDao extends GenericDAOInterface<Empleado, Integer>{

  	//lectura
	public Empleado getById(Integer idEmpleado);
	public Empleado getByExample(Empleado empleado);
	public List<String>  verificacionBasica(InfoAuditoria infoAuditoria, Empleado empleado);

    //transaccion
    public Empleado agregar(InfoAuditoria infoAuditoria, Empleado empleado);
    public Empleado modificar(InfoAuditoria infoAuditoria, Empleado empleado);
    public Empleado eliminarPorId(InfoAuditoria infoAuditoria, Integer idEmpleado);

    // KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN
    
} 
