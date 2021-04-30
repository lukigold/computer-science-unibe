
# Exercise 7

This week we have the final iteration of this exercise and implement the game logic.
Depending on your design, this may require you to use refactoring techniques to rearrange things and make the code more modular.

Before you start, you should finish the tasks from the previous exercise. It is also a good idea to clean up and refactor your code at this point.

# Stage 3

## Main game logic

So far, only individual parts of the game have been implemented and tested.
The final step in this exercise is to implement a main `Game` class that
provides (or delegates) the game logic. This should look similar to the `Game`
class in exercise 2 (Snakes & Ladders). 
Implement the main method (like `Game#main` in Snakes & Ladders) that, when executed, starts a new game with two players in the following way:

### Basic set up
Make sure that the game starts and sets up two players and the board depending on a valid input file (e.g., default.txt in exercise 5/games/).

### Rounds, Winner, and Players
The following logic has to be implemented to make a checkers game possible:
- Player black always starts first.
- Unlike an ordinary move, a capturing move can consist of several such hops - 
if a piece takes an opponent's piece and the new position allows it to take another piece, then it must do so straight away. 

    The move finishes only
    - if you can not capture an additional opponent's piece or
    - you have reached the far edge with an uncrowned piece. In case your piece is already crowned, then your move finished according to condition 1 (see the last point).

- Check the two winning conditions at the end of each round: 
  - Can the next player still make a **valid** move at all? If not then he loses.
      - Remember: A valid move for a normal piece is: Diagonally moving forwards or forward-jumping over an opponent piece.
      - Remember: A valid move for a king piece is: Diagonally move forwards/backward or forward-jumping/backward-jumping over an opponent piece.
  - Has the opponent player any pieces left on the board? If not then the opponent lost the game, and you won.
- The current player can only move his own pieces, not the ones from the opponent player.
- Once any of the winning conditions occurred, the game is over, then you should display both the winner and loser.

## Testing
Write tests for  **at least** the following scenarios:
- Player movement (verify that player can move pieces in the correct direction and cannot
  move onto invalid/blocked tiles)
- Player wins
- Player has only blocked pieces and loses

We encourage you to write more tests than mentioned in the above descriptions.

## Polishing

Finally, finish off your implementation by cleaning up your project and making
sure that you follow the principles taught in the course. This includes things
like
- documentation (Javadoc, inline comments where necessary),
- design by contract,
- responsibility-driven design (state responsibilities in class comments!).


## Document your designs
So far, you have designed individual parts of the game like a parser, renderer, player, etc. You have designed classes with a reason in your mind like the role of a class, how responsibilities should be distributed among classes and how they should interact with each other. It is important to look back and document your design. 

- Create a UML class diagram showing the design of your game. If classes are interacting with each other, include their relations in the diagram. Name the diagram  "ClassDiagramFinal". Take a picture of the handmade diagram or scan it and add it to your repository.

- Describe the differences between the current UML and the initial one for exercise 5. What changed? Have there been some bigger refactoring requirements? If yes, why was it necessary? Is the code more clear now?
  Write these observations into a file called `changes.md`

## Release of the game

Tag your work with `v3`

```
git tag -a v3 -m "Checkers Stage 3"
```
Do not forget to push the tag using `git push origin --tags`.

Congratulations, you have implemented your own game from scratch!


## Deadline
Submit your solution by pushing your code to your group repository until:
___Friday, 23 April, 13:00___
