
/* CONSTRAINT DROP CS_MOVIMIENTO_STOCK */
ALTER TABLE IF EXISTS CS_MOVIMIENTO_STOCK 
	DROP  CONSTRAINT IF EXISTS "CS_MOVIMIENTO_STOCK-id_producto_fk"; 

ALTER TABLE IF EXISTS CS_MOVIMIENTO_STOCK 
	DROP  CONSTRAINT IF EXISTS "CS_MOVIMIENTO_STOCK-id_usuario_fk"; 











