# 🔐 EduTrack - Clean Architecture & Basic Auth

Este projeto é uma implementação de referência de um **módulo de autenticação e autorização** robusto, construído seguindo rigorosamente os princípios da **Clean Architecture** e **DDD (Domain-Driven Design)**.

O objetivo é fornecer uma base segura e desacoplada para lidar com usuários, logins e proteção de rotas via **HTTP Basic Auth (Stateless)**, pronta para ser integrada em aplicações que prezam pela simplicidade e padrões REST.

## 🎯 Funcionalidades

- [x] **Autenticação Stateless via Basic Auth** (Nativo do HTTP)
- [x] **Cadastro de Usuários com Criptografia** (BCrypt)
- [x] **Arquitetura Limpa** (Isolamento total do Domínio)
- [x] **Proteção de Rotas e Role-Based Access**
- [x] **Documentação Automática via Swagger UI**
- [x] **Tratamento de Exceções Centralizado**

# 🧠 Decisões Arquiteturais

Este projeto foi desenhado **intencionalmente** para demonstrar conhecimento avançado em **Engenharia de Software**.  
Cada decisão técnica abaixo foi tomada de forma consciente, priorizando **manutenibilidade, testabilidade, desacoplamento e clareza de domínio**.

## 1️⃣ Clean Architecture (Arquitetura Limpa)

**Decisão**  
Isolamento do núcleo da aplicação (**Domain** e **Application**) de detalhes externos como **Web**, **Banco de Dados** e **Security**.

**Por quê?**  
As regras de negócio devem ser o centro do software. O **Core** não depende do Spring Framework; o Spring é apenas um detalhe de infraestrutura que é “plugado” ao redor do domínio.

Esse isolamento garante que:
- Mudanças em frameworks não afetem a lógica de negócio
- Bancos de dados podem ser trocados sem refatorar o Core
- O sistema permaneça altamente testável e sustentável a longo prazo

---

## 2️⃣ Domain-Driven Design (DDD) — Rich Model

**Decisão**  
Uso de **Value Objects** (ex: `Email`, `Password`) no lugar de tipos primitivos (`String`) espalhados pelo sistema.

**Por quê?**  
Para evitar o *anti-pattern* **Primitive Obsession**.

Um e-mail não é apenas uma `String`; ele possui regras próprias de formatação e validade.  
Ao encapsular essas regras dentro de um `Value Object`, garantimos que:

- É impossível existir um e-mail inválido no domínio
- As regras ficam centralizadas
- Eliminamos validações duplicadas (`if`s espalhados pelo código)
- O domínio se torna mais expressivo e semântico

---

## 3️⃣ Inversão de Dependência (Gateways)

**Decisão**  
Os **Use Cases** dependem de **interfaces** (ex: `UserRepository`) e não de implementações concretas (ex: `JpaUserRepository`).

**Por quê?**  
O domínio precisa persistir dados, mas **não deve saber como** isso é feito (SQL, JPA, Arquivo, etc).

A infraestrutura é quem se adapta às regras do domínio — nunca o contrário.  
Isso proporciona:

- Baixíssimo acoplamento
- Facilidade extrema para testes unitários
- Uso de mocks sem necessidade de banco de dados real
- Maior flexibilidade arquitetural

---

## 4️⃣ Configuração Manual de Beans

**Decisão**  
Declaração dos **Use Cases** via classes `@Configuration`, evitando o uso de `@Service` nas classes de negócio.

**Por quê?**  
Para manter o domínio **100% agnóstico ao framework**.

Adicionar anotações do Spring (`@Service`, `@Component`) diretamente nas regras de negócio cria um acoplamento desnecessário com a infraestrutura.

Com a configuração manual:
- O Core é **Pure Java**
- As regras de negócio podem rodar até em um projeto console
- O Spring se torna apenas um detalhe de execução

---

## 5️⃣ HTTP Basic Authentication (Stateless)

**Decisão**  
Uso de **HTTP Basic Authentication** configurado com política de sessão **STATELESS**.

**Por quê?**  
Para reduzir **complexidade acidental**.

Diferente de:
- Sessões baseadas em cookies
- JWT (com refresh tokens, expiração e gerenciamento adicional)

O Basic Auth utiliza diretamente o cabeçalho padrão do protocolo HTTP:

# 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3+**
- **Spring Security 6** (HTTP Basic Authentication)
- **Spring Data JPA**
- **PostgreSQL** (Driver JDBC)
- **OpenAPI / Swagger UI**
- **Docker** (opcional, para banco de dados)

## 📋 Pré-requisitos

- **JDK 21**
- **PostgreSQL** rodando na porta `5432`

## 🔐 Configuração de Variáveis de Ambiente

O projeto utiliza variáveis de ambiente para proteger dados sensíveis.
Configure no sistema operacional ou na IDE:

- DB_PASSWORD=senha_do_postgres

## ▶️ Executar a Aplicação
No diretório raiz do projeto:

-📘 Documentação da API (Swagger)
Com a aplicação rodando, acesse:

-👉 http://localhost:8080/swagger-ui.html

A documentação permite visualizar e testar todos os endpoints disponíveis.

## 🧪 Como Testar os Endpoints
A API utiliza HTTP Basic Authentication.
Não existe endpoint de login — as credenciais são enviadas diretamente no header.

- 1️⃣ Registrar Usuário (Público)
POST /auth/register

Body
json
Copiar código
{
  "login": "usuario",
  "email": "usuario@email.com",
  "password": "123",
}

- 2️⃣ Acessar Dados Protegidos
GET /auth/me

Autenticação (Basic Auth)

Username: usuario
Password: 123

O endpoint retorna os dados do usuário autenticado.

## 📝 Licença
Este projeto foi desenvolvido para fins educacionais e de portfólio.

- Sinta-se livre para:
- Estudar a arquitetura
- Usar como referência
- Adaptar ideias para projetos próprios
