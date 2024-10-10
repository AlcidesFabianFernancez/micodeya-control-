 -- Permiso para casos de android
ALTER TABLE zk_fcm_token ADD COLUMN sin_permiso_android boolean;

-- Usuario que solo provee nombre y no tiene contraseña ni nada
ALTER TABLE zk_usuario ADD COLUMN anonimo boolean;



