package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.micodeya.controlmercaderia.backend.entities.cs.Pedidos;
import com.micodeya.controlmercaderia.backend.entities.cs.Producto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "CS_DETALLE_PEDIDO")
@SQLDelete(sql = "UPDATE CS_DETALLE_PEDIDO SET zk_eliminado = true, zk_fec_elim= now() WHERE id_detalle_pedido=?")
@Where(clause = "zk_eliminado=false")
public class DetallePedido extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_detalle_pedido_seq", allocationSize = 1)
	private Integer idDetallePedido;

	@NotNull(message = "Pedido: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedidos pedido; 

	@NotNull(message = "Producto: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto; 

	@NotNull(message = "Cantidad Pedido: no debe ser nulo")
	private Integer cantidadPedido;

	private Integer precioUnitario;


	public DetallePedido(Integer id) {
		this.idDetallePedido = id;
	}

	public DetallePedido(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public DetallePedido(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
