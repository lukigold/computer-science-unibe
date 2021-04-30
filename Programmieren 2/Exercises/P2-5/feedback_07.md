# Exercise 7 - Feedback

#### 1. Implementation of Main Game Logic
Your implementation of the main game logic has to fulfill the following requirements according to our specifications:  
- [X] `Game` class provides or delegates the game logic.  
- [X] Game starts with basic setup: 2 players and board is based on valid input file  
- [X] Player black starts first  
- [X] A player can only move his own pieces  
- [X] Once winning conditions are met, the game should display its winner and loser 

*During capturing move you must capture all pieces until...* 
- [X] no more piece can be captured  
- [X] piece reaches opponent's side and gets crowned  

*Check winning conditions after each round*  
- [X] If no valid move can be made, the player loses  
- [X] If all pieces have been lost to the opponent, the player loses  
 
Looking at your solution, you have fulfilled all of our specifications, good job.


#### 2. Testing
In the exercise description we asked you to test at least the following three scenarios:  
- [X] Movement of pieces (verify that pieces move in correct direction and cannot move onto invalid/blocked tiles)  
- [X] Player wins the game  
- [_] Player has only blocked pieces and loses  

Currently there doesn't seem to be a test that covers the case where a player loses the game because none of his pieces
can be moved. Please add one during revision.


#### 3. Polishing
With all features implemented and tested it was time for you to polish your implementation which includes applying the
concepts that you have learned so far including:

*Documentation (JavaDoc and inline comments where necessary)*  
Overall a bit inconsistent. Some classes are documented very well while others have methods without any JavaDoc at all.

*Design by Contract*  
Looks good. Some conditions were not described in JavaDoc accordingly.

*Responsibility Driven Design*  
Pretty solid. You have added a class comment for all your classes which is good.


#### 4. UML Class Diagram
Overall your class diagram looks good but the `Field` class is missing relations to the remaining classes. Other
relations are also just linked with a line without any additional details such as aggregation or composition.
Please add the links for the `Field` class and be more specific for the remaining relations that are just simple lines.

It also has a good level of abstraction where all the necessary details are present to understand how your application
works, and it leaves out unimportant aspects (e.g. private helper methods) that do not contribute to the overall picture.  
Your described changes sound reasonable and are documented well and are also reflected in the final diagram.


## Status: Revise
Please fix the following points during revision until **Friday, April 30th**:
- Add a test that covers the scenario where a player loses because all of his pieces are blocked.
- Fix small issues with UML class diagram
