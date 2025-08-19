# LiterAlura

![GitHub last commit](https://img.shields.io/github/last-commit/Douglas-Perez/LiterAlura-Challenge-One)
![Java](https://img.shields.io/badge/Java-ED8B00?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/SpringBoot-6DB33F?logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?logo=postgresql&logoColor=white)

Este é um projeto de **Catálogo de Livros** chamado LiterAlura, desenvolvido em Java com Spring Boot. A aplicação permite buscar livros através da API Guntendex, armazenar os dados em um banco de dados PostgreSQL e consultar informações de livros e autores via terminal.

## Funcionalidades

* **Buscar livro pelo título**: Consulta a API e salva o livro no banco.
* **Listar livros registrados**: Exibe todos os livros salvos.
* **Listar autores registrados**: Exibe todos os autores com seus livros.
* **Listar autores vivos em um determinado ano**: Consulta autores que estavam vivos em um ano específico.
* **Listar livros em um determinado idioma**: Filtra livros pelo idioma.
* **Interação via terminal**: Menu com 5 opções + sair.

## Menu

```
--- MENU ---
1- Buscar livro pelo título
2- Listar livros registrados
3- Listar autores registrados
4- Listar autores vivos em um determinado ano
5- Listar livros em um determinado idioma
0- Sair
```

## Tecnologias Utilizadas

* **Java 17+**: Lógica principal do programa.
* **Spring Boot**: Framework para criar a aplicação.
* **Spring Data JPA / Hibernate**: Persistência de dados.
* **PostgreSQL**: Banco de dados relacional.
* **HttpClient**: Requisições HTTP para a API Guntendex.
* **Guntendex API**: Fonte de dados de livros.

## Estrutura do Projeto

* `LiterAluraApplication.java`: Classe principal que inicia o Spring Boot.
* `Principal.java`: Gerencia o menu e interações do usuário.
* `ConsumoApi.java`: Faz requisições HTTP à API Guntendex.
* `DadosLivro.java` / `DadosAutor.java` / `DadosResultado.java`: Models para mapear os dados da API.
* `Livro.java` / `Autor.java`: Entidades do banco de dados.
* `LivroRepository.java` / `AutorRepository.java`: Interfaces para acesso ao banco de dados.

## Como Executar o Projeto

1. **Clone o repositório**:

```bash
git clone git@github.com:Douglas-Perez/LiterAlura.git
```

2. **Configure o banco de dados PostgreSQL**:

* Crie um banco chamado `alura_livros`.
* Configure `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` no `application.properties`.

3. **Execute a aplicação**:

```bash
./mvnw spring-boot:run
```

4. **Interaja via terminal** seguindo o menu.

## Licença

Este projeto é de uso livre.

