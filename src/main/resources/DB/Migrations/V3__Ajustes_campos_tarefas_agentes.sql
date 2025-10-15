--V3: Migration para Organizar colunas nos modelos que realmente faz sentido atribuir

ALTER TABLE tarefas ADD COLUMN nivel_de_demanda VARCHAR(100);


ALTER TABLE agentes ADD COLUMN responsabilidade VARCHAR(100);


ALTER TABLE agentes DROP COLUMN nivel_de_demanda;


ALTER TABLE tarefas DROP COLUMN responsabilidade;
