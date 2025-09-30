
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

| Método            Rota                        Descrição                          

| POST   -->  /api/localizacao         -->     Registra nova localização         
| GET    -->  /api/localizacao         -->     Lista todas as localizações        
| GET    -->  /api/localizacao/{id}    -->     Busca uma localização por ID       
| DELETE -->  /api/localizacao/{id}    -->     Remove uma localização por ID     

LocalizacaoDTO

A classe `LocalizacaoDTO` é um **Data Transfer Object (DTO)** utilizado para transportar as informações de **localização de um pet** entre as camadas da aplicação e também nas chamadas de API.

Campo	         Tipo	            Descrição	                                                          Exemplo
sensorId ----	 String ----------	Identificador único do sensor ou dispositivo de rastreamento ---	"sensor-abc-123"
latitude ----	 double ----------	Coordenada geográfica de latitude ------------------------------	-23.55052
longitude ---  double ----------	Coordenada geográfica de longitude -----------------------------  -46.633308
dataHora	---  LocalDateTime ---	Data e hora em que a localização foi capturada -----------------  "2025-09-30T16:00:00"

---

# Testes

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

Uma coleção de testes para facilitar o uso da API está incluída no repositório:  
[PetAPI-PostmanCollection.json](PetAPI-PostmanCollection.json)

---

