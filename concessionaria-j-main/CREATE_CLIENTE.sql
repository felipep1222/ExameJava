CREATE TABLE clientes (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    NOME VARCHAR(200),
    EMAIL VARCHAR(200)
)