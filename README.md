# Team3-DownForAnything-projekt2-JVMexplorer

## About


## How to play


## Branch modelling
To further enhance the quality and integrity of our project, we implemented a rigorous code review process that came into play at significant milestones. After completing major sections of work or achieving substantial progress on a feature branch, the responsible developer would initiate a pull request. This process was integral to our workflow, ensuring that every significant update underwent thorough scrutiny before integration into the main branch.

The pull request process was not just a formality but a critical review stage where another team member, different from the one who developed the feature, conducted an exhaustive evaluation. This peer review served multiple purposes: it ensured code quality, maintained consistency across the project, and facilitated knowledge transfer within the team. Reviewers were tasked with examining the code for functionality, adherence to project standards, potential bugs, and overall performance. They also provided constructive feedback, suggesting improvements or optimizations where necessary.

This collaborative review process not only safeguarded our project from errors and inconsistencies but also fostered a culture of learning and mutual assistance. By having different team members review each other's work, we ensured a fresh perspective on every feature, which often led to valuable insights and enhancements. Moreover, this approach minimized the risk of introducing bugs into the main branch, thereby maintaining the project's stability and integrity.

Once a pull request was thoroughly reviewed and deemed satisfactory, it was then merged into the main branch. This methodical approach to integration helped us maintain a clean, stable, and well-documented codebase, which was crucial for the successful completion of our game project.

## Test concept
In our JUnit testing framework, we systematically employ equivalence class partitioning to validate the functionality of the classes Entity, GameController, and Renderer. We focus on the methods move, rotate, and render, as well as strategies for controlling the stick figure using arrow keys. This method groups input conditions into sets that the program treats equivalently, ensuring comprehensive test coverage with an optimized set of test cases. Additionally, mock tests are utilized to simulate interaction with dependencies, ensuring that our tests can run in isolation and behave as expected under controlled conditions. Each test case is meticulously documented to indicate the equivalence class it represents. Detailed information about these classes and their corresponding tests can be found in the accompanying table.

### Class Entity
Equivalence tests for methods move() and rotate():

### Class `Entity`
| Number | Name                     | Method parameters | Initial object state            | Expected result                                              |
|--------|--------------------------|-------------------|---------------------------------|--------------------------------------------------------------|
| 1      | testMoveValidDirection   | Valid direction   | Position before move            | "Position is updated correctly to the right"                 |
| 2      | testMoveWithNoMovement   | None              | Position unchanged              | "Stick figure's position remains unchanged with no movement" |
| 3      | testRotateValid          | Set angle         | Angle before rotation           | "Correct rotation of the stick figure by a set angle"        |
| 4      | testRotateAndMove        | Combination       | Position and angle before test  | "Correct updates in position and rotation"                   |


### Class GameController
Equivalence tests for controlling the stick figure using arrow keys:

| Number | Name            | Method parameters | Initial object state           | Expected result                                                      |
|--------|-----------------|-------------------|--------------------------------|----------------------------------------------------------------------|
| 1      | testMoveLeft    | Left arrow key    | Position and velocity before   | "The stick figure moves left, updating its position and velocity"    |
| 2      | testMoveRight   | Right arrow key   | Position and velocity before   | "The stick figure moves right, updating its position and velocity"   |
| 3      | testJump        | Up arrow key      | Position and velocity before   | "The stick figure jumps, updating its position and velocity upward"  |
| 4      | testNoMovement  | No key pressed    | Position unchanged             | "Stick figure does not move and position remains unchanged"          |

### Class Renderer
Equivalence tests for the `render()` method:

| Number | Name                 | Method parameters | Initial object state       | Expected result                                            |
|--------|----------------------|-------------------|----------------------------|------------------------------------------------------------|
| 1      | testRenderWithEntities | Entities present | Before rendering          | "Game scene renders with multiple entities correctly"      |
| 2      | testRenderNoEntities  | No entities        | Before rendering           | "Game scene renders correctly without entities, no artifacts" |
| 3      | testRenderDuringMove | Stick figure moving | Position during movement  | "Rendering during movement shows fluid animations and correct positioning" |

This test concept ensures that the game responds correctly to user inputs, the graphical display functions flawlessly, and the game remains stable under various conditions. Mock tests are particularly valuable for isolating and testing specific components without the need for actual dependencies.

## Class diagram

