create table  pedido (
id UUID  primary key,
observacao varchar(50),
data_abertura timestamp,
data_fechamento timestamp,
situacao varchar(30),
valor_total  numeric(1000,2)


)