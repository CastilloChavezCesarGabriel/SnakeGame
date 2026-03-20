# Snake Game

This game lets users control a snake that moves across a dark-themed grid, eating food to grow longer while avoiding collisions with its own body and the boundary walls. The project follows a strict MVC architecture with a complete decoupling between the UI framework and the application logic through an adapter layer, ensuring that the Model, View contracts and Controller contain zero Swing or AWT imports.

## MVC Structure

- **Model**: Manages the snake movement, food spawning, collision detection, score tracking and boundary validation, and notifies the Controller of game state changes through the Observer pattern.
- **View**: Defines pure framework-agnostic contracts for rendering, user input events and game state transitions, and communicates to the Controller exclusively through listener interfaces.
- **Controller**: Processes user input events, updates the Model on each game tick, coordinates the rendering pipeline through the Visitor pattern and manages the game lifecycle (start, restart, game over).

## Java Libraries Used

- **java.util.LinkedList**: Stores the snake body segments as an ordered collection for head insertion and tail removal.
- **java.util.ArrayList**: Stores observer and listener lists in notifiers and the view adapter.
- **java.util.List**: Defines list contracts for observer and listener collections.
- **java.util.Random**: Generates random grid positions for food spawning.
- **javax.swing.Timer**: Drives the game loop at a fixed tick interval through the `SwingTimer` adapter.
- **javax.swing.JPanel**: Provides the rendering surface for the game in the Swing adapter.
- **javax.swing.JFrame**: Creates and configures the application window in the Swing adapter.
- **javax.swing.JOptionPane**: Displays the game over confirmation dialog in the Swing adapter.
- **java.awt.Graphics**: Base graphics context passed by Swing during paint callbacks.
- **java.awt.Graphics2D**: Enhanced graphics context with antialiasing and round rectangle support.
- **java.awt.RenderingHints**: Configures antialiasing for smooth rendering in the Swing adapter.
- **java.awt.Color**: Defines fill and stroke colors for snake segments, food, board and UI elements.
- **java.awt.Font**: Sets font family, weight and size for score display and start screen text.
- **java.awt.FontMetrics**: Measures text width and height for centered string rendering.
- **java.awt.Point**: Represents button position coordinates in the start screen.
- **java.awt.Dimension**: Sets the preferred panel size for the game window.
- **java.awt.event.KeyAdapter**: Provides a base class for the keyboard input handler in the Swing adapter.
- **java.awt.event.KeyEvent**: Identifies arrow key codes for direction changes.
- **java.awt.event.MouseAdapter**: Provides a base class for the mouse input handler in the Swing adapter.
- **java.awt.event.MouseEvent**: Captures click coordinates for start button detection.

## Dependencies

- Java 21+
- No external dependencies (pure Java and Swing)

## Features

- Real-time snake movement on a dark-themed checkerboard grid.
- Arrow key controls for direction changes with opposite-direction prevention.
- Food spawning at random grid positions with glowing highlight effect.
- Snake growth on food consumption with score tracking.
- Self-collision and boundary collision detection.
- Game over dialog with restart or exit options.
- Start the screen with a centered title, instruction text and styled start button.
- Antialiased rendering with rounded snake segments and food glow effects.
- Score display rendered through the Visitor pattern pipeline.
- Complete framework decoupling through an adapter layer (Swing isolated from MVC).

## Architecture

