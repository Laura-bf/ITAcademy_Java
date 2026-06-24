# M7 — Vehicle Workshop Registration App

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)

## Description

A vehicle workshop registration application that manages cars, bikes, trucks, wheels, owners, drivers and driving licenses. Three progressive levels build on the same domain model:

- **N1** — Basic Swing GUI (`JOptionPane`) to register cars and bikes with their wheels.
- **N2** — Console-based input. Adds owners (with insurance/garage), drivers, and license validation per vehicle type.
- **N3** — Full-featured console app with reusable menus for creating vehicles, owners, and drivers, plus license-type matching.

## Technologies

- Java 8
- Swing (`JOptionPane` dialogs in N1)
- Console-based I/O (N2, N3)

## Key Concepts

- **Inheritance** — `Vehicle` abstract base; `Car`, `Bike`, `Truck` subclasses.
- **Composition** — Vehicles contain `Wheel` collections and reference `Owner`/`Driver`.
- **Interfaces** — `DrivingAble` for license-checking logic.
- **License System** — `License` class with type validation (A=Moto, B=Car, C=Truck), ID format checking.

## Project Structure

```
M7_Vehicles/
└── src/
    ├── N1/
    │   ├── Vehicle.java         (abstract)
    │   ├── Car.java
    │   ├── Bike.java
    │   ├── Wheel.java
    │   └── WorkshopApp.java     (entry point, Swing GUI)
    ├── N2/
    │   ├── Vehicle.java / Car.java / Bike.java / Truck.java
    │   ├── Wheel.java
    │   ├── Person.java / Owner.java / Driver.java
    │   ├── License.java
    │   ├── DrivingAble.java     (interface)
    │   └── Nivel2_WhorkshopApp.java  (entry point, console)
    └── N3/
        ├── Vehicle.java / Car.java / Bike.java / Truck.java
        ├── Wheel.java
        ├── Person.java / Owner.java / Driver.java
        ├── License.java
        ├── DrivingAble.java     (interface)
        └── N3_App.java          (entry point, console with menus)
```

## How to Run

```bash
cd M7_Vehicles/src
javac N1/WorkshopApp.java
java N1.WorkshopApp
```

For N2 or N3, replace the class/package names accordingly:

```bash
javac N2/Nivel2_WhorkshopApp.java
java N2.Nivel2_WhorkshopApp
```

## Built With

- Plain Java SE (no external dependencies)
