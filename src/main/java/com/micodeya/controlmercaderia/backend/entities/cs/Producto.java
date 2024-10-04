package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.micodeya.controlmercaderia.backend.entities.cs.Categoria;
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
@Table(name = "CS_PRODUCTO")
@SQLDelete(sql = "UPDATE CS_PRODUCTO SET zk_eliminado = true, zk_fec_elim= now() WHERE id_producto=?")
@Where(clause = "zk_eliminado=false")
public class Producto extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_producto_seq", allocationSize = 1)
	private Integer idProducto;

	@NotEmpty(message = "Producto: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Producto: cantidad de caracteres debe ser entre 0 y 60")
	private String producto;

	@Size(min = 0, max = 60, message = "Descripción: cantidad de caracteres debe ser entre 0 y 60")
	private String descripcion;

	@NotNull(message = "Precio Unitario: no debe ser nulo")
	private Integer precioUnitario;

	private Integer stockAtual;

	@NotNull(message = "Stock Minimo: no debe ser nulo")
	private Integer stockMinimo;

	@NotNull(message = "Estado: no debe ser nulo")
	private Boolean activo;

	@NotNull(message = "Categoría: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria; 


	public Producto(Integer id) {
		this.idProducto = id;
	}

	public Producto(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public Producto(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
