ALTER TABLE medicos ADD COLUMN ativo tinyint;
UPDATE medicos set ativo = 1
