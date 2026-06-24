# M6 — Payroll Management System

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)

## Description

A payroll calculation system built with Java that models a hierarchy of employees using **polymorphism** and a **Factory** design pattern. The project is organised in three progressive levels (N1, N2, N3).

- **N1** — Basic payroll: creates `Employee` and `Volunteer` instances with different payment rates via `PaymentFactory`.
- **N2** — Full class hierarchy: `Boss`, `Manager`, `Senior`, `Mid`, `Junior` and `Volunteer` with salary-range validation.
- **N3** — Adds an **IRPF** tax factory for net-salary calculation and an optional bonus system.

## Technologies

- Java 8
- Console-based I/O

## Key Concepts

- **Polymorphism & Inheritance** — `AbsStaffMember` abstract base class; `Employee` hierarchy (`Boss`, `Manager`, `Senior`, `Mid`, `Junior`); `Volunteer`.
- **Interfaces** — `IPaymentRate` (gross salary calculation), `IRPF` (tax deduction, N3 only).
- **Factory Pattern** — `PaymentFactory` creates payment-rate strategies; `IRPFFactory` creates tax strategies.
- **MVC-like separation** — `JobsController` (application logic), `EmployeeRepository` (persistence), `Main` (view).

## Project Structure

```
M6_Jobs/
├── N1/
│   └── src/com/jobs/
│       ├── domain/
│       │   ├── AbsStaffMember.java      (abstract base)
│       │   ├── Employee.java            (uses IPaymentRate)
│       │   ├── Volunteer.java
│       │   └── IPaymentRate.java        (interface)
│       ├── application/
│       │   ├── JobsController.java
│       │   └── PaymentFactory.java
│       ├── persistence/
│       │   └── EmployeeRepository.java
│       └── view/
│           └── Main.java                (entry point)
├── N2/
│   └── src/com/jobs/
│       ├── domain/
│       │   ├── AbsStaffMember.java
│       │   ├── Employee.java
│       │   ├── Boss.java / Manager.java / Senior.java / Mid.java / Junior.java
│       │   ├── Volunteer.java
│       │   └── IPaymentRate.java
│       ├── application/
│       │   ├── JobsController.java
│       │   └── PaymentFactory.java
│       ├── persistence/
│       │   └── EmployeeRepository.java
│       └── view/
│           └── Main.java
└── N3/
    └── src/com/jobs/
        ├── domain/
        │   ├── AbsStaffMember.java
        │   ├── Employee.java            (bonus + IRPF support)
        │   ├── Boss.java / Manager.java / Senior.java / Mid.java / Junior.java
        │   ├── Volunteer.java
        │   ├── IPaymentRate.java
        │   └── IRPF.java                (tax interface)
        ├── application/
        │   ├── JobsController.java
        │   ├── PaymentFactory.java
        │   └── IRPFFactory.java         (tax strategies)
        ├── persistence/
        │   └── EmployeeRepository.java
        └── view/
            └── Main.java                (entry point)
```

## How to Run

```bash
cd M6_Jobs/N3/src
javac com/jobs/view/Main.java
java com.jobs.view.Main
```

Replace `N3` with `N1` or `N2` to run other levels.

## Built With

- Plain Java SE (no external dependencies)
