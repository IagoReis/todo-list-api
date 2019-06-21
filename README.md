# TODO List API

TODO List é uma API que disponibiliza endpoints para gerenciamento de tarefas.

O projeto foi desenvolvido em Java utilizando Spring Boot e banco de dados em Cassandra.

**Dependências**
- Docker
- Docker Compose
- Java 11 ou superior
- Maven 3.6.0 ou superior

## Como subir o projeto com Docker e Docker Compose?

O projeto já possui os arquivos necessários para ser executado local em um ambiente Docker.

**Porta de execução do projeto:** Conforme configurado no arquivo `.docker/docker-compose.yaml` o container será exposto para a porta 8000 da máquina local.

**Cassandra:** Conforme o arquivo `.docker/Dockerfile` a imagem docker do projeto está configurada para rodar local, se conectando no host `cassandra` e porta `9042` do cassandra que serão criados pelo Docker Compose. Mas é possível alterar o endereço do Cassandra, basta alterar as propriedades `spring.data.cassandra.host` e `spring.data.cassandra.port`.

**Observação:** Caso altere o endereço do Cassandra, deverá criar o Keyspace e Table no novo banco de dados. *Nâo sendo necessário executar este comando caso opte pela configuração padrão descrita nos dois passos abaixo.*

```
CREATE KEYSPACE IF NOT EXISTS todolist WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes = true;
CREATE TABLE IF NOT EXISTS todolist.task (id uuid, description text, status text, PRIMARY KEY(id))
```

**Passo 00:** Criar o banco de dados de testes

O projeto está configurado para se conectar no host `127.0.0.1` e porta `9050` do cassandra durante os testes.

Para subir o banco de dados de testes já configurado, basta executar o comando abaixo:

```
docker-compose -f .docker/docker-compose-cassandra.yaml up -d
```

**Passo 01:** Criar imagem customizada do projeto

Dentro da raiz do projeto criar imagem customizada utilizando o comando

```
mvn clean package docker:build
```

Ou executar o comando abaixo para gerar a imagem do projeto sem executar os testes

```
mvn clean package docker:build -DskipTests
```

**Passo 02:** Executar projeto já com o banco de dados

```
docker-compose -f .docker/docker-compose.yaml up
```

Após executar os dois passos acima, o TODO List estará disponível no endereço http://localhost:8000

## Enpoints

### Cadastrar tarefa

Método HTTP: POST

Endpoint: `<HOST>`:`<PORT>`/api/v1/task/

Parâmetros do corpo da requisição (json): 

- **description:** Descrição da atividade, pode ser inserido um texto livre neste parâmetro
- **status:** Status da atividade, apenas é possível passar os valores PENDING ou COMPLETED 

```
{
    "description": "<DESCRICAO>",
    "status": "<STATUS>"
}
```
Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X POST -d '{"description":"Teste de Tarefa","status":"PENDING"}' http://localhost:8000/api/v1/task
```

### Consultar todas as tarefas

Método HTTP: GET

Endpoint: `<HOST>`:`<PORT>`/api/v1/task/

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X GET http://localhost:8000/api/v1/task
```

### Consultar tarefa por id

Método HTTP: GET

Endpoint: `<HOST>`:`<PORT>`/api/v1/task/<ID>

Parâmetros da URL:

- **id:** ID da atividade, o ID é obtido na inserção, alteração e consulta de tarefas

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X GET http://localhost:8000/api/v1/task/963916b0-9255-11e9-9758-f78468c1ccd8
```

### Editar tarefa

Método HTTP: PUT

Endpoint: `<HOST>`:`<PORT>`/api/v1/task/<ID>

Parâmetros da URL:

- **id:** ID da atividade, o ID é obtido na inserção, alteração e consulta de tarefas

Parâmetros do corpo da requisição (json): 

- **description:** Descrição da atividade, pode ser inserido um texto livre neste parâmetro
- **status:** Status da atividade, apenas é possível passar os valores PENDING ou COMPLETED 

```
{
    "description": "<DESCRICAO>",
    "status": "<STATUS>"
}
```

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X PUT -d '{"description":"Teste de Tarefa","status":"COMPLETED"}' http://localhost:8000/api/v1/task/963916b0-9255-11e9-9758-f78468c1ccd8
```

### Excluir tarefa por id

Método HTTP: DELETE

Endpoint: `<HOST>`:`<PORT>`/api/v1/task/<ID>

Parâmetros da URL:

- **id:** ID da atividade, o ID é obtido na inserção, alteração e consulta de tarefas

Exemplo de requisição cURL:

```
curl -H "Content-Type: application/json" -X DELETE http://localhost:8000/api/v1/task/963916b0-9255-11e9-9758-f78468c1ccd8
```

## Helthcheck

É possível consultar o status do projeto através do endereço `http://<HOST>:<PORT>/actuator/health`.

Exemplo: http://localhost:8000/actuator/health/

## Métricas

É possível consultar o tempo e status das requisições no endereço `http://<HOST>:<PORT>/actuator/metrics/http.server.requests`.

Exemplo: http://localhost:8000/actuator/metrics/http.server.requests
