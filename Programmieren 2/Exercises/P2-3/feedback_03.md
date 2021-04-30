# Exercise 3 - Feedback

**1. Implement the four given hooman commands**  
Your solution correctly executes the hooman commands `right n`, `left n`, `down n` and `up n`. Well done!

**2. Split parsing and execution into separate classes**  
You have split the different responsibilities of the program into separate classes/objects, good.  

**3. State pre- and post-conditions in API documentation**  
You have mentioned some pre- and post-conditions in JavaDoc but also missed a few like in the `Hooman` class.  

**4. Check pre- and post-conditions using the 'assert' keyword**  
You use the `assert` keyword or throw appropriate exceptions to check your pre- and post-conditions. Good.

**5. Check class invariants**  
The `Hooman` class has a suitable invariant but the `Environment` class which has quite a few responsibilities and
variables is missing a class invariant to check its state. Try to cover all classes in the future (same for pre- and
post-conditions and documentation).

**6. Deal with all input programs**  
You handle all input programs very well, good job!
Empty lines, invalid input and commands that would otherwise cause out of bounds exceptions do not break your program.
  
**7. Write class comments incl. responsibilities and invariants**  
Looks good.

**8. UML diagrams**  
- *Class Diagram*  
  Your UML class diagram looks good. Since the purpose of a class diagram can be to show the program to someone who hasn't
  seen it before and give him an idea how it is built up, less can sometimes be more, meaning that a good level of
  abstraction that leaves out unimportant details can improve the diagram to understand it more easily without unnecessary
  details. In your example you could for instance leave out "unnecessary" helper methods like the `createTextArea()` etc. 
  methods as they aren't that important to the general understanding of the project.
  
- *Sequence Diagram*  
  Your UML sequence diagram correctly represents the process of calling the method `Environment.createFrom(...)`. 

**9. General Notes**  
For a better OOP approach consider the following: Create an interface `Command` and/or parent class `Command` similar to
the one you already have. Additionally create a `CommandUp`, `CommandDown` etc. class for each direction that uses the
interface and/or inherits from the parent. In each individual command class, you could have an `execute()` method that
contains the implementation from the different switch-cases in your `Environment` class. Currently the `createFrom()`
method is pretty long and clustered. With the above suggested approach, you could remove the switch-statement and
simply have a for-loop that iterates over all commands and calls `execute()` for every single one of them which then
runs the corresponding code. This makes a cleaner solution and has a better OOP approach.



## Status: Accepted (ok)
You passed exercise 3, good.
