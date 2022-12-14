# produto-pedido

  💻 Sobre o projeto
  
    Permite cadastrar produto/serviço  e realizar pedidos dos produtos/serviços   .

  ⚙️ Funcionalidades
    
    Produto/Serviço
      * Cadastrar novos produtos/serviços;
      * Buscar lista de protutos cadastrados por status;
      * Buscar produto/serviço  pelo UUID;
      * Alterar produto/serviço pelo UUID;
      * Deletar o produto/serviço através do UUID;

    Pedido
      * Abrir pedido, recebendo uma lista produto/serviço;
      * Buscar lista de pedidos por situação(Aberto/Fechado);
      * Buscar pedido através do UUID do pedido;
      * Deletar um item do pedido  através o UUID do item-pedido;
      * Alterar itens do pedido;
      * Finalizar pedido trazendo o valor total gasto;
      

  🛠 Tecnologias e padrão utilizadas
  
    * Arquitetura padrão MVC;
    * Spring Data;
    * Java 11;
    * Maven;
    * Docker;
    * Flyway;
    * Postgres;
    * Lombok;
    * Swagger;
    
  🧭 Rodando a aplicação
    
    Clonar o repositório do projeto;
    
    Importar o projeto na IDE de sua preferência;
    
   Dentro do diretório do projeto, buildar com o Maven
    mvn clean install
    O projeto baixará as dependências necessárias e buildará com sucesso. Caso não complete com sucesso, 
    verifique o log do build para encontrar possíveis erros.
   
   Configurar o DB Postgres localmente com o Docker
    Com o terminal dentro do diretório do projeto, executar o docker-compose abaixo
    docker-compose up
    
   Conectar-se ao DB com o manager de sua preferência. O usuário e senha do DB podem ser consultados no arquivo application.yml
    
   Documentação da API
    A documentação da API é feita através do swagger, e quando a aplicação estiver rodando em ambiente local você pode acessá-la pelo link :
    http://localhost:8080/swagger-ui.html#

💡 Regras de negócio implementadas
  
    Produto/Serviço
      * É possivel diferenciar produto e serviço através  do enumerado TipoProdutoServico;
      * Não é possivel deletar produtos/serviços que estejam em uso em um pedido;
      * Não é possivel adicionar um produto/serviço com status falso em um pedido;
      
    Pedido
      * É possivel adicionar um novo produto/serviço em um pedido que já foi aberto pelo endpoint de /Put do pedido, 
        a qualquer momento desde que esteja com status Aberto;
      * Ao alterar um pedido, e ele já tiver o mesmo produto/serviço que o informado, as informações atuais do item do pedido adicionadas, 
        sobrescreverão o item de pedido de mesmo ID do banco;
      * Não será permitido que itens do pedido sejam adicionado com a quantidade 0;
      * Não será permitido fazer alterações em pedidos com situação Fechado;
      * Pedido pode receber um percentual de desconto quando for fechado, e o mesmo será aplicado apenas em itens do tipo produto;
  
    



   



