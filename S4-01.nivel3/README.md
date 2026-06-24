# S4-01 Nivel 3 — Simple REST API (Maven)

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.6-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)

## Description

Same as S4-01 Nivel 2 but built with Maven instead of Gradle. A minimal Spring Boot REST API exposing greeting endpoints with Postman collection support.

## Technologies

- Java 11
- Spring Boot 2.5.6
- Maven
- Spring Web
- Spring Data JPA
- H2 Database (runtime)

## Key Concepts

- Maven build configuration
- REST controller with path variables
- Minimal project structure

## API Endpoints

| Method | Path         | Description       |
|--------|--------------|-------------------|
| GET    | `/v1/test`   | Hello Gradle!     |
| GET    | `/v1/`       | HELLO WORLD!      |
| GET    | `/v1/{name}` | Greet by name     |

## Project Structure

```
src/main/java/com/demo/
├── S401Nivel3App.java
└── controller/
    └── InitialController.java
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
- [Maven](https://maven.apache.org/)
