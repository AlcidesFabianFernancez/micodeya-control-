
/* TABLE CS_PEDIDOS */
CREATE TABLE CS_PEDIDOS
(
	id_pedidos numeric(9,0) NOT NULL,
	id_proveedor numeric(9,0) NOT NULL,
	fecha_pedido date NOT NULL,
	fecha_entrega date NOT NULL,
	estado_pedido character varying(60) NOT NULL,

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

ALTER TABLE CS_PEDIDOS 
	ADD CONSTRAINT "CS_PEDIDOS-id_pedidos_pk" PRIMARY KEY (id_pedidos);

CREATE SEQUENCE CS_PEDIDOS_SEQ
	increment 1 minvalue 1 maxvalue 9223372036854775807 start 100 cache 1;
