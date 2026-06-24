# M9 — Cohetes N2 (Swing GUI)

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk) ![Swing](https://img.shields.io/badge/UI-Swing-007396?logo=java)

## Description

Rocket simulation with a full graphical interface built with Swing. After setting target speeds through dialogs, a `JFrame` window displays two rocket dashboards side by side. Each dashboard shows propeller information (ID, max power, current power, target power) and real-time speed with a background thread. Buttons allow starting/pausing the rocket and accelerating or braking.

## Technologies

- Java 8
- Swing (`JFrame`, `JPanel`, `JButton`, `JLabel`)
- Multithreading (`Runnable`, `Thread`)

## Key Concepts

- GUI with `JFrame` and custom panels
- Real-time UI updates via background threads
- Rocket speed visualization
- Event-driven control (Start / Accelerate / Brake)

## Project Structure

```
src/rockets/
├── domain/
│   ├── Propeller.java    — Propeller model (Runnable, JLabel binding)
│   └── Rocket.java       — Rocket model (code, speed, propellers)
└── view/
    ├── Window.java       — Main JFrame
    ├── RocketPanel.java  — Rocket dashboard panel
    ├── ControlPanel.java — Start / Accelerate / Brake buttons
    └── Rockets_APP.java  — Entry point (main)
```

## How to Run

```bash
cd src
javac rockets/view/Rockets_APP.java
java rockets.view.Rockets_APP
```

## Built With

- OpenJDK / Java SE 8+
