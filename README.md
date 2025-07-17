# 🏥 Voll.med API - Gerenciamento Completo de Clínicas com Autenticação JWT e Agendamento de Consultas

Este projeto é uma **API RESTful** abrangente desenvolvida em Java com o framework Spring Boot, projetada para o gerenciamento de informações de médicos, pacientes e, agora, **agendamento e cancelamento de consultas**. A API incorpora um sistema de autenticação e autorização baseado em JWT (JSON Web Tokens) e inclui a integração com o Swagger/OpenAPI para documentação interativa.

---

## 📌 Sobre o Projeto

A **Voll.med API** foi construída como uma solução robusta para a gestão de dados clínicos, demonstrando e praticando conceitos avançados de desenvolvimento de APIs seguras e complexas com Spring Boot, tais como:

* **Arquitetura RESTful**: Design de endpoints HTTP claros e sem estado para operações CRUD sobre recursos de médicos, pacientes e consultas.
* **Autenticação e Autorização JWT**: Implementação completa de um fluxo de login que gera um JWT para autenticar usuários e proteger os endpoints da API usando Spring Security.
* **Spring Security**: Configuração e integração do Spring Security para gerenciar a segurança da aplicação, incluindo filtros de requisição, gerenciamento de sessões `STATELESS` e permissão de acesso à documentação da API.
* **Persistência de Dados**: Utilização do **Spring Data JPA** para interagir com um banco de dados relacional, mapeando entidades (`Medico`, `Paciente`, `Endereco`, `Usuario`, `Consulta`).
* **Validação de Dados**: Implementação de validações rigorosas com **Jakarta Bean Validation** (`@Valid`, `@NotBlank`, `@Email`, `@Pattern`, `@NotNull`) para garantir a integridade e conformidade dos dados de entrada em todas as operações.
* **DTOs (Data Transfer Objects)**: Uso de records para definir o formato dos dados de entrada e saída, promovendo a separação de responsabilidades e a segurança da API.
* **Gerenciamento Transacional**: Aplicação de `@Transactional` para garantir a atomicidade e consistência das operações de escrita no banco de dados.
* **Paginação**: Implementação de paginação para as listagens de médicos e pacientes, otimizando o desempenho e a escalabilidade.
* **Soft Delete**: Médicos e pacientes são "excluídos" logicamente (inativados) em vez de removidos fisicamente do banco de dados, preservando o histórico.
* **Lombok**: Redução de código boilerplate (getters, setters, construtores, `equals`/`hashCode`).
* **Tratamento de Erros Global**: Implementação de um `RestControllerAdvice` para centralizar o tratamento de exceções, retornando respostas de erro padronizadas e informativas, incluindo exceções de regras de negócio (`ValidacaoException`).
* **Agendamento e Cancelamento de Consultas**: Lógica de negócio complexa para agendar consultas, incluindo validações de horário de funcionamento da clínica, disponibilidade do médico e paciente, e regras para cancelamento com antecedência mínima.
* **Documentação da API**: Integração com **Springdoc OpenAPI (Swagger UI)** para gerar automaticamente a documentação interativa dos endpoints da API, facilitando o teste e a compreensão.

---

## 🚀 Tecnologias Utilizadas

<div>
 <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
 <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white">
 <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white">
 <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white">
 <img src="https://img.shields.io/badge/Lombok-E10098?style=for-the-badge&logo=lombok&logoColor=white">
 <img src="https://img.shields.io/badge/Jakarta_Validation-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
 <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white">
 <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">
 <img src="https://img.shields.io/badge/H2_Database-2DB2DD?style=for-the-badge&logo=h2&logoColor=white">
</div>

---

## 🧱 Estrutura do Projeto

