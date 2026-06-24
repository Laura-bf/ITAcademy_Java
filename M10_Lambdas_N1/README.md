# M10 — Lambdas N1

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)

## Description

Introductory exercises with lambdas and the Stream API across three phases:

- **Fase 1** — Filter a list of names: those starting with a given letter and having a specific length; those containing a given letter; numbers converted to strings prefixed with `e` (even) or `o` (odd); month list iteration with lambda and method reference.
- **Fase 2** — Functional interface `IFase2` with method `getPiValue()`; instantiated via lambda returning `3.1415`.
- **Fase 3** — Functional interface `IFase3` with method `reverse(String)`; implemented via lambda for string reversal.

## Technologies

- Java 8
- Stream API
- Functional interfaces (`@FunctionalInterface`)

## Key Concepts

- Lambda expressions
- Method references (`System.out::println`)
- Stream pipeline: `filter()`, `map()`, `collect()`, `forEach()`
- `Collectors.joining()` for string aggregation
- Custom `@FunctionalInterface` definition

## Project Structure

```
src/
├── fase1/
│   └── Fase1.java       — Stream filtering exercises
├── fase2/
│   ├── IFase2.java      — Functional interface (Pi)
│   └── Fase2.java       — Lambda instantiation
└── fase3/
    ├── IFase3.java      — Functional interface (reverse)
    └── Fase3.java       — Lambda implementation
```

## How to Run

```bash
cd src
javac fase1/Fase1.java
java fase1.Fase1
```

_Repeat for `fase2.Fase2` and `fase3.Fase3`._

## Built With

- OpenJDK / Java SE 8+
