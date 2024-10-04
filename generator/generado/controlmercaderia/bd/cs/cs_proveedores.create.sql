
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
