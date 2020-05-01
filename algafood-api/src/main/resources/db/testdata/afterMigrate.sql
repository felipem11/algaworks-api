set foreign_key_checks = 0;

delete from cidade;
delete from cozinha;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from produto;
delete from restaurante;
delete from restaurante_forma_pagamento;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'São Paulo');
insert into estado (id, nome) values (3, 'Ceará');
insert into estado (id, nome) values (4, 'Rio de Janeiro');

insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1);
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1);
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);	

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1); 
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2);
