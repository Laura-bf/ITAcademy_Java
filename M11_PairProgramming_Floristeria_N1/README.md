# M11 — Florist Shop Management (N1)

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)
![Pair Programming](https://img.shields.io/badge/Pair%20Programming-6A0DAD?logo=people&amp;logoColor=white)

## Description

A florist shop management application developed as a **pair programming** exercise with a fellow bootcamp classmate. Uses the **Command** design pattern extensively to manage products (trees, flowers, decorations) and florist stock. Follows an MVC architecture with a state-driven menu system and a Swing-based GUI.

## Technologies

- Java 11+
- Swing (`JOptionPane`)

## Key Concepts

- **Command Pattern** — `Command` class encapsulates menu actions (`CreateFloristCommand`, `ChooseFloristCommand`, `CreateTreeCommand`, `CreateFlowerCommand`, `CreateDecorationCommand`, `ShowProductsCommand`, `ExitMenuCommand`, `ExitFloristMenuCommand`).
- **State Machine** — `StateValue` enum (`MAIN_MENU`, `FLORIST_MENU`, `EXIT_MENU`) drives navigation via `Session`.
- **MVC Architecture** — `Controller` abstract base; concrete controllers per menu (e.g., `MainMenuController`, `FloristMenuController`).
- **Singleton Repository** — `FloristRepository` singleton holds all florist data in memory.
- **Inheritance** — `Product` base with `Tree`, `Flower`, `Decoration` subclasses.
- **Enum** — `Material` enum (`WOOD`, `PLASTIC`) for decoration materials.

## Project Structure

```
M11_PairProgramming_Floristeria_N1/
└── src/com/nivel1/
    ├── model/
    │   ├── domain/
    │   │   ├── Florist.java
    │   │   ├── Product.java         (abstract base)
    │   │   ├── Tree.java
    │   │   ├── Flower.java
    │   │   ├── Decoration.java
    │   │   ├── Material.java        (enum: WOOD, PLASTIC)
    │   │   └── ActiveFlorist.java   (active florist session)
    │   └── persistence/
    │       └── FloristRepository.java
    ├── controller/
    │   ├── Controller.java          (abstract)
    │   ├── mainMenu/
    │   │   ├── MainMenuController.java
    │   │   ├── CreateFloristController.java
    │   │   ├── ChooseFloristController.java
    │   │   └── ExitMenuController.java
    │   └── floristMenu/
    │       ├── FloristMenuController.java
    │       ├── CreateTreeController.java
    │       ├── CreateFlowerController.java
    │       ├── CreateDecorationController.java
    │       ├── ShowProductsController.java
    │       └── ExitFloristMenuController.java
    └── view/
        ├── FloristApp.java          (entry point)
        ├── Menu.java                (menu renderer with JOptionPane)
        ├── MessageView.java
        ├── ReadInfoWindow.java
        ├── ShowInfoWindow.java
        ├── mainMenu/
        │   ├── CreateFloristCommand.java
        │   ├── ChooseFloristCommand.java
        │   └── ExitMenuCommand.java
        ├── floristMenu/
        │   ├── CreateTreeCommand.java
        │   ├── CreateFlowerCommand.java
        │   ├── CreateDecorationCommand.java
        │   ├── ShowProductsCommand.java
        │   └── ExitFloristMenuCommand.java
        └── utils/
            ├── Command.java
            ├── Session.java
            └── StateValue.java
```

## How to Run

```bash
cd M11_PairProgramming_Floristeria_N1/src
javac com/nivel1/view/FloristApp.java
java com.nivel1.view.FloristApp
```

## Built With

- Plain Java SE (no external dependencies)