```
SnakeGame/
├── src/
│   ├── App.java                                       # Entry point that wires the MVC architecture pattern
│   ├── model/
│   │   ├── Model.java                                 # Game coordinator with observer notifications
│   │   ├── Snake.java                                 # Snake entity with head, body, movement and collision
│   │   ├── Food.java                                  # Food entity with random spawning and position matching
│   │   ├── Bound.java                                 # Boundary validator and random position generator
│   │   ├── Direction.java                             # Movement enum with translation and opposite detection
│   │   ├── IRenderable.java                           # Shared interface for position iteration
│   │   └── visitor/
│   │       └── IGameVisitor.java                      # Visitor interface with visit, spotlight and tally
│   ├── view/
│   │   ├── IViewImplementation.java                   # Main view contract for Controller communication
│   │   ├── IGameView.java                             # Callback interface for user input events
│   │   ├── IGameCanvas.java                           # Canvas drawing contract (render, highlight, inscribe, prepare)
│   │   ├── IRenderCallback.java                       # Delegate callback contract for rendering
│   │   ├── IGameTimer.java                            # Framework-agnostic timer contract (start, stop)
│   │   └── ITimerCallback.java                        # Timer tick callback interface
│   ├── adapters/swing/
│   │   ├── SwingView.java                             # Swing implementation of IViewImplementation
│   │   ├── SwingGameCanvas.java                       # Swing implementation of IGameCanvas that delegates to painters
│   │   ├── Painter.java                               # Abstract base with shared Graphics2D and cellSize fields
│   │   ├── SnakePainter.java                          # Rounded snake segment rendering with edge and body colors
│   │   ├── FoodPainter.java                           # Food rendering with glow effect and shine accent
│   │   ├── BoardPainter.java                          # Background fill, checkerboard tiling, border outline and score tally
│   │   ├── StartScreen.java                           # Title, instruction, button shaping and labeling
│   │   ├── GameScreen.java                            # Creates SwingGameCanvas and delegates to render callbacks
│   │   ├── GameOverDialog.java                        # Game over confirmation dialog
│   │   ├── SwingTimer.java                            # Swing implementation of IGameTimer using javax.swing.Timer
│   │   ├── SwingKeyListener.java                      # Keyboard input handler that translates key events to IGameView calls
│   │   └── SwingMouseListener.java                    # Mouse input handler that detects start button clicks via StartScreen
│   ├── controller/
│   │   ├── Controller.java                            # MVC orchestrator that implements IGameView, IGameListener, IRenderCallback and ITimerCallback
│   │   └── GameRenderer.java                          # Game visitor that renders entities through IGameCanvas
│   ├── observer/
│   │   ├── IGameListener.java                         # Observer interface (onStateChanged, onGameOver)
│   │   ├── IGameNotifier.java                         # Notifier contract (add, tick, end)
│   │   └── GameStateNotifier.java                     # Concrete notifier implementation
│   └── utilities/
│       ├── Position.java                              # Immutable 2D position with visitor access and translation
│       └── IPositionVisitor.java                      # Visitor interface for Position coordinates
```

## Design Patterns

| Pattern            | Usage                                                                                                                                                             |
|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Observer**       | Model notifies Controller of game state changes (state changed, game over) through `IGameListener` and `IGameNotifier`.                                           |
| **Visitor**        | Snake, Food and Position expose data through visitor interfaces without exposing state directly. `GameRenderer` visits entities through `IGameVisitor`.           |
| **Abstract Class** | `Painter` provides shared `Graphics2D` and `cellSize` fields to `SnakePainter`, `FoodPainter` and `BoardPainter`.                                                 |
| **Strategy**       | `IRenderable` allows entities to choose their visual mode (`visit` or `spotlight`) independently, and new entities can be added without modifying `IGameVisitor`. |
| **Facade**         | `Model` simplifies access to `Snake`, `Food`, `Bound` and `IGameNotifier`.                                                                                        |
| **Adapter**        | `SwingView`, `SwingGameCanvas` and all Swing adapter classes implement framework-agnostic view contracts using Swing components.                                  |

## Program Flow

1. When the application launches, `App` assembles the Model with a `Bound`, cell size and a `GameStateNotifier`, creates a `SwingView` adapter and wires the `Controller` which registers itself as an observer of the Model and as a listener of the View.