[Class diagram link](https://mermaid.ink/svg/pako:eNqNVttu2zgQ_RVC-6K0tuFLnDhGEaC1g-12G9RI2iywVR9oaWyzkUiDpOy4QfpD-wl965d1KOpG2fXmxaZmhjNnDmeGfPRCEYE39sKYKjVldClpEvCISQg1E5y8-RjwTEempewx4K9eAU8TkNQILi8DTshLsqFxCso_qUw_f6lpPiz8Wy0ZX9YMAv5U-P-TJjARfMGWDYmWIo5BmrCEtEm6jqiGWUx3IK_FBhLg2l_EguoTshEsshEZZ5rRmH0D_9PN-xa5ASVSGcKblEcxVJbt0lLBhPINNfit0kH2Xoj1R5YUML6TuRAxUE6Yeo2ZbKApndFUQQ5Gs_B-H6LSYu3XBZSzJCN0mlpiZ1KsQeqdoVSk8xgKQeGASu14WFGTnR8LQ3IlXiNZjt3agKtJDmZzIJmSknd31--QTw47y8dLklDG8_P9_KXOr1H8w3gktqimS3ApMBk44jLE37Cb4cGjv-Lkb0PgQJT5tQIJCRbAJJUSawDt32bpS-Xmik6ynX72W4cWYnoS97kbmJqKLfdRPsHeOCloyI9S2Bxxh11kTNWwMo5J8dAy6CAuM8OqRcsP86_YAza3Pyqu-WsmbahFzNz6MMm6BROGEJsmBL_sqLoeKy66XUuG-qLzKqXto4NFYDGUeOtIv5OsjokUmtoGNuhnQjHzdYd2QiLl9rO-QYU0hmfAUqALbz7juoVo9HHYfyVYPncMtoSZVSaLsnZxUB4DiToM058SPCkNGf9N2IXLFbDlStclWxbpleMlknQ7K92XPNqZVTTMURa-pol79jGW9rFi-O3hNbJ-LIaeJg_VMh8oNIp81_6ksb8YH1KBqyhzOGSv0rmWNNTPch4x20B7xnYEHmvDMqsiqZKFG-ARyGp6ZzVD5jS8X0qBV4L1aueJLXff_tXHBebA1ULIxFxK8LBvgsxIQHIaXRpFv3FpIhpcjnk2lK64xtxB7dW5g7lM79aUucJrprou91Tqii8ZTqO8_JagrzFdiYNLxJ8Uui6P0FwfWUSzIFzwt0DX1jgzLBnWKwk0miAYXdmvmsaIMuDV6yHweoFHXrTbl7jc2Ko0kkah1reQTudyr3_H5Od_IcbX8PNHwBtvBSfIPU74LERtTB_fsbadmsGyyz37HJPVHsViLcsKdG2du4CY6MW4ber-n4Nio5NJvuEwxc_3nSdqETrAAl6m5kSGvITJC5SVtgcqNQ-_V6gugEMbnXiqud_o9pwG3Gt5-IzDV0mEz96sGQJPr_AZGXhjXEZU3gceVi3a0RTnzI6H3ljLFFqevXvyV7IrvIoYkuaNFzRWKFxT_q8QpQ1-euNH78Eb94bnndFw2B-dnp_2h73BqN_ydt64Pej0zk67o7PRWXfUPR0MB4Onlvctc9HrdHvd7qh_fja4uLg4H1w8_QKD_A9T)

Architecture Documentation for the JVMJourney Game Class Diagram
----------------------------------------------------------------

### Architectural Choice

The architecture of the JVMJourney game is designed to support flexibility, extensibility, and maintainability. The individual classes and their relationships were crafted with a focus on modularity and loose coupling. Here are the main reasons for the chosen architecture:

1.  Modularity: By dividing functionalities into specific classes (such as `GameController`, `Player`, `Object`, `Renderer`), a clear separation of responsibilities is achieved. This facilitates maintenance and testing of individual components.

2.  Extensibility: The use of general classes like `MovingObject` and `Object`, which serve as bases for more specific entities like `Player`, allows the game to be easily expanded with new movable or interactive elements. This structure supports the creation of new classes with minimal changes to existing code.

3.  Reusability: Elements like `PositionVector` and `KeyPolling` are designed to be reused in different parts of the game, which minimizes redundancy and enhances consistency throughout the project.

4.  Performance Optimization: The classes `GameLoopTimer` and `Renderer` are crucial for controlling game performance. `GameLoopTimer` enables precise control over the game update rate, while `Renderer` handles efficient drawing of game objects and managing resources.

5.  Facilitation of Debugging: The clear structuring and naming of classes and the logical arrangement of their relationships simplify debugging. Errors can be identified and resolved faster as the cause of a problem is easier to pinpoint.

6.  Support for Diverse Inputs: The `KeyPolling` class provides a flexible solution for processing keyboard inputs, which is essential for game interactivity. The ability to query the state of a key at any time contributes to the game's responsiveness.

### Explanation of the Class Diagram Structure

The class diagram is carefully designed to clarify the relationships and dependencies between the classes:

-   GameController: Acts as the central hub that orchestrates the interaction between game objects (`Player`, `Object`), inputs (`KeyPolling`), and rendering (`Renderer`).
-   Player and MovingObject: `Player` inherits from `MovingObject`, which simplifies the implementation of movements and other player-specific actions like jumping and landing.
-   Renderer: Responsible for drawing all objects in the game, where the "composed of" relationship (`1` to `*`) with `Object` supports the dynamics and diversity of the game world.
-   StatisticsEngine and GameConfig: These classes are used for configuring and monitoring the game to facilitate adjustments and provide performance statistics.

### Architectural Conclusion

The chosen architecture for the JVMJourney game is based on the principles of object orientation and follows best practices in software development. It supports effective development methods through its clear structure and flexibility. This design allows the development team to efficiently maintain, expand, and optimize the game, while ensuring high code quality and game performance.Architecture Documentation for the JVMJourney Game Class Diagram

### Feature List

# Graphs
- **Memory Usage Over Time**
    - Eden Space
    - Old Gen
    - Survivor Space
- Thread Count
- Moving Player
- Punching
- Bouncing Watermelon
- Watermelon Squishing
- Story Windows
    - Tutorial
    - Story

# Future Feature List

- Health Bar
- Parkour Ability
- Garbage Collection Example

