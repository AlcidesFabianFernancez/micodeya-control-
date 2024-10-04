package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.micodeya.controlmercaderia.backend.entities.cs.Producto;
import com.micodeya.controlmercaderia.backend.entities.cs.Usuario;
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
@Table(name = "CS_MOVIMIENTO_STOCK")
@SQLDelete(sql = "UPDATE CS_MOVIMIENTO_STOCK SET zk_eliminado = true, zk_fec_elim= now() WHERE id_movimiento_stock=?")
@Where(clause = "zk_eliminado=false")
public class MovimientoStock extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_movimiento_stock_seq", allocationSize = 1)
	private Integer idMovimientoStock;

	@NotNull(message = "Producto: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto; 

	//positivas para entrada, negativas para salidas, Ej. entro 5(valor en positivo, salió -2(valor en negativo)
	@NotNull(message = "Cantidad movimiento: no debe ser nulo")
	private Integer cantidadMovimiento;

	//Ej. entrada, salida, ajuste, devolución etc.
	@NotEmpty(message = "Tipo Movimiento: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Tipo Movimiento: cantidad de caracteres debe ser entre 0 y 60")
	private String tipoMovimiento;

	@NotNull(message = "Usuario: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario; 


	public MovimientoStock(Integer id) {
		this.idMovimientoStock = id;
	}

	public MovimientoStock(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public MovimientoStock(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
