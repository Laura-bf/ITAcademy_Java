# S501 Spring REST API Nivel 1 — White Collar Gallery

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.0-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)

## Description

REST API for managing art galleries ("White Collar Gallery"). Supports CRUD operations for shops and their picture inventories using Spring Data JPA with an H2 in-memory database. Includes Postman collections.

## Technologies

- Java 11
- Spring Boot 2.6.0
- Maven
- Spring Web
- Spring Data JPA
- Spring Data REST
- H2 Database (runtime)
- Spring DevTools

## Key Concepts

- RESTful API design
- One-to-many relationship (`Shop` → `Picture` via `@OneToMany`)
- JPA entity modeling with automatic ID generation
- Service layer (`ShopService`)
- Repository pattern (`ShopRepository`)

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
src/main/java/com/white_collar/nivel1/
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
- [H2 Database](https://www.h2database.com/)
