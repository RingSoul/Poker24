import java.util.LinkedList;

public class PokerGame {

    private CardBag cardBag;
    private int numDecks; // equivalent to:
    // 1) number of cards available for calculation manipulation
    // 2) number of CardDeck objects per player
    private static final int DEFAULT_NUM_DECKS = 4; // standard game with 4 decks
    private int numPlayers;
    private Player[] players;
    private int turnMonitor; // represents the index in the array players, the player whose turn is up
    private int desiredCalculationResult; // represents what the calculations should aim towards; default 24
    private LinkedList<ActionRecord> actionRecordList;


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
        actionRecordList = new LinkedList<>();
    }

    /* Pre-game, private helper methods */

    private void createCardDecksForPlayers() {
        // make sure that the cards are distributed evenly from the bag to decks (some will be leftover)
        int cardsPerDeck = CardBag.MAX_CAPACITY / (numPlayers * numDecks);
        for (Player player : players) {
            CardDeck[] cardDecks = new CardDeck[numDecks];
            for (int d = 0; d < numDecks; d++) {
                CardDeck cardDeck = new CardDeck();
                cardBag.assignCardsToDeck(cardDeck, cardsPerDeck);
                cardDecks[d] = cardDeck;
            }
            player.setCardDecks(cardDecks);
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

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getTurnMonitor() {
        return turnMonitor;
    }

    public void setTurnMonitor(int turnMonitor) {
        this.turnMonitor = turnMonitor;
    }

    public int getDesiredCalculationResult() {
        return desiredCalculationResult;
    }

    public void setDesiredCalculationResult(int desiredCalculationResult) {
        this.desiredCalculationResult = desiredCalculationResult;
    }

    public LinkedList<ActionRecord> getActionRecordList() {
        return actionRecordList;
    }

    public void setActionRecordList(LinkedList<ActionRecord> actionRecordList) {
        this.actionRecordList = actionRecordList;
    }

    /* Other methods that help the game facilitates */

    public boolean isGameEnded() { // game ends when all players run out of cards
        /* to be implemented */
        return false;
    }

    public Player getCurrentPlayer() {
        return players[turnMonitor];
    }

    public Player getNextPlayer() { // return the player that should be playing for the next turn (AUTO-increment solely by this method call)
        return players[(turnMonitor + 1) % numPlayers];
    }

    public Player getPreviousPlayer() {
        if (turnMonitor > 0)
            return players[turnMonitor - 1];
        else
            return players[players.length - 1];
    }

    public void incrementTurnMonitor() {
        turnMonitor = (turnMonitor + 1) % numPlayers;
    }

    public String decksOfCurrentPlayerToString() {
        return players[turnMonitor].decksToString();
    }
}
