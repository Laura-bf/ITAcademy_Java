# S502 Dice Game Nivel 1 (Fases 1 y 2)

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.2-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?logo=mongodb)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger)

## Description

Dice game REST API. Players can register, roll two dice, and have their results tracked. A roll wins when the sum equals 7. Supports dual persistence with JPA (MySQL/H2) and MongoDB. Includes comprehensive test coverage and Swagger documentation.

## Technologies

- Java 11
- Spring Boot 2.6.2
- Maven
- Spring Web
- Spring Data JPA
- Spring Data MongoDB
- H2 Database (runtime)
- MySQL Connector (runtime)
- Swagger (Springfox 2.9.2)
- JUnit 5
- Mockito

## Key Concepts

- Dice roll logic (two 6-sided dice, win on sum of 7)
- Dual persistence: JPA (`Player`) and MongoDB (`MongoPlayer`)
- Player ranking by win rate
- Profile-based persistence selection (`mysql`, `test`, `mongodb`)
- DTO pattern (`PlayerDTO`)
- Full test coverage (8 test files)

## API Endpoints

| Method | Path                     | Description                    |
|--------|--------------------------|--------------------------------|
| POST   | `/players`               | Create a new player            |
| PUT    | `/players`               | Change player name             |
| POST   | `/players/{id}/rolls`    | Player rolls the dice          |
| DELETE | `/players/{id}/rolls`    | Delete all rolls for a player  |
| GET    | `/players`               | List all players with win rates|
| GET    | `/players/{id}/rolls`    | List all rolls for a player    |
| GET    | `/players/ranking`       | Get all players' ranking       |
| GET    | `/players/ranking/loser` | Get the worst player           |
| GET    | `/players/ranking/winner`| Get the best player            |

## Project Structure

```
src/main/java/com/diceGame/
├── Application.java
├── configuration/
│   └── SwaggerConfig.java
├── controllers/
│   └── PlayerController.java
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

Swagger UI at `http://localhost:8080/swagger-ui.html`.

## How to Test

```bash
./mvnw test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data MongoDB](https://spring.io/projects/spring-data-mongodb)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Springfox Swagger](https://springfox.github.io/springfox/)
- [JUnit 5](https://junit.org/junit5/)
