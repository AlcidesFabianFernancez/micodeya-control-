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
@Table(name = "CS_CARGO")
@SQLDelete(sql = "UPDATE CS_CARGO SET zk_eliminado = true, zk_fec_elim= now() WHERE id_cargo=?")
@Where(clause = "zk_eliminado=false")
public class Cargo extends AbstractModelZk implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_cargo_seq", allocationSize = 1)
	private Integer idCargo;

	@NotEmpty(message = "Nombre Cargo: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Nombre Cargo: cantidad de caracteres debe ser entre 0 y 60")
	private String cargo;

	@Size(min = 0, max = 60, message = "Descripción: cantidad de caracteres debe ser entre 0 y 60")
	private String descripcion;

	@NotEmpty(message = "Nivel Acceso: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Nivel Acceso: cantidad de caracteres debe ser entre 0 y 60")
	private String nivelAcceso;

	@NotNull(message = "Activo: no debe ser nulo")
	private Boolean activo;


	public Cargo(Integer id) {
		this.idCargo = id;
	}

	public Cargo(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public Cargo(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
