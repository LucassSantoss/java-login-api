# API de Autenticação com Java e Spring Boot

Esta é um projeto de API REST para autenticação de usuários, construído com Java e Spring Boot. A autenticação é baseada em tokens JWT (JSON Web Token).

---

## ▶️ Como Executar com Docker

Para executar a aplicação, você precisa ter o Docker instalado. Siga os passos abaixo:

1.  **Construa a imagem Docker:**
    Navegue até o diretório raiz do projeto (onde o `Dockerfile` está localizado) e execute o seguinte comando no seu terminal:
    ```bash
    docker build -t login-api .
    ```

2.  **Execute o contêiner:**
    Após a imagem ser construída com sucesso, inicie um contêiner com o comando:
    ```bash
    docker run -p 8080:8080 login-api
    ```
    A aplicação estará disponível em `http://localhost:8080`.

---

## Endpoints da API

A API possui dois endpoints principais para registro e login de usuários.

### `POST /auth/register`

Este endpoint é usado para registrar um novo usuário no sistema.

* **Request Body**:
  O corpo da requisição deve ser um JSON contendo o nome, email e senha do usuário.

    ```json
    {
        "name": "Seu Nome",
        "email": "usuario@exemplo.com",
        "password": "sua-senha-forte"
    }
    ```

* **Success Response (200 OK)**:
  Se o usuário for registrado com sucesso, a API retornará o nome do usuário e um token JWT.

    ```json
    {
        "name": "Seu Nome",
        "token": "seu.jwt.token.aqui"
    }
    ```

* **Error Response (400 Bad Request)**:
  Se o email fornecido já estiver cadastrado, a API retornará um erro `400 Bad Request`.

---

### `POST /auth/login`

Este endpoint é usado para autenticar um usuário existente e obter um token de acesso.

* **Request Body**:
  O corpo da requisição deve ser um JSON contendo o email e a senha do usuário.

    ```json
    {
        "email": "usuario@exemplo.com",
        "password": "sua-senha-forte"
    }
    ```

* **Success Response (200 OK)**:
  Se as credenciais estiverem corretas, a API retornará o nome do usuário e um novo token JWT.

    ```json
    {
        "name": "Seu Nome",
        "token": "seu.jwt.token.aqui"
    }
    ```

* **Error Response (400 Bad Request)**:
  Se o usuário não for encontrado ou a senha estiver incorreta, a API retornará um erro `400 Bad Request`.