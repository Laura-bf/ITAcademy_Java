# M8 — Video Management App (N2)

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)

## Description

An extended version of the **M8_Videos_N1** video management application. Maintains the same MVC architecture and Swing-based GUI. Builds upon N1 with refined session management and additional internal logic improvements.

## Technologies

- Java 8
- Swing (`JOptionPane`)

## Key Concepts

- **MVC Architecture** — Same layered structure as N1.
- **Singleton Pattern** — `UserWindow`, `VideoWindow`, `UserRepository`.
- **DTO Pattern** — `UserDTO` for active session user.
- **Repository Pattern** — In-memory user storage.

## Project Structure

```
M8_Videos_N2/
└── src/com/video/
    ├── model/
    │   ├── domain/
    │   │   ├── User.java
    │   │   ├── Video.java
    │   │   └── Tag.java
    │   ├── DTO/
    │   │   └── UserDTO.java
    │   └── persistance/
    │       └── UserRepository.java
    ├── controller/
    │   ├── UserController.java
    │   └── VideoController.java
    ├── com/utilities/
    │   └── DataValidation.java
    └── view/
        ├── application/
        │   └── VideosApp.java     (entry point)
        ├── UserWindow.java
        └── VideoWindow.java
```

## How to Run

```bash
cd M8_Videos_N2/src
javac com/video/view/application/VideosApp.java
java com.video.view.application.VideosApp
```

## Built With

- Plain Java SE (no external dependencies)
