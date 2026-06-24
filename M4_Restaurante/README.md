# M4 Restaurante

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)

## Description

A restaurant menu simulation with a graphical interface (JOptionPane) for browsing pizza dishes and prices, building an order, verifying dish availability, calculating the total bill, computing the coin and bill breakdown for cash payment, and handling errors with custom exceptions.

## Technologies

- Java 11+
- Swing (JOptionPane)

## Key Concepts

- HashMap for menu (dish-to-price mapping)
- JOptionPane dialogs for user interaction
- ArrayList for order management
- Dish existence validation
- Coin and bill breakdown algorithm
- Custom exception classes (EmptyOrderException, InvalidPaymentException, NotExistingDishException)
- Exception handling with try-catch

## Project Structure

```
M4_Restaurante/
  src/
    N1/
      Fase1.java
      Fase2.java
      Fase3.java
    N2/
      Excepciones_Restaurante.java
    N3/
      EmptyOrderException.java
      InvalidPaymentException.java
      NotExistingDishException.java
      ExcepPersonal_Restaurante.java
```

## How to Run

Each file contains its own `main` method and can be run independently.

```bash
cd src
javac N1/Fase3.java
java N1.Fase3
```

The most complete versions are `N2.Excepciones_Restaurante` (generic exceptions) and `N3.ExcepPersonal_Restaurante` (custom exceptions).

## Built With

- Java 11+ — Eclipse IDE
