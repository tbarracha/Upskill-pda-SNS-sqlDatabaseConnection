-- ** eliminar tabelas se existentes **
-- CASCADE CONSTRAINTS para eliminar as restri??es de integridade das chaves prim?rias e chaves ?nicas
-- PURGE elimina a tabela da base de dados e da "reciclagem"

DROP TABLE empregado                CASCADE CONSTRAINTS PURGE;
DROP TABLE empregadoEfetivo         CASCADE CONSTRAINTS PURGE;
DROP TABLE empregadoTemporario      CASCADE CONSTRAINTS PURGE;
DROP TABLE falta                    CASCADE CONSTRAINTS PURGE;
DROP TABLE ferias                   CASCADE CONSTRAINTS PURGE;
DROP TABLE avaliacao                CASCADE CONSTRAINTS PURGE;
DROP TABLE avaliacaoTemporario      CASCADE CONSTRAINTS PURGE;
DROP TABLE avaliacaoEfetivo         CASCADE CONSTRAINTS PURGE;
DROP TABLE departamento             CASCADE CONSTRAINTS PURGE;
DROP TABLE empregadoDepartamento    CASCADE CONSTRAINTS PURGE;

-- ** criação de tabelas **

CREATE TABLE empregado (
    idEmpregado             INTEGER GENERATED AS IDENTITY   CONSTRAINT pkEmpregadoIdEmpregado           PRIMARY KEY,
    nome                    VARCHAR(40)                     CONSTRAINT nnEmpregadoNome                  NOT NULL,
    dataNascimento          DATE                            CONSTRAINT nnEmpregadoDataNascimento        NOT NULL,
    nrIdentificacaoCivil    INTEGER                         CONSTRAINT ckEmpregadoNrIdentificacaoCivil  CHECK(REGEXP_LIKE(nrIdentificacaoCivil, '^\d{6,}$'))
                                                            CONSTRAINT ukEmpregadoNrIdentificacaoCivil  UNIQUE,
    nif                     INTEGER                         CONSTRAINT nnEmpregadoNif                   NOT NULL
                                                            CONSTRAINT ckEmpregadoNif                   CHECK(REGEXP_LIKE(nif, '^\d{9}$'))
                                                            CONSTRAINT ukEmpregadoNif                   UNIQUE
);

CREATE TABLE empregadoEfetivo (
    idEmpregado         INTEGER         CONSTRAINT pkEmpregadoEfetivoIdEmpregado   PRIMARY KEY,
    salarioMensalBase   NUMERIC(10,2)   CONSTRAINT nnEmpregadoEfetivoSalarioMensalBase   NOT NULL
                                        CONSTRAINT ckEmpregadoEfetivoSalarioMensalBase   CHECK (salarioMensalBase >= 500)
);

CREATE TABLE empregadoTemporario (
    idEmpregado INTEGER         CONSTRAINT pkEmpregadoTemporarioIdEmpregado PRIMARY KEY,
    salarioHora NUMERIC(4,2)    CONSTRAINT nnEmpregadoTemporarioSalarioHora NOT NULL
);

CREATE TABLE falta (
    idEmpregado     INTEGER,
    dataInicio      DATE,
    dataFim         DATE,
    justificacao    VARCHAR(50),
                            
    CONSTRAINT pkFaltaIdEmpregadoDataInicio PRIMARY KEY (idEmpregado, dataInicio),
    CONSTRAINT ckFaltaDataInicioDataFim     CHECK (dataFim>=dataInicio)
);

CREATE TABLE ferias (
    idEmpregado     INTEGER,
    dataInicio      DATE,
    dataFim         DATE        CONSTRAINT nnFeriasDataFim   NOT NULL,
    
    CONSTRAINT pkFeriasIdEmpregadoDataInicio    PRIMARY KEY (idEmpregado, dataInicio),
    CONSTRAINT ckFeriasDataInicioDataFim        CHECK(dataFim>=dataInicio)
);

