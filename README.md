# Poker24 #
## Topics & Applications ##
### Stack (Data Structure) ###
- Used to check the balance of arithmatic expressions
- Used to evaluate the result of arithmatic infix expressions
### Deque (Data Structure) ###
- Used to resemble a deck of cards
- Fits this game by enabling access to both the top and bottom of the deck.
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
- Inheritance: Overriding methods that throw exceptions means the subclass version can only throw the same or fewer number of exceptions, and these exceptions should be the exact same type or relevant subclasses