```

📁 api/
├── 📁 .idea/                      (Arquivos de configuração da IDE)
├── 📁 .mvn/                       (Arquivos do Maven Wrapper)
├── 📁 src/
│   └── 📁 main/
│       └── 📁 java/
│           └── 📁 med/
│               └── 📁 voll/
│                   └── 📁 api/
│                       ├── 📁 controller/           (Controladores REST para Médicos, Pacientes, Autenticação e Consultas)
│                       ├── 📁 domain/               (Classes de domínio e DTOs para entidades)
│                       │   ├── 📁 consulta/         (Classes e records relacionados à entidade Consulta e suas validações)
│                       │   ├── 📁 endereco/         (Classes e records relacionados à entidade Endereco)
│                       │   ├── 📁 medico/           (Classes e records relacionados à entidade Medico)
│                       │   ├── 📁 paciente/         (Classes e records relacionados à entidade Paciente)
│                       │   └── 📁 usuario/          (Classes e records relacionados à entidade Usuario e Autenticação)
│                       ├── 📁 infra/                (Classes de infraestrutura)
│                       │   ├── 📁 exception/        (Tratamento global de erros)
│                       │   ├── 📁 security/         (Configurações de segurança, JWT e filtros)
│                       │   └── 📁 Springdoc/        (Configuração do Swagger/OpenAPI)
│                       ├── 📁 repository/           (Interfaces de repositório Spring Data JPA)
│                       ├── 📁 service/              (Classes de serviço com a lógica de negócio)
│                       └── 📄 ApiApplication.java   (Classe principal da aplicação Spring Boot)
│       └── 📁 resources/
│           └── 📄 application.properties (Configurações do banco de dados e JWT Secret)
├── 📄 .gitignore
├── 📄 api.iml                     (Arquivo de módulo da IDE)
├── 📄 pom.xml                     (Configurações de dependências Maven)
└── 📄 README.md

````

---

## ⚙️ Como Usar

Para executar este projeto (backend API) em sua máquina local, siga os passos abaixo:

1.  **Pré-requisitos**:
    * Java 17 ou superior.
    * Maven.
    * Uma IDE (IntelliJ IDEA, Eclipse, VS Code com plugins Java).
    * Um banco de dados relacional (ex: PostgreSQL, MySQL). Para desenvolvimento, o **H2 Database** em memória pode ser usado (configuração padrão do Spring Boot).

2.  **Clone o repositório:**
    ```bash
    git clone https://github.com/AbnerVital/Med-Vool-API
    ```

3.  **Navegue até o diretório do projeto:**
    ```bash
    cd api # Ou o nome do diretório do seu projeto
    ```

4.  **Configuração do Banco de Dados e JWT Secret**:
    * Abra o arquivo `src/main/resources/application.properties`.
    * Configure as propriedades do seu banco de dados.
        * **Exemplo para PostgreSQL:**
            ```properties
            spring.datasource.url=jdbc:postgresql://localhost:5432/vollmed_api_db
            spring.datasource.username=seu_usuario
            spring.datasource.password=sua_senha
            spring.jpa.hibernate.ddl-auto=update
            spring.jpa.show-sql=true
            ```
        * **Exemplo para H2 (em memória):**
            ```properties
            spring.datasource.url=jdbc:h2:mem:vollmed_db
            spring.datasource.driverClassName=org.h2.Driver
            spring.datasource.username=sa
            spring.datasource.password=
            spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
            spring.jpa.hibernate.ddl-auto=update
            spring.jpa.show-sql=true
            ```
    * **Adicione a chave secreta para o JWT**:
        ```properties
        api.security.token.secret=SUA_CHAVE_SECRETA_JWT_AQUI # Use uma string longa e complexa!
        ```

5.  **Compile e execute o projeto:**

    * **Via IDE (Recomendado)**:
        1.  Abra o projeto na sua IDE (ex: IntelliJ IDEA -> `File` -> `Open` -> selecione a pasta do projeto).
        2.  Aguarde a IDE resolver as dependências do Maven.
        3.  Execute a classe `ApiApplication.java`.

    * **Via Terminal (com Maven)**:
        ```bash
        mvn spring-boot:run
        ```
    * A API será iniciada, por padrão, em `http://localhost:8080`.

### Documentação da API (Swagger UI)

Após iniciar a aplicação, você pode acessar a documentação interativa da API no seu navegador:

* **URL do Swagger UI**: `http://localhost:8080/swagger-ui.html`

Nesta interface, você poderá visualizar todos os endpoints, seus métodos HTTP, parâmetros esperados, modelos de requisição/resposta e testar as chamadas diretamente.

### Autenticação e Autorização

Todos os endpoints, **exceto `/login` e os da documentação (`/v3/api-docs/**`, `/swagger-ui.html`, `/swagger-ui/**`)**, exigem autenticação. Para acessar os recursos protegidos, você precisará de um JWT válido.

