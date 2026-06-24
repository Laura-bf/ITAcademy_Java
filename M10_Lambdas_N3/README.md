# M10 — Lambdas N3

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)

## Description

Student data processing using the Stream API. A list of `Student` objects (name, age, course, grade) is queried with five stream operations:

1. Print name and age of every student.
2. Filter students whose name starts with `A`.
3. Filter students with a grade ≥ 5 (passing).
4. Filter passing students who do **not** study PHP.
5. Filter Java students who are of legal age (≥ 18).

## Technologies

- Java 11+
- Stream API
- Lambda expressions

## Key Concepts

- Stream filtering with `filter()` and chained predicates
- `Collectors.toList()` for result collection
- Method references and lambda expressions
- POJO model class (`Student`)

## Project Structure

```
src/n3/
├── Student.java   — Student POJO (name, age, course, grade)
└── Main.java      — Entry point with stream queries
```

## How to Run

```bash
cd src
javac n3/Main.java
java n3.Main
```

## Built With

- OpenJDK / Java SE 8+
