# S4.01 Postman N1 — Spring Boot Initial Demo

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.5-6DB33F?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-02303A?logo=gradle)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)

## Description

Introductory Spring Boot project that exposes a REST controller (`/v1`) returning user data. Includes Postman collections for API testing. Uses JPA with an in-memory H2 database.

## Technologies

- Java 8
- Spring Boot 2.5.5
- Gradle
- Spring Data JPA
- Spring Web
- H2 Database (runtime)

## Key Concepts

- REST controller with `@GetMapping` endpoints (`/test`, `/`, `/{name}`, `/user/{uuid}`)
- Service layer (`IUserService` / `UserServiceImpl`)
- DTO pattern (`UserResponseDto`, `ResponseDto`)
- Repository pattern (`IRepository`, `UserRepository`)
- Postman collection for manual testing

## API Endpoints

| Method | Path           | Description                 |
|--------|----------------|-----------------------------|
| GET    | `/v1/test`     | Hello Gradle!               |
| GET    | `/v1/`         | HELLO WORLD!                |
| GET    | `/v1/{name}`   | Greet by name               |
| GET    | `/v1/user/{uuid}` | Fetch user data by UUID  |

## Project Structure

```
src/main/java/com/example/demo/
├── SpringBootInitialDemoMasterNuevoApplication.java
├── configuration/
│   ├── SpringConfiguration.java
│   └── WebMVCConfiguration.java
├── controller/
│   └── InitialController.java
├── dto/
│   ├── ResponseDto.java
│   └── UserResponseDto.java
├── repository/
│   ├── IRepository.java
│   └── UserRepository.java
└── service/
    ├── IUserService.java
    └── impl/UserServiceImpl.java
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
- [H2 Database](https://www.h2database.com/)
