# S501 Spring REST API Nivel 2 — White Collar Gallery (MySQL)

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.0-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql)

## Description

Extended version of S501 Nivel 1 with MySQL connector support. Same REST API for managing art shops and picture inventories, now configurable for MySQL persistence alongside H2.

## Technologies

- Java 11
- Spring Boot 2.6.0
- Maven
- Spring Web
- Spring Data JPA
- Spring Data REST
- H2 Database (runtime)
- MySQL Connector (runtime)
- Spring DevTools

## Key Concepts

- Same REST API as Nivel 1
- Added MySQL database support
- Shop and Picture entity relationship

## API Endpoints

| Method | Path                 | Description                    |
|--------|----------------------|--------------------------------|
| POST   | `/shops`             | Create a new shop              |
| GET    | `/shops`             | List all shops                 |
| POST   | `/shops/{id}/pictures` | Add a picture to a shop      |
| GET    | `/shops/{id}/pictures` | List pictures in a shop      |
| DELETE | `/shops/{id}/pictures` | Remove all pictures from shop |

## Project Structure

```
src/main/java/com/white_collar/nivel2/
├── Application.java
├── controllers/
│   └── ShopController.java
└── model/
    ├── domain/
    │   ├── Picture.java
    │   └── Shop.java
    ├── repositories/
    │   └── ShopRepository.java
    └── services/
        └── ShopService.java
```

## How to Run

```bash
./mvnw spring-boot:run
```

## How to Test

```bash
./mvnw test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL](https://www.mysql.com/)
