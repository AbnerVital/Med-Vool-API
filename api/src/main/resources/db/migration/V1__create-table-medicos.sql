CREATE TABLE medicos (
    id INTEGER PRIMARY KEY AUTOINCREMENT, -- Corrigido aqui!
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    crm VARCHAR(6) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    logradouro VARCHAR(100) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL,
    cidade VARCHAR(100) NOT NULL
    -- O 'primary key(id)' no final não é mais necessário aqui
    -- porque já foi definido junto ao 'id' na linha do 'id'.
    -- Se você mantiver, pode gerar um erro de PRIMARY KEY duplicada.
);