drop database proj_pratico;

CREATE DATABASE proj_pratico
default character set utf8
default collate utf8_general_ci;

USE proj_pratico;

CREATE TABLE arquiteto(
id_arquiteto int(6) NOT NULL AUTO_INCREMENT,
nome varchar(50),
cau varchar(15),
telefone varchar(20),
email varchar(30) default 'sem email',
end_escritório varchar(50),
PRIMARY KEY (id_arquiteto)
)default charset utf8;

CREATE TABLE projeto(
id_projeto int(6) NOT NULL AUTO_INCREMENT,
nome varchar(50),
autor varchar(50),
arquiteto varchar(50),
disciplina varchar(20),
data_recebimento date,
data_inicio date,
data_termino date,
data_entrega date,
status_aprovação enum('aprovado','não_aprovado') default 'não_aprovado',
revisão int(3)NOT NULL default'0',
PRIMARY KEY (id_projeto),
FOREIGN KEY (arquiteto) references arquiteto(nome)
)default charset utf8;

CREATE TABLE equipe(
disciplina varchar(15),
integrante varchar(50),
PRIMARY KEY (integrante)
)default charset utf8;

CREATE TABLE projetista(
id_projetista int(3) AUTO_INCREMENT,
nome varchar(50),
telefone varchar(20),
especialidade varchar(20),
contrato Enum('interno','terceirizado'),
KEY (id_projetista)
)default charset utf8;

CREATE TABLE orgao(
id_orgao int(3) AUTO_INCREMENT,
nome varchar(25),
telefone varchar(20),
UF varchar(50) DEFAULT 'BRASIL_INTEIRO',
PRIMARY KEY (id_orgao)
)default charset utf8;

CREATE TABLE obra(
id_obra int(3) AUTO_INCREMENT,
nome_responsavel varchar(50),
endereço_construção varchar(100),
tipo varchar (20),
PRIMARY KEY (id_obra)
)default charset utf8;

CREATE TABLE coordena(
coordenador varchar(50),
projetista varchar(50),
PRIMARY KEY (coordenador,projetista),
FOREIGN KEY (coordenador) references projetista(nome),
FOREIGN KEY (projetista) references projetista(nome)
)default charset utf8;

CREATE TABLE libera(
id_orgao varchar(3) NOT NULL,
id_projeto int(6) NOT NULL,
aprovacao_orgao Enum('aprovado','não_aprovado'),
Documentação varchar(20),
PRIMARY KEY (id_orgao,id_projeto),
FOREIGN KEY (id_orgao) references orgaos(id_orgao),
FOREIGN KEY (id_projeto) references id_projeto(projeto)
)default charset utf8;
