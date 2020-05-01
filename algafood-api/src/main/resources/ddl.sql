create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
create table cidade (id bigint not null auto_increment, nome varchar(255) not null, estado_id bigint not null, primary key (id)) engine=InnoDB
create table cozinha (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table estado (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table forma_pagamento (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null, nome varchar(255), primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table produto (id bigint not null auto_increment, ativo bit not null, descricao varchar(255), nome varchar(255), preco decimal(19,2), restaurante_id bigint not null, primary key (id)) engine=InnoDB
create table restaurante (id bigint not null auto_increment, data_atualizacao datetime(6), data_cadastro datetime(6) not null, endereco_bairro varchar(255), endereco_cep varchar(255), endereco_complemento varchar(255), endereco_logradouro varchar(255), endereco_numero varchar(255), nome varchar(255) not null, taxa_frete decimal(19,2) not null, cozinha_id bigint not null, endereco_cidade_id bigint, primary key (id)) engine=InnoDB
create table restaurante_forma_pagamento (restaurante_id bigint not null, forma_pagamento_id bigint not null) engine=InnoDB
create table usuario (id bigint not null, data_cadastro date, email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table cidade add constraint FKkworrwk40xj58kevvh3evi500 foreign key (estado_id) references estado (id)
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table produto add constraint FKb9jhjyghjcn25guim7q4pt8qx foreign key (restaurante_id) references restaurante (id)
alter table restaurante add constraint FK76grk4roudh659skcgbnanthi foreign key (cozinha_id) references cozinha (id)
alter table restaurante add constraint FKbc0tm7hnvc96d8e7e2ulb05yw foreign key (endereco_cidade_id) references cidade (id)
alter table restaurante_forma_pagamento add constraint FK7aln770m80358y4olr03hyhh2 foreign key (forma_pagamento_id) references forma_pagamento (id)
alter table restaurante_forma_pagamento add constraint FKa30vowfejemkw7whjvr8pryvj foreign key (restaurante_id) references restaurante (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
insert into cozinha (id, nome) values (1, 'Tailandesa')
insert into cozinha (id, nome) values (2, 'Indiana')
insert into estado (id, nome) values (1, 'Minas Gerais')
insert into estado (id, nome) values (2, 'São Paulo')
insert into estado (id, nome) values (3, 'Ceará')
insert into estado (id, nome) values (4, 'Rio de Janeiro')
insert into cidade (id, nome, estado_id) values (1, 'Uberlândia', 1)
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1)
insert into cidade (id, nome, estado_id) values (3, 'São Paulo', 2)
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2)
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) values (1, utc_timestamp, 'Thai Gourmet', 10, 1, "01443-002", "Av Paulista", "123", "Ap 63", "Centro", 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (2, utc_timestamp, 'Thai Delivery', 0, 1)
insert into restaurante (id, data_cadastro, nome, taxa_frete, cozinha_id) values (3, utc_timestamp, 'Tuk Tuk Comida Indiana', 0, 2)
insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito')
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito')
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro')
insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas')
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas')
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Marguerita", "Suculenta", 35.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Pizza Queijo", "Gostosa", 23.90, true, 1)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Arroz com Feijão", "Para o dia a dia", 15.00, true, 2)
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Caldo Verde", "Comida Fitness", 17.00, true, 2)
