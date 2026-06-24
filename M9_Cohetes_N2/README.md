# M9 — Cohetes N2 (Console + Threads)

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)

## Description

Rocket simulation with a more detailed domain model. Each rocket has a code, a current speed, a target speed, and a list of propellers. Propellers run as independent threads (`Runnable`) and adjust power incrementally toward a target. The main application uses `JOptionPane` dialogs to set target speeds, distributes power across propellers, and provides an interactive control panel to accelerate or decelerate rockets.

## Technologies

- Java 8
- `javax.swing.JOptionPane` (lightweight GUI dialogs)
- Multithreading (`Runnable`, `Thread`)

## Key Concepts

- Multi-threaded propeller simulation
- Power distribution algorithm
- Speed calculation: `speed + 100 * √(totalCurrentPower)`
- Thread lifecycle management (start / stop)

## Project Structure

```
src/nivel2/
├── Propeller.java     — Propeller model (Runnable)
├── Rocket.java        — Rocket model (code, speed, propellers)
└── Rockets_APP.java   — Entry point (main)
```

## How to Run

```bash
cd src
javac nivel2/Rockets_APP.java
java nivel2.Rockets_APP
```

## Built With

- OpenJDK / Java SE 8+
