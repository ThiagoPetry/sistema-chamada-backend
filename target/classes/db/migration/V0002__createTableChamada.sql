CREATE TABLE chamada (
    id BIGINT NOT NULL AUTO_INCREMENT,
    matricula_aluno BIGINT NOT NULL,
    presenca BOOLEAN NOT NULL,
    data_hora DATETIME NOT NULL,

    PRIMARY KEY(id)
);

DROP TRIGGER IF EXISTS `sistemachamada`.`chamada_BEFORE_INSERT`;

DELIMITER $$
USE `sistemachamada`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistemachamada`.`chamada_BEFORE_INSERT` BEFORE INSERT ON `chamada` FOR EACH ROW
BEGIN
	SET @max := (SELECT MAX(id) FROM chamada);
    SET @count := (SELECT COUNT(id) FROM chamada);

    if (@max = @count) THEN
		SET @next_value := @max + 1;
    elseif (@count = 0) THEN
        SET @next_value := 1;
    else
		SET @next_value := @count;
    end if;

    SET NEW.id = @next_value;
END$$
DELIMITER ;