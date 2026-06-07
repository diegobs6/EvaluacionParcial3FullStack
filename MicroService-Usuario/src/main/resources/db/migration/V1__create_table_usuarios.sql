CREATE TABLE usuarios
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    run       VARCHAR(13)  NOT NULL,
    nombre    VARCHAR(150) NOT NULL,
    apellido  VARCHAR(150) NOT NULL,
    fecha_nac datetime     NOT NULL,
    correo    VARCHAR(150) NOT NULL,
    direccion VARCHAR(300) NOT NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY (id)
);

ALTER TABLE usuarios
    ADD CONSTRAINT uc_usuarios_run UNIQUE (run);