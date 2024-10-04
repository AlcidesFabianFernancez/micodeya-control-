package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.DetallePedidoDao;
import com.micodeya.controlmercaderia.backend.dao.cs.DetallePedidoJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.DetallePedido;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class DetallePedidoDaoImpl extends GenericDAO<DetallePedido, Integer> implements DetallePedidoDao {

    @Autowired
    private DetallePedidoJpa jpa;
    
    public DetallePedidoDaoImpl() {
        referenceId = "idDetallePedido";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idDetallePedido,pedido.idPedidos,pedido.estadoPedido,producto.idProducto,producto.producto,cantidadPedido,precioUnitario";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idDetallePedido","ID", "pedido","Pedido", "producto","Producto", "cantidadPedido","Cantidad Pedido", "precioUnitario","Precio Unitario"));
        
    }
    
    @Override
    protected Class<DetallePedido> getEntityBeanType() {
        return DetallePedido.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, DetallePedido detallePedido) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = detallePedido.getIdDetallePedido() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public DetallePedido getById(Integer idDetallePedido) {
        return jpa.findById(idDetallePedido)
            .orElseThrow(() -> new ValidacionException("Detalle Pedido no encontrada", "idDetallePedido", idDetallePedido));
    }

    @Override
    @Transactional(readOnly = true) 
    public DetallePedido getByExample(DetallePedido detallePedido) {	
        return jpa.findOne(Example.of(detallePedido, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, DetallePedido detallePedido) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = detallePedido.getIdDetallePedido()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, detallePedido));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, DetallePedido detallePedido){

        

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, DetallePedido detallePedidoDto, DetallePedido detallePedidoExistente){

        detallePedidoExistente.setPedido(detallePedidoDto.getPedido());
		detallePedidoExistente.setProducto(detallePedidoDto.getProducto());
		detallePedidoExistente.setCantidadPedido(detallePedidoDto.getCantidadPedido());
		detallePedidoExistente.setPrecioUnitario(detallePedidoDto.getPrecioUnitario());

        setearDatosDefault(infoAuditoria, detallePedidoExistente);

    }
        
    @Override
    @Transactional
    public DetallePedido agregar(InfoAuditoria infoAuditoria, DetallePedido detallePedido) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, detallePedido);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(detallePedido, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return detallePedido; 
    }

    @Override
    @Transactional
    public DetallePedido modificar(InfoAuditoria infoAuditoria, DetallePedido detallePedido) {

        DetallePedido detallePedidoExistente = getById(detallePedido.getIdDetallePedido());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, detallePedido, detallePedidoExistente);
        //preModificar(infoAuditoria, detallePedido, detallePedidoExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(detallePedidoExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return detallePedidoExistente;

    }
    
    @Override
    @Transactional
    public DetallePedido eliminarPorId(InfoAuditoria infoAuditoria, Integer idDetallePedido) {

        DetallePedido detallePedidoExistente = jpa.findById(idDetallePedido)
                .orElseThrow(() -> new ValidacionException("Detalle Pedido no encontrada para eliminar", "idDetallePedido", idDetallePedido));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idDetallePedido,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return detallePedidoExistente;
    }	

}
