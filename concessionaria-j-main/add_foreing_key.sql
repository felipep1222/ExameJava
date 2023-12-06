-- create table veiculos(
--     id number GENERATED always as IDENTITY PRIMARY key,
--     marca varchar(200),
--     modelo varchar(200),
--     ano int,
--     valor number(9,2),
--     cliente_id number
-- );

alter table VEICULOS add CONSTRAINT fk_veiculos_cliente 
FOREIGN KEY (cliente_id)
REFERENCES clientes(id)