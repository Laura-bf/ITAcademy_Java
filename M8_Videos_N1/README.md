# M8 — Video Management App (N1)

![Java](https://img.shields.io/badge/Java-8-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)

## Description

A video management application with a full **MVC architecture** and Swing-based GUI (`JOptionPane` dialogs). Users can register, log in, create videos with tags, and view their posted videos.

## Technologies

- Java 8
- Swing (`JOptionPane`)

## Key Concepts

- **MVC Architecture** — Model (`domain`), View (`view`), Controller (`controller`).
- **Singleton Pattern** — `UserWindow`, `VideoWindow`, `UserRepository`.
- **DTO Pattern** — `UserDTO` extends `User` to represent the active session user.
- **Data Validation** — `DataValidation` utility class for input checks.
- **Repository Pattern** — `UserRepository` holds all users in memory.

## Project Structure

```
M8_Videos_N1/
└── src/com/video/
    ├── model/
    │   ├── domain/
    │   │   ├── User.java          (userId, name, surname, password, regDate, postedVideos)
    │   │   ├── Video.java         (videoId, url, title, tags)
    │   │   └── Tag.java           (tag string)
    │   ├── DTO/
    │   │   └── UserDTO.java       (active user singleton)
    │   └── persistance/
    │       └── UserRepository.java (in-memory storage)
    ├── controller/
    │   ├── UserController.java
    │   └── VideoController.java
    ├── com/utilities/
    │   └── DataValidation.java
    └── view/
        ├── application/
        │   └── VideosApp.java     (entry point)
        ├── UserWindow.java        (user registration/login dialogs)
        └── VideoWindow.java       (video creation/tagging dialogs)
```

## How to Run

```bash
cd M8_Videos_N1/src
javac com/video/view/application/VideosApp.java
java com.video.view.application.VideosApp
```

## Built With

- Plain Java SE (no external dependencies)
