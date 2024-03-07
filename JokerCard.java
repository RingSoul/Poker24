public class JokerCard extends Card {
    public JokerCard() {
        super();
    }

    @Override
    public void setVisible(boolean isVisible) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    } // mutator disabled

    @Override
    public String toString() {
        return "Joker!";
    }
}
