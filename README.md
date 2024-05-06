# Team3-DownForAnything-projekt2-JVMexplorer

## About


## How to Play "JVM Journey"

Welcome to *JVM Journey*, an exciting game that challenges you to navigate through levels, avoid obstacles, and interact with objects to understand system behaviors. Below is a detailed guide on how to get started, play the game, and utilize its features.

### Cloning and Starting the Game

1. **Clone the Game from GitHub**
   - Navigate to the GitHub repository.
   - Clone the repository to your local machine using `git clone [https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer.git]`.
   - Ensure you have Java installed on your system to run the game.

2. **Launch the Game**
   - Navigate to the cloned repository directory.
   - Run the game using `java -jar JVMJourney.java`.

### Tutorial and Controls

- **Tutorial Windows**: When you first start the game, tutorial windows will guide you through the gameplay mechanics. These windows explain key elements like:
  - **Watermelons**: Represent threads in the game. Each watermelon you encounter has a thread associated with it.
  - **Player Controls**: Use the arrow keys to move the player around the game environment.
  - **Action Control**: Press the spacebar to 'strike' at objects like watermelons.

- **Completing the Tutorial**: You must successfully perform all actions explained in the tutorial before proceeding. Once completed, Level 1 will begin.

### Gameplay

- **Level 1**:
  - Ten watermelons are generated, each linked to a separate thread.
  - Striking a watermelon with the spacebar will close its associated thread.
  - The effect of this can be monitored in the statistics view, which displays changes in memory usage and thread count.
  - Watermelons continuously spawn to allow for ongoing observation of memory and thread metrics in the game's line charts.

### Exiting the Game

- To exit the game, navigate to the navbar, select "Game", and then choose "Quit".

### Tips

- Keep an eye on your statistics view to understand how your actions affect system resources.
- Experiment with different strategies to manage the spawning watermelons and associated threads.

Thank you for playing *JVM Journey*. Enjoy navigating the levels and mastering the system mechanics!


## Branch modelling
To further enhance the quality and integrity of our project, we implemented a rigorous code review process that came into play at significant milestones. After completing major sections of work or achieving substantial progress on a feature branch, the responsible developer would initiate a pull request. This process was integral to our workflow, ensuring that every significant update underwent thorough scrutiny before integration into the main branch.

The pull request process was not just a formality but a critical review stage where another team member, different from the one who developed the feature, conducted an exhaustive evaluation. This peer review served multiple purposes: it ensured code quality, maintained consistency across the project, and facilitated knowledge transfer within the team. Reviewers were tasked with examining the code for functionality, adherence to project standards, potential bugs, and overall performance. They also provided constructive feedback, suggesting improvements or optimizations where necessary.

This collaborative review process not only safeguarded our project from errors and inconsistencies but also fostered a culture of learning and mutual assistance. By having different team members review each other's work, we ensured a fresh perspective on every feature, which often led to valuable insights and enhancements. Moreover, this approach minimized the risk of introducing bugs into the main branch, thereby maintaining the project's stability and integrity.

Once a pull request was thoroughly reviewed and deemed satisfactory, it was then merged into the main branch. This methodical approach to integration helped us maintain a clean, stable, and well-documented codebase, which was crucial for the successful completion of our game project.


## Example Pull Requests for Reviews and Merging

Below are examples of pull requests where reviews were discussed and feedback was incorporated, demonstrating our team's use of GitHub's collaborative features:

### [Game Engine Menubar Enhancement #63](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/pull/63)

#### Description
In this pull request, multiple menu items were removed from the game engine's menubar, specifically mainMenuItem, resetGameItem, goBackItem, and goNextItem. These changes aimed to declutter the user interface, assuming these options were either infrequently used or accessible through other means. Additionally, the pull request included an update to the alert dialog information to better inform users about future features, enhancing user communication.

#### Discussion and Feedback
- **Öztürk Umut (oeztuumu)** initiated the pull request with commits to remove unused menu options, explaining the potential benefits for UI decluttering.
- **Durresi2**, another team member, reviewed the changes and recommended including details about testing to verify that the removal of these menu items does not affect other functionalities. They also suggested updating user documentation to reflect these changes, and posed questions about the rationale behind the specific items removed.
- The author responded to the review by adding testing details and further clarifying the rationale behind the removal decisions, emphasizing that the removed items were either infrequently used or accessible through alternative means.

#### Outcomes
The pull request was merged, contributing to better code maintenance and readability, and the branch `gameEngine_menubar_enhancement` was subsequently deleted. This pull request was also added to the Milestone 3: Documentation, Testing, and Bug Fixes.

