
# PetAPI - Localização de Pets Perdidos

API REST construída como parte de um desafio técnico com o objetivo de facilitar a localização de pets perdidos através de um dispositivo GPS acoplado à coleira, retornando informações detalhadas do endereço utilizando a API pública do [PositionStack](https://positionstack.com/).

---

# Funcionalidades

- Receber dados de localização (ID do sensor, latitude, longitude, data/hora)
- Consultar a API PositionStack e retornar:
  - País
  - Estado
  - Cidade
  - Bairro
  - Endereço (rua, avenida etc.)
- Persistir os dados de localização do pet
- Listar todas as localizações
- Buscar localização por ID
- Remover localização por ID

---

##  Arquitetura Utilizada

O projeto foi estruturado seguindo os princípios da **Arquitetura Hexagonal (Ports and Adapters)**.

# Camadas:

- **domain.model**: Entidades e modelos centrais do domínio
- **domain.port.in**: Interfaces (casos de uso) que expõem as operações de entrada
- **domain.port.out**: Interfaces que representam as dependências externas (ex: banco de dados, API externa)
- **application.service**: Implementação dos casos de uso
- **infrastructure.adapter.in.controller**: Controllers da API REST (Spring MVC)
- **infrastructure.adapter.out.persistence**: Repositórios e comunicação com banco
- **infrastructure.client**: Comunicação com API externa (PositionStack)

Essa abordagem promove **baixo acoplamento**, **alta coesão**, **facilidade de teste** e **substituição de tecnologias**.

---

# Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- H2 Database (em memória)
- API PositionStack
- Lombok
- JUnit & Mockito (para testes)

---

# Como Executar o Projeto

## Pré-requisitos

- Java 17
- Maven
- IDE (STS)

# Passos

```bash
# 1. Clone o projeto
git clone <URL_DO_REPOSITORIO>
cd api-pet-hexagonal

# 2. Compile e rode a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080/api/localizacao`

---

# Endpoints
                    
## | POST   -->  /api/localizacao         -->     Registra nova localização  

### Request

Na requisição POST utilizamos os paremetros por meio do body, passando um objeto json,  conforme o exemplo abaixo:

```json
{
  "sensorId": "PET-002",
  "latitude": -22.971964,
  "longitude": -22.971964,,
  "dataHora": "2025-07-21T11:30:00"
}
```

|Campo	   |     Tipo	       |          Descrição	                                           |          Exemplo     |
|----------|-----------------|---------------------------------------------------------------|-----------------     |
|sensorId  |	 String        |	Identificador único do sensor ou dispositivo de rastreamento | "sensor-abc-123"     |
|latitude  | double          |	Coordenada geográfica de latitude                            |       -22.971964     |
|longitude |  double         |	Coordenada geográfica de longitude                           |       -22.971964     |
|dataHora	 |  LocalDateTime  |	Data e hora em que a localização foi capturada               | "2025-07-21T11:30:00"|

### Response 

O response devolve o objeto cadastrado.

```json
    {
        "id": 1,
        "sensorId": "PET-003",
        "latitude": -30.034647,
        "longitude": -51.217658,
        "dataHora": "2025-07-21T13:45:00",
        "pais": "Brazil",
        "estado": "Rio Grande Do Sul",
        "cidade": "Porto Alegre",
        "bairro": "Farroupilha",
        "endereco": "Largo Professor Francisco de Paula Brochado Rocha, Porto Alegre, RS, Brazil"
    }
```

## | GET    -->  /api/localizacao         -->     Lista todas as localizações 

### Request

Na requisição GET utilizamos os paremetros por meio do body, passando um objeto json,  conforme o exemplo abaixo:

```json
{
  "sensorId": "PET-002",
  "latitude": -22.971964,
  "longitude": -22.971964,,
  "dataHora": "2025-07-21T11:30:00"
}
```

|Campo	   |     Tipo	       |          Descrição	                                           |          Exemplo     |
|----------|-----------------|---------------------------------------------------------------|-----------------     |
|sensorId  |	 String        |	Identificador único do sensor ou dispositivo de rastreamento | "sensor-abc-123"     |
|latitude  | double          |	Coordenada geográfica de latitude                            |       -22.971964     |
|longitude |  double         |	Coordenada geográfica de longitude                           |       -22.971964     |
|dataHora	 |  LocalDateTime  |	Data e hora em que a localização foi capturada               | "2025-07-21T11:30:00"|

### Response 

O response devolve uma lista com os objetos cadastrados no endpoint de POST

```json
[
    {
        "id": 1,
        "sensorId": "PET-003",
        "latitude": -30.034647,
        "longitude": -51.217658,
        "dataHora": "2025-07-21T13:45:00",
        "pais": "Brazil",
        "estado": "Rio Grande Do Sul",
        "cidade": "Porto Alegre",
        "bairro": "Farroupilha",
        "endereco": "Largo Professor Francisco de Paula Brochado Rocha, Porto Alegre, RS, Brazil"
    },
    {
        "id": 2,
        "sensorId": "PET-002",
        "latitude": -22.971964,
        "longitude": -43.182553,
        "dataHora": "2025-07-21T11:30:00",
        "pais": "Brazil",
        "estado": "Rio De Janeiro",
        "cidade": "Rio de Janeiro",
        "bairro": "Copacabana",
        "endereco": "Avenida Atlantica 0, Rio de Janeiro, Brazil"
    },
    {
        "id": 3,
        "sensorId": "PET-001",
        "latitude": -23.55052,
        "longitude": -46.633308,
        "dataHora": "2025-07-21T10:00:00",
        "pais": "Brazil",
        "estado": "Sao Paulo",
        "cidade": "São Paulo",
        "bairro": "Se",
        "endereco": "Sé, São Paulo, Brazil"
    }
]
```

## | GET    -->  /api/localizacao/{id}    -->     Busca uma localização por ID

### Request

Na requisição GET por ID, é passado o valor de ID diretamente na URL, conforme o exemplo abaixo:

*{{baseURL}}/api/localizacao/1*

### Response 

O response devolve um Objeto com a requisição do Id que foi passado.

```json
    {
        "id": 1,
        "sensorId": "PET-003",
        "latitude": -30.034647,
        "longitude": -51.217658,
        "dataHora": "2025-07-21T13:45:00",
        "pais": "Brazil",
        "estado": "Rio Grande Do Sul",
        "cidade": "Porto Alegre",
        "bairro": "Farroupilha",
        "endereco": "Largo Professor Francisco de Paula Brochado Rocha, Porto Alegre, RS, Brazil"
    }
```

## | DELETE -->  /api/localizacao/{id}    -->     Remove uma localização por ID     

### Request

Na requisição DELETE por ID, é passado o valor de ID diretamente na URL, conforme o exemplo abaixo:

*{{baseURL}}/api/localizacao/1*

### Response 

O response devolve o Status code 204 e retorna 1 indicando sucesso da deleção.
                                                                                     
---

## Testes

Foram implementados testes de unidade e integração usando JUnit e Mockito.

### Executar testes:
```bash
./mvnw test
```

---

# Banco de Dados

A aplicação usa **H2 em memória**, acessível em:  
`http://localhost:8080/h2-console`  
JDBC URL: `jdbc:h2:mem:testdb`  
Usuário: `sa`  
Senha: *(em branco)*

---

# Premissas e Decisões Técnicas

- **Arquitetura hexagonal** foi adotada para garantir desacoplamento e organização de responsabilidades.
- **PositionStack API** foi usada para resolver o problema de geolocalização reversa de forma simples e gratuita.
- **H2 in-memory** foi utilizada para facilitar testes e execução local.
- **DTOs** foram usados para transportar os dados da camada de entrada para o domínio.


---

# Observabilidade

- Logs da aplicação disponíveis no console (pode-se integrar com ferramentas como ELK, Grafana no futuro)
- Estrutura preparada para inclusão de métricas e traces (ex: Spring Actuator + Micrometer)

---

# Coleção Postman

Uma collection de testes para facilitar o uso da API está incluída no repositório:  
PetAPI-PostmanCollection.json

---

