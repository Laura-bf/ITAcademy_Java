# IT Academy — Backend Java Bootcamp

![Java](https://img.shields.io/badge/Java-8%2B-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5–2.6-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3-C71A36?logo=apachemaven)
![Gradle](https://img.shields.io/badge/Gradle-7-02303A?logo=gradle)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql)
![MongoDB](https://img.shields.io/badge/MongoDB-5-47A248?logo=mongodb)
![JWT](https://img.shields.io/badge/JWT-Auth-000000?logo=jsonwebtokens)
![Swagger](https://img.shields.io/badge/Swagger-3-85EA2D?logo=swagger)
![JUnit](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5)

A comprehensive showcase of backend Java projects developed during the **IT Academy** (Barcelona, 2021) bootcamp. The curriculum progresses from fundamental programming concepts through enterprise-level **Spring Boot** microservices, covering relational and NoSQL databases, OOP design patterns, concurrency, and REST API development.

---

## Highlights

What stands out when reviewing this portfolio is not just the breadth of topics, but the **velocity of growth**. In roughly five months the codebase evolves from simple variable declarations to a Spring Boot microservice with JWT authentication, dual persistence (JPA + MongoDB), Swagger documentation, and a full test suite — a trajectory that mirrors what is expected of a production-ready backend developer.

### Clean Code & SOLID Principles

From the earliest modules the code reflects awareness of **clean code** practices: meaningful class and method names, single-responsibility classes, and consistent formatting. By **M6 (Jobs)**, the **Open/Closed** and **Dependency Inversion** principles are in full effect — an `IPaymentRate` interface decouples salary calculation from employee types, and a `PaymentFactory` handles object creation. The **MVC architecture** in **M8 (Videos)** and **M11 (Floristeria)** cleanly separates concerns across `controller`, `service`, `persistence`, and `view` layers, making the code testable and maintainable.

### Design Patterns Applied

- **Factory Pattern** (M6) — polymorphic payment and tax strategy creation
- **Command Pattern** (M11) — menu actions encapsulated as command objects with a state-machine-driven interface
- **MVC Pattern** (M8, M9, M11) — consistent three-layer separation across multiple projects
- **Repository Pattern** (M8, S4, S5) — data access abstraction reused across all Spring Boot projects
- **DTO Pattern** (M8, S502) — clean separation between domain models and API contracts

### Testing & Quality

The later modules (S501, S502) include **JUnit 5** tests covering service logic, repository integration, and controller endpoints. These are not superficial tests — they validate real business rules (shop capacity limits, dice roll win rates, player ranking calculations) and demonstrate familiarity with **Mockito**, **Spring Boot Test**, and layered test organization.

### Production-Ready Concerns

- **Custom exceptions** (M4) — domain-specific exception hierarchy (`EmptyOrderException`, `InvalidPaymentException`)
- **JWT security** (S502) — filters, token utilities, security configuration, and stateless session management implemented from scratch
- **API documentation** (S402, S502) — Swagger/OpenAPI configuration for auto-generated endpoint docs
- **Dual persistence** (S502) — same service layer operating over both JPA and MongoDB via Spring profiles
- **Request filtering** (S402 N3) — custom servlet filters for cross-cutting concerns

### Pair Programming

**M11** was developed as a pair-programming exercise, resulting in a notably well-structured codebase. The Command pattern implementation, state-machine menu navigation, and clean separation of concerns reflect strong collaboration and collective code ownership.

---

In short, this repository captures a developer who not only learns quickly, but also internalises **engineering fundamentals** — writing code that is readable, testable, and built to last.

## Learning Journey

### Phase 1 — Foundations
_M1–M4: Variables, arrays, loops, console I/O, exception handling_

| Project | Description |
|---------|-------------|
| [M1_Variables](./M1_Variables) | Data types, string/primitive declaration, formatted console output |
| [M2_LetrasRepetidas](./M2_LetrasRepetidas) | Char arrays, nested loops, number pyramids, countdown timer |
| [M3_NombresCiudades](./M3_NombresCiudades) | Console input, array sort/transform, average calculator, Fibonacci |
| [M4_Restaurante](./M4_Restaurante) | Restaurant menu simulation, bill calculation, custom exceptions |

### Phase 2 — Databases
_M5, S2: MySQL schema design, SQL queries, MongoDB document databases_

| Project | Description |
|---------|-------------|
| [M5_MySQL](./M5_MySQL) | Database schemas for optician, pizzeria, YouTube, and Spotify |
| [M5_Queries](./M5_Queries) | SQL practice — joins, aggregates, subqueries (classic EMP/DEPT schema) |
| [S2.03_MongoDB](./S2.03_MongoDB) | MongoDB document models for optician, pizzeria, YouTube, Spotify |
| [S2.04_MongoDB Queries](./S2.04_MongoDB%20Queries) | 33 MongoDB queries — filters, regex, aggregation pipeline |

### Phase 3 — OOP & Design Patterns
_M6–M8, M11: Inheritance, polymorphism, MVC, Command pattern, Swing GUI_

| Project | Description |
|---------|-------------|
| [M6_Jobs](./M6_Jobs) | Payroll system — polymorphism, interfaces, Factory pattern, tax calculation |
| [M7_Vehicles](./M7_Vehicles) | Vehicle workshop — Swing GUI, domain model, license/owner management |
| [M8_Videos](./M8_Videos_N1) | Video management — full MVC architecture, DTOs, repositories, Swing views |
| [M11_Floristeria](./M11_PairProgramming_Floristeria_N1) | Florist shop — Command pattern, CRUD, stock management, ticketing system |

### Phase 4 — Concurrency & Functional Programming
_M9–M10: Threads, rocket simulation, Lambdas, Streams API_

| Project | Description |
|---------|-------------|
| [M9_Cohetes](./M9_Cohetes_N1) | Rocket simulation — multi-threaded acceleration, Swing control panel |
| [M10_Lambdas](./M10_Lambdas_N1) | Lambda exercises — filtering, functional interfaces, Stream processing |

### Phase 5 — Spring Boot & REST APIs
_S4–S5: RESTful services, JPA, H2, MongoDB, Swagger, JWT security, testing_

| Project | Description |
|---------|-------------|
| [S4.01_Postman](./S4.01_Postman_N1) | Spring Boot intro — REST controller with Postman collections (Gradle) |
| [S4-01_N2](./S4-01.nivel2) | Spring Boot REST API (Gradle) |
| [S4-01_N3](./S4-01.nivel3) | Spring Boot REST API (Maven) |
| [S402_N1](./S402.nivel1) | Employee CRUD — JPA, Thymeleaf, Swagger documentation |
| [S402_N2](./S402.nivel2) | Employee management + file I/O service |
| [S402_N3](./S402.nivel3) | Employee management + request filters |
| [S501_Gallery](./S501.SpringRESTapi.nivel1) | White Collar Gallery — REST API, JPA, H2, unit & integration tests |
| [S502_DiceGame](./S502.DiceGame.Nivel1.Fases1y2) | Dice Game — REST API, JPA + MongoDB, Swagger |
| [S502_DiceGame+JWT](./S502.DiceGame.Nivel1yNivel2) | Dice Game + JWT authentication/authorization |
| [S502_DiceGame_N3](./S502.DiceGame.Nivel3) | Dice Game — refined architecture, DTOs, comprehensive testing |

---

## Built With

| Category | Technologies |
|----------|-------------|
| **Languages** | Java 8–11, SQL, JavaScript (MongoDB) |
| **Frameworks** | Spring Boot 2.5–2.6, JPA/Hibernate, Thymeleaf |
| **Databases** | MySQL, H2 (in-memory), MongoDB |
| **Build Tools** | Maven, Gradle |
| **API & Docs** | Swagger/OpenAPI 3, Postman |
| **Security** | JWT (JSON Web Tokens) |
| **Testing** | JUnit 5, Spring Boot Test, Mockito |
| **Desktop UI** | Java Swing |
| **IDE** | Eclipse |

---

## Getting Started

### Prerequisites

- Java 8 or 11 (check each project's `pom.xml` or build file)
- Maven or Gradle (varies per project)
- MySQL or MongoDB for database-related projects

### Running a Project

**Java-only projects (M1–M4, M6–M11):**
```bash
cd <project-dir>/src
javac <package-path>/Main.java
java <package-path>.Main
```

**Maven projects (S4–S5):**
```bash
cd <project-dir>
mvn spring-boot:run   # run application
mvn test              # run tests
```

**Gradle projects:**
```bash
cd <project-dir>
gradle bootRun
```

**SQL scripts (M5):**
Execute `.sql` files directly in MySQL Workbench or CLI.

**MongoDB scripts (S2):**
```bash
mongosh < script.js
```

---

## Project Structure

```
ITAcademy_Java-master/
├── M1_Variables/                     # Phase 1: Foundations
├── M2_LetrasRepetidas/
├── M3_NombresCiudades/
├── M4_Restaurante/
├── M5_MySQL/                         # Phase 2: Databases
├── M5_Queries/
├── S2.03_MongoDB/
├── S2.04_MongoDB Queries/
├── M6_Jobs/                          # Phase 3: OOP & Patterns
├── M7_Vehicles/
├── M8_Videos_N1/ … N3/
├── M11_PairProgramming_Floristeria_N1/ … N3/
├── M9_Cohetes_N1/ … N3/              # Phase 4: Concurrency
├── M10_Lambdas_N1/ … N3/
├── S4.01_Postman_N1/                 # Phase 5: Spring Boot
├── S4-01.nivel2/ … nivel3/
├── S402.nivel1/ … nivel3/
├── S501.SpringRESTapi.nivel1/ … nivel3/
├── S502.DiceGame.Nivel1.Fases1y2/
├── S502.DiceGame.Nivel1yNivel2/
├── S502.DiceGame.Nivel3/
└── README.md
```

---

## About IT Academy

[**IT Academy**](https://www.barcelonactiva.cat/es/it-academy) is a Barcelona-based training program promoted by **Barcelona Activa** (Barcelona City Council's economic development agency). It offers intensive bootcamps designed to train tech professionals and connect them with local job opportunities.

This repository contains all exercises and projects completed during the **Backend Java** track (2021) — approximately 5 months of full-time study with progressive difficulty across individual and pair-programming assignments. The curriculum follows a structured, project-based methodology covering everything from Java fundamentals to enterprise-level Spring Boot development, with a strong emphasis on practical coding over theory.
