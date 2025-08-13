--V2: Migration para adicionar a coluna de Rank na tabela de tarefas

ALTER TABLE tarefas
ADD COLUMN rank VARCHAR(255);
