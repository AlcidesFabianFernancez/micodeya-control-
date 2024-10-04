
/* TABLE CS_CARGO */
CREATE TABLE CS_CARGO
(
	id_cargo numeric(9,0) NOT NULL,
	cargo character varying(60) NOT NULL,
	descripcion character varying(60),
	nivel_acceso character varying(60) NOT NULL,
	activo boolean NOT NULL DEFAULT 'true',

	/*campos genericos auditoria*/
	zk_usr_crea character varying(60) not null,
	zk_usr_modi character varying(60),
	zk_fec_crea timestamp without time zone not null,
	zk_ult_modi timestamp without time zone not null,
	zk_empresa_core character varying(30) not null,
	zk_cuenta character varying(60) not null,
	zk_eliminado boolean not null,
	zk_fec_elim timestamp without time zone,
	zk_uuid character varying(100) not null
)
WITH (
  OIDS=FALSE
);

ALTER TABLE CS_CARGO 
	ADD CONSTRAINT "CS_CARGO-id_cargo_pk" PRIMARY KEY (id_cargo);

CREATE SEQUENCE CS_CARGO_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;


/* TABLE CS_CATEGORIA */
CREATE TABLE CS_CATEGORIA
(
	id_categoria numeric(9,0) NOT NULL,
	categoria character varying(60) NOT NULL,
	descripcion_categoria character varying(60),
	activo boolean NOT NULL DEFAULT 'true',

	/*campos genericos auditoria*/
	zk_usr_crea character varying(60) not null,
	zk_usr_modi character varying(60),
	zk_fec_crea timestamp without time zone not null,
	zk_ult_modi timestamp without time zone not null,
	zk_empresa_core character varying(30) not null,
	zk_cuenta character varying(60) not null,
	zk_eliminado boolean not null,
	zk_fec_elim timestamp without time zone,
	zk_uuid character varying(100) not null
)
WITH (
  OIDS=FALSE
);

ALTER TABLE CS_CATEGORIA 
	ADD CONSTRAINT "CS_CATEGORIA-id_categoria_pk" PRIMARY KEY (id_categoria);

CREATE SEQUENCE CS_CATEGORIA_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;


/* TABLE CS_EMPLEADO */
CREATE TABLE CS_EMPLEADO
(
	id_empleado numeric(9,0) NOT NULL,
	nombre character varying(60) NOT NULL,
	apellido character varying(60) NOT NULL,
	numero_documento numeric(12,0) NOT NULL,
	fecha_nacimiento date,
	genero character varying(2) NOT NULL,
	activo boolean NOT NULL DEFAULT 'true',

	/*campos genericos auditoria*/
	zk_usr_crea character varying(60) not null,
	zk_usr_modi character varying(60),
	zk_fec_crea timestamp without time zone not null,
	zk_ult_modi timestamp without time zone not null,
	zk_empresa_core character varying(30) not null,
	zk_cuenta character varying(60) not null,
	zk_eliminado boolean not null,
	zk_fec_elim timestamp without time zone,
	zk_uuid character varying(100) not null
)
WITH (
  OIDS=FALSE
);

ALTER TABLE CS_EMPLEADO 
	ADD CONSTRAINT "CS_EMPLEADO-id_empleado_pk" PRIMARY KEY (id_empleado);

CREATE SEQUENCE CS_EMPLEADO_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;


/* TABLE CS_PROVEEDORES */
CREATE TABLE CS_PROVEEDORES
(
	id_proveedores numeric(9,0) NOT NULL,
	proveedor character varying(60) NOT NULL,
	contacto character varying(60) NOT NULL,
	direccion character varying(100),
	activo boolean NOT NULL DEFAULT 'true',

	/*campos genericos auditoria*/
	zk_usr_crea character varying(60) not null,
	zk_usr_modi character varying(60),
	zk_fec_crea timestamp without time zone not null,
	zk_ult_modi timestamp without time zone not null,
	zk_empresa_core character varying(30) not null,
	zk_cuenta character varying(60) not null,
	zk_eliminado boolean not null,
	zk_fec_elim timestamp without time zone,
	zk_uuid character varying(100) not null
)
WITH (
  OIDS=FALSE
);

