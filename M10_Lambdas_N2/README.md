# M10 — Lambdas N2

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)

## Description

Lambda and Stream API exercises focused on text string processing and a functional calculator.

- **TextNumbers** — Sorts a list of alphanumeric strings by length (ascending/descending), alphabetically by first character, prioritises strings starting with `e`, replaces `a` with `4`, and filters purely numeric strings. Also demonstrates a functional calculator interface with lambdas for addition, subtraction, multiplication, and division.
- **ICalculadora** — `@FunctionalInterface` declaring `operation(float x, float y)`.

## Technologies

- Java 8
- Stream API
- Functional interfaces (`@FunctionalInterface`)
- `Comparator` with lambdas

## Key Concepts

- Custom sorting with `Comparator.comparing()`
- Stream `map()` for character replacement
- Stream `filter()` with custom predicate
- Functional interface for arithmetic operations
- Lambda-based calculator implementation

## Project Structure

```
src/n2/
├── ICalculadora.java   — Functional interface (operation)
└── TextNumbers.java    — Main class with string processing
```

## How to Run

```bash
cd src
javac n2/TextNumbers.java
java n2.TextNumbers
```

## Built With

- OpenJDK / Java SE 8+
