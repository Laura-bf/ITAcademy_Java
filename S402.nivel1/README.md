# S402 Nivel 1 — Employee CRUD with Thymeleaf & Swagger

![Java](https://img.shields.io/badge/Java-11-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.6-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)

## Description

Full-stack web application for employee management. Provides CRUD operations via a Thymeleaf frontend with automatic salary assignment based on job role (`CLERK`, `BOSS`, `MANAGER`). Includes Swagger UI for API documentation.

## Technologies

- Java 11
- Spring Boot 2.5.6
- Maven
- Spring Web (MVC)
- Spring Data JPA
- Thymeleaf
- Swagger (Springfox 2.9.2)
- H2 Database (runtime)
- Spring DevTools

## Key Concepts

- Full CRUD with Thymeleaf views (`ModelAndView`)
- Employee entity with JPA
- Job roles as Java enum (`CLERK`, `BOSS`, `MANAGER`)
- DTO pattern (`EmployeeDTO`)
- Service layer (`EmployeeService` / `EmployeeServiceImpl`)
- Repository pattern (`EmployeeRepository`)
- Custom error handling (`ErrorsController`)
- Swagger configuration (`SwaggerConfig`)

## API Endpoints

| Method | Path                      | Description              |
|--------|---------------------------|--------------------------|
| GET    | `/Empleados`              | List all employees       |
| GET    | `/Empleados/{id}`         | View employee details    |
| GET    | `/Empleados/Nuevos`       | Show create form         |
| GET    | `/Empleados/Edicion/{id}` | Show edit form           |
| POST   | `/Empleados/Updates`      | Save employee changes    |
| GET    | `/Empleados/Bajas/{id}`   | Delete employee          |
| POST   | `/Empleados/Filtros`      | Filter employees by job  |
| GET    | `/Errors`                 | Custom error page        |

## Project Structure

```
src/main/java/com/empleados/S402/nivel1/
├── Application.java
├── configuration/
│   └── SwaggerConfig.java
├── controllers/
│   ├── EmployeeController.java
│   └── ErrorsController.java
└── model/
    ├── DTO/EmployeeDTO.java
    ├── domain/
    │   ├── Employee.java
    │   └── Job.java
    ├── repositories/
    │   ├── EmployeeLoader.java
    │   └── EmployeeRepository.java
    └── services/
        ├── EmployeeService.java
        └── EmployeeServiceImpl.java
```

## How to Run

```bash
./mvnw spring-boot:run
```

Access the app at `http://localhost:8080/Empleados` and Swagger UI at `http://localhost:8080/swagger-ui.html`.

## How to Test

```bash
./mvnw test
```

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Thymeleaf](https://www.thymeleaf.org/)
- [Springfox Swagger](https://springfox.github.io/springfox/)
- [H2 Database](https://www.h2database.com/)
