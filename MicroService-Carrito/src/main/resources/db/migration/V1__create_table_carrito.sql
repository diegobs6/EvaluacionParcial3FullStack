CREATE TABLE carrito
(
    id_carrito     BIGINT AUTO_INCREMENT NOT NULL,
    id_usuario     BIGINT NULL,
    fecha_creacion datetime NULL,
    estado         VARCHAR(255) NULL,
    CONSTRAINT pk_carrito PRIMARY KEY (id_carrito)
);

CREATE TABLE item_carrito
(
    id_item     BIGINT AUTO_INCREMENT NOT NULL,
    id_producto BIGINT NOT NULL,
    cantidad    INT NULL,
    precio_uni DOUBLE NULL,
    id_carrito  BIGINT NULL,
    CONSTRAINT pk_item_carrito PRIMARY KEY (id_item)
);

ALTER TABLE item_carrito
    ADD CONSTRAINT FK_ITEM_CARRITO_ON_ID_CARRITO FOREIGN KEY (id_carrito) REFERENCES carrito (id_carrito);