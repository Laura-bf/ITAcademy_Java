# S4-01 Nivel 2 — Simple REST API (Gradle)

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.6-6DB33F?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-02303A?logo=gradle)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)

## Description

Simple Spring Boot REST API built with Gradle. Exposes a REST controller with basic greeting endpoints. Includes Postman collections for testing.

## Technologies

- Java 11
- Spring Boot 2.5.6
- Gradle
- Spring Web
- Spring Data JPA
- H2 Database (runtime)

## Key Concepts

- Minimal REST controller setup
- Path variable handling (`@PathVariable`)
- Gradle build configuration

## API Endpoints

| Method | Path         | Description       |
|--------|--------------|-------------------|
| GET    | `/v1/test`   | Hello Gradle!     |
| GET    | `/v1/`       | HELLO WORLD!      |
| GET    | `/v1/{name}` | Greet by name     |

## Project Structure

```
src/main/java/com/demo/
├── S401Nivel2App.java
└── controller/
    └── InitialController.java
```

## How to Run

```bash
gradle bootRun
```

## How to Test

```bash
gradle test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)
