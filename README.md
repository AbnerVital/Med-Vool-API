# 🏥 Voll.med API - Gerenciamento de Médicos e Pacientes com Autenticação JWT

Este projeto é uma **API RESTful** completa desenvolvida em Java com o framework Spring Boot, projetada para o gerenciamento de informações de médicos e pacientes. A API oferece um conjunto robusto de operações CRUD (Criar, Ler, Atualizar, Excluir/Inativar) para ambas as entidades, incorporando validações de dados rigorosas, paginação para consultas eficientes, tratamento transacional e gerenciamento de erros global.

---

## 📌 Sobre o Projeto

A **Voll.med API** evoluiu para incluir um sistema de segurança robusto, demonstrando e praticando conceitos essenciais de desenvolvimento de APIs seguras com Spring Boot, tais como:

* **Arquitetura RESTful**: Design de endpoints HTTP claros e sem estado para operações CRUD sobre recursos de médicos e pacientes.
* **Autenticação e Autorização JWT**: Implementação completa de um fluxo de login que gera um JWT para autenticar usuários e proteger os endpoints da API usando Spring Security.
* **Spring Security**: Configuração e integração do Spring Security para gerenciar a segurança da aplicação, incluindo filtros de requisição e gerenciamento de sessões `STATELESS`.
* **Persistência de Dados**: Utilização do **Spring Data JPA** para interagir com um banco de dados relacional, mapeando entidades (`Medico`, `Paciente`, `Endereco`, `Usuario`).
* **Validação de Dados**: Implementação de validações rigorosas com **Jakarta Bean Validation** (`@Valid`, `@NotBlank`, `@Email`, `@Pattern`, `@NotNull`) para garantir a integridade dos dados de entrada.
* **DTOs (Data Transfer Objects)**: Uso de records para definir o formato dos dados de entrada e saída, promovendo a separação de responsabilidades e a segurança da API.
* **Gerenciamento Transacional**: Aplicação de `@Transactional` para garantir a atomicidade e consistência das operações de escrita no banco de dados.
* **Paginação**: Implementação de paginação para as listagens de médicos e pacientes, otimizando o desempenho e a escalabilidade para grandes volumes de dados.
* **Soft Delete**: Médicos e pacientes são "excluídos" logicamente (inativados) em vez de removidos fisicamente do banco de dados, preservando o histórico.
* **Lombok**: Redução de código boilerplate (getters, setters, construtores, `equals`/`hashCode`).
* **Tratamento de Erros Global**: Implementação de um `RestControllerAdvice` para centralizar o tratamento de exceções, retornando respostas de erro padronizadas.

---

## 🚀 Tecnologias Utilizadas

<div>
 <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white">
 <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white">
 <img src="https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white">
 <img src="https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=json-web-tokens&logoColor=white">
 <img src="https://img.shields.io/badge/Lombok-E10098?style=for-the-badge&logo=lombok&logoColor=white">
 <img src="https://img.shields.io/badge/Jakarta_Validation-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
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
│                       ├── 📁 controller/           (Controladores REST para Médicos, Pacientes e Autenticação)
│                       ├── 📁 domain/               (Classes de domínio e DTOs para entidades)
│                       │   ├── 📁 endereco/         (Classes e records relacionados à entidade Endereco)
│                       │   ├── 📁 medico/           (Classes e records relacionados à entidade Medico)
│                       │   ├── 📁 paciente/         (Classes e records relacionados à entidade Paciente)
│                       │   └── 📁 usuario/          (Classes e records relacionados à entidade Usuario e Autenticação)
│                       ├── 📁 infra/                (Classes de infraestrutura)
│                       │   ├── 📁 exception/        (Tratamento global de erros)
│                       │   └── 📁 security/         (Configurações de segurança, JWT e filtros)
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
    cd api 
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

### Autenticação e Autorização

Todos os endpoints, **exceto `/login`**, exigem autenticação. Para acessar os recursos protegidos, você precisará de um JWT válido.

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
    * Para todas as outras requisições (POST, GET, PUT, DELETE para `/medicos` e `/pacientes`), inclua o JWT no cabeçalho `Authorization` no formato `Bearer <SEU_TOKEN_JWT>`.
    * **Exemplo de Cabeçalho HTTP**:
        ```
        Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ...
        ```

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
    * **Parâmetros de Query (Opcionais)**:
        * `page`: Número da página (padrão: 0).
        * `size`: Quantidade de itens por página (padrão: 2).
        * `sort`: Campo para ordenação (ex: `sort=nome,asc` ou `sort=email,desc`).
    * **Exemplo**: `http://localhost:8080/medicos?page=0&size=5&sort=nome`
    * **Resposta**: `200 OK` com uma página de `DadosListagemMedico`.

* **`GET /medicos/{id}`**
    * **Descrição**: Detalha as informações de um médico específico pelo seu ID.
    * **Exemplo**: `http://localhost:8080/medicos/1`
    * **Resposta**: `200 OK` com `DadosDetalhamentoMedico` ou `404 Not Found` se o ID não existir.

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
    * **Descrição**: Inativa um médico pelo seu ID (realiza um "soft delete"). O médico não será mais listado nas buscas por médicos ativos.
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
    * **Parâmetros de Query (Opcionais)**:
        * `page`: Número da página (padrão: 0).
        * `size`: Quantidade de itens por página (padrão: 10).
        * `sort`: Campo para ordenação (ex: `sort=nome,asc` ou `sort=email,desc`).
    * **Exemplo**: `http://localhost:8080/pacientes?page=0&size=5&sort=nome`
    * **Resposta**: `200 OK` com uma página de `DadosListagemPaciente`.

* **`GET /pacientes/{id}`**
    * **Descrição**: Detalha as informações de um paciente específico pelo seu ID.
    * **Exemplo**: `http://localhost:8080/pacientes/1`
    * **Resposta**: `200 OK` com `DadosDetalhamentoPaciente` ou `404 Not Found` se o ID não existir.

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
    * **Descrição**: Inativa um paciente pelo seu ID (realiza um "soft delete"). O paciente não será mais listado nas buscas por pacientes ativos.
    * **Exemplo**: `http://localhost:8080/pacientes/1`
    * **Resposta**: `204 No Content`.

---

## 📈 Melhorias Futuras

* Implementar a entidade `Consulta`, permitindo o agendamento e gerenciamento de consultas entre médicos e pacientes.
* Adicionar validações mais complexas para agendamentos de consultas (ex: horários disponíveis do médico).
* Gerar documentação interativa da API utilizando **OpenAPI/Swagger**.
* Implementar testes de integração e unitários mais abrangentes para todos os serviços e controladores, incluindo os de segurança.
* Refinar o tratamento de exceções para cenários específicos e mensagens de erro mais amigáveis.
* Otimizar o desempenho de consultas para grandes volumes de dados.

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
