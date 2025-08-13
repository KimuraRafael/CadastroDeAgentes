--V2: Migration para adicionar a coluna de Rank na tabela de tarefas
--Uma vez executado este arquivo se torna imutável, então qualquer adição neste arquivo não surtirá efeito
ALTER TABLE tarefas
ADD COLUMN rank VARCHAR(255);