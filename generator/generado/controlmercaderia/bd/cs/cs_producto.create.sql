
/* TABLE CS_PRODUCTO */
CREATE TABLE CS_PRODUCTO
(
	id_producto numeric(9,0) NOT NULL,
	producto character varying(60) NOT NULL,
	descripcion character varying(60),
	precio_unitario numeric(9,0) NOT NULL,
	stock_atual numeric(9,0),
	stock_minimo numeric(9,0) NOT NULL,
	activo boolean NOT NULL DEFAULT 'true',
	id_categoria numeric(9,0) NOT NULL,

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

ALTER TABLE CS_PRODUCTO 
	ADD CONSTRAINT "CS_PRODUCTO-id_producto_pk" PRIMARY KEY (id_producto);

CREATE SEQUENCE CS_PRODUCTO_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;
