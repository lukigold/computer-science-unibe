# Exercise 6
In the last exercise, we started with an implementation of the *Checkers* game. We focused
on parsing, rendering and movement of pieces, but did not implement any game logic yet.  
This week's goal is straightforward: Get a working game!


## Git Tags
In order to mark milestones, we start using **git's tag features**. Tags are used to mark
important commits with a name, e.g. a version number.
This feature is documented on [git-scm.com](https://git-scm.com/book/en/v2/Git-Basics-Tagging).
Your first task is to read this documentation and learn about git tags.


## Finish Stage 1
Before you start with stage 2 of the game, you need to make sure that you meet all the
requirements for exercise 5. If you missed some things, you should fix them and make sure
everything works as intended. Once you have done that, use git to create an annotated tag
that marks exercise 5 as the finished stage 1 by typing the following into your terminal:
```
git tag -a v1 -m "Checkers Stage 1"
```
You can then push the tags with `git push origin --tags` to our server.


## Stage 2
The main part of this exercise is implementing stage 2 of the *Checkers* game. Your focus
should be to implement the required features, but also go back over your existing code to
improve it where necessary, with regards to documentation, testing, design by contract
and so on. In order to pass this exercise, you must implement the following features:

### Crowning a Piece
According to the *Checkers* rules, whenever one of your pieces reaches the opponent's edge,
it changes into a *king*. Implement this logic within your game so that **pieces get turned into kings**. 
Write **tests** that cover your solution to make sure that it works as intended.

### Jumping
Allow pieces and kings of a player to **diagonally jump** over the ones from the opponent and
subsequently remove them from the board. To implement it, you can extend the movement in a 
way so that when a player moves diagonally to an adjacent square that is occupied by the opponent
and the square behind it is empty, the piece/king moves to that empty square and removes the
one which was jumped over. 
No further logic needs to be implemented for this part. Write **tests** which show that when a
player diagonally jumps over an opponent that said piece or king gets removed from the board.

### Movement Validity
In this task you have to implement the **movement logic** according to the rules of *Checkers*.
This includes the following features:
- No piece or king can move off the board
- Pieces and kings can only move on dark squares (diagonally)
- Only one piece or king can occupy a square
- Pieces can only move forward
- Pieces can jump over multiple opponents but only when moving forward
- Kings can jump forward and backward
- Kings can jump over multiple opponents both moving forward and backward

If an invalid move is detected, it gets rejected.
Write **tests** to cover as many scenarios as possible to check your solution.
Make sure to search for edge cases and name your tests accordingly, so we know what they try to cover.

Once you have completed all tasks, tag your work as usual:
```
git tag -a v2 -m "Checkers Stage 2"
```


## Deadline
Submit your solution by pushing your code to your group repository until:
___Friday, 16 April, 13:00___