CREATE TABLE avaliacao (
    idAvaliacao VARCHAR(3)  CONSTRAINT pkAvaliacaoIdAvaliacao   PRIMARY KEY,
    descricao   VARCHAR(15) CONSTRAINT nnAvaliacaoDescricao     NOT NULL
);

CREATE TABLE avaliacaoTemporario (
    idDepartamento  VARCHAR(5),
    idEmpregado     INTEGER,
    dataInicio      DATE,
    idAvaliacao     VARCHAR(3),
    
    CONSTRAINT pkAvaliacaoTemporarioIdEmpregadoDataInicio   PRIMARY KEY (idDepartamento, idEmpregado, dataInicio)
);

CREATE TABLE avaliacaoEfetivo (
    idEmpregado INTEGER,
    ano         INTEGER     CONSTRAINT ckAvaliacaoEfetivoAno            CHECK(ano>=2015)    NOT NULL,
    idAvaliacao VARCHAR(3)  CONSTRAINT nnAvaliacaoEfetivoIdAvaliacao    NOT NULL,
    
    CONSTRAINT pkAvaliacaoEfetivoIdEmpregadoAno PRIMARY KEY (idEmpregado, ano)
);

CREATE TABLE departamento (
    idDepartamento          VARCHAR(5)  CONSTRAINT pkDepartamentoIdDepartamento PRIMARY KEY,
    idDepartamentoSuperior  VARCHAR(5),
    designacao              VARCHAR(50) CONSTRAINT nnDepartamentoIdDesignacao   NOT NULL
);


CREATE TABLE empregadoDepartamento (
    idDepartamento  VARCHAR(5),
    idEmpregado     INTEGER,
    dataInicio      DATE,
    dataFim         DATE,
    
    CONSTRAINT pkEmpregadoDepartamentoIdDepartamentoIdEmpregadoDataInicio PRIMARY KEY (idDepartamento, idEmpregado, dataInicio),
    CONSTRAINT ckEmpregadoDepartamentoDataInicioDataFim                   CHECK(dataFim>=dataInicio)
);


-- ** chaves estrangeiras ** 

ALTER TABLE empregadoEfetivo    ADD CONSTRAINT fkEmpregadoEfetivoIdEmpregado                            FOREIGN KEY (idEmpregado)                               REFERENCES empregado (idEmpregado);

ALTER TABLE empregadoTemporario ADD CONSTRAINT fkEmpregadoTemporarioIdEmpregado                         FOREIGN KEY (idEmpregado)                               REFERENCES empregado (idEmpregado);

ALTER TABLE falta               ADD CONSTRAINT fkFaltaIdEmpregado                                       FOREIGN KEY (idEmpregado)                               REFERENCES empregado (idEmpregado);

ALTER TABLE ferias              ADD CONSTRAINT fkFeriasIdEmpregado                                      FOREIGN KEY (idEmpregado)                               REFERENCES empregadoEfetivo (idEmpregado);

ALTER TABLE avaliacaoTemporario ADD CONSTRAINT fkAvaliacaoTemporarioIdEmpregado                         FOREIGN KEY (idEmpregado)                               REFERENCES empregadoTemporario (idEmpregado);
ALTER TABLE avaliacaoTemporario ADD CONSTRAINT fkAvaliacaoTemporarioIdDepartamentoIdEmpregadoDataInicio FOREIGN KEY (idDepartamento, idEmpregado, dataInicio)   REFERENCES empregadoDepartamento (idDepartamento, idEmpregado, dataInicio);
ALTER TABLE avaliacaoTemporario ADD CONSTRAINT fkAvaliacaoTemporarioIdAvaliacao                         FOREIGN KEY (idAvaliacao)                               REFERENCES avaliacao (idAvaliacao);

