public class Player {
    private String name;
    private int score;
    private CardDeck[] cardDecks;
    private int calcAttemptPerTurn;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        cardDecks = null;
        calcAttemptPerTurn = 3;
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

    public int getCalcAttemptPerTurn() {
        return calcAttemptPerTurn;
    }

    public void setCalcAttemptPerTurn(int calcAttemptPerTurn) {
        this.calcAttemptPerTurn = calcAttemptPerTurn;
    }

    public boolean hasNoCard() {
        for (CardDeck deck : cardDecks)
            if (!deck.hasNoCard())
                return false;
        return true;
    }

    // remove a card from the top of the selected deck,
    // and hide it under a random deck of the designated recipient
    public void giveCardToPlayer(Player recipient, CardDeck from) {
        CardDeck recipientRandomDeck = recipient.getCardDecks()[(int) (Math.random() * cardDecks.length)];
        from.removeFromTopAndHideUnderAnotherDeck(recipientRandomDeck);
    }
    // drawCardFromBagToDeck(CardDeck cardDeck)

    public String decksToString() {
        StringBuilder builder = new StringBuilder();
        for (CardDeck deck : cardDecks) {
            builder.append(deck.toString()).append("  ");
        }
        return builder.toString();
    }
}
