package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "CS_EMPLEADO")
@SQLDelete(sql = "UPDATE CS_EMPLEADO SET zk_eliminado = true, zk_fec_elim= now() WHERE id_empleado=?")
@Where(clause = "zk_eliminado=false")
public class Empleado extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_empleado_seq", allocationSize = 1)
	private Integer idEmpleado;

	@NotEmpty(message = "Nombre: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Nombre: cantidad de caracteres debe ser entre 0 y 60")
	private String nombre;

	@NotEmpty(message = "Apellido: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Apellido: cantidad de caracteres debe ser entre 0 y 60")
	private String apellido;

	@NotNull(message = "Numero Documento: no debe ser nulo")
	private BigDecimal numeroDocumento;

	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Formula("TO_CHAR(fecha_nacimiento, 'dd/MM/yyyy')")
	private String fechaNacimientoMask;

	// valueList-> MA:Masculino;null:null
	@NotEmpty(message = "Genero: no debe estar vacio")
	@Size(min = 0, max = 2, message = "Genero: cantidad de caracteres debe ser entre 0 y 2")
	private String genero;

	@NotNull(message = "Activo: no debe ser nulo")
	private Boolean activo;


	public Empleado(Integer id) {
		this.idEmpleado = id;
	}

	public Empleado(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public Empleado(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
