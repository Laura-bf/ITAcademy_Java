# M11 — Florist Shop Management (N3)

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)
![Pair Programming](https://img.shields.io/badge/Pair%20Programming-6A0DAD?logo=people&amp;logoColor=white)

## Description

Pair-programming extension of **M11_PairProgramming_Floristeria_N2** that adds a **purchase and ticketing system**. Users can create purchase tickets, cancel or pay for purchases, and view historical tickets with total gains. Introduces `SHOP_MENU` and `PURCHASE_MENU` states to the Command-driven interface.

## Technologies

- Java 11+
- Swing (`JOptionPane`)

## Key Concepts

- **Command & State Machine** — Same architecture as N1/N2, extended with purchase-related states.
- **Ticketing System** — `Ticket` class (id, date, product list, total amount) and `ActiveTicket` singleton for managing the current in-progress purchase.
- **Gains Calculation** — Total revenue computed from completed tickets.
- **Stock Cloning** — When a purchase starts, stock is cloned to track available inventory during the transaction.

## New / Changed Files (vs N2)

| Area | Key Additions |
|------|-------------|
| `model/domain/` | `Ticket.java`, `ActiveTicket.java` |
| `controller/shopMenu/` | `ShopMenuController`, `PurchaseController`, `ShowTicketsController`, `ShowGainsController`, `ExitShopController` |
| `controller/purchaseMenu/` | `PurchaseMenuController`, `AddProductController`, `PayPurchaseController`, `CancelPurchaseController`, `ExitPurchaseController` |
| `controller/mainMenu/` | `ShopController` (new main-menu option) |
| `view/shopMenu/` | `PurchaseCommand`, `ShowTicketsCommand`, `ShowGainsCommand`, `ExitShopCommand` |
| `view/purchaseMenu/` | `AddProductCommand`, `PayPurchaseCommand`, `CancelPurchaseCommand`, `ExitPurchaseCommand` |
| `view/mainMenu/` | `ShopCommand` |
| `StateValue.java` | Added `SHOP_MENU`, `PURCHASE_MENU` |

## Project Structure

```
M11_PairProgramming_Floristeria_N3/
└── src/com/nivel3/
    ├── model/
    │   ├── domain/
    │   │   ├── Florist.java / Product.java / Tree.java
    │   │   ├── Flower.java / Decoration.java / Material.java
    │   │   ├── ActiveFlorist.java
    │   │   ├── Ticket.java            (purchase ticket)
    │   │   └── ActiveTicket.java      (current purchase session)
    │   └── persistence/
    │       └── FloristRepository.java
    ├── controller/
    │   ├── Controller.java
    │   ├── mainMenu/        (MainMenuController, ShopController, CreateFloristController, etc.)
    │   ├── floristMenu/     (FloristMenuController, AddProductController, etc.)
    │   ├── addProdMenu/     (AddMenuController, CreateTreeController, etc.)
    │   ├── removeProdMenu/  (RemoveMenuController, RemoveTreeController, etc.)
    │   ├── stockMenu/       (StockMenuController, AllProductsController, etc.)
    │   ├── shopMenu/        (ShopMenuController, PurchaseController, ShowTicketsController, ShowGainsController)
    │   └── purchaseMenu/    (PurchaseMenuController, AddProductController, PayPurchaseController, CancelPurchaseController)
    └── view/
        ├── FloristApp.java            (entry point)
        ├── Menu.java / MessageView.java / ReadInfoWindow.java / ShowInfoWindow.java
        ├── mainMenu/  floristMenu/  addProdMenu/  removeProdMenu/  stockMenu/
        ├── shopMenu/  purchaseMenu/
        └── utils/     (Command.java, Session.java, StateValue.java, ExitToFloristMenuCommand.java)
```

## How to Run

```bash
cd M11_PairProgramming_Floristeria_N3/src
javac com/nivel3/view/FloristApp.java
java com.nivel3.view.FloristApp
```

## Built With

- Plain Java SE (no external dependencies)
