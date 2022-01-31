CREATE TABLE frequencias (
    id BIGINT NOT NULL AUTO_INCREMENT,
    matricula_aluno BIGINT NOT NULL,
    status BOOLEAN NOT NULL,

    PRIMARY KEY(id)
);

DROP TRIGGER IF EXISTS `sistemachamada`.`frequencia_BEFORE_INSERT`;

DELIMITER $$
USE `sistemachamada`$$
CREATE DEFINER = CURRENT_USER TRIGGER `sistemachamada`.`frequencia_BEFORE_INSERT` BEFORE INSERT ON `frequencias` FOR EACH ROW
BEGIN
	SET @max := (SELECT MAX(id) FROM frequencias);
    SET @count := (SELECT COUNT(id) FROM frequencias);

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