1.  **Realize o Login para Obter um JWT**:
    * **`POST /login`**
        * **Descrição**: Autentica um usuário e retorna um JSON Web Token (JWT).
        * **Corpo da Requisição (JSON)**:
            ```json
            {
                "login": "seu_usuario",
                "senha": "sua_senha"
            }
            ```
            * **Nota**: Certifique-se de que o usuário `seu_usuario` e `sua_senha` existam no seu banco de dados. Você pode precisar criar um usuário manualmente para o primeiro login (ex: via script SQL ou ferramenta de banco de dados).
        * **Resposta (JSON)**:
            ```json
            {
                "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ..."
            }
            ```
        * Copie o valor do `token`.

2.  **Use o JWT para Acessar Endpoints Protegidos**:
    * Para todas as outras requisições (POST, GET, PUT, DELETE para `/medicos`, `/pacientes` e `/consultas`), inclua o JWT no cabeçalho `Authorization` no formato `Bearer <SEU_TOKEN_JWT>`.
    * **Exemplo de Cabeçalho HTTP**:
        ```
        Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ...
        ```
    * No Swagger UI, você pode clicar no botão "Authorize" (geralmente no canto superior direito) e inserir seu token JWT no campo "Value" (precedido por `Bearer `).

### Endpoints da API

Uma vez que o backend esteja rodando e você tenha um token JWT, você pode interagir com os seguintes endpoints:


#### Endpoints de Médicos (`/medicos`) - **Requer Autenticação**

* **`POST /medicos`**
    * **Descrição**: Cadastra um novo médico no sistema.
    * **Corpo da Requisição (JSON)**:
        ```json
        {
            "nome": "Dr. João Silva",
            "email": "joao.silva@voll.med",
            "telefone": "11987654321",
            "crm": "123456",
            "especialidade": "ORTOPEDIA",
            "endereco": {
                "logradouro": "Rua das Palmeiras",
                "bairro": "Centro",
                "cep": "00000000",
                "cidade": "São Paulo",
                "uf": "SP",
                "complemento": "Apto 101",
                "numero": "123"
            }
        }
        ```
    * **Resposta**: `201 Created` com os detalhes do médico cadastrado e o URI de acesso.

* **`GET /medicos`**
    * **Descrição**: Lista todos os médicos ativos, com suporte a paginação.
    * **Parâmetros de Query (Opcionais)**: `page`, `size`, `sort`.
    * **Exemplo**: `http://localhost:8080/medicos?page=0&size=5&sort=nome`
    * **Resposta**: `200 OK` com uma página de `DadosListagemMedico`.

* **`GET /medicos/{id}`**
    * **Descrição**: Detalha as informações de um médico específico pelo seu ID.
    * **Exemplo**: `http://localhost:8080/medicos/1`
    * **Resposta**: `200 OK` com `DadosDetalhamentoMedico` ou `404 Not Found`.

* **`PUT /medicos`**
    * **Descrição**: Atualiza as informações de um médico existente.
    * **Corpo da Requisição (JSON)**:
        ```json
        {
            "id": 1,
            "nome": "Dr. João Silva Atualizado",
            "telefone": "11999999999",
            "endereco": {
                "cidade": "Rio de Janeiro"
            }
        }
        ```
        *Apenas os campos fornecidos no JSON serão atualizados.*
    * **Resposta**: `200 OK` com `DadosDetalhamentoMedico` do médico atualizado.

* **`DELETE /medicos/{id}`**
    * **Descrição**: Inativa um médico pelo seu ID (realiza um "soft delete").
    * **Exemplo**: `http://localhost:8080/medicos/1`
    * **Resposta**: `204 No Content`.

#### Endpoints de Pacientes (`/pacientes`) - **Requer Autenticação**

* **`POST /pacientes`**
    * **Descrição**: Cadastra um novo paciente no sistema.
    * **Corpo da Requisição (JSON)**:
        ```json
        {
            "nome": "Maria Oliveira",
            "email": "maria.oliveira@email.com",
            "telefone": "21987654321",
            "cpf": "123.456.789-00",
            "endereco": {
                "logradouro": "Rua das Flores",
                "bairro": "Jardim",
                "cep": "10000000",
                "cidade": "Niterói",
                "uf": "RJ",
                "complemento": "Casa 2",
                "numero": "456"
            }
        }
        ```
    * **Resposta**: `201 Created` com os detalhes do paciente cadastrado e o URI de acesso.

* **`GET /pacientes`**
    * **Descrição**: Lista todos os pacientes ativos, com suporte a paginação.
    * **Parâmetros de Query (Opcionais)**: `page`, `size`, `sort`.
    * **Exemplo**: `http://localhost:8080/pacientes?page=0&size=5&sort=nome`
    * **Resposta**: `200 OK` com uma página de `DadosListagemPaciente`.

