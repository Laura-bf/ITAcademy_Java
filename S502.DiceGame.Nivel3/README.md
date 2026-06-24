# S502 Dice Game Nivel 3 — Refined Architecture with DTOs

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.3-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?logo=mongodb)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger)

## Description

Refined architecture of the Dice Game with separated concerns. Rolls are stored as MongoDB documents independently from players (JPA entities). Uses DTOs for API communication, with separate domain models, documents, and repositories.

## Technologies

- Java 11
- Spring Boot 2.6.3
- Maven
- Spring Web
- Spring Security
- JWT (JJWT 0.9.1)
- Spring Data JPA
- Spring Data MongoDB
- MySQL Connector (runtime)
- Swagger (Springfox 2.9.2)
- JUnit 5

## Key Concepts

- Separated architecture: `documents/` (MongoDB Roll), `entities/` (JPA Player)
- DTO models: `RollDTO`, `PlayerDTO`
- Independent repositories: `RollRepository` (Mongo), `PlayerRepository` (JPA)
- JWT authentication with Spring Security
- Same dice game logic (two dice, win on sum of 7)
- Clean layered architecture

## API Endpoints

| Method | Path                     | Authentication | Description                    |
|--------|--------------------------|----------------|--------------------------------|
| POST   | `/players`               | No             | Create a new player (Sign-Up)  |
| POST   | `/login`                 | No             | Authenticate (get JWT token)   |
| GET    | `/players`               | Yes            | List all players               |
| PUT    | `/players`               | Yes            | Change player name             |
| POST   | `/players/{id}/rolls`    | Yes            | Player rolls the dice          |
| GET    | `/players/{id}/rolls`    | Yes            | List all rolls for a player    |
| DELETE | `/players/{id}/rolls`    | Yes            | Delete all rolls for a player  |
| GET    | `/players/ranking`       | Yes            | Get players' ranking           |
| GET    | `/players/ranking/loser` | Yes            | Get the worst player           |
| GET    | `/players/ranking/winner`| Yes            | Get the best player            |

## Project Structure

```
src/main/java/com/diceGame/nivel3/
├── Application.java
├── configuration/
│   └── SwaggerConfig.java
├── controllers/
│   └── PlayerController.java
├── domain/
│   ├── documents/
│   │   └── Roll.java
│   ├── entities/
│   │   └── Player.java
│   └── models/
│       ├── PlayerDTO.java
│       └── RollDTO.java
├── model/
│   └── services/
│       └── PlayerService.java
├── persistance/
│   └── repositories/
│       ├── PlayerRepository.java
│       └── RollRepository.java
└── security/
    ├── JWTAuthenticationLoginFilter.java
    ├── JWTAuthorizationFilter.java
    ├── JwtUtil.java
    ├── SecurityConfig.java
    └── SecurityConstants.java
```

## How to Run

```bash
./mvnw spring-boot:run
```

### Authentication Flow

1. Register via `POST /players` with `{"name": "player1", "password": "pass"}`
2. Login via `POST /login` with same credentials → receive JWT in `Authorization` header
3. Use `Authorization: Bearer <token>` for all subsequent requests

## How to Test

```bash
./mvnw test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [JJWT](https://github.com/jwtk/jjwt)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Springfox Swagger](https://springfox.github.io/springfox/)
