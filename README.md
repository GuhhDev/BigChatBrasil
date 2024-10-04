# Big Chat Brasil! 🇧🇷💬📱

Este projeto consiste em dois módulos principais: um backend em **Spring Boot** com **Gradle** e um frontend em **React**. O backend expõe uma API RESTful para gerenciar clientes e mensagens, enquanto o frontend é uma interface simples que interage com essa API.

## Requisitos

Certifique-se de ter as seguintes ferramentas instaladas:

- **Java 17** (ou superior)
- **Gradle** (para gerenciamento de dependências do backend)
- **Node.js** (v14 ou superior) com **npm** ou **yarn** (para o frontend)
- **Docker** e **Docker Compose** (para rodar o banco de dados PostgreSQL e outros serviços)

## Instruções para Rodar o Backend (Spring Boot)

O backend é uma aplicação **Spring Boot** que gerencia o sistema de clientes e mensagens. Ele está configurado para usar o **PostgreSQL** como banco de dados, que será rodado via **Docker Compose**.

### 1. Configuração do Banco de Dados (PostgreSQL com Docker Compose)

1. Certifique-se de que você tem o **Docker** e **Docker Compose** instalados.

2. No diretório do projeto, crie e inicie o container do PostgreSQL usando o seguinte comando:

   ```bash
   docker-compose up -d
### 2. Compilar e Rodar o Backend 
1. Navegue até a pasta do **backend** do projeto (onde está o arquivo build.gradle):
2. Compile e execute o projeto usando o Gradle:

    ```bash
   ./gradlew bootRun
   
## Instruções para Rodar o Frontend (React)
O frontend é uma aplicação React que interage com a API exposta pelo backend Spring Boot.

### 1. Instalar Dependências
Navegue até a pasta do frontend do projeto:

    cd frontend

Instale as dependências necessárias usando npm ou yarn:
    
    npm install | yarn install

### 2. Execute o projeto:

    npm start | yarn start

## Como Testar a Aplicação
- Abra o navegador e vá para **http://localhost:3000.**
- Use os botões disponíveis na interface para interagir com a API, como cadastrar um cliente, listar clientes, enviar mensagens, etc.

## Problemas Comuns
### 1. CORS Policy (Cross-Origin Resource Sharing)
   Se você receber um erro de CORS ao tentar conectar o frontend ao backend, certifique-se de que o CORS está configurado no backend Spring Boot. Verifique se há uma configuração global de CORS ou use a anotação @CrossOrigin nos controladores.

### 2. Porta em Conflito
   Se a porta 8080 ou 3000 estiver em uso por outra aplicação, altere a porta no arquivo application.properties do backend ou no script package.json do frontend.