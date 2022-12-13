create table itens_pedido (
id UUID  primary key,
pedido_id UUID,
observacao varchar(150),
produto_servico_id UUID,
quantidade integer,
 constraint fk_pedido_id foreign key (pedido_id) references pedido (id),
 constraint fk_produto_servico_id foreign key (produto_servico_id) references produto_servico (id)
)