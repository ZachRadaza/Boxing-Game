# Boxing Game (Java 2D)

A 2D boxing game built completely from scratch in Java, featuring a custom game engine, original art, and mechanics inspired by a mix of **Mortal Kombat** and **For Honor**, but adapted to the pace and rules of boxing.

## Project Highlights
- Developed a **custom 2D game engine** (no external frameworks), handling:
  - Game loop & frame timing
  - Input handling (keyboard/controller)
  - Collision detection & hitboxes
  - Rendering pipeline for sprites and animations
- Designed and drew **all art assets** (fighters, UI, arenas) with the heml of Machine Learning websites.
- Implemented core fighting game mechanics:
  - Stamina/guard system
  - Movement, dodging, and positioning
  - Match rounds and victory conditions

## What I Learned
- **Game engine fundamentals**: updating/rendering loops, time-step control, and performance optimization in 2D environments.
- **Collision systems**: building hitboxes/hurtboxes for precise combat interaction.
- **Input design**: responsive controls to player actions.
- **Game design**: translating boxing mechanics into engaging fighting-game-style gameplay.
- **Art pipeline**: creating and integrating original art assets into a real-time engine.

## Why this project matters
This project challenged me to build everything from the ground up—engine, graphics, logic, and assets—giving me hands-on experience in both **low-level Java game programming** and **game design principles**. It deepened my understanding of how fighting games are structured under the hood, and how to balance technical execution with creative design.

# Quickstart

### Prerequisites
- [Java JDK 8+](https://www.oracle.com/java/technologies/javase-downloads.html) installed  
- (Optional) [Eclipse IDE](https://www.eclipse.org/downloads/) if you want to open and edit the project  

---

## Running the Project

### Option 1: Run in Eclipse (for developers)
1. Open Eclipse  
2. Go to: `File` → `Import...` → `Existing Projects into Workspace`  
3. Select the project folder and click **Finish**  
4. Right-click `Main.java` (inside `src/main/`) → `Run As` → `Java Application`

---

### Option 2: Run without Eclipse (for users)
1. Open a terminal in the project directory  
2. Compile all source files into the `bin/` folder:
   ```bash
   javac -d bin src/*/*.java src/*/*/*.java
3. Run the main class:
   ```bash
   java -cp bin main.Main

##Project Structure
```plaintext
Boxing Game/
 ├── .settings/        # Eclipse settings
 ├── Art/              # Original sprites & icons
 │   ├── FrameSprites/
 │   ├── PlayerSpriteLeft/
 │   ├── PlayerSpriteRight/
 │   ├── ResourceSprites/
 │   └── StanceIcon/
 ├── bin/              # Compiled classes
 │   ├── collision/
 │   ├── graphics/
 │   ├── input/
 │   ├── main/         # Contains Main.class (entry point)
 │   ├── resources/
 │   └── sprites/
 └── src/              # Java source code
     ├── collision/
     ├── graphics/
     ├── input/
     ├── main/         # Contains Main.java (entry point)
     ├── resources/
     └── sprites/

```
