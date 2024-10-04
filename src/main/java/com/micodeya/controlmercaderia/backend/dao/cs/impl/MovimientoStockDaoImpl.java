package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.MovimientoStockDao;
import com.micodeya.controlmercaderia.backend.dao.cs.MovimientoStockJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.MovimientoStock;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class MovimientoStockDaoImpl extends GenericDAO<MovimientoStock, Integer> implements MovimientoStockDao {

    @Autowired
    private MovimientoStockJpa jpa;
    
    public MovimientoStockDaoImpl() {
        referenceId = "idMovimientoStock";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idMovimientoStock,producto.idProducto,producto.producto,cantidadMovimiento,tipoMovimiento,usuario.idUsuario,usuario.usuario";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idMovimientoStock","ID", "producto","Producto", "cantidadMovimiento","Cantidad movimiento", "tipoMovimiento","Tipo Movimiento", "usuario","Usuario"));
        
    }
    
    @Override
    protected Class<MovimientoStock> getEntityBeanType() {
        return MovimientoStock.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = movimientoStock.getIdMovimientoStock() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public MovimientoStock getById(Integer idMovimientoStock) {
        return jpa.findById(idMovimientoStock)
            .orElseThrow(() -> new ValidacionException("Movimiento Stock no encontrado", "idMovimientoStock", idMovimientoStock));
    }

    @Override
    @Transactional(readOnly = true) 
    public MovimientoStock getByExample(MovimientoStock movimientoStock) {	
        return jpa.findOne(Example.of(movimientoStock, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = movimientoStock.getIdMovimientoStock()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, movimientoStock));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock){

        

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, MovimientoStock movimientoStockDto, MovimientoStock movimientoStockExistente){

        movimientoStockExistente.setProducto(movimientoStockDto.getProducto());
		movimientoStockExistente.setCantidadMovimiento(movimientoStockDto.getCantidadMovimiento());
		movimientoStockExistente.setTipoMovimiento(movimientoStockDto.getTipoMovimiento());
		movimientoStockExistente.setUsuario(movimientoStockDto.getUsuario());

        setearDatosDefault(infoAuditoria, movimientoStockExistente);

    }
        
    @Override
    @Transactional
    public MovimientoStock agregar(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, movimientoStock);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(movimientoStock, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return movimientoStock; 
    }

    @Override
    @Transactional
    public MovimientoStock modificar(InfoAuditoria infoAuditoria, MovimientoStock movimientoStock) {

        MovimientoStock movimientoStockExistente = getById(movimientoStock.getIdMovimientoStock());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, movimientoStock, movimientoStockExistente);
        //preModificar(infoAuditoria, movimientoStock, movimientoStockExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(movimientoStockExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return movimientoStockExistente;

    }
    
    @Override
    @Transactional
    public MovimientoStock eliminarPorId(InfoAuditoria infoAuditoria, Integer idMovimientoStock) {

        MovimientoStock movimientoStockExistente = jpa.findById(idMovimientoStock)
                .orElseThrow(() -> new ValidacionException("Movimiento Stock no encontrado para eliminar", "idMovimientoStock", idMovimientoStock));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idMovimientoStock,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return movimientoStockExistente;
    }	

}
