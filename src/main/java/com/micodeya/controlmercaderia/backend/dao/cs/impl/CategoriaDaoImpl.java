package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.CategoriaDao;
import com.micodeya.controlmercaderia.backend.dao.cs.CategoriaJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Categoria;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class CategoriaDaoImpl extends GenericDAO<Categoria, Integer> implements CategoriaDao {

    @Autowired
    private CategoriaJpa jpa;
    
    public CategoriaDaoImpl() {
        referenceId = "idCategoria";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idCategoria,categoria,descripcionCategoria,activo";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idCategoria","ID", "categoria","Nombre Categoría", "descripcionCategoria","Descripción Categoría", "activo","Estado"));
        
    }
    
    @Override
    protected Class<Categoria> getEntityBeanType() {
        return Categoria.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Categoria categoria) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = categoria.getIdCategoria() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Categoria getById(Integer idCategoria) {
        return jpa.findById(idCategoria)
            .orElseThrow(() -> new ValidacionException("Categoria no encontrada", "idCategoria", idCategoria));
    }

    @Override
    @Transactional(readOnly = true) 
    public Categoria getByExample(Categoria categoria) {	
        return jpa.findOne(Example.of(categoria, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Categoria categoria) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = categoria.getIdCategoria()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, categoria));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Categoria categoria){

        categoria.setActivo(JeBoot.nvl(categoria.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Categoria categoriaDto, Categoria categoriaExistente){

        categoriaExistente.setCategoria(categoriaDto.getCategoria());
		categoriaExistente.setDescripcionCategoria(categoriaDto.getDescripcionCategoria());
		categoriaExistente.setActivo(categoriaDto.getActivo());

        setearDatosDefault(infoAuditoria, categoriaExistente);

    }
        
    @Override
    @Transactional
    public Categoria agregar(InfoAuditoria infoAuditoria, Categoria categoria) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, categoria);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(categoria, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return categoria; 
    }

    @Override
    @Transactional
    public Categoria modificar(InfoAuditoria infoAuditoria, Categoria categoria) {

        Categoria categoriaExistente = getById(categoria.getIdCategoria());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, categoria, categoriaExistente);
        //preModificar(infoAuditoria, categoria, categoriaExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(categoriaExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return categoriaExistente;

    }
    
    @Override
    @Transactional
    public Categoria eliminarPorId(InfoAuditoria infoAuditoria, Integer idCategoria) {

        Categoria categoriaExistente = jpa.findById(idCategoria)
                .orElseThrow(() -> new ValidacionException("Categoria no encontrada para eliminar", "idCategoria", idCategoria));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idCategoria,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return categoriaExistente;
    }	

}
