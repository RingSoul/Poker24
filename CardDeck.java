import java.util.Iterator;

public class CardDeck {
    private Deque<Card> deque;

    public CardDeck() {
        // empty constructor
    }

    public Deque<Card> getDeque() {
        return deque;
    }

    public void setDeque(Deque<Card> deque) {
        this.deque = deque;
    }

    public void addToTop(Card card) { // top == front
        deque.addToFront(card);
    }

    public void addToBottom(Card card) { // bottom == back
        deque.addToBack(card);
    }

    public boolean hasNoCard() {
        return deque.isEmpty();
    }

    public Card showTop() {
        return deque.peekFront();
    }

    public String toString() { // return a string containing the CardDeck's cards from top to bottom
        StringBuilder builder = new StringBuilder();
        for (Card card : deque) {
            builder.append(card.toString()).append(" ");
        } // extra space in the end after the last loop
        return builder.deleteCharAt(deque.getNumEntries() - 1).toString();
    }
}
