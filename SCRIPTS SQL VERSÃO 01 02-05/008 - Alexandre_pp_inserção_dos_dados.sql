insert into arquiteto(nome,cau,telefone,end_escritório) values 
('Sammy Saffe','00000000','3342-2277','pituba'),
('Cassiano Neto','00000000','333333333','pituba'),
('Eduardo Castro','00000000','333333333','pituba'),
('Fernando Mello','00000000','333333333','pituba'),
('Jurandir','00000000','333333333','pituba'),
('Luciano Oliveira','00000000','333333333','pituba'),
('Luís Grappi','00000000','333333333','pituba'),
('Williane','00000000','333333333','pituba'),
('Luís Augusto','00000000','333333333','pituba'),
('Sidney Quintela','00000000','3333-7000','pituba'),
('Luciano Vasconcellos','00000000','333333333','pituba');

insert into coordena(coordenador,projetista) values 
('Dimitri','Carlis'),
('Dimitri','Alexandre'),
('Dimitri','Arthur'),
('Carlis','Rodrigo');

insert into equipe(disciplina,integrante) values 
('Elétrica','Alexandre'),
('Elétrica','Dimitri'),
('Elétrica','Arthur'),
('Hidrossanitário','Carlis'),
('Hidrossanitário','Rodrigo');

insert into projeto(nome,autor,disciplina,data_recebimento) values 
('casa_alberto','Dimitri','Elétrica','2018-06-25'),
('casa_alberto','Rodrigo','Hidrossanitário','2018-06-25'),
('casa_wenes','Alexandre','Elétrica','2018-01-13'),
('casa_wenes','Rodrigo','Hidrossanitário','2018-01-13'),
('casa_mariana','Alexandre','Elétrica','2019-02-10'),
('casa_mariana','Carlis','hidrossanitário','2019-02-10'),
('bahia mar(edificio)','Alexandre','Elétrica','2018-07-07'),
('bahia mar(edificio)','Carlis','Hidrossanitário','2018-07-07'),
('bahia mar(edificio)','Maria do Socorro','incêndio','2018-07-07'),
('bahia mar(edificio)','Alexandre','SPDA','2018-07-07');

insert into projetista(nome,telefone,especialidade,contrato) values 
('Dimitri','333-3333', 'Elétrica','interno'),
('Alexandre','333-3333', 'Elétrica','interno'),
('Rodrigo','333-3333', 'Hidrossanitário','interno'),
('Arthur','333-3333', 'Hidrossanitário','interno'),
('Carlis','333-3333', 'Hidrossanitário','interno'),
('Rafael Cruz','333-3333','Hidrossanitário','terceirizado'),
('Valdecir Lima','333-3333','Elétrica','terceirizado'),
('Maria do Socorro','333-3333','Incêndio','terceirizado'),
('Edfran','333-3333', 'Hidrossanitário','terceirizado');


insert into orgao (nome,telefone,UF) values 
('coelba','08000710800','BA'),
('embasa','08000555195','BA'),
('corpo de bombeiros','71-3116-6621','BA'),
('energisa', '63-3219-5000','TO'),
('corpo de bombeiros','11-3396-2000','SP');


