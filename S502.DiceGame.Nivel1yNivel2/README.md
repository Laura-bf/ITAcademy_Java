# S502 Dice Game Nivel 1 y 2 — with JWT Security

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.2-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-000000?logo=jsonwebtokens)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?logo=mongodb)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger)

## Description

Extended version of S502 Nivel 1 with Spring Security and JWT authentication. Players must authenticate via `POST /login` to receive a Bearer token, which is then required for all game operations.

## Technologies

- Java 11
- Spring Boot 2.6.2
- Maven
- Spring Web
- Spring Security
- JWT (JJWT 0.9.1)
- Spring Data JPA
- Spring Data MongoDB
- H2 Database (runtime)
- MySQL Connector (runtime)
- Swagger (Springfox 2.9.2)
- JUnit 5

## Key Concepts

- JWT authentication and authorization
- `SecurityConfig` with stateless session management
- `JWTAuthenticationLoginFilter` — intercepts `/login` POST
- `JWTAuthorizationFilter` — validates Bearer token on every request
- `JwtUtil` — token creation and validation utility
- Profile-based dual persistence (JPA / MongoDB)
- Same dice game logic as Nivel 1

## API Endpoints

| Method | Path                     | Authentication | Description                    |
|--------|--------------------------|----------------|--------------------------------|
| POST   | `/players`               | No             | Register (Sign-Up)             |
| POST   | `/login`                 | No             | Authenticate (get JWT token)   |
| PUT    | `/players`               | Yes            | Change player name             |
| POST   | `/players/{id}/rolls`    | Yes            | Player rolls the dice          |
| DELETE | `/players/{id}/rolls`    | Yes            | Delete all rolls for a player  |
| GET    | `/players`               | Yes            | List all players               |
| GET    | `/players/{id}/rolls`    | Yes            | List all rolls for a player    |
| GET    | `/players/ranking`       | Yes            | Get players' ranking           |
| GET    | `/players/ranking/loser` | Yes            | Get the worst player           |
| GET    | `/players/ranking/winner`| Yes            | Get the best player            |

## Project Structure

```
src/main/java/com/diceGame/
├── Application.java
├── configuration/
│   └── SwaggerConfig.java
├── controllers/
│   └── PlayerController.java
├── security/
│   ├── JWTAuthenticationLoginFilter.java
│   ├── JWTAuthorizationFilter.java
│   ├── JwtUtil.java
│   ├── SecurityConfig.java
│   └── SecurityConstants.java
└── model/
    ├── DTO/PlayerDTO.java
    ├── domain/
    │   ├── MongoPlayer.java
    │   ├── Player.java
    │   └── Roll.java
    ├── persistance/
    │   ├── MongoPlayerRepository.java
    │   └── PlayerRepository.java
    └── services/
        ├── MongoPlayerServiceImpl.java
        ├── PlayerService.java
        └── PlayerServiceImpl.java
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
- [Springfox Swagger](https://springfox.github.io/springfox/)
