public enum CardValue {
    // card names paired with their char and int representations
    ACE('A', 1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
    EIGHT(8), NINE(9), TEN(10), JACK('J', 11), QUEEN('Q', 12), KING('K', 13);

    private char charRepresentation;
    private int numRepresentation;

    private CardValue(int numRepresentation) {
        this.numRepresentation = numRepresentation;
    }
    private CardValue(char charRepresentation, int numRepresentation) { // for Ace, Jack, Queen and King only
        this.charRepresentation = charRepresentation;
        this.numRepresentation = numRepresentation;
    }

    public char getCharRepresentation() {
        return charRepresentation;
    }
    public int getNumRepresentation() {
        return numRepresentation;
    }
}