ALTER TABLE avaliacaoEfetivo    ADD CONSTRAINT fkAvaliacaoEfetivoIdEmpregado                            FOREIGN KEY (idEmpregado)                               REFERENCES empregadoEfetivo (idEmpregado);
ALTER TABLE avaliacaoEfetivo    ADD CONSTRAINT fkAvaliacaoEfetivoIdAvaliacao                            FOREIGN KEY (idAvaliacao)                               REFERENCES avaliacao (idAvaliacao);

ALTER TABLE departamento        ADD CONSTRAINT fkDepartamentoidDepartamentoSuperior                     FOREIGN KEY (idDepartamentoSuperior)                    REFERENCES departamento (idDepartamento);

ALTER TABLE empregadoDepartamento ADD CONSTRAINT fkEmpregadoDepartamentoIdDepartamento                  FOREIGN KEY (idDepartamento)                            REFERENCES departamento (idDepartamento);
ALTER TABLE empregadoDepartamento ADD CONSTRAINT fkEmpregadoDepartamentoIdEmpregado                     FOREIGN KEY (idEmpregado)                               REFERENCES empregado (idEmpregado);

-- ** guardar em DEFINITIVO as altera??es na base de dados, se a op??o Autocommit do SQL Developer n?o estiver ativada **
-- COMMIT;


-- ** tabela Empregado **

INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Belmiro Cunha', TO_DATE('1985-01-13','yyyy-mm-dd'), 1111111, 111111111); 
INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Luisa Coelho',  TO_DATE('1980-05-03','yyyy-mm-dd'), 2222222, 222222222); 
INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Jo?o Pereira',  TO_DATE('1970-09-05','yyyy-mm-dd'), 3333333, 333333333); 
INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Carlos Silva',  TO_DATE('1983-02-10','yyyy-mm-dd'), 4444444, 444444444); 
INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Anibal Dias',   TO_DATE('1982-10-12','yyyy-mm-dd'), 5555555, 555555555); 
INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Anibal Dias',   TO_DATE('1983-04-22','yyyy-mm-dd'), 6666666, 666666666); 
INSERT INTO empregado (nome, dataNascimento, nrIdentificacaoCivil, nif) VALUES ('Joana Freitas', TO_DATE('1995-03-15','yyyy-mm-dd'), 7777777, 777777777); 

-- ** tabela Falta **

INSERT INTO falta (idEmpregado, dataInicio, dataFim, justificacao) VALUES (2, TO_DATE('2015-05-15','yyyy-mm-dd'), TO_DATE('2015-05-20','yyyy-mm-dd'), 'gripe'); 
INSERT INTO falta (idEmpregado, dataInicio, dataFim, justificacao) VALUES (3, TO_DATE('2018-11-05','yyyy-mm-dd'), TO_DATE('2018-11-15','yyyy-mm-dd'), 'apoio a familiares'); 
INSERT INTO falta (idEmpregado, dataInicio, dataFim, justificacao) VALUES (5, TO_DATE('2018-08-15','yyyy-mm-dd'), TO_DATE('2018-08-31','yyyy-mm-dd'), 'fratura'); 
INSERT INTO falta (idEmpregado, dataInicio, dataFim, justificacao) VALUES (6, TO_DATE('2020-09-25','yyyy-mm-dd'), TO_DATE('2020-09-28','yyyy-mm-dd'), 'gripe'); 

-- ** tabela EmpregadoEfetivo **

INSERT INTO empregadoEfetivo (idEmpregado, salarioMensalBase) VALUES (1,1500); 
INSERT INTO empregadoEfetivo (idEmpregado, salarioMensalBase) VALUES (2,700);
INSERT INTO empregadoEfetivo (idEmpregado, salarioMensalBase) VALUES (3,1000);
INSERT INTO empregadoEfetivo (idEmpregado, salarioMensalBase) VALUES (4,1000);

-- ** tabela EmpregadoTemporario **

INSERT INTO empregadoTemporario (idEmpregado, salarioHora) VALUES (5,7); 
INSERT INTO empregadoTemporario (idEmpregado, salarioHora) VALUES (6,7);
INSERT INTO empregadoTemporario (idEmpregado, salarioHora) VALUES (7,5);

