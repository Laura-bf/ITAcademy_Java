# M8 — Video Management App (N3)

![Java](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk)
![Swing](https://img.shields.io/badge/Swing-GUI-008080?logo=java)

## Description

An extended version of **M8_Videos_N2** that adds a **video player with a timer** to the video management application. Users can now play, pause, continue, and stop videos with an on-screen timer display. Built on the same MVC architecture as the previous levels.

## Technologies

- Java 11+
- Swing (`JOptionPane`, `JFrame`, `JButton`, `JLabel`)

## Key Concepts

- **MVC Architecture** — Same model/controller/view structure from N1/N2.
- **Multithreading** — `PlayVideoTimer` implements `Runnable` to manage video playback timing on a separate thread.
- **Swing GUI Components** — Full `JFrame`-based `VideoPlayerWindow` with Play/Pause/Continue/Stop buttons and a timer display.
- **Singleton Pattern** — `UserWindow`, `VideoWindow`, `UserRepository`.

## New / Changed Files (vs N2)

| File | Description |
|------|-------------|
| `PlayVideoTimer.java` | `Runnable` timer that counts seconds and updates the display. Supports pause/continue via `wait()`/`notify()`. |
| `VideoPlayerWindow.java` | `JFrame`-based video player UI with play/pause/continue/stop controls. |
| `VideosApp.java` | Updated entry point — adds a "Play Video" option that opens the player window. |

## Project Structure

```
M8_Videos_N3/
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
        │   └── VideosApp.java        (entry point)
        ├── UserWindow.java
        ├── VideoWindow.java
        ├── VideoPlayerWindow.java    (player GUI)
        └── PlayVideoTimer.java       (timer thread)
```

## How to Run

```bash
cd M8_Videos_N3/src
javac com/video/view/application/VideosApp.java
java com.video.view.application.VideosApp
```

## Built With

- Plain Java SE (no external dependencies)
