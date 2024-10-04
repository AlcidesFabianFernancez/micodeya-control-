package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "CS_CATEGORIA")
@SQLDelete(sql = "UPDATE CS_CATEGORIA SET zk_eliminado = true, zk_fec_elim= now() WHERE id_categoria=?")
@Where(clause = "zk_eliminado=false")
public class Categoria extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_categoria_seq", allocationSize = 1)
	private Integer idCategoria;

	@NotEmpty(message = "Nombre Categoría: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Nombre Categoría: cantidad de caracteres debe ser entre 0 y 60")
	private String categoria;

	@Size(min = 0, max = 60, message = "Descripción Categoría: cantidad de caracteres debe ser entre 0 y 60")
	private String descripcionCategoria;

	@NotNull(message = "Estado: no debe ser nulo")
	private Boolean activo;


	public Categoria(Integer id) {
		this.idCategoria = id;
	}

	public Categoria(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public Categoria(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
