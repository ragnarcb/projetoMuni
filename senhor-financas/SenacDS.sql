drop database if exists dbteste;

create database dbteste;

use dbteste;

create table usuario (
idusuario int not null primary key auto_increment,
nome varchar(255),
cpf varchar(11) unique,
email varchar(255),
datanascimento date,
login varchar(255),
senha varchar(255)
);


create table receita (
idreceita int not null primary key auto_increment,
idusuario int, foreign key (idusuario) references usuario (idusuario),
descricao varchar(255),
valor decimal(10,2),
datareceita date
);


create table despesa (
iddespesa int not null primary key auto_increment,
idusuario int, foreign key (idusuario) references usuario (idusuario),
descricao varchar(255),
valor decimal(10,2),
datavencimento date,
datapagamento date
);



insert into usuario (nome, cpf, email, datanascimento, login, senha) 
values ('Adriano', '01234567890', 'adriano@gmail.com', '1978-02-24', 'adriano', 'adriano');

insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 8000.00, '2023-01-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 4000.00, '2023-01-21');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 8000.00, '2023-02-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'consultoria', 5000.00, '2023-02-10');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 8000.00, '2023-03-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'consultoria', 2000.00, '2023-03-10');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 3500.00, '2023-03-21');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 8000.00, '2023-04-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 8000.00, '2023-05-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'consultoria', 5000.00, '2023-05-10');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 8000.00, '2023-06-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 1000.00, '2023-06-21');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2023-07-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 5000.00, '2023-07-21');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2023-08-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2023-09-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 2000.00, '2023-09-21');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2023-10-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'consultoria', 3000.00, '2023-10-10');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 3000.00, '2023-10-21');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2023-11-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'consultoria', 4000.00, '2023-11-10');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2023-12-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 5000.00, '2023-12-21');

insert into receita (idusuario, descricao, valor, datareceita) values (1, 'salário', 9000.00, '2022-06-01');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'consultoria', 6000.00, '2022-06-10');
insert into receita (idusuario, descricao, valor, datareceita) values (1, 'aulas', 5000.00, '2022-06-21');

insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-01-10', '2023-01-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 3000.00, '2023-01-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-02-10', '2023-02-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2023-02-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-03-10', '2023-03-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2023-03-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 4000.00, '2023-03-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-04-10', '2023-04-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-05-10', '2023-05-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2023-05-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-06-10', '2023-06-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 2000.00, '2023-06-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-07-10', '2023-07-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2023-07-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-08-10', '2023-08-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2023-08-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 3500.00, '2023-08-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-09-10', '2023-09-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-10-10', '2023-10-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 4500.00, '2023-10-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-11-10', '2023-11-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2023-12-10', '2023-12-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2023-12-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 5000.00, '2023-12-12', null);

insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Aluguel', 2500.00, '2022-12-10', '2022-12-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Escola', 1500.00, '2022-12-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (1, 'Comida', 5000.00, '2022-12-12', null);



insert into usuario (nome, cpf, email, datanascimento, login, senha) values ('Melo', '09876543210', 'melo@gmail.com', '2000-07-17', 'melo', 'melo');

insert into receita (idusuario, descricao, valor, datareceita) values (2, 'salário', 9000.00, '2023-05-01');
insert into receita (idusuario, descricao, valor, datareceita) values (2, 'consultoria', 6000.00, '2023-06-10');
insert into receita (idusuario, descricao, valor, datareceita) values (2, 'aulas', 5000.00, '2023-07-21');
insert into receita (idusuario, descricao, valor, datareceita) values (2, 'salário', 9000.00, '2023-08-01');
insert into receita (idusuario, descricao, valor, datareceita) values (2, 'consultoria', 6000.00, '2023-09-10');
insert into receita (idusuario, descricao, valor, datareceita) values (2, 'aulas', 5000.00, '2023-10-21');

insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (2, 'Aluguel', 2500.00, '2023-05-10', '2023-05-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (2, 'Escola', 1500.00, '2023-06-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (2, 'Comida', 5000.00, '2023-07-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (2, 'Aluguel', 2500.00, '2023-08-10', '2023-05-10');
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (2, 'Escola', 1500.00, '2023-09-12', null);
insert into despesa (idusuario, descricao, valor, datavencimento, datapagamento) values (2, 'Comida', 5000.00, '2023-10-12', null);

select * from usuario;
