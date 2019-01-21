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

    For lines 141 - 144 in method move(), the code can be integrate into the blowBubble() method (line 48) within the same
class. And then just call the blowBubble() method.
    Lines 146 - 149 in method move() can be integrate into the turnAround() method (line87), and just call the method
turnAround() instead of the original code.
    for lines 154 - 160 in method move() can be written as a separate method to determine whether the hungry fish will
move up or down, and can just call that method in move().



### Explanation

    My solution get rid of the code smell by breaking the original move() method into several parts that can be extracted
and merge to existing methods within the class. Therefore, the method is more readable and shorter.

============================================================

## Code Smell 2: Alternative Classes with Different Interfaces

### Code Smell Category: Object-Orientation Abusers

### List of classes and line numbers involved:

* Class HungryFish
* Class Fish

### Description:

Most methods from both classes has same the same name and same functionality.

### Solution:

Implement Class HungryFish as a subclass of Class Fish, since most of the methods are the same.
For the subclass HungryFish, the class initializer should override the initializer in class Fish.


### Explanation

By implement the HungryFish class as a subclass of the Fish class, there will be much less duplicated code since most
method in HungryFish are identical to the ones in Fish class such as move(), turnAround(), drawString and etc. This
will make the resulting code less bulky and more readable.

============================================================

## Code Smell 3: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how the description of the code smell applies to this particular code.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]

### Explanation

[How does your solution get rid of the code smell? Write your explanation here.]

============================================================

## Code Smell 4: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how the description of the code smell applies to this particular code.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]

### Explanation

[How does your solution get rid of the code smell? Write your explanation here.]

============================================================

## Code Smell 5: [Write the code smell name]

### Code Smell Category: [Write the code smell category name]

### List of classes and line numbers involved:

* [Write a class and list of line numbers, one class per asterisk, that describe the smell]

### Description:

[In your own words, explain how the description of the code smell applies to this particular code.]

### Solution:

[In your own words, explain how you might solve this code smell:
how would you refactor the code?]

### Explanation

[How does your solution get rid of the code smell? Write your explanation here.]

============================================================
