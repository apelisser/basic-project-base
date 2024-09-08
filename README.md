## Resumo

Este projeto serve como ponto de partida para o desenvolvimento de aplicações REST utilizando Spring Boot (com spring-web).

Os recursos já implementados no projeto são:

- **Internacionalização**
  - foi implementada uma estrutura de internacionalização para as mensagens da aplicação;
  - `en_US` está definido como padrão, com fácil personalização;

- **Contexto**
  - foi implementada uma estrutura para o uso de contexto na aplicação;
  - incluído o `requestId` em todas as requisições atendidas;
  - o `requestId` é retornado no cabeçalho `x-request-id` de todas as respostas;
  - é possível adicionar mais informações ao contexto, conforme necessário;

- **Logs**
  - foi configurado um arquivo de personalização para os logs da aplicação utilizando Logback;
  - o `requestId` presente no contexto é incluído automaticamente nos logs da aplicação, facilitando o rastreamento;

- **Exception Handler**
  - foi implementada uma estrutura para manipulação de exceções;
  - as validações foram personalizadas para os principais erros tratados pelo framework em requisições REST;
  - o retorno foi padronizado com `problem-detail`, formato agora suportado pelo Spring Framework;
  - é possível adicionar manipuladores específicos para exceções conforme necessário;

---

## Instruções para execução da aplicação

### Tecnologias utilizadas
- Java Development Kit 21
- Maven
- Docker
- Git
- Postman

### Passos para execução
1. Clonar o repositório utilizando o comando abaixo  
```sh
git clone <repositorio>
```

2. Acessar diretório criado pelo passo anterior  
```sh
cd <diretorio>
``` 

3. Fazer o build do projeto  
```sh
mvn clean package
```

4. Criar uma imagem docker para a aplicação  
```sh
docker build -t app-base-project:1.0 .
```
Onde: `-t` é utilizado para especificar o nome da imagem e a tag  

5. Executar os serviços definidos no arquivo docker-compose.yml  
```sh
docker-compose up -d
```

**Após executar os passos acima, os seguintes serviços estarão em execução:**
- 1 instância do NGINX atendendo na porta `80`
- 2 instâncias da aplicação (backend) atendendo nas portas `8081` e `8082`

### Encerrar execução das imagens  
Para encerrar a execução das imagens Docker, siga os passos abaixo:
1. acessar diretório do arquivo **docker-compose.yml**  
2. executar o comando `docker compose down`

Obs: se desejar remover imagem da aplicação  
```sh
docker image rm app-base-project:1.0  
```
---

## Endpoints (exemplos)

Abaixo estão alguns exemplos para os recursos disponíveis:

- ### Obter informações da aplicação
    - Método: **GET**
    - Endpoint: http://localhost:80/base-app/api/v1/info
    - Response:
      ```json
      {
        "name": "string",
        "description": "string",
        "appVersion": "string",
        "springBootVersion": "string",
        "javaVersion": "string"
      }
      ```

- ### Testar internacionalização
  - Método: **GET**  
  - Endpoint: http://localhost:80/base-app/api/v1/i18n/test
  - Header:
    - Accept-Language: `en-US`ou `pt-BR`
  - Response (en-US): 
    ```json
    {
        "message": "TEST (en-US)"
    }
    ```

- ### Quando houver erros na requisição
  - Método: **GET**  
  - Endpoint: http://localhost:80/base-app/api/v1/unknown  
  - Response (exemplo `en-US`):
    ```json
    {
      "type": "about:blank",
      "title": "Resource not found",
      "status": 404,
      "detail": "Resource 'api/v1/unknown' does not exist.",
      "instance": "/base-app/api/v1/unknown",
      "timestamp": "2024-09-07T22:01:47.31595866Z",
      "requestId": "f5281b21-888e-4730-98e2-26ec71afcd61",
      "userMessage": "The accessed resource does not exist."
    }
    ```