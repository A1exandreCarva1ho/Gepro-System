 
//subconsulta

SELECT nome, telefone FROM projetista WHERE id_projetista =
(SELECT id_projetista FROM projetista WHERE especialidade = 'elétrica');

// exemplo função max para saber qual a ultima revisão de um projeto

SELECT
  max(N_revisão)
FROM
  projeto
WHERE id_projeto = '1';

// descobrir quais registros estão nulos e obter o nome e tel do arquiteto para preencher esse dado.

SELECT
  nome, telefone
FROM
  arquiteto
WHERE cau is Null;

