# ForumHub 🧠💬

ForumHub é uma API REST desenvolvida com **Spring Boot** que simula um sistema de fórum online. Com autenticação JWT e integração ao banco de dados MySQL, permite que usuários registrem tópicos de discussão, façam listagens, atualizações e exclusões, tudo seguindo boas práticas RESTful.

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Flyway** (controle de versionamento do banco)
- **MySQL** (persistência de dados)
- **JWT (JSON Web Token)** para autenticação
- **Hibernate** (ORM)
- **Maven** (gerenciador de dependências)

---

## 🎯 Objetivo

O projeto tem como finalidade servir como base para sistemas de discussão online, como fóruns acadêmicos ou de suporte, permitindo o cadastro de tópicos por usuários autenticados.

---

## ⚙️ Como rodar o projeto localmente
Clone o repositório: git clone https://github.com/seu-usuario/forumhub.git

Configure o banco de dados MySQL:
Crie o banco: 
````
CREATE DATABASE forumhub;
````

Atualize o arquivo application.properties com seu usuário e senha do banco.
````
spring.datasource.username=root
spring.datasource.password=senha_do_mysql
````

Rode o projeto com o Spring Boot (main do ApiApplication).

Antes de prosseguirmos para as requisições no Postman ou Insomnia, temos que criar um Usuario para poder fazer login.
Execute essa query SQL no seu MySQL (ajuste se necessário):
````
INSERT INTO usuario (nome, email, senha)
VALUES ('Jonas', 'jonas@email.com', '$2a$10$ehXb8a6j3eZOfGm1YzP0lO1ZPRn9FJutLQklMkP8i5O5T9yU05hA6');
````
-- senha: 123456

## 🔐 Autenticação

A autenticação é feita via **JWT**.
Vamos utilizar o Insomnia ou Postman para realizar as requisições.
Caminho: http://localhost:8080/

### Login
Rota: POST /login

Body:
```json
{
  "email": "jonas@email.com",
  "senha": "123456"
}
````

Irá retornar um token para que seja utilizado para poder liberar as requisições.
O token deve ser enviado nas requisições protegidas (sem "") via Header ou Auth > Beaer Token > Token.

# 📌 Funcionalidades

## ✅ Criar tópico
Rota: POST /topicos

Body:
```json
{
  "titulo": "Dúvida sobre Spring Boot",
  "mensagem": "Como configurar o application.properties?",
  "autorId": 1,
  "cursoId": 1
}
````

## Cadastrar curso
Rota: POST / cursos

ex:

Body:
```json
{
  "nome": "Java",
  "categoria": "Programação"
}
````

## 🔎 Buscar tópico por ID
Rota: GET /topicos/{id}

Exemplo: GET /topicos/1

## ✏️ Atualizar tópico
Rota: PUT /topicos/{id}

Body:
```json
{
  "titulo": "Erro Flyway",
  "mensagem": "Erro ao rodar as migrations."
}
````

## ❌ Deletar tópico

Rota: DELETE /topicos/{id}

Retorno: 204 No Content


## 🧠 Autor
Desenvolvido por Jonas Alisson 👨‍💻


