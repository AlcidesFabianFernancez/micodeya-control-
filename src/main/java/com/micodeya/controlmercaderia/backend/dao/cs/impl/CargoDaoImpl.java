package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.CargoDao;
import com.micodeya.controlmercaderia.backend.dao.cs.CargoJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Cargo;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class CargoDaoImpl extends GenericDAO<Cargo, Integer> implements CargoDao {

    @Autowired
    private CargoJpa jpa;
    
    public CargoDaoImpl() {
        referenceId = "idCargo";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idCargo,cargo,descripcion,nivelAcceso,activo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idCargo","ID", "cargo","Nombre Cargo", "descripcion","Descripción", "nivelAcceso","Nivel Acceso", "activo","Activo"));
        
    }
    
    @Override
    protected Class<Cargo> getEntityBeanType() {
        return Cargo.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Cargo cargo) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = cargo.getIdCargo() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Cargo getById(Integer idCargo) {
        return jpa.findById(idCargo)
            .orElseThrow(() -> new ValidacionException("Cargo no encontrado", "idCargo", idCargo));
    }

    @Override
    @Transactional(readOnly = true) 
    public Cargo getByExample(Cargo cargo) {	
        return jpa.findOne(Example.of(cargo, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Cargo cargo) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = cargo.getIdCargo()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, cargo));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Cargo cargo){

        cargo.setActivo(JeBoot.nvl(cargo.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Cargo cargoDto, Cargo cargoExistente){

        cargoExistente.setCargo(cargoDto.getCargo());
		cargoExistente.setDescripcion(cargoDto.getDescripcion());
		cargoExistente.setNivelAcceso(cargoDto.getNivelAcceso());
		cargoExistente.setActivo(cargoDto.getActivo());

        setearDatosDefault(infoAuditoria, cargoExistente);

    }
        
    @Override
    @Transactional
    public Cargo agregar(InfoAuditoria infoAuditoria, Cargo cargo) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, cargo);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(cargo, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return cargo; 
    }

    @Override
    @Transactional
    public Cargo modificar(InfoAuditoria infoAuditoria, Cargo cargo) {

        Cargo cargoExistente = getById(cargo.getIdCargo());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, cargo, cargoExistente);
        //preModificar(infoAuditoria, cargo, cargoExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(cargoExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return cargoExistente;

    }
    
    @Override
    @Transactional
    public Cargo eliminarPorId(InfoAuditoria infoAuditoria, Integer idCargo) {

        Cargo cargoExistente = jpa.findById(idCargo)
                .orElseThrow(() -> new ValidacionException("Cargo no encontrado para eliminar", "idCargo", idCargo));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idCargo,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return cargoExistente;
    }	

}
