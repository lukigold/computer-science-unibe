# Exercise 1 - Feedback


#### Custom Algorithm
You have implemented a solution using a custom algorithm. You passed all the tests that we provided,
and also 18/23 extra test cases designed for the special characters of the regex method. Good work!
Check the new class `FilePatternTestExtended` to see those additional test cases.


#### Encapsulation
You set the class variables `private`, well done.  
Note: If you want to allow inheritance for variables and methods use `protected`.


#### Naming
The naming convention is good.


#### Documentation
You added one-line comments explaining the logic behind your algorithm a bit which is good.  
The JavaDoc comment on line 18 to 24 in `FilePattern.java` belongs to the `FilePattern` constructor on line 26. You
should therefore not put anything else like the `pattern` variable in between, otherwise it can not match both together
(JavaDoc and related code). Check the second exercise hour for more details. Your IDE usually warns you about this too.


#### Commit Messages
Your commit messages could me more descriptive on what you changed (e.g. which new features you added). 
You can also directly refer to parts of the code like `filename.method()` to accurately describe your changes.
(Check reading material of exercise 1)


#### General Notes
- If you are using an IDE like IntelliJ IDEA, don't just look for errors but also warnings that are pointed out to you.
  They usually help you to improve your overall code quality. For example:
  Your if-statement on line 69 could be simplified from
  ```
  if(j < filename.length()) //this means one element of filename was not reached => cannot match
  			return false;
  		return true;
  ```
  ... to just ...
  ````
  return j >= filename.length();
  ````
  Since the if statement already returns a boolean on which you base your return statement, you can simply return that
  instead of writing additional return true/false statements.





## Status: Accepted (ok)
You have passed exercise 1, good job.
