

ALTER TABLE CS_EMPLEADO 
	ADD CONSTRAINT "CS_EMPLEADO-numero_documento_uq" UNIQUE (numero_documento);










ALTER TABLE CS_EMPLEADO 
	ADD CONSTRAINT "CS_EMPLEADO-genero_check" CHECK (genero in ( 'MA', 'null' ));