This example showcases effective use of GitHub's review features, ensuring that changes are well-documented and justified before integration into the main project.


### [Game Engine Enhancements #64](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/pull/64)

#### Description
Significant updates were made to the GameController class to handle game mechanics and tutorial progression more effectively, along with UI enhancements and testing improvements.

#### Discussion and Feedback
- **Öztürk Umut (oeztuumu)** detailed the enhancements in game mechanics and UI, addressing the expansion of the GameController class and updates to FXML files.
- **durresi2** queried about the integration of game mechanics with UI interactions and the challenges faced during implementation.
- The dialogue covered the advantages of refactoring, implementation challenges, and the impact on the game's architecture. The discussion emphasized on maintaining a cohesive flow between game logic and UI updates, which led to a more manageable system.

#### Outcomes
The pull request successfully merged 17 commits from the branch `gameEngine` into `dev`, showcasing an excellent example of collaborative problem-solving and code integration.


## Test concept
In our JUnit testing framework, we systematically employ equivalence class partitioning to validate the functionality of the classes Player, Watermelon, GameController and Renderer. We focus on the methods move, rotate, and render, as well as strategies for controlling the stick figure using arrow keys. This method groups input conditions into sets that the program treats equivalently, ensuring comprehensive test coverage with an optimized set of test cases. Additionally, mock tests are utilized to simulate interaction with dependencies, ensuring that our tests can run in isolation and behave as expected under controlled conditions. Each test case is meticulously documented to indicate the equivalence class it represents. Detailed information about these classes and their corresponding tests can be found in the accompanying table.

### Defined equivalence classes

| Class              | Equivalence Class                  | Inputs                                                           | Expected Result                                                                                        |
|--------------------|------------------------------------|------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------|
| **Player**         | Movement in Air                    | `inAir = true`, varying `currentVelocity`                        | Position and velocity are updated according to gravity and existing velocity.                          |
|                    | Movement on Ground                 | `inAir = false`, varying `currentVelocity`                       | Position updates based on velocity with friction applied, velocity reduces unless at friction limit.    |
|                    | Jumping                            | `inAir = false`, command to jump                                 | `inAir` becomes true, vertical velocity reflects jump strength, simulating an upward movement.          |
|                    | Landing                            | `inAir = true`, reaching ground level                            | `inAir` becomes false, position set to ground level, vertical velocity resets.                         |
|                    | Punching and Cooldown              | Various states of `punchCooldown`                                | When cooldown is zero, player can punch. Cooldown resets and counts down until punching stops.         |
| **WaterMelon**     | Bouncing Off Ground                | `currentVelocity` having a downward component                    | Upon collision with ground, y component of velocity becomes positive (bounces up).                     |
|                    | Bouncing Off Wall                  | `currentVelocity` directed towards a wall                        | Upon collision, x component of velocity reverses, simulating a bounce off the wall.                    |
|                    | Free Movement                      | No obstacles, various `currentVelocity`                          | Position updates smoothly in trajectory without bouncing.                                              |
| **GameController** | Arrow Key Movement                 | Key inputs for left, right, and jump                             | Player's position and velocity update based on the direction indicated by the key press.               |
|                    | No Key Pressed                     | No movement keys pressed                                         | Player remains stationary, with no changes in position or velocity.                                    |
|                    | Simultaneous Movement and Jump     | Right or left key along with jump key pressed                    | Player moves horizontally while also initiating a jump.                                                |
|                    | Invalid Key Press                  | Non-directional keys pressed                                     | No movement or action taken, demonstrating that only specific keys influence gameplay.                 |
|                    | Movement beyond Minimum Boundary   | Player attempts to move left past the minimum X boundary         | Player's position does not decrease past the minimum X boundary, movement is constrained within limits.|
|                    | Movement beyond Maximum Boundary   | Player attempts to move right past the maximum width boundary    | Player's position does not exceed the maximum width boundary, ensuring movement is contained.          |
| **RenderTest**     | Render Object                      | `GraphicsContext`, `MovingObject` with image and size            | Context draws the image at specified position and size.                                                |
|                    | Render Nothing                     | `GraphicsContext`, `MovingObject` without objects                | No image is drawn despite rendering attempt, verifying absence of object rendering.                    |
|                    | Render Movement                    | `GraphicsContext`, `MovingObject` with updated position          | Context redraws the image at new position, verifying the object's movement rendering.                  |


This test concept ensures that the game responds correctly to user inputs, the graphical display functions flawlessly, and the game remains stable under various conditions. Mock tests are particularly valuable for isolating and testing specific components without the need for actual dependencies.

