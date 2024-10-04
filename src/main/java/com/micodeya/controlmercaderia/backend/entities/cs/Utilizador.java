package com.micodeya.controlmercaderia.backend.entities.cs;

import java.io.Serializable;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.micodeya.controlmercaderia.backend.entities.cs.Cargo;
import com.micodeya.controlmercaderia.backend.entities.cs.Empleado;
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
@Table(name = "CS_UTILIZADOR")
@SQLDelete(sql = "UPDATE CS_UTILIZADOR SET zk_eliminado = true, zk_fec_elim= now() WHERE id_utilizador=?")
@Where(clause = "zk_eliminado=false")
public class Utilizador extends AbstractModelZk implements Serializable{

	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
	@SequenceGenerator(name = "generator", sequenceName = "cs_utilizador_seq", allocationSize = 1)
	private Integer idUtilizador;

	@NotEmpty(message = "Nombre Usuario: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Nombre Usuario: cantidad de caracteres debe ser entre 0 y 60")
	private String usuario;

	@NotEmpty(message = "Contraseñaa: no debe estar vacio")
	@Size(min = 0, max = 60, message = "Contraseñaa: cantidad de caracteres debe ser entre 0 y 60")
	private String contrasenha;

	@NotNull(message = "Cargo: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_cargo")
	private Cargo cargo; 

	@NotNull(message = "Empleado: no debe ser nulo")
	@ManyToOne
	@JoinColumn(name = "id_empleado")
	private Empleado empleado; 

	@Size(min = 2, max = 500, message = "Imagen Portada: cantidad de caracteres debe ser entre 2 y 500")
	private String imagenPortada;

	@NotNull(message = "Estado: no debe ser nulo")
	private Boolean activo;


	public Utilizador(Integer id) {
		this.idUtilizador = id;
	}

	public Utilizador(String zkCuenta) {
		this.zkCuenta = zkCuenta;
	}

	public Utilizador(String zkEmpresaCore, String zkCuenta) {
		this.zkEmpresaCore = zkEmpresaCore;
		this.zkCuenta = zkCuenta;
	}

	// KGC-NOREPLACE-OTROS-INI
    // KGC-NOREPLACE-OTROS-FIN

}
