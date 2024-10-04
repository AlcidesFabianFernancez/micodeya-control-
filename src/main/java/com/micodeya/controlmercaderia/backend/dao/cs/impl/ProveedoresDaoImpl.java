package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.ProveedoresDao;
import com.micodeya.controlmercaderia.backend.dao.cs.ProveedoresJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Proveedores;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class ProveedoresDaoImpl extends GenericDAO<Proveedores, Integer> implements ProveedoresDao {

    @Autowired
    private ProveedoresJpa jpa;
    
    public ProveedoresDaoImpl() {
        referenceId = "idProveedores";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idProveedores,proveedor,contacto,direccion,activo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idProveedores","ID", "proveedor","Proveedor", "contacto","Contacto", "direccion","Dirección", "activo","Estado"));
        
    }
    
    @Override
    protected Class<Proveedores> getEntityBeanType() {
        return Proveedores.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Proveedores proveedores) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = proveedores.getIdProveedores() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Proveedores getById(Integer idProveedores) {
        return jpa.findById(idProveedores)
            .orElseThrow(() -> new ValidacionException("Proveedores no encontrado", "idProveedores", idProveedores));
    }

    @Override
    @Transactional(readOnly = true) 
    public Proveedores getByExample(Proveedores proveedores) {	
        return jpa.findOne(Example.of(proveedores, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Proveedores proveedores) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = proveedores.getIdProveedores()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, proveedores));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Proveedores proveedores){

        proveedores.setActivo(JeBoot.nvl(proveedores.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Proveedores proveedoresDto, Proveedores proveedoresExistente){

        proveedoresExistente.setProveedor(proveedoresDto.getProveedor());
		proveedoresExistente.setContacto(proveedoresDto.getContacto());
		proveedoresExistente.setDireccion(proveedoresDto.getDireccion());
		proveedoresExistente.setActivo(proveedoresDto.getActivo());

        setearDatosDefault(infoAuditoria, proveedoresExistente);

    }
        
    @Override
    @Transactional
    public Proveedores agregar(InfoAuditoria infoAuditoria, Proveedores proveedores) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, proveedores);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(proveedores, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return proveedores; 
    }

    @Override
    @Transactional
    public Proveedores modificar(InfoAuditoria infoAuditoria, Proveedores proveedores) {

        Proveedores proveedoresExistente = getById(proveedores.getIdProveedores());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, proveedores, proveedoresExistente);
        //preModificar(infoAuditoria, proveedores, proveedoresExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(proveedoresExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return proveedoresExistente;

    }
    
    @Override
    @Transactional
    public Proveedores eliminarPorId(InfoAuditoria infoAuditoria, Integer idProveedores) {

        Proveedores proveedoresExistente = jpa.findById(idProveedores)
                .orElseThrow(() -> new ValidacionException("Proveedores no encontrado para eliminar", "idProveedores", idProveedores));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idProveedores,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return proveedoresExistente;
    }	

}
