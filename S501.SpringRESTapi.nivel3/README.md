# S501 Spring REST API Nivel 3 — White Collar Gallery (Tests)

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.1-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql)

## Description

Extended version of S501 Nivel 1 with comprehensive test coverage. The same REST API for managing art shops and pictures now includes unit and integration tests using JUnit 5, Mockito, and Spring Boot Test.

## Technologies

- Java 11
- Spring Boot 2.6.1
- Maven
- Spring Web
- Spring Data JPA
- Spring Data REST
- H2 Database (runtime)
- MySQL Connector (runtime)
- Spring DevTools
- JUnit 5
- Mockito
- REST Assured

## Key Concepts

- Unit tests for domain models (`ShopTest`, `PictureTest`)
- Repository integration tests (`ShopRepositoryTest` with `@DataJpaTest`)
- Service layer tests (`ShopServiceTest` with `@SpringBootTest`)
- Controller layer tests (`ShopControllerTest` with `@WebMvcTest`)
- MockMvc for HTTP request simulation

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
src/main/java/com/white_collar/nivel3/
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

src/test/java/com/white_collar/nivel3/
├── controllers/
│   └── ShopControllerTest.java
└── model/
    ├── domain/
    │   ├── PictureTest.java
    │   └── ShopTest.java
    ├── repositories/
    │   └── ShopRepositoryTest.java
    └── services/
        └── ShopServiceTest.java
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
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)
- [REST Assured](https://rest-assured.io/)
