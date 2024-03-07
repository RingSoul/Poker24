public class Card implements Cloneable {
    // instance variables
    private CardValue value;
    private CardSuit suit;
    private boolean isVisible; // a card given to another player will become invisible, so the other player must guess what value it is

    // constructor
    public Card() {
        value = null;
        suit = null;
        isVisible = true;
    }

    // accessors and mutators
    public CardValue getValue() {
        return value;
    }
    public CardSuit getSuit() {
        return suit;
    }
    public boolean isVisible() {
        return isVisible;
    }
    public void setValue(CardValue value) {
        this.value = value;
    }
    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
