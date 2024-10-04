package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.EmpleadoDao;
import com.micodeya.controlmercaderia.backend.dao.cs.EmpleadoJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Empleado;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class EmpleadoDaoImpl extends GenericDAO<Empleado, Integer> implements EmpleadoDao {

    @Autowired
    private EmpleadoJpa jpa;
    
    public EmpleadoDaoImpl() {
        referenceId = "idEmpleado";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idEmpleado,nombre,apellido,numeroDocumento,fechaNacimientoMask,genero,activo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idEmpleado","ID", "nombre","Nombre", "apellido","Apellido", "numeroDocumento","Numero Documento", "fechaNacimiento","Fecha Nacimiento", "haNacimientoMask","Fecha Nacimiento", "genero","Genero", "activo","Activo"));
        
    }
    
    @Override
    protected Class<Empleado> getEntityBeanType() {
        return Empleado.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Empleado empleado) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = empleado.getIdEmpleado() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Empleado getById(Integer idEmpleado) {
        return jpa.findById(idEmpleado)
            .orElseThrow(() -> new ValidacionException("Empleado no encontrado", "idEmpleado", idEmpleado));
    }

    @Override
    @Transactional(readOnly = true) 
    public Empleado getByExample(Empleado empleado) {	
        return jpa.findOne(Example.of(empleado, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Empleado empleado) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = empleado.getIdEmpleado()!=null;
        List<FilterAux> filterList = null;

        if ((!modificar && jpa.countByNumeroDocumento(empleado.getNumeroDocumento()) > 0)
				 || (modificar && jpa.countByIdEmpleadoNotAndNumeroDocumento(
					empleado.getIdEmpleado(), empleado.getNumeroDocumento()) > 0)) {
			errorValList.add("Numero de documento ya existe");
		}


        errorValList.addAll(verificacionAdicional(infoAuditoria, empleado));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Empleado empleado){

        empleado.setActivo(JeBoot.nvl(empleado.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Empleado empleadoDto, Empleado empleadoExistente){

        empleadoExistente.setNombre(empleadoDto.getNombre());
		empleadoExistente.setApellido(empleadoDto.getApellido());
		empleadoExistente.setNumeroDocumento(empleadoDto.getNumeroDocumento());
		empleadoExistente.setFechaNacimiento(empleadoDto.getFechaNacimiento());
		empleadoExistente.setGenero(empleadoDto.getGenero());
		empleadoExistente.setActivo(empleadoDto.getActivo());

        setearDatosDefault(infoAuditoria, empleadoExistente);

    }
        
    @Override
    @Transactional
    public Empleado agregar(InfoAuditoria infoAuditoria, Empleado empleado) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, empleado);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(empleado, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return empleado; 
    }

    @Override
    @Transactional
    public Empleado modificar(InfoAuditoria infoAuditoria, Empleado empleado) {

        Empleado empleadoExistente = getById(empleado.getIdEmpleado());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, empleado, empleadoExistente);
        //preModificar(infoAuditoria, empleado, empleadoExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(empleadoExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return empleadoExistente;

    }
    
    @Override
    @Transactional
    public Empleado eliminarPorId(InfoAuditoria infoAuditoria, Integer idEmpleado) {

        Empleado empleadoExistente = jpa.findById(idEmpleado)
                .orElseThrow(() -> new ValidacionException("Empleado no encontrado para eliminar", "idEmpleado", idEmpleado));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idEmpleado,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return empleadoExistente;
    }	

}
