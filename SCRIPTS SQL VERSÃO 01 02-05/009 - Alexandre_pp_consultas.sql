use proj_pratico;

SELECT nome
FROM projetista
WHERE contrato = 'interno'
ORDER BY nome;

SELECT nome
FROM projetista
WHERE contrato = 'terceirizado'
ORDER BY especialidade;


SELECT DISTINCT coordena.coordenador, projetista.especialidade as departamento
FROM coordena join projetista on coordena.coordenador = projetista.nome;

SELECT nome, UF
FROM orgao
WHERE UF ='BA'
ORDER BY nome;

SELECT nome, autor, disciplina, data_recebimento,revisão
FROM projeto
WHERE autor ='rodrigo' or autor = 'carlis'
ORDER BY data_recebimento;

select disciplina, count(integrante) as n°_de_integrantes
from equipe
where disciplina = 'hidrossanitário'
having count(integrante);