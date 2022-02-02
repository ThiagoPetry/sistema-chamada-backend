CREATE TABLE chamada (
    id BIGINT NOT NULL AUTO_INCREMENT,
    matricula_aluno BIGINT NOT NULL,
    presenca BOOLEAN NOT NULL,
    data_hora DATETIME NOT NULL,

    PRIMARY KEY(id)
);