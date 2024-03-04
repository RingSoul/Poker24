public class Card {
    // instance variables
    private CardValue value;
    private CardSuit suit;
    private boolean isJoker;
    private boolean isVisible;

    // constructor
    public Card(boolean isJoker, CardValue value, CardSuit suit) {
        this.isJoker = isJoker;
        if (!isJoker) {
            this.value = value;
            this.suit = suit;
        }
        this.isVisible = true; // always true when a card is initially created
    }

    // normal accessors
    public CardValue getValue() {
        return value;
    }
    public CardSuit getSuit() {
        return suit;
    }
    public boolean isJoker() {
        return isJoker;
    }
    public boolean isVisible() {
        return isVisible;
    }

    // mutator methods ONLY for the 2 joker cards (Rule: joker cards are wildcard and can use any number+suit)
    public void setJokerCardValue(CardValue value) {
        this.value = value;
    }
    public void setJokerCardSuit(CardSuit suit) {
        this.suit = suit;
    }

    // mutator method for visibility (Rule: A player can give cards to another player; it would be added to the bottom of the CardDeck object with visibility as false)
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String toString() {
        if (isJoker)
            return "Joker!";
        if (value.getNumRepresentation() >= 2 && value.getNumRepresentation() <= 10)
            return suit.toConsoleString() + value.getNumRepresentation();
        else
            return suit.toConsoleString() + value.getCharRepresentation();
    }
}
