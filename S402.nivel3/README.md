# S402 Nivel 3 — Employee CRUD + File Service + Filter

![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.5.7-6DB33F?logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?logo=thymeleaf)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?logo=swagger)
![H2 Database](https://img.shields.io/badge/H2-4479A1?logo=h2)

## Description

Extended version of S402 Nivel 2 with a servlet filter that adds a custom `IT-Academy-Exercise: Simple-Service` header to every HTTP response.

## Technologies

- Java 17
- Spring Boot 2.5.7
- Maven
- Spring Web (MVC)
- Spring Data JPA
- Thymeleaf
- Swagger (Springfox 2.9.2)
- H2 Database (runtime)
- Spring DevTools

## Key Concepts

- Servlet `Filter` implementation (`ServiceFilter`)
- Custom response header injection
- Employee CRUD with Thymeleaf
- File upload/download
- Swagger API documentation

## API Endpoints

Same as Nivel 1 and 2 — all responses include the `IT-Academy-Exercise: Simple-Service` header.

## Project Structure

```
src/main/java/com/empleados/S402/nivel3/
├── Application.java
├── configuration/
│   └── SwaggerConfig.java
├── controllers/
│   ├── EmployeeController.java
│   └── ErrorsController.java
├── filters/
│   └── ServiceFilter.java
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
        ├── EmployeeServiceImpl.java
        ├── FileService.java
        └── FileServiceImpl.java
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
- [Thymeleaf](https://www.thymeleaf.org/)
- [Springfox Swagger](https://springfox.github.io/springfox/)
- [H2 Database](https://www.h2database.com/)