* **`GET /pacientes/{id}`**
    * **Descrição**: Detalha as informações de um paciente específico pelo seu ID.
    * **Exemplo**: `http://localhost:8080/pacientes/1`
    * **Resposta**: `200 OK` com `DadosDetalhamentoPaciente` ou `404 Not Found`.

* **`PUT /pacientes`**
    * **Descrição**: Atualiza as informações de um paciente existente.
    * **Corpo da Requisição (JSON)**:
        ```json
        {
            "id": 1,
            "nome": "Maria Oliveira Atualizada",
            "telefone": "21998877665",
            "endereco": {
                "bairro": "Centro Atualizado"
            }
        }
        ```
        *Apenas os campos fornecidos no JSON serão atualizados.*
    * **Resposta**: `200 OK` com `DadosDetalhamentoPaciente` do paciente atualizado.

* **`DELETE /pacientes/{id}`**
    * **Descrição**: Inativa um paciente pelo seu ID (realiza um "soft delete").
    * **Exemplo**: `http://localhost:8080/pacientes/1`
    * **Resposta**: `204 No Content`.

#### Endpoints de Consultas (`/consultas`) - **Requer Autenticação**

* **`POST /consultas`**
    * **Descrição**: Agenda uma nova consulta no sistema. Inclui diversas validações de regras de negócio.
    * **Corpo da Requisição (JSON)**:
        ```json
        {
            "idMedico": 1,         // Opcional: Se não informado, um médico disponível será escolhido aleatoriamente pela especialidade.
            "idPaciente": 1,
            "data": "30/07/2025 14:00",
            "especialidade": "ORTOPEDIA" // Obrigatório se idMedico não for informado.
        }
        ```
    * **Validações Incluídas**:
        * Paciente e Médico devem existir e estar ativos.
        * Consulta deve ser agendada com no mínimo 30 minutos de antecedência.
        * Consulta deve estar dentro do horário de funcionamento da clínica (7h às 18h, exceto domingo).
        * Médico não pode ter outra consulta agendada no mesmo horário.
        * Paciente não pode ter outra consulta agendada no mesmo dia.
    * **Resposta**: `200 OK` com `DadosDetalhamentoConsulta` ou `400 Bad Request` em caso de violação de regras de negócio.

* **`GET /consultas/{id}`**
    * **Descrição**: Detalha as informações de uma consulta específica pelo seu ID.
    * **Exemplo**: `http://localhost:8080/consultas/1`
    * **Resposta**: `200 OK` com `DadosDetalhamentoConsulta` ou `404 Not Found`.

* **`DELETE /consultas`**
    * **Descrição**: Cancela uma consulta existente. A consulta é inativada e um motivo de cancelamento é registrado.
    * **Corpo da Requisição (JSON)**:
        ```json
        {
            "idConsulta": 1,
            "motivo": "PACIENTE_DESISTIU" // Pode ser PACIENTE_DESISTIU, MEDICO_CANCELOU, OUTROS
        }
        ```
    * **Validações Incluídas**:
        * Consulta deve existir.
        * Consulta só pode ser cancelada com no mínimo 24 horas de antecedência.
    * **Resposta**: `204 No Content` ou `400 Bad Request` em caso de violação de regras de negócio.

---

## 📈 Melhorias Futuras

* Implementar testes de integração e unitários mais abrangentes para todos os serviços e controladores, incluindo os de segurança e as regras de negócio de agendamento/cancelamento.
* Refinar o tratamento de exceções para cenários específicos e mensagens de erro mais amigáveis.
* Otimizar o desempenho de consultas para grandes volumes de dados.
* Adicionar funcionalidades de relatório e estatísticas sobre consultas, médicos e pacientes.
* Implementar um mecanismo de notificação (ex: e-mail, SMS) para agendamentos e cancelamentos de consultas.

---

## 👨‍💻 Autor

| [<img src="https://avatars.githubusercontent.com/u/102125924?v=4" width=115><br><sub>Abner Vital</sub>](https://github.com/AbnerVital) |
| :------------------------------------------------------------------------------------------------------------------------------------: |

---

## 📫 Contato

* **LinkedIn**: [@Abner Vital](https://www.linkedin.com/in/abner-vital-233730141/)
* **GitHub**: [@AbnerVital](https://github.com/AbnerVital)

---

> Desenvolvido durante os estudos na [Alura](https://www.alura.com.br/)
