public class Player {
    private String name;
    private int score;
    private CardDeck[] cardDecks;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        cardDecks = null;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScoreBy(int increase) {
        score += increase;
    }

    public CardDeck[] getCardDecks() {
        return cardDecks;
    }

    public void setCardDecks(CardDeck[] cardDecks) {
        this.cardDecks = cardDecks;
    }

    public boolean hasNoCard() {
        for (CardDeck deck : cardDecks)
            if (!deck.hasNoCard())
                return false;
        return true;
    }

    // giveTheTopCardFromDeckTo(Player player, CardDeck cardDeck)
    // addCardFromBagToDeck(CardDeck cardDeck)

    public String decksToString() {
        StringBuilder builder = new StringBuilder();
        for (CardDeck deck : cardDecks) {
            builder.append(deck.toString()).append("  ");
        }
        return builder.toString();
    }
}
