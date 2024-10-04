

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Cargo - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Cargo - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Cargo - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Cargo - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Cargo - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Categoria - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Categoria - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Categoria - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Categoria - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Categoria - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Usuario - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Usuario - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Usuario - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Usuario - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Usuario - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;


