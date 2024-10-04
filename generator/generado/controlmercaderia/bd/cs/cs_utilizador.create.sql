
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
