package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.UtilizadorDao;
import com.micodeya.controlmercaderia.backend.dao.cs.UtilizadorJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Utilizador;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class UtilizadorDaoImpl extends GenericDAO<Utilizador, Integer> implements UtilizadorDao {

    @Autowired
    private UtilizadorJpa jpa;
    
    public UtilizadorDaoImpl() {
        referenceId = "idUtilizador";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idUtilizador,usuario,contrasenha,cargo.idCargo,cargo.nombreCargo,empleado.idEmpleado,empleado.nombre,empleado.apellido,imagenPortada,activo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idUtilizador","ID", "usuario","Nombre Usuario", "contrasenha","Contraseñaa", "cargo","Cargo", "empleado","Empleado", "imagenPortada","Imagen Portada", "activo","Estado"));
        
    }
    
    @Override
    protected Class<Utilizador> getEntityBeanType() {
        return Utilizador.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Utilizador utilizador) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = utilizador.getIdUtilizador() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Utilizador getById(Integer idUtilizador) {
        return jpa.findById(idUtilizador)
            .orElseThrow(() -> new ValidacionException("Usuario no encontrado", "idUtilizador", idUtilizador));
    }

    @Override
    @Transactional(readOnly = true) 
    public Utilizador getByExample(Utilizador utilizador) {	
        return jpa.findOne(Example.of(utilizador, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Utilizador utilizador) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = utilizador.getIdUtilizador()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, utilizador));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Utilizador utilizador){

        utilizador.setImagenPortada(JeBoot.nvl(utilizador.getImagenPortada(),"[]"));
		utilizador.setActivo(JeBoot.nvl(utilizador.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Utilizador utilizadorDto, Utilizador utilizadorExistente){

        utilizadorExistente.setUsuario(utilizadorDto.getUsuario());
		utilizadorExistente.setContrasenha(utilizadorDto.getContrasenha());
		utilizadorExistente.setCargo(utilizadorDto.getCargo());
		utilizadorExistente.setEmpleado(utilizadorDto.getEmpleado());
		utilizadorExistente.setImagenPortada(utilizadorDto.getImagenPortada());
		utilizadorExistente.setActivo(utilizadorDto.getActivo());

        setearDatosDefault(infoAuditoria, utilizadorExistente);

    }
        
    @Override
    @Transactional
    public Utilizador agregar(InfoAuditoria infoAuditoria, Utilizador utilizador) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, utilizador);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(utilizador, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return utilizador; 
    }

    @Override
    @Transactional
    public Utilizador modificar(InfoAuditoria infoAuditoria, Utilizador utilizador) {

        Utilizador utilizadorExistente = getById(utilizador.getIdUtilizador());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, utilizador, utilizadorExistente);
        //preModificar(infoAuditoria, utilizador, utilizadorExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(utilizadorExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return utilizadorExistente;

    }
    
    @Override
    @Transactional
    public Utilizador eliminarPorId(InfoAuditoria infoAuditoria, Integer idUtilizador) {

        Utilizador utilizadorExistente = jpa.findById(idUtilizador)
                .orElseThrow(() -> new ValidacionException("Usuario no encontrado para eliminar", "idUtilizador", idUtilizador));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idUtilizador,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return utilizadorExistente;
    }	

}
