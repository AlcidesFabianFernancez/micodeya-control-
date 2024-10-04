package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.micodeya.controlmercaderia.backend.entities.cs.Proveedores;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import zzz.com.micodeya.backend.core.entities.AbstractModelZk;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CS_PEDIDOS")
@SQLDelete(sql = "UPDATE CS_PEDIDOS SET zk_eliminado = true, zk_fec_elim= now() WHERE id_pedidos=?")
@Where(clause = "zk_eliminado=false")
public class Pedidos extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_pedidos_seq", allocationSize = 1)
	private Integer idPedidos;

	@NotNull(message = "Proveedor: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	private Proveedores proveedor; 

	@NotNull(message = "Fecha Pedido: no debe ser nulo")
	@Temporal(TemporalType.DATE)
	private Date fechaPedido;

	@Formula("TO_CHAR(fecha_pedido, 'dd/MM/yyyy')")
	private String fechaPedidoMask;

	@NotNull(message = "Fecha Entrega: no debe ser nulo")
	@Temporal(TemporalType.DATE)
	private Date fechaEntrega;

	@Formula("TO_CHAR(fecha_entrega, 'dd/MM/yyyy')")
	private String fechaEntregaMask;

	//Ej. pendiente, recibido, cancelado
	@NotEmpty(message = "Estado Pedido: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Estado Pedido: cantidad de caracteres debe ser entre 0 y 60")
	private String estadoPedido;


	public Pedidos(Integer id) {
		this.idPedidos = id;
	}

	public Pedidos(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public Pedidos(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
