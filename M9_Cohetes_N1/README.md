# M9 — Cohetes N1 (Console)

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)

## Description

Console-based rocket simulation (Level 1). Creates two `Rocket` objects identified by an 8-character alphanumeric code and stores the number of propellers each one carries. Outputs the rocket descriptions via `toString()`.

## Technologies

- Java 11+

## Key Concepts

- Object-oriented programming
- Custom exceptions (code length / propeller validation)
- `toString()` override

## Project Structure

```
src/nivel1/Fase1/
├── Rocket.java         — Rocket model (code, numProps)
└── Rockets_APP.java    — Entry point (main)
```

## How to Run

```bash
cd src
javac nivel1/Fase1/Rockets_APP.java
java nivel1.Fase1.Rockets_APP
```

## Built With

- OpenJDK / Java SE 8+
