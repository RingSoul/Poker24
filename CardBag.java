import java.util.Random;

public final class CardBag {
    private Card[] cards; // array of cards
    private int numCards = 54; // constantly updated as cards are removed from the bag into the decks
    public static final int MAX_CAPACITY = 54;
    private final Random randIndexGenerator; // will generator random index for the cards in the bag

    public CardBag() {
        cards = new Card[numCards];
        fill(cards);
        randIndexGenerator = new Random();
    }

    private void fill(Card[] cards) {
        int index = 0; // index of the array cards
        for (CardValue value : CardValue.values()) { // for all 13 values
            for (CardSuit suit : CardSuit.values()) { // for all 4 suits
                cards[index] = new Card(false, value, suit);
                index++;
            }
        } // by the end of the nested loops, indices 0 to 51 will be filled with cards
        /* last two indices will always contain joker */
        cards[numCards - 2] = new Card(true, null, null); // joker
        cards[numCards - 1] = new Card(true, null, null); // joker
    }

    // deck = the CardDeck object to place card in
    // numCards = the number of cards to be placed into the specified deck
    // will use CardDeck's addToFront method
    public void assignCardsToDeck(CardDeck deck, int numCards) {
        Deque<Card> deque = new Deque<>();
        for (int i = 0; i < numCards; i++) {
            deque.addToFront(drawRandomCard(false)); // to ensure fair game, joker is not included in the beginning
        }
        deck.setDeque(deque);
    } // by the end of the method, deck will contain <numCards> Card objects

    // private helper that uses randIndexGenerator to remove an entry from CardBag and return it
    public Card drawRandomCard(boolean shouldIncludeJokers) {
        if (numCards > 0) {
            int adjustRange = 0; // 0 if joker included, 2 otherwise
            if (!shouldIncludeJokers)
                adjustRange = 2; // possible range of indices would be reduced by 2 if joker not wanted
            Card randomCard = null;
            int index = 0;
            while (randomCard == null) {
                index = randIndexGenerator.nextInt(MAX_CAPACITY - adjustRange);
                randomCard = cards[index];
            }
            cards[index] = null;
            numCards--;
            return randomCard;
        } else { // if empty
            return null;
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public void setCards(Card[] cards) {
        this.cards = cards;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Card card : cards) {
            builder.append(card.toString()).append(" ");
        } // extra space after the last loop
        return builder.deleteCharAt(cards.length - 1).toString();
    }
}
