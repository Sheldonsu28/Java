# Markdown

Markdown is a plain-text file format. There are lots of programming tools that use Markdown, and it's useful and
easy to learn. Hash marks (the number sign) indicate headers. Asterisks indicate lists.

# List of code smells

## Code Smell 1: Long Method

### Code Smell Category: Bloaters

### List of classes and line numbers involved:

* Class HungryFish, lines 132-161.

### Description:

The contains too many lines that can be split up and integrate into other methods in the class.

### Solution:

  [  For lines 141 - 144 in method move(), the code can be integrate into the blowBubble() method (line 48) within the same
class. And then just call the blowBubble() method.
    Lines 146 - 149 in method move() can be integrate into the turnAround() method (line87), and just call the method
turnAround() instead of the original code.
    for lines 154 - 160 in method move() can be written as a separate method to determine whether the hungry fish will
move up or down, and can just call that method in move().]



### Explanation

    My solution get rid of the code smell by breaking the original move() method into several parts that can be extracted
and merge to existing methods within the class. Therefore, the method is more readable and shorter.

============================================================

## Code Smell 2: Duplicate Code

### Code Smell Category: Dispensables

### List of classes and line numbers involved:

* Class HungryFish
* Class Fish

### Description:

Most methods from both classes have same the same name and same functionality.

### Solution:

Implement Class HungryFish as a subclass of Class Fish, since most of the methods are the same.
For the subclass HungryFish, the class initializer should override the initializer in class Fish.


### Explanation

[By implement the HungryFish class as a subclass of the Fish class, there will be much less duplicated code since most
method in HungryFish are identical to the ones in Fish class such as move(), turnAround(), drawString and etc. This
will make the resulting code less bulky and more readable.]

============================================================

## Code Smell 3: Switch Statements

### Code Smell Category: Object-Orientation Abusers

### List of classes and line numbers involved:

* Class Fish, lines 63 - 80.
* Class HungryFish, lines 62 - 81.

### Description:

The turnAround() method in both of the classes have a long switch statement with multiple conditions, there are better ways
to implement the turnaround method.

### Solution:

In your own words, explain how you might solve this code smell:
how would you refactor the code?

### Explanation

Each class have a boolean variable called goingRight, we can use this variable to our advantage. We can hard code out the
string representation of the fish for each condition (going left or right). That is, if the variable goingRight is true,
we can just return the hard coded String representation of the fish going right, else return the string representation
of the fish going left.

============================================================

## Code Smell 4: Shotgun Surgery

### Code Smell Category: Change Preventers

### List of classes and line numbers involved:

* Class Fish, line line 132
* Class HungryFish, line 132
* Class Bubble, lines 82-133


### Description:

The problem is that if i want to make the coordinate of the objects into a separate class of object, i have to modify
any methods in those classes that relate to coordinate.

### Solution:

My solution is to make the coordinate in the classes as a separate class called coordinate. With in the class, i will
implement four different method called moveUp(), moveDown(), moveLeft(), moveRight() and getCoordinate() with the
functionality suggests by their name.

### Explanation

By placing the coordinate into a separate object, if we ever change the we we store coordinate, we don't need to change
all the other classes, we just need to change the coordinate class itself.

============================================================

## Code Smell 5: Inappropriate Intimacy

### Code Smell Category: Couplers

### List of classes and line numbers involved:

* Class FishFrame, lines 18-44

### Description:

This is a Inappropriate Intimacy because the method paint() access the internal parameter of class FishTank directly.
The method assumes that the variable myLittleFishies exists, which violate the principle that classes should know as
little as the other classes.

### Solution:

The solution is to move the method to the FishTank class and make the FishTank Class extend JFrame just like what class
FishFrame is doing, basically marge FishFrame into Class FishTank.

### Explanation

By moving the method to the FishTank class, the method don't have to access myLittleFishies from out side of the class,
instead, the method became part of the FishTank, hence resolve the code smell.
============================================================