## Class diagram
![Class Diagramm with MVC Pattern](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/blob/6a36362f1c075354680667ecf1ae36e07e5ba6d1/JVMexplorerMVC.png)


Architecture Documentation for the JVMJourney Game Class Diagram
----------------------------------------------------------------

### Architectural Choice

The architecture of the JVMJourney game is designed to support flexibility, extensibility, and maintainability. The individual classes and their relationships were crafted with a focus on modularity and loose coupling. Here are the main reasons for the chosen architecture:

1.  Modularity: By dividing functionalities into specific classes (such as `ch.zhaw.it.pm2.jvmjourney.controllers.gameController.GameController`, `Player`, `Object`, `Renderer`), a clear separation of responsibilities is achieved. This facilitates maintenance and testing of individual components.

2.  Extensibility: The use of general classes like `MovingObject` and `Object`, which serve as bases for more specific entities like `Player`, allows the game to be easily expanded with new movable or interactive elements. This structure supports the creation of new classes with minimal changes to existing code.

3.  Reusability: Elements like `PositionVector` and `KeyPolling` are designed to be reused in different parts of the game, which minimizes redundancy and enhances consistency throughout the project.

4.  Performance Optimization: The classes `GameLoopTimer` and `Renderer` are crucial for controlling game performance. `GameLoopTimer` enables precise control over the game update rate, while `Renderer` handles efficient drawing of game objects and managing resources.

5.  Facilitation of Debugging: The clear structuring and naming of classes and the logical arrangement of their relationships simplify debugging. Errors can be identified and resolved faster as the cause of a problem is easier to pinpoint.

6.  Support for Diverse Inputs: The `KeyPolling` class provides a flexible solution for processing keyboard inputs, which is essential for game interactivity. The ability to query the state of a key at any time contributes to the game's responsiveness.

### Explanation of the Class Diagram Structure

The class diagram is carefully designed to clarify the relationships and dependencies between the classes:

-   ch.zhaw.it.pm2.jvmjourney.controllers.gameController.GameController: Acts as the central hub that orchestrates the interaction between game objects (`Player`, `Object`), inputs (`KeyPolling`), and rendering (`Renderer`).
-   Player and MovingObject: `Player` inherits from `MovingObject`, which simplifies the implementation of movements and other player-specific actions like jumping and landing.
-   Renderer: Responsible for drawing all objects in the game, where the "composed of" relationship (`1` to `*`) with `Object` supports the dynamics and diversity of the game world.
-   StatisticsEngine and GameConfig: These classes are used for configuring and monitoring the game to facilitate adjustments and provide performance statistics.

### Architectural Conclusion

The chosen architecture for the JVMJourney game is based on the principles of object orientation and follows best practices in software development. It supports effective development methods through its clear structure and flexibility. This design allows the development team to efficiently maintain, expand, and optimize the game, while ensuring high code quality and game performance.Architecture Documentation for the JVMJourney Game Class Diagram

### Feature List

**Graphs**
- Memory Usage Over Time
    - Eden Space
    - Old Gen
    - Survivor Space
- Thread Count

**Game**
- Moving Player
- Punching
- Bouncing Watermelon
- Watermelon Squishing
- Story Windows
    - Tutorial
    - Story

# Future Feature List

|     ID    |     Feature Name             |     Description                                |     Issue                               |
|-----------|------------------------------|------------------------------------------------|------------------------------------------|
| 1         | Level 2 (Garbage Collector)  | Introduce a level which explains jvm garbage collector   | [Future Feature: Create Level 2 to Explain JVM Garbage Collector](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/issues/56)     |
| 2         | Level 3 (Thread Management)  | Level with advanced Thread Management explanation            | [Future Feature: Create Level 3 for Advanced Thread Management](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/issues/57)    |
| 3         | CPU Implementation           | Show how JVM Dynamics cause CPU reactions      | [Future feature: Implement CPU Usage Graph with Thread Functions](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/issues/58)   |
| 4         | Points-System for the Player | Implement a scoring system for players        | [Future feature: Implement Scoring System for Players Across All Levels](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/issues/59)       |
| 5         | Health Bar for the Player    | Visual representation of player's health for advanced levels     | [Future feature: Display Health Bar for Player](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/issues/60)                |
| 6         | Parkour Ability              | Player ability to perform parkour maneuvers   | [Future feature: Parkour Ability](https://github.zhaw.ch/PM2-IT23tbZH-ruiz-urak/Team3-DownForAnything-projekt2-JVMexplorer/issues/61)           |




