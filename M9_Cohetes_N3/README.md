# M9 — Cohetes N3 (Swing GUI + Gear Selector)

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk) ![Swing](https://img.shields.io/badge/UI-Swing-007396?logo=java)

## Description

Enhanced rocket simulation with a Swing graphical interface. Builds on N2 by adding a gear selector (`JList`) that lets the user choose acceleration/deceleration steps (x1–x5). Each rocket's dashboard displays per-propeller information with colour feedback (red for target reached or paused). Propeller threads update the UI in real time.

## Technologies

- Java 11+
- Swing (`JFrame`, `JPanel`, `JButton`, `JLabel`, `JList`)
- Multithreading (`Runnable`, `Thread`)
- Event listeners (`ActionListener`, `ListSelectionListener`)

## Key Concepts

- Multi-gear acceleration (x1–x5 multiplier)
- Colour-coded UI status feedback
- Thread-safe UI updates
- Event-driven control with gear selection

## Project Structure

```
src/rocketsN3/
├── domain/
│   ├── Propeller.java    — Propeller model (Runnable, gear-aware)
│   └── Rocket.java       — Rocket model (code, speed, propellers)
└── view/
    ├── Window.java       — Main JFrame
    ├── RocketPanel.java  — Rocket dashboard panel
    ├── ControlPanel.java — Controls + gear selector
    └── Rockets_APP.java  — Entry point (main)
```

## How to Run

```bash
cd src
javac rocketsN3/view/Rockets_APP.java
java rocketsN3.view.Rockets_APP
```

## Built With

- OpenJDK / Java SE 8+
