# M11 — Florist Shop Management (N2)

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)
![Pair Programming](https://img.shields.io/badge/Pair%20Programming-6A0DAD?logo=people&amp;logoColor=white)

## Description

Pair-programming extension of **M11_PairProgramming_Floristeria_N1** that adds **add/remove stock** functionality. Builds on the Command pattern and state-machine architecture with dedicated menus (`ADD_MENU`, `REMOVE_MENU`, `STOCK_MENU`) for managing product inventory.

## Technologies

- Java 11+
- Swing (`JOptionPane`)

## Key Concepts

- **Command Pattern** — Same structure as N1 with additional commands for stock management.
- **State Machine** — `StateValue` extended with `ADD_MENU`, `REMOVE_MENU`, `STOCK_MENU`, `EXIT_APP`.
- **Extended Controllers** — New `AddProductController`, `RemoveProductController`, `ChooseStockController` and their sub-controllers for each product type.
- **Stock Views** — Dedicated views for showing all products, trees, flowers, or decorations stock.

## New / Changed Files (vs N1)

| Area | Key Additions |
|------|-------------|
| `controller/addProdMenu/` | `AddMenuController`, `CreateTreeController`, `CreateFlowerController`, `CreateDecorationController` |
| `controller/removeProdMenu/` | `RemoveMenuController`, `RemoveTreeController`, `RemoveFlowerController`, `RemoveDecorationController` |
| `controller/stockMenu/` | `StockMenuController`, `AllProductsController`, `TreeStockController`, `FlowerStockController`, `DecorationStockController` |
| `controller/floristMenu/` | `AddProductController`, `RemoveProductController`, `ChooseStockController` |
| `view/addProdMenu/` | `CreateTreeCommand`, `CreateFlowerCommand`, `CreateDecorationCommand` |
| `view/removeProdMenu/` | `RemoveTreeCommand`, `RemoveFlowerCommand`, `RemoveDecorationCommand` |
| `view/stockMenu/` | `AllProductsCommand`, `TreeStockCommand`, `FlowerStockCommand`, `DecorationStockCommand` |
| `view/floristMenu/` | `AddProductCommand`, `RemoveProductCommand`, `ChooseStockCommand` |
| `view/utils/` | `ExitToFloristMenuCommand` |
| `StateValue.java` | Added `ADD_MENU`, `REMOVE_MENU`, `STOCK_MENU`, `EXIT_APP` |

## Project Structure

```
M11_PairProgramming_Floristeria_N2/
└── src/com/nivel2/
    ├── model/
    │   ├── domain/
    │   │   ├── Florist.java / Product.java / Tree.java
    │   │   ├── Flower.java / Decoration.java / Material.java
    │   │   └── ActiveFlorist.java
    │   └── persistence/
    │       └── FloristRepository.java
    ├── controller/
    │   ├── Controller.java
    │   ├── mainMenu/        (MainMenuController, CreateFloristController, etc.)
    │   ├── floristMenu/     (FloristMenuController, AddProductController, etc.)
    │   ├── addProdMenu/     (AddMenuController, CreateTreeController, etc.)
    │   ├── removeProdMenu/  (RemoveMenuController, RemoveTreeController, etc.)
    │   └── stockMenu/       (StockMenuController, AllProductsController, etc.)
    └── view/
        ├── FloristApp.java          (entry point)
        ├── Menu.java / MessageView.java / ReadInfoWindow.java / ShowInfoWindow.java
        ├── mainMenu/  floristMenu/  addProdMenu/  removeProdMenu/  stockMenu/
        └── utils/     (Command.java, Session.java, StateValue.java, ExitToFloristMenuCommand.java)
```

## How to Run

```bash
cd M11_PairProgramming_Floristeria_N2/src
javac com/nivel2/view/FloristApp.java
java com.nivel2.view.FloristApp
```

## Built With

- Plain Java SE (no external dependencies)
