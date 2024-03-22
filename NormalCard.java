public class NormalCard extends Card {
    public NormalCard(CardSuit suit, CardValue value) {
        super();
        setSuit(suit);
        setValue(value);
    }

    @Override
    public void setSuit(CardSuit suit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    } // mutator disabled

    @Override
    public void setValue(CardValue value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    } // mutator disabled

    @Override
    public String toString() {
        if (!isVisible())
            return "The content of this card is not visible.  Perhaps you can experiment with some operations to induce its value?";
        if (getValue().getNumRepresentation() >= 2 && getValue().getNumRepresentation() <= 10) // if value is between 2 and 10
            return getSuit().toConsoleString() + getValue().getNumRepresentation();
        else // if value is 11 to 13 (J to K), or 1 (A)
            return getSuit().toConsoleString() + getValue().getCharRepresentation();
    }
}
