# 🎹 Piano Learning Game

An interactive piano learning application developed with Java Swing. Learn to play songs by pressing the falling notes!

## 📋 Features

- **5 Different Songs** - Songs with varying difficulty levels from easy to hard
- **Free Play Mode** - Play any notes you want freely
- **Scoring System** - Earn points by pressing the correct notes
- **High Score Tracking** - Your highest score is saved for each song
- **Long Note Support** - Some notes require you to hold the key
- **MIDI Sound System** - Real-time piano sounds

## 🎵 Songs

| Song | Difficulty | Note Count | Speed |
|------|------------|------------|-------|
| Twinkle Twinkle Little Star | ⭐ Easy | 42 | Slow |
| Mary Had a Little Lamb | ⭐ Easy | 26 | Medium |
| Ode to Joy - Beethoven | ⭐⭐ Medium | 63 | Fast |
| Für Elise - Beethoven | ⭐⭐⭐ Hard | 68 | Very Fast |
| Canon in D - Pachelbel | ⭐⭐⭐ Hard | 96 | Fastest |

## 🎮 How to Play

### Keyboard Keys
**White Keys (Main Notes):**
| Key | Note |
|-----|------|
| A | Do (C) |
| S | Re (D) |
| D | Mi (E) |
| F | Fa (F) |
| J | Sol (G) |
| K | La (A) |
| L | Si (B) |

**Black Keys (Sharp Notes - In Free Play Mode):**
| Key | Note |
|-----|------|
| W | Do# (C#) |
| E | Re# (D#) |
| T | Fa# (F#) |
| Y | Sol# (G#) |
| U | La# (A#) |

### Game Rules

1. **Follow the Falling Notes** - Notes fall from the top of the screen
2. **Press the Correct Key** - Press the right key when the note reaches the golden line
3. **Long Notes** - Hold the key for orange-colored notes marked "LONG"
4. **Earn Points** - Correct note = +100 points, Long note = +200 points
5. **Avoid Mistakes** - Wrong or missed note = -50 points

### Special Keys

- **ESC** - Return to main menu

## 🛠️ Installation

### Requirements
- Java JDK 8 or higher
- NetBeans IDE (recommended) or any Java IDE

### Running

1. Open the project in your IDE
2. Run the `NoteCatcher.java` file
3. Select a song from the main menu and start playing!

### Manual Compilation

```bash
cd src
javac NoteCatcher/*.java
java NoteCatcher.NoteCatcher
```

## 📁 Project Structure

```
NoteCatcher/
├── src/
│   └── NoteCatcher/
│       ├── NoteCatcher.java    # Main application entry point
│       ├── AnaMenu.java        # Main menu screen
│       ├── OyunPaneli.java     # Game logic and rendering
│       ├── Not.java            # Note class
│       ├── SesCal.java         # MIDI sound system
│       ├── SerbestCalma.java   # Free play mode
│       ├── SarkiTwinkle.java   # Twinkle Twinkle song
│       ├── SarkiMary.java      # Mary Had a Little Lamb
│       ├── SarkiOde.java       # Ode to Joy
│       ├── SarkiFurElise.java  # Für Elise
│       └── SarkiCanon.java     # Canon in D
├── build.xml                   # Ant build file
└── README.md                   # This file
```

## 🎨 Screenshots

### Main Menu
- Song selection
- Difficulty level display
- Free play mode access

  <img width="300" height="984" alt="Ekran görüntüsü 2026-01-06 133340" src="https://github.com/user-attachments/assets/62f2e59a-6f05-4987-bebf-5a1cbc56ae92" />

### Game Screen
- 7 white piano keys
- Falling colored notes
- Score and high score display
- Golden target line

<img width="300" height="985" alt="Ekran görüntüsü 2026-01-06 134313" src="https://github.com/user-attachments/assets/c00c1940-5809-42d9-9bbb-973bc648fd5c" />
<img width="300" height="984" alt="Ekran görüntüsü 2026-01-06 134328" src="https://github.com/user-attachments/assets/73f28103-8f37-46d0-869d-773fe6cf0d27" />

## 🔧 Technical Details

- **Language:** Java
- **GUI:** Swing
- **Sound:** Java MIDI Synthesizer
- **FPS:** 50 (20ms update)
- **Minimum Resolution:** 900x800

## 👥 Developers

- Hüseyin
- Burak

## 📄 License

This project was developed for educational purposes.

---

🎹 *Have fun and good luck!*
