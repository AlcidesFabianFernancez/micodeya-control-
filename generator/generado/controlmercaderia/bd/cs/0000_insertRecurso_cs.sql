

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
	(95, 'Detalle Pedido - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Detalle Pedido - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Detalle Pedido - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Detalle Pedido - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Detalle Pedido - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Empleado - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Empleado - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Empleado - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Empleado - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Empleado - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Movimiento Stock - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Movimiento Stock - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Movimiento Stock - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Movimiento Stock - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Movimiento Stock - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Pedidos - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Pedidos - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Pedidos - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Pedidos - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Pedidos - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Producto - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Producto - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Producto - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Producto - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Producto - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;



INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(95, 'Proveedores - Menu', 'cs', '', 'VIS', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(96, 'Proveedores - Listar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(97, 'Proveedores - Agregar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(98, 'Proveedores - Modificar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
ON CONFLICT (id_recurso)
DO UPDATE SET nombre = EXCLUDED.nombre, grupo = EXCLUDED.grupo, descripcion = EXCLUDED.descripcion, tipo = EXCLUDED.tipo, zk_usr_modi = 'base', zk_ult_modi = EXCLUDED.zk_ult_modi;

INSERT INTO zk_recurso
(id_recurso, nombre, grupo, descripcion, tipo, activo, zk_usr_crea, zk_usr_modi, zk_fec_crea, zk_ult_modi, zk_empresa_core, zk_cuenta, zk_eliminado, zk_fec_elim, zk_uuid)
VALUES
	(99, 'Proveedores - Eliminar', 'cs', '', 'FUN', true, 'base', null, NOW(), NOW(), 'EMPKUAA', 'base', false, null, uuid_generate_v4())
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