-- ** tabela Departamento **
INSERT INTO departamento (idDepartamento, idDepartamentoSuperior, designacao) VALUES ('DIR', NULL, 'Dire??o'); 
INSERT INTO departamento (idDepartamento, idDepartamentoSuperior, designacao) VALUES ('DRH', 'DIR', 'Departamento de Recursos Humanos'); 
INSERT INTO departamento (idDepartamento, idDepartamentoSuperior, designacao) VALUES ('DSI', 'DIR', 'Departamento de Sistemas Inform?ticos'); 
INSERT INTO departamento (idDepartamento, idDepartamentoSuperior, designacao) VALUES ('DAU', 'DSI', 'Departamento de Apoio ao Utilizador'); 
INSERT INTO departamento (idDepartamento, idDepartamentoSuperior, designacao) VALUES ('DMI', 'DSI', 'Departamento de Manuten??o Inform?tica'); 

-- ** tabela EmpregadoDepartamento **

INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DIR', 1, TO_DATE('2010-09-15','yyyy-mm-dd'), NULL); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DRH', 2, TO_DATE('2010-10-26','yyyy-mm-dd'), NULL); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DAU', 3, TO_DATE('2015-03-07','yyyy-mm-dd'), TO_DATE('2019-09-09','yyyy-mm-dd')); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DMI', 3, TO_DATE('2019-09-10','yyyy-mm-dd'), NULL); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DAU', 4, TO_DATE('2018-04-12','yyyy-mm-dd'), NULL); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DAU', 5, TO_DATE('2018-08-01','yyyy-mm-dd'), TO_DATE('2018-08-31','yyyy-mm-dd')); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DMI', 6, TO_DATE('2020-09-20','yyyy-mm-dd'), TO_DATE('2020-09-30','yyyy-mm-dd')); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DRH', 7, TO_DATE('2019-11-15','yyyy-mm-dd'), TO_DATE('2019-12-31','yyyy-mm-dd')); 
INSERT INTO empregadoDepartamento (idDepartamento, idEmpregado, dataInicio, dataFim) VALUES ('DRH', 7, TO_DATE('2020-03-15','yyyy-mm-dd'), TO_DATE('2020-04-15','yyyy-mm-dd')); 

-- ** tabela Avaliacao **

INSERT INTO avaliacao (idAvaliacao, descricao) VALUES ('MB', 'MUITO BOM'); 
INSERT INTO avaliacao (idAvaliacao, descricao) VALUES ('B', 'BOM');
INSERT INTO avaliacao (idAvaliacao, descricao) VALUES ('S', 'SUFICIENTE');
INSERT INTO avaliacao (idAvaliacao, descricao) VALUES ('I', 'INSUFICIENTE');

-- ** tabela AvaliacaoTemporario **

INSERT INTO avaliacaoTemporario (idDepartamento, idEmpregado, dataInicio, idAvaliacao) VALUES ('DAU', 5, TO_DATE('2018-08-01','yyyy-mm-dd'), 'S');
INSERT INTO avaliacaoTemporario (idDepartamento, idEmpregado, dataInicio, idAvaliacao) VALUES ('DMI', 6, TO_DATE('2020-09-20','yyyy-mm-dd'), 'S');
INSERT INTO avaliacaoTemporario (idDepartamento, idEmpregado, dataInicio, idAvaliacao) VALUES ('DRH', 7, TO_DATE('2019-11-15','yyyy-mm-dd'), 'MB');
INSERT INTO avaliacaoTemporario (idDepartamento, idEmpregado, dataInicio, idAvaliacao) VALUES ('DRH', 7, TO_DATE('2020-03-15','yyyy-mm-dd'), 'MB');

-- ** tabela AvaliacaoEfetivo **

INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (1, 2015, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (1, 2016, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (1, 2017, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (1, 2018, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (1, 2019, 'MB');

INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (2, 2015, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (2, 2016, 'B'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (2, 2017, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (2, 2018, 'MB'); 
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (2, 2019, 'MB');

INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (3, 2016, 'S');
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (3, 2017, 'S');
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (3, 2018, 'S');
INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (3, 2019, 'S');

INSERT INTO avaliacaoEfetivo (idEmpregado, ano, idAvaliacao) VALUES (4, 2019, 'MB');

-- ** tabela Ferias **

INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2011-08-15','yyyy-mm-dd'), TO_DATE('2011-08-31','yyyy-mm-dd')); 
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2012-08-15','yyyy-mm-dd'), TO_DATE('2012-08-31','yyyy-mm-dd')); 
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2013-08-01','yyyy-mm-dd'), TO_DATE('2013-08-11','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2014-08-15','yyyy-mm-dd'), TO_DATE('2014-08-31','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2015-08-15','yyyy-mm-dd'), TO_DATE('2015-08-31','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2016-08-15','yyyy-mm-dd'), TO_DATE('2016-08-31','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2017-08-15','yyyy-mm-dd'), TO_DATE('2017-08-31','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (1, TO_DATE('2019-08-01','yyyy-mm-dd'), TO_DATE('2019-08-31','yyyy-mm-dd'));

INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (2, TO_DATE('2014-03-01','yyyy-mm-dd'), TO_DATE('2014-03-25','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (2, TO_DATE('2015-07-01','yyyy-mm-dd'), TO_DATE('2015-07-20','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (2, TO_DATE('2016-06-01','yyyy-mm-dd'), TO_DATE('2016-06-30','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (2, TO_DATE('2017-07-01','yyyy-mm-dd'), TO_DATE('2017-07-27','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (2, TO_DATE('2018-05-01','yyyy-mm-dd'), TO_DATE('2018-05-31','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (2, TO_DATE('2019-12-10','yyyy-mm-dd'), TO_DATE('2019-12-31','yyyy-mm-dd'));

INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (3, TO_DATE('2016-06-01','yyyy-mm-dd'), TO_DATE('2016-06-30','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (3, TO_DATE('2017-07-01','yyyy-mm-dd'), TO_DATE('2017-07-11','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (3, TO_DATE('2018-03-01','yyyy-mm-dd'), TO_DATE('2018-03-18','yyyy-mm-dd'));
INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (3, TO_DATE('2019-05-01','yyyy-mm-dd'), TO_DATE('2019-05-15','yyyy-mm-dd'));

INSERT INTO ferias (idEmpregado, dataInicio, dataFim) VALUES (4, TO_DATE('2019-05-01','yyyy-mm-dd'), TO_DATE('2019-05-31','yyyy-mm-dd'));

-- ** guardar em DEFINITIVO as altera??es na base de dados, se a op??o Autocommit do SQL Developer n?o estiver ativada **
-- COMMIT;

--A. Cláusulas Join
--1) Obter o produto cartesiano (i.e. CROSS JOIN) entre as tabelas Empregado e EmpregadoEfetivo
select e.idEmpregado, ee.idEmpregado as idEmpregadoEfetivo
from empregado e
cross join empregadoEfetivo ee;



--2)Obter toda a informação dos empregados efetivos, por ordem alfabética dos nomes
select e.*, ee.salarioMensalBase from empregado e join empregadoEfetivo ee on e.idEmpregado = ee.idEmpregado
order by e.nome;

--3)Obter as faltas dos empregados efetivos
select e.*, f.* from empregado e join falta f on e.idEmpregado = f.idEmpregado 
join empregadoEfetivo ee on e.idEmpregado = ee.idEmpregado;

--4)Obter as faltas dos empregados temporários
select e.nome, e.nrIdentificacaoCivil, et.idEmpregado, f.dataInicio, f.dataFim, f.justificacao
from empregado e
join empregadoTemporario et on e.idEmpregado = et.idEmpregado
join falta f on et.idEmpregado = f.idEmpregado;

--5)Obter as avaliações dos empregados temporários

select distinct e.idEmpregado, e.nome, e.nrIdentificacaoCivil, ed.idDepartamento, ed.dataInicio, ed.dataFim, a.descricao
from empregado e
join avaliacaotemporario at on e.idEmpregado = at.idEmpregado
join avaliacao a on at.idAvaliacao = a.idAvaliacao
join empregadodepartamento ed on e.idEmpregado = ed.idEmpregado
where e.idEmpregado in (select idEmpregado from empregadotemporario)
order by e.idEmpregado, ed.dataInicio;

--6)Obter o nome e o número de identificação civil de todos empregados que nunca faltaram

select
  e.nome,
  e.nrIdentificacaoCivil
from
  empregado e
where
  not exists (
    select 1
    from falta f
    where f.idEmpregado = e.idEmpregado
  );


--7) Obter o identificador e a designação de todos os departamentos juntamente com a designação do
--respetivo departamento do nível hierárquico superior
select
  d1.idDepartamento,
  d1.designacao as designacaoDepartamento,
  d2.designacao as designacaoDepartamentoSuperior
from
  departamento d1
left join
  departamento d2 on d1.idDepartamentoSuperior = d2.idDepartamento
  order by
  d1.designacao desc;

--8)Obter pares de empregados distintos e com idades diferentes
select
  e1.idEmpregado as idEmpregado1,
  e1.nome as nome1,
  e2.idEmpregado as idEmpregado2,
  e2.nome as nome2
from
  empregado e1
join
  empregado e2 ON e1.idEmpregado < e2.idEmpregado
where
  abs(extract(year from current_date) - extract(year from e1.dataNascimento)) !=
  abs(extract(year from current_date) - extract(year from e2.dataNascimento));





--B)
--1) Obter o nome e o número de identificação civil de todos os empregados que tiveram pelo menos uma
--avaliação "MUITO BOM"

select
  e.nome,
  e.nrIdentificacaoCivil
from
  empregado e
where
  e.idEmpregado IN (
    select ae.idEmpregado
    from avaliacaoEfetivo ae
    join avaliacao a on ae.idAvaliacao = a.idAvaliacao
    where lower(a.descricao) = 'muito bom'
    union
    select at.idEmpregado
    from avaliacaoTemporario at
    join avaliacao a on at.idAvaliacao = a.idAvaliacao
    where lower(a.descricao) = 'muito bom'
  );

--2) Obter a descrição das avaliações que são comuns a empregados efetivos e temporários,
-- Seleciona as avaliações que são comuns a empregados efetivos e temporários
select distinct
  a.descricao
from
  avaliacao a
where
  exists (
    select 1
    from avaliacaoEfetivo ae
    where ae.idAvaliacao = a.idAvaliacao
  )
  and exists (
    select 1
    from avaliacaoTemporario at
    where at.idAvaliacao = a.idAvaliacao
  );



--3)Obter o nome e o número de identificação civil dos empregados efetivos que tiveram sempre classificação
--“MUITO BOM”

select
  e.nome,
  e.nrIdentificacaoCivil
from
  empregado e
where
  e.idEmpregado in (
    select ae.idEmpregado
    from avaliacaoEfetivo ae
    where ae.idEmpregado not in (
      select ae_inner.idEmpregado
      from avaliacaoEfetivo ae_inner
      join avaliacao a on ae_inner.idAvaliacao = a.idAvaliacao
      where lower(a.descricao) <> 'muito bom'
  ));
--4)Obter o nome e o número de identificação civil dos empregados efetivos cujas férias têm todas duração
--superior a 15 dias
select
  e.nome,
  e.nrIdentificacaoCivil
from
  empregado e
where
  e.idEmpregado IN (
    select ef.idEmpregado
    from empregadoEfetivo ef
    
    where not exists (
      select 1
      from ferias f
      where f.idEmpregado = ef.idEmpregado
      and f.dataFim - f.dataInicio <= 15
    )
    
  )order by e.nome;
