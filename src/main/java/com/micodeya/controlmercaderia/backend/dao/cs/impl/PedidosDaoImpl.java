package com.micodeya.controlmercaderia.backend.dao.cs.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micodeya.controlmercaderia.backend.dao.cs.PedidosDao;
import com.micodeya.controlmercaderia.backend.dao.cs.PedidosJpa;
import com.micodeya.controlmercaderia.backend.entities.cs.Pedidos;
import lombok.extern.slf4j.Slf4j;
import zzz.com.micodeya.backend.core.dao.GenericDAO;
import zzz.com.micodeya.backend.core.exception.ValidacionException;
import zzz.com.micodeya.backend.core.util.FilterAux;
import zzz.com.micodeya.backend.core.util.JeBoot;
import zzz.com.micodeya.backend.core.util.security.InfoAuditoria;

@Slf4j
@Service
public class PedidosDaoImpl extends GenericDAO<Pedidos, Integer> implements PedidosDao {

    @Autowired
    private PedidosJpa jpa;
    
    public PedidosDaoImpl() {
        referenceId = "idPedidos";
        atributosDefault = "infoAuditoria,zkUltimaModificacionMask,idPedidos,proveedor.idProveedores,proveedor.proveedor,fechaPedidoMask,fechaEntregaMask,estadoPedido";

        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-INI
        // atributos que no estan en el default. Debe iniciar con "," o estar vacio
        atributosExtras = "";
        // KGC-NOREPLACE-ATRIBUTOS-EXTRAS-FIN

        // nombreAtributo, etiqueta (clave, valor)
		etiquetaAtributos.putAll(Map.of("idPedidos","ID", "proveedor","Proveedor", "fechaPedido","Fecha Pedido", "haPedidoMask","Fecha Pedido", "fechaEntrega","Fecha Entrega", "haEntregaMask","Fecha Entrega", "estadoPedido","Estado Pedido"));
        
    }
    
    @Override
    protected Class<Pedidos> getEntityBeanType() {
        return Pedidos.class;
    }

    // KGC-NOREPLACE-OTROS-INI
    
    @Transactional(readOnly = true)
    private List<String> verificacionAdicional(InfoAuditoria infoAuditoria, Pedidos pedidos) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = pedidos.getIdPedidos() != null;
        List<FilterAux> filterList = null;


        return errorValList;
    }

    // KGC-NOREPLACE-OTROS-FIN					

    @Override
    @Transactional(readOnly = true)
    public Pedidos getById(Integer idPedidos) {
        return jpa.findById(idPedidos)
            .orElseThrow(() -> new ValidacionException("Pedidos no encontrado", "idPedidos", idPedidos));
    }

    @Override
    @Transactional(readOnly = true) 
    public Pedidos getByExample(Pedidos pedidos) {	
        return jpa.findOne(Example.of(pedidos, ExampleMatcher.matching().withIgnoreCase())).orElse(null);
    }
                    

    //Se ejecuta en el Rest, antes de llamar al DAO
    @Override
    @Transactional(readOnly = true)
    public List<String> verificacionBasica(InfoAuditoria infoAuditoria, Pedidos pedidos) {

        List<String> errorValList = new LinkedList<String>();
        boolean modificar = pedidos.getIdPedidos()!=null;
        List<FilterAux> filterList = null;

        


        errorValList.addAll(verificacionAdicional(infoAuditoria, pedidos));

        return errorValList;

    }


    // Setea los valores de los datos por default si el valor ingresado es nulo
    private void setearDatosDefault(InfoAuditoria infoAuditoria, Pedidos pedidos){

        

    }

    // Setea los datos de la entidad nueva a la entidad recuperada de la BD
    private void setearDatosModificar(InfoAuditoria infoAuditoria, Pedidos pedidosDto, Pedidos pedidosExistente){

        pedidosExistente.setProveedor(pedidosDto.getProveedor());
		pedidosExistente.setFechaPedido(pedidosDto.getFechaPedido());
		pedidosExistente.setFechaEntrega(pedidosDto.getFechaEntrega());
		pedidosExistente.setEstadoPedido(pedidosDto.getEstadoPedido());

        setearDatosDefault(infoAuditoria, pedidosExistente);

    }
        
    @Override
    @Transactional
    public Pedidos agregar(InfoAuditoria infoAuditoria, Pedidos pedidos) {

        // KGC-NOREPLACE-PRE-AGREGAR-INI
        setearDatosDefault(infoAuditoria, pedidos);
        // KGC-NOREPLACE-PRE-AGREGAR-FIN

        jpa.save(this.preGuardado(pedidos, infoAuditoria));

        // KGC-NOREPLACE-POS-AGREGAR-INI
        // KGC-NOREPLACE-POS-AGREGAR-FIN

        return pedidos; 
    }

    @Override
    @Transactional
    public Pedidos modificar(InfoAuditoria infoAuditoria, Pedidos pedidos) {

        Pedidos pedidosExistente = getById(pedidos.getIdPedidos());
        
        // KGC-NOREPLACE-PRE-MODIFICAR-INI
        setearDatosModificar(infoAuditoria, pedidos, pedidosExistente);
        //preModificar(infoAuditoria, pedidos, pedidosExistente);
        // KGC-NOREPLACE-PRE-MODIFICAR-FIN

        jpa.save(this.preGuardado(pedidosExistente, infoAuditoria));

        // KGC-NOREPLACE-POS-MODIFICAR-INI
        // KGC-NOREPLACE-POS-MODIFICAR-FIN

        return pedidosExistente;

    }
    
    @Override
    @Transactional
    public Pedidos eliminarPorId(InfoAuditoria infoAuditoria, Integer idPedidos) {

        Pedidos pedidosExistente = jpa.findById(idPedidos)
                .orElseThrow(() -> new ValidacionException("Pedidos no encontrado para eliminar", "idPedidos", idPedidos));


        // KGC-NOREPLACE-PRE-ELIMINAR-INI
        // KGC-NOREPLACE-PRE-ELIMINAR-FIN

        jpa.deleteById(this.preEliminado(idPedidos,infoAuditoria));

        // KGC-NOREPLACE-POS-ELIMINAR-INI
        // KGC-NOREPLACE-POS-ELIMINAR-FIN

        return pedidosExistente;
    }	

}
