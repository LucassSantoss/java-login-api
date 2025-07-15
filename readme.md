# API de Autenticação com Java e Spring Boot

Esta é um projeto de API REST para autenticação de usuários, construído com Java e Spring Boot. A autenticação é baseada em tokens JWT (JSON Web Token).

---

## ▶️ Como Executar

Existem duas maneiras de executar este projeto: um ambiente de desenvolvimento completo com Docker Compose ou gerando uma imagem de produção otimizada.

### 🚀 Ambiente de Desenvolvimento com Docker Compose

Esta é a forma recomendada para desenvolver a aplicação. Requer Docker e Docker Compose instalados.

1.  **Inicie o Ambiente:**
    Na raiz do projeto (onde o arquivo `docker-compose.yml` está), execute o seguinte comando:
    ```bash
    docker compose up
    ```

2.  **Acesse a Aplicação:**
    A API estará disponível em `http://localhost:8080`.

Este ambiente de desenvolvimento vem com vários benefícios:
* **Hot Reload:** Qualquer alteração que você fizer no código-fonte será detectada e a aplicação reiniciará automaticamente.
* **Depuração Remota (Debug):** A porta de depuração `5006` está exposta. Você pode conectar o debugger da sua IDE (IntelliJ, VS Code, etc.) a `localhost:5006` para depurar o código em tempo real.
* **Dados Persistentes:** O banco de dados H2 é salvo em arquivo no diretório `/data` (que está no `.gitignore`), garantindo que seus dados de desenvolvimento não sejam perdidos ao reiniciar o contêiner.

### 📦 Gerando uma Imagem de Produção

Este fluxo é usado para criar uma imagem Docker final e otimizada, pronta para ser implantada em um ambiente de produção.

1.  **Construa a imagem Docker:**
    Navegue até o diretório raiz do projeto e execute o seguinte comando:
    ```bash
    docker build -t login-api .
    ```

2.  **Execute o contêiner:**
    Após a imagem ser construída, inicie um contêiner com o comando:
    ```bash
    docker run -p 8080:8080 login-api
    ```

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