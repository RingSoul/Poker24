public class PokerGame {

    private CardBag cardBag;
    private int numDecks; // equivalent to:
    // 1) number of cards available for calculation manipulation
    // 2) number of CardDeck stacks
    private static final int DEFAULT_NUM_DECKS = 4; // standard game with 4 decks
    private int numPlayers;
    private Player[] players;
    private int turnMonitor; // represents the index in the array players, the player whose turn is up

    public PokerGame(Player[] players) { // "default" constructor
        this(players, DEFAULT_NUM_DECKS);
    } // standard game with 4 decks

    public PokerGame(Player[] players, int numDecks) { // custom game
        cardBag = new CardBag();
        this.numDecks = numDecks;
        numPlayers = players.length;
        this.players = players;
        createCardDecksForPlayers();
        turnMonitor = (int) (Math.random() * numPlayers); // randomized starting player
    }

    /* Pre-game, private helper methods */

    private void createPlayers(String[] names) { // Question: where to get the names? --> from front end
        players = new Player[numPlayers];
        for (int p = 0; p < players.length; p++) {
            players[p] = new Player(names[p]);
        }
    }

    private void createCardDecksForPlayers() {
        // make sure that the cards are distributed evenly from the bag to decks (some will be leftover)
        int cardsPerDeck = CardBag.MAX_CAPACITY / players.length / numDecks;
        for (int p = 0; p < players.length; p++) {
            CardDeck[] cardDecks = new CardDeck[numDecks];
            for (int d = 0; d < numDecks; d++) {
                CardDeck cardDeck = new CardDeck();
                cardBag.assignCardsToDeck(cardDeck, cardsPerDeck);
                cardDecks[d] = cardDeck;
            }
            players[p].setCardDecks(cardDecks);
        }
    }

    /* Accessors and mutators */

    public CardBag getCardBag() {
        return cardBag;
    }

    public void setCardBag(CardBag cardBag) {
        this.cardBag = cardBag;
    }

    public int getNumDecks() {
        return numDecks;
    }

    public void setNumDecks(int numDecks) {
        this.numDecks = numDecks;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getTurnMonitor() {
        return turnMonitor;
    }

    public void incrementTurnMonitor() {
        turnMonitor = (turnMonitor + 1) % numPlayers;
    }

    /* Other methods that help the game facilitates */

    public boolean isGameEnded() { // game ends when all players run out of cards
        for (Player player : players)
            if (!player.hasNoCard())
                return false;
        return true;
    }

    public Player getCurrentPlayer() { // return the player that should be playing for this current turn
        return players[turnMonitor];
    }

    public String decksOfCurrentPlayerToString() {
        return players[turnMonitor].decksToString();
    }
}
