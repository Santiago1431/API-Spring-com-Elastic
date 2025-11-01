# API Spring Boot com Elasticsearch

Este projeto é uma API RESTful desenvolvida em Spring Boot como parte de um trabalho universitário sobre Tecnologias de Banco de Dados. O objetivo principal é demonstrar as capacidades do Elasticsearch para além de um simples banco de dados, focando em:

1.  **Operações CRUD** (Create, Read, Update, Delete) via REST API.
2.  **Buscas Avançadas** (Query DSL), incluindo Full-Text Search e filtros complexos.
3.  **Agregações de Dados** (Análise em Tempo Real) para calcular métricas e agrupar dados.

## Funcionalidades

* API REST completa para gestão de um catálogo de `Produtos`.
* Modelo de dados que inclui objetos aninhados (`Distribuidores`).
* Mapeamento de dados avançado usando `@MultiField` (para filtros `.keyword`) e `@Nested` (para listas de objetos).
* Endpoint de busca avançada (`/search`) que permite filtrar por nome, marca e faixa de preço.
* Ambiente de desenvolvimento completo com Docker Compose (Elasticsearch e Kibana).

## Tecnologias Utilizadas

* **Java 17+** (configurado para 21 no `pom.xml`)
* **Spring Boot 3.x**
* **Spring Data Elasticsearch**
* **Maven**
* **Lombok**
* **Elasticsearch 8.11.0**
* **Kibana 8.11.0**
* **Docker** e **Docker Compose**

## Como Executar o Projeto

### Pré-requisitos

* Java 17 (ou superior) instalado
* Maven
* Docker e Docker Compose
* Postman ou insomnia ou cURL

### 1. Iniciar o Ambiente (Elasticsearch e Kibana)

Na raiz do projeto, onde se encontra o ficheiro `docker-compose.yml`, execute o seguinte comando:

```sh
docker compose -p nomeDoContainer -f docker-compose.yml up -d
```

isto irá iniciar:

Elasticsearch na porta http://localhost:9200

Kibana na porta http://localhost:5601

### 2. Iniciar Projeto Maven Spring
Num novo terminal, na raiz do projeto, execute a aplicação Spring:
```sh
mvn spring-boot:run
```

Ou pode Abrir Na sua IDE JAVA

A API estará disponível em http://localhost:8080.


### 3. Operações CRUD

Endpoint: POST /produtos
Endpoint: GET /produtos
Endpoint: GET /produtos/{id}
Endpoint: PUT /produtos

Execute Os Body JSON do Arquivo Inserts.txt no Postman,Insomnia ou curl

Pode Inserir Outros caso queira

### 7. Busca Avançada (Query DSL)

Endpoint: GET /produtos/searc

- Parâmetros (Query Params):

- - nome (string): Busca textual (Full-Text Search) no nome.

-  - marca (string): Filtro exato (keyword) pela marca.

-  - precoMin (integer): Filtro de faixa de preço (range).

- - precoMax (integer): Filtro de faixa de preço (range).

### 8. Agregações (Estatísticas)

1. Acesse o Kibana: http://localhost:5601.

2. No menu (☰), vá para Management > Stack Management.

3. Em "Kibana", clique em Data Views.

4. Clique em Create Data View.

5. Dê um nome (ex: "Produtos") e use o padrão de índice produtos*.

6. Em "Timestamp field", selecione "I don't want to use the time filter".

7. Clique em Create data view.

8. Agora você pode ir a Analytics > Visualize Library e usar o Lens (a ferramenta moderna de arrastar e soltar) para criar as suas visualizações:

9. Contagem por Marca (Gráfico de Pizza): Arraste o campo marca.keyword para o centro.

10. Média de Preço por Marca (Gráfico de Barras): Arraste marca.keyword para o Eixo X e preco (mudando a agregação para "Average") para o Eixo Y.

11. Contagem por Distribuidor (Gráfico de Barras): Arraste distribuidores.nome.keyword para o Eixo X (isto usará a agregação nested automaticamente).

12. Crie Um DashBoard e coloque os Data View


