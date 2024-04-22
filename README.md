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

| Equivalence Class | Test Case                  | Description                                                                                       |
|-------------------|----------------------------|---------------------------------------------------------------------------------------------------|
| move              | testMoveValidDirection     | Tests moving the stick figure to the right, ensuring that the position is updated correctly.      |
| move              | testMoveWithNoMovement     | Verifies that the stick figure's position remains unchanged when no movement is commanded.        |
| rotate            | testRotateValid            | Tests correct rotation of the stick figure by a set angle.                                        |
| rotate            | testRotateWithZero         | Verifies that the stick figure's rotation remains unchanged when the rotation change is zero.     |
| rotate            | testRotateAndMove          | Tests the combination of moving and rotating to check for correct updates in position and rotation.|


### Class GameController
Equivalence tests for controlling the stick figure using arrow keys:

| Equivalence Class | Test Case         | Description                                                                                         |
|-------------------|-------------------|-----------------------------------------------------------------------------------------------------|
| key control       | testMoveLeft      | The stick figure should move left, updating its position and velocity accordingly.                  |
| key control       | testMoveRight     | The stick figure should move right, updating its position and velocity accordingly.                 |
| key control       | testJump          | The stick figure should jump (move upward), updating its position and velocity accordingly.         |
| key control       | testCrouch        | The stick figure should crouch (move downward), updating its position accordingly.                  |
| key control       | testNoMovement    | If no arrow key action is taken, the stick figure should not move, and its position should remain unchanged.|

### Class Renderer
Equivalence tests for the `render()` method:

| Equivalence Class | Test Case            | Description                                                                                         |
|-------------------|----------------------|-----------------------------------------------------------------------------------------------------|
| render            | testRenderWithEntities | Tests rendering the game scene with multiple entities to ensure correct display and positioning.   |
| render            | testRenderNoEntities | Verifies that the game scene is rendered correctly without entities and no visual artifacts occur. |
| render            | testRenderDuringMove | Tests rendering while the stick figure is moving to confirm fluid animations and correct position updates.|

This test concept ensures that the game responds correctly to user inputs, the graphical display functions flawlessly, and the game remains stable under various conditions. Mock tests are particularly valuable for isolating and testing specific components without the need for actual dependencies.

## Class diagram