2. A dark-themed start screen appears with the game title, an instruction label and a styled start button. When the user clicks the button, `SwingMouseListener` checks `StartScreen.isClicked()` and translates the mouse event into an `IGameView.onStartRequested()` call. The Controller requests a `IGameTimer` from the View adapter and starts the game loop.

3. On each timer tick, the `SwingTimer` adapter calls `ITimerCallback.onTick()` on the Controller, which calls `Model.update()`. The Model moves the snake, checks for food collision and evaluates game over conditions. If the snake eats food, the food relocates to a random grid position and the score increments internally.

4. After each update, the Model notifies the Controller through `IGameListener.onStateChanged()`. The Controller triggers a repaint, and the Swing adapter creates a `SwingGameCanvas` which delegates rendering to `SnakePainter`, `FoodPainter` and `BoardPainter`. The `GameRenderer` visitor walks the Model entities and passes their positions to the canvas through `IPositionVisitor`.

5. Arrow key presses are captured by `SwingKeyListener` and translated into `IGameView` direction calls. The Controller forwards each direction change to the Model which validates and applies it to the Snake.

6. When the snake collides with itself or exits the boundary, the Model notifies the Controller through `IGameListener.onGameOver()`. The Controller stops the timer and the `GameOverDialog` presents a restart or exit choice, translating the user response into `IGameView.onRestartRequested()` or `IGameView.onExitRequested()`.

## Setting up on Mac

### Prerequisites

1. **Java 21+**:
   - Download the DMG installer from [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
   - Choose the **macOS** tab and select the `.dmg` file matching your chip (Apple Silicon or Intel x64).
   - Double-click the downloaded `.dmg`, then double-click the `.pkg` installer and follow the prompts.
   - Once installed, verify with:
   ```bash
   java --version
   ```
   If you need to set `JAVA_HOME` explicitly, add this to your `~/.zshrc`:
   ```bash
   export JAVA_HOME=$(/usr/libexec/java_home)
   ```

### Steps

1. Clone the repository and create a new working branch (since the **main** branch is reserved for production code only):
   ```bash
   git clone <repository-url>
   cd SnakeGame
   git checkout -b <branch-name>
   ```
2. Compile the project:
   ```bash
   mkdir -p out
   javac -d out src/utilities/*.java src/observer/*.java src/model/visitor/*.java src/model/*.java src/view/*.java src/adapters/swing/*.java src/controller/*.java src/App.java
   ```
3. Run the application:
   ```bash
   java -cp out App
   ```

## Setting up on Windows

### Prerequisites

1. **Java 21+**:
   - Download the MSI or EXE installer from [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/).
   - Choose the **Windows** tab and select the `.msi` or `.exe` file.
   - Run the installer and follow the prompts. The installer automatically adds Java to your system PATH.
   - Once installed, open Command Prompt or PowerShell and verify with:
   ```cmd
   java --version
   ```
   If other tools require `JAVA_HOME`, set it manually:
   - Open **Settings > System > About > Advanced system settings > Environment Variables**
   - Under System Variables, click **New**
   - Variable name: `JAVA_HOME`
   - Variable value: `C:\Program Files\Java\jdk-21` (adjust to your installed version)

### Steps

1. Clone the repository and create a new working branch (since the **main** branch is reserved for production code only):
   ```cmd
   git clone <repository-url>
   cd SnakeGame
   git checkout -b <branch-name>
   ```
2. Compile the project:
   ```cmd
   mkdir out
   javac -d out src\utilities\*.java src\observer\*.java src\model\visitor\*.java src\model\*.java src\view\*.java src\adapters\swing\*.java src\controller\*.java src\App.java
   ```
3. Run the application:
   ```cmd
   java -cp out App
   ```

## License

Creative Commons Attribution 4.0 International License (CC BY 4.0).

## Acknowledgements

This project demonstrates a clean code approach to game development, such as it decouples the MVC architecture pattern from the UI framework through an adapter layer, and it ensures that adding new game entities or switching to a different rendering framework requires no changes to the business core.
