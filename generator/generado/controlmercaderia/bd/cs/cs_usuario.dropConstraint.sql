
/* CONSTRAINT DROP CS_USUARIO */
ALTER TABLE IF EXISTS CS_USUARIO 
	DROP  CONSTRAINT IF EXISTS "CS_USUARIO-id_cargo_fk"; 

ALTER TABLE IF EXISTS CS_USUARIO 
	DROP  CONSTRAINT IF EXISTS "CS_USUARIO-id_empleado_fk"; 