ALTER TABLE CS_PROVEEDORES 
	ADD CONSTRAINT "CS_PROVEEDORES-id_proveedores_pk" PRIMARY KEY (id_proveedores);

CREATE SEQUENCE CS_PROVEEDORES_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;


/* TABLE CS_USUARIO */
CREATE TABLE CS_USUARIO
(
	id_usuario numeric(9,0) NOT NULL,
	usuario character varying(60) NOT NULL,
	contrasenha character varying(60) NOT NULL,
	id_cargo numeric(9,0) NOT NULL,
	id_empleado numeric(9,0) NOT NULL,
	imagen_portada character varying(500) DEFAULT '[]',
	activo boolean NOT NULL DEFAULT 'true',

	/*campos genericos auditoria*/
	zk_usr_crea character varying(60) not null,
	zk_usr_modi character varying(60),
	zk_fec_crea timestamp without time zone not null,
	zk_ult_modi timestamp without time zone not null,
	zk_empresa_core character varying(30) not null,
	zk_cuenta character varying(60) not null,
	zk_eliminado boolean not null,
	zk_fec_elim timestamp without time zone,
	zk_uuid character varying(100) not null
)
WITH (
  OIDS=FALSE
);

ALTER TABLE CS_USUARIO 
	ADD CONSTRAINT "CS_USUARIO-id_usuario_pk" PRIMARY KEY (id_usuario);

CREATE SEQUENCE CS_USUARIO_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;


/* TABLE CS_UTILIZADOR */
CREATE TABLE CS_UTILIZADOR
(
	id_utilizador numeric(9,0) NOT NULL,
	usuario character varying(60) NOT NULL,
	contrasenha character varying(60) NOT NULL,
	id_cargo numeric(9,0) NOT NULL,
	id_empleado numeric(9,0) NOT NULL,
	imagen_portada character varying(500) DEFAULT '[]',
	activo boolean NOT NULL DEFAULT 'true',

	/*campos genericos auditoria*/
	zk_usr_crea character varying(60) not null,
	zk_usr_modi character varying(60),
	zk_fec_crea timestamp without time zone not null,
	zk_ult_modi timestamp without time zone not null,
	zk_empresa_core character varying(30) not null,
	zk_cuenta character varying(60) not null,
	zk_eliminado boolean not null,
	zk_fec_elim timestamp without time zone,
	zk_uuid character varying(100) not null
)
WITH (
  OIDS=FALSE
);

ALTER TABLE CS_UTILIZADOR 
	ADD CONSTRAINT "CS_UTILIZADOR-id_utilizador_pk" PRIMARY KEY (id_utilizador);

CREATE SEQUENCE CS_UTILIZADOR_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;



ALTER TABLE CS_EMPLEADO 
	ADD CONSTRAINT "CS_EMPLEADO-numero_documento_uq" UNIQUE (numero_documento);










ALTER TABLE CS_EMPLEADO 
	ADD CONSTRAINT "CS_EMPLEADO-genero_check" CHECK (genero in ( 'MA', 'null' ));




/* CONSTRAINT CS_USUARIO */
ALTER TABLE CS_USUARIO 
	ADD CONSTRAINT "CS_USUARIO-id_cargo_fk"  FOREIGN KEY (id_cargo)
	REFERENCES cs_cargo (id_cargo) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE CS_USUARIO 
	ADD CONSTRAINT "CS_USUARIO-id_empleado_fk"  FOREIGN KEY (id_empleado)
	REFERENCES cs_empleado (id_empleado) ON UPDATE NO ACTION ON DELETE NO ACTION;

















/* CONSTRAINT CS_UTILIZADOR */
ALTER TABLE CS_UTILIZADOR 
	ADD CONSTRAINT "CS_UTILIZADOR-id_cargo_fk"  FOREIGN KEY (id_cargo)
	REFERENCES cs_cargo (id_cargo) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE CS_UTILIZADOR 
	ADD CONSTRAINT "CS_UTILIZADOR-id_empleado_fk"  FOREIGN KEY (id_empleado)
	REFERENCES cs_empleado (id_empleado) ON UPDATE NO ACTION ON DELETE NO ACTION;

















