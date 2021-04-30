# Exercise 2

In the second exercise, we are extending an existing project by applying 
object-oriented design principles. We learn how to reuse classes and 
interfaces and how to write proper tests for the added functionality. The 
reading materials cover some guidelines on how to write good code, comments, 
and documentation.

Due to your outstanding performance in programming, you've been hired by the 
prestigious ACME Inc, Programming division. At the moment ACME Inc. is into 
game development. Their latest game, *Snakes and Ladders*, has become a real 
success. Therefore, they are currently looking into adding new features.

In order to pass this exercise, you need to modify the current implementation 
of the Snakes and Ladders game (presented during the lecture) in the following 
ways:

1. Add the following new types of squares

 - `SkipSquare`: Skip the next player's turn.
  
 - `SwapSquare`: When landing on a swap square, the player that landed
	  on it swaps place with the player who plays next (e.g., when there
	  are 3 players and player 3 lands on a swap square, player 3 and
	  player 1 swap places).

- `WormholeEntranceSquare` and `WormholeExitSquare`: When entering a wormhole 
  (upon landing on a `WormholeEntranceSquare`), the player is transferred to a 
  random `WormholeExitSquare`.

2. Add/extend tests to the classes `SkipSquareTest`, 
   `SwapSquareTest`, and `WormholeTest`. Your tests should make use of the corresponding 
   squares. Some (partial and minimal) tests are already implemented; check 
   the corresponding classes.

3. Make sure that your implementation of the `WormholeEntranceSquare` and 
   `WormholeExitSquare` classes pass the tests in the `WormholeTest` class.

4. Include the new squares in the `Game.main()` method, such that each kind of 
   square is included at least once in the board. Note that for the wormholes, 
   you should have at least two exits.

5. Add API documentation to the `snakes.ISquare` interface according to the 
   rules provided by Oracle (see 
   [http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)).

6. Add class comments to all square classes, describing the responsibilities 
   and functionality of each class.

## Import the exercise into the IDE
- In the terminal go to the p2 folder, run the command `git pull p2-exercises master`.
- Open IntellijIDEA.
- Import the existing project.
- Once the project is imported, you can configure/verify the project setting.
- To configure/verify the project setting, click on `File` -> `Project Structure`.
    Here you can configure the project JDK, modules, project sources, and dependencies.
    - To configure Project SDK click on `Project`. Make sure you are using JDK 13 or higher and also in the `project language level`.
    - Now click on `Modules` -> under `Sources` tab, verify if the `src` folder path is marked as `Source`.
        If not, then you should select the project `src` folder and `Mark as` -> `Sources`.
        Similarly, mark the project `test` folder as `Tests`.
    -  In `Modules` sub-window, under `Dependencies` tab, configure/verify junit5 and other library dependencies.
     
## Add the Junit5 library into the project 
- Open a test file e.g., `SimpleGameTest` and Right click on `@Test' -> add Junit5 to the build path.

## Hints

You are allowed to change all the existing classes and add new ones. Before 
starting to code however, we advise you to think about which class has which 
responsibility, and which class needs to know (and be able to manipulate) 
which data.

## Mandatory Reading Material

- Sections *Format of a Doc Comment* and *Doc Comment Checking Tool* in 
  [http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)


## Optional Reading Material

- Remaining sections in 
  [http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html](http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html)


## Deadline

Submit your solutions by pushing your code to the git repository by 
___Friday, 12 March, 13:00___.
