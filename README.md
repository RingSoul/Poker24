# Poker24 #
## Topics & Applications ##
### Stack (Data Structure) ###
- Used to check the balance of arithmatic expressions
- Used to evaluate the result of arithmatic infix expressions
### Deque (Data Structure) ###
- Used to resemble a deck of cards
- Fits this game by enabling access to both the top and bottom of the deck
###Interface###
- Java provides interfaces for many data structures
- Iterable: Enable for-each loop for a data structure
- Iterator: Usually would override next and hasNext method (so the for-each loop calls them in the background)
### Abstract keyword ###
- Abstract superclass preferred over interface when wanting to inherit non-constant fields
- Abstract methods used for forcing subclasses to override them
### Enumeration ###
- A set of constant value declarations
- Useful for non-changing attributes such as CardSuit, CardValue, and Operation (keeping relevant attributes to one place)
### Exception ###
- `throws` and `throw` used together in a method; `throws` in the method signature, `throw` in the implementation
- But `try` and `catch` blocks should be used by client methods, not by the method itself that throws the exception
- Programmer-Defined Exception by using the `extends` keyword with relevant built-in superclasses
- Inheritance: Overriding methods that throw **unchecked** exceptions means the subclass version can only throw the same or fewer number of exceptions, and these exceptions should be the exact same type or relevant subclasses (whereas checked exceptions do not need to adhere to these rules, e.g. UnsupportedOperationException in Card's subclasses)
