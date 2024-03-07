import java.util.Random;

public final class CardBag {
    private Card[] cards; // array of cards
    private int numCards = 54; // constantly updated as cards are removed from the bag into the decks
    public static final int MAX_CAPACITY = 54;
    private final Random randIndexGenerator; // will generator random index for the cards in the bag

    public CardBag() {
        cards = new Card[numCards];
        randIndexGenerator = new Random();
        fillAndShuffle();
    }

    private void fillAndShuffle() {
        fill();
        shuffle();
    }

    private void fill() {
        int index = 0; // index of the array cards
        for (CardValue value : CardValue.values()) { // for all 13 values
            for (CardSuit suit : CardSuit.values()) { // for all 4 suits
                cards[index] = new NormalCard(suit, value);
                index++;
            }
        } // by the end of the nested loops, indices 0 to 51 will be filled with cards
        /* last two indices of a newly-filled bag will always contain joker */
        cards[numCards - 2] = new JokerCard(); // joker
        cards[numCards - 1] = new JokerCard(); // joker
    }

    private void shuffle() {
        Card[] newlyArrangedCards = new Card[MAX_CAPACITY];
        for (int i = 0; i < MAX_CAPACITY; i++)
            newlyArrangedCards[i] = drawRandomCard(true);
        cards = newlyArrangedCards;
    }

    // deck = the CardDeck object to place card in
    // numCards = the number of cards to be placed into the specified deck
    // will use CardDeck's addToFront method
    public void assignCardsToDeck(CardDeck deck, int numCards) {
        Deque<Card> deque = new Deque<>();
        for (int i = 0; i < numCards; i++) {
            deque.addToFront(drawRandomCard(false)); // to ensure fair game, joker is not included in anyone's deck for the start of the game
        }
        deck.setDeque(deque);
    } // by the end of the method, deck will contain <numCards> Card objects

    // uses randIndexGenerator to remove an entry from CardBag and return it
    // parameter used to determine if a JokerCard object can be returned
    public Card drawRandomCard(boolean shouldIncludeJokers) {
        Card randomCard = null;
        randomCard = cards[randIndexGenerator.nextInt(MAX_CAPACITY)];
        if (!shouldIncludeJokers) {
            boolean isJoker = (randomCard instanceof JokerCard);
            while (isJoker) {
                randomCard = cards[randIndexGenerator.nextInt(MAX_CAPACITY)];
                isJoker = (randomCard instanceof JokerCard);
            }
        }
        return randomCard;
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
