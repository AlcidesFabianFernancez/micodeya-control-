package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.ProductoDao;
import com.micodeya.controlmercaderia.backend.dao.cs.ProductoJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Producto;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class ProductoDaoImpl extends GenericDAO<Producto, Integer> implements ProductoDao {

    @Autowired
    private ProductoJpa jpa;
    
    public ProductoDaoImpl() {
        referenceId = "idProducto";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idProducto,producto,descripcion,precioUnitario,stockAtual,stockMinimo,activo,categoria.idCategoria,categoria.categoria";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idProducto","ID", "producto","Producto", "descripcion","Descripción", "precioUnitario","Precio Unitario", "stockAtual","Stock Atual", "stockMinimo","Stock Minimo", "activo","Estado", "categoria","Categoría"));
        
    }
    
    @Override
    protected Class<Producto> getEntityBeanType() {
        return Producto.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Producto producto) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = producto.getIdProducto() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Producto getById(Integer idProducto) {
        return jpa.findById(idProducto)
            .orElseThrow(() -> new ValidacionException("Producto no encontrado", "idProducto", idProducto));
    }

    @Override
    @Transactional(readOnly = true) 
    public Producto getByExample(Producto producto) {	
        return jpa.findOne(Example.of(producto, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Producto producto) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = producto.getIdProducto()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, producto));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Producto producto){

        producto.setActivo(JeBoot.nvl(producto.getActivo(),false));

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Producto productoDto, Producto productoExistente){

        productoExistente.setProducto(productoDto.getProducto());
		productoExistente.setDescripcion(productoDto.getDescripcion());
		productoExistente.setPrecioUnitario(productoDto.getPrecioUnitario());
		productoExistente.setStockAtual(productoDto.getStockAtual());
		productoExistente.setStockMinimo(productoDto.getStockMinimo());
		productoExistente.setActivo(productoDto.getActivo());
		productoExistente.setCategoria(productoDto.getCategoria());

        setearDatosDefault(infoAuditoria, productoExistente);

    }
        
    @Override
    @Transactional
    public Producto agregar(InfoAuditoria infoAuditoria, Producto producto) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, producto);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(producto, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return producto; 
    }

    @Override
    @Transactional
    public Producto modificar(InfoAuditoria infoAuditoria, Producto producto) {

        Producto productoExistente = getById(producto.getIdProducto());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, producto, productoExistente);
        //preModificar(infoAuditoria, producto, productoExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(productoExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return productoExistente;

    }
    
    @Override
    @Transactional
    public Producto eliminarPorId(InfoAuditoria infoAuditoria, Integer idProducto) {

        Producto productoExistente = jpa.findById(idProducto)
                .orElseThrow(() -> new ValidacionException("Producto no encontrado para eliminar", "idProducto", idProducto));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idProducto,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return productoExistente;
    }	

}
