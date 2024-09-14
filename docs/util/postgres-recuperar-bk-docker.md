
## Eliminar y crear esquema
- Conectarse al psql de contenedor
- Ejemplo
> docker exec -it <tu-contenedor> psql -U <tu-usuario> -d <tu-base-datos>
- Con datos 
> docker exec -it controlmercaderia-container psql -U controlmercaderiauser -d controlmercaderiabd

Eliminar
> DROP SCHEMA IF EXISTS public CASCADE;

Crear nuevo esquema y cambiar su owner
> CREATE SCHEMA public AUTHORIZATION controlmercaderiauser;

Crear extension
> CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

Probar extension
> select uuid_generate_v4();

## Recuperar BD

### Importar BD via pg_restore, formato custom
- Ejemplo
> docker exec -i <tu-contenedor> pg_restore --verbose --clean --no-acl --no-owner -h localhost -U <tu-usuario> -d <tu-base-datos> < ./latest.dump

- Con datos mac
> docker exec -i controlmercaderia-container pg_restore --verbose --clean --no-acl --no-owner -h localhost -U controlmercaderiauser -d controlmercaderiabd < '/Users/TU_USUARIO/opt/bkbd/controlmercaderia/archivo_20231211084140.backup'

- Con datos windows (usar cmd)
> cd ....raiz del proyecto donde esta el docker-compose.yaml
> docker exec -i controlmercaderia-container pg_restore --verbose --clean --no-acl --no-owner -h localhost -U controlmercaderiauser -d controlmercaderiabd < "C:\Users\TU_USUARIO\opt\bkbd\controlmercaderia\archivo_20231211084140.backup"
- Otro con datos

### Importar BD via psql, formato pgsql,sql
- https://davejansen.com/how-to-dump-and-restore-a-postgresql-database-from-a-docker-container/

