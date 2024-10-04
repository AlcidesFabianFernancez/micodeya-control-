
/* TABLE CS_MOVIMIENTO_STOCK */
CREATE TABLE CS_MOVIMIENTO_STOCK
(
	id_movimiento_stock numeric(9,0) NOT NULL,
	id_producto numeric(9,0) NOT NULL,
	cantidad_movimiento numeric(9,0) NOT NULL,
	tipo_movimiento character varying(60) NOT NULL,
	id_usuario numeric(9,0) NOT NULL,

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

ALTER TABLE CS_MOVIMIENTO_STOCK 
	ADD CONSTRAINT "CS_MOVIMIENTO_STOCK-id_movimiento_stock_pk" PRIMARY KEY (id_movimiento_stock);

CREATE SEQUENCE CS_MOVIMIENTO_STOCK_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;
