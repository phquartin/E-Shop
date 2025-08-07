# Microserviço de Carrinho de Compras

## Visão Geral
Este projeto é um microserviço que implementa um carrinho de compras para uma loja online. O serviço permite que os clientes criem carrinhos, adicionem produtos, atualizem quantidades, realizem pagamentos e fechem seus carrinhos. O projeto utiliza uma API externa de loja fake ([FakeStoreAPI](https://fakestoreapi.com)) para obter informações sobre produtos.

## Arquitetura
O projeto segue uma arquitetura de microserviços com as seguintes camadas:

- **Controladores (Controllers)**: Responsáveis por receber as requisições HTTP e direcionar para os serviços apropriados.
- **Serviços (Services)**: Contêm a lógica de negócio da aplicação.
- **Repositórios (Repositories)**: Responsáveis pela persistência dos dados.
- **Entidades (Entities)**: Representam os objetos de domínio da aplicação.
- **Clientes (Clients)**: Responsáveis pela comunicação com serviços externos.

## Funcionalidades Principais
- Criação de carrinhos de compras
- Consulta de carrinhos por ID
- Atualização de carrinhos (produtos e quantidades)
- Processamento de pagamentos
- Fechamento de carrinhos
- Consulta de produtos da loja externa
- Cache de produtos para melhorar a performance

## Tecnologias Utilizadas
- **Spring Boot**: Framework para desenvolvimento de aplicações Java
- **Spring Data MongoDB**: Para persistência de dados no MongoDB
- **Spring Data Redis**: Para cache de dados no Redis
- **Spring Cloud OpenFeign**: Para comunicação com a API externa
- **Lombok**: Para redução de código boilerplate
- **Docker**: Para containerização da aplicação e seus serviços dependentes

## Banco de Dados e Cache
O projeto utiliza:
- **MongoDB**: Para armazenamento persistente dos carrinhos de compras
- **Redis**: Para cache de produtos, melhorando a performance e reduzindo chamadas à API externa

## Configuração e Execução

### Pré-requisitos
- Docker e Docker Compose
- Java 17 ou superior
- Maven

### Executando com Docker Compose
O projeto inclui um arquivo `docker-compose.yml` que configura o MongoDB e o Redis necessários para a aplicação:

```bash
docker-compose up -d
```

### Executando a Aplicação
```bash
./mvnw spring-boot:run
```

## Endpoints da API

### Carrinho de Compras
- **POST /basket**: Cria um novo carrinho de compras
- **GET /basket/{id}**: Obtém um carrinho pelo ID
- **PUT /basket/{id}**: Atualiza um carrinho existente
- **PUT /basket/{id}/payment**: Processa o pagamento de um carrinho
- **DELETE /basket/{id}**: Fecha um carrinho (muda o status para CLOSED)

### Produtos
- **GET /products**: Obtém todos os produtos da loja
- **GET /products/{id}**: Obtém um produto específico pelo ID

## Estados do Carrinho
Um carrinho pode estar em um dos seguintes estados:
- **OPENED**: Carrinho aberto, pode ser modificado
- **CLOSED**: Carrinho fechado, não pode ser modificado
- **SOLD**: Carrinho vendido (pagamento processado)

## Métodos de Pagamento
O sistema suporta os seguintes métodos de pagamento:
- **PIX**: Pagamento via PIX
- **DEBIT**: Pagamento com cartão de débito
- **CREDIT**: Pagamento com cartão de crédito

## Boas Práticas Implementadas
- **Arquitetura em Camadas**: Separação clara de responsabilidades
- **Injeção de Dependências**: Uso de Spring para gerenciamento de dependências
- **Cache**: Implementação de cache para melhorar performance
- **Tratamento de Exceções**: Manipulação adequada de erros
- **Containerização**: Uso de Docker para facilitar implantação
- **Imutabilidade**: Uso de records para objetos de transferência de dados
- **Builder Pattern**: Uso do padrão Builder para criação de objetos complexos