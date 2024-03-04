public abstract class PokerGameManager { // the intersection between front end and back end
    // abstract superclass of the two supported gaming Managers, thus allowing them to inherit fields and methods

    private PokerGame pokerGame;
    private String message;
    /* message utility:
     * tells whose turn it is
     * warns if an exception is detected (invalid user input, for example)
     */

    public PokerGameManager() { // default constructor that solely initialize message
        pokerGame = null;
        message = "Welcome to Poker24!";
    }

    public void createPokerGame(Player[] players, int numDecks) { // method to initialize the PokerGame object
        pokerGame = new PokerGame(players, numDecks);
    }

    public String getMessage() { // accessor
        return message;
    }

    public void setMessage(String message) { // mutator
        this.message = message;
    }

    // differing implementations of displayMessage and play methods for console and GUI
    // abstract keyword forces them to override it
    public abstract void displayMessage(); // display message relating to the status of the game
    public abstract void initializeGameSetting(); // initialize the game setting (number of players, number of decks, player names, etc.)
    public abstract void play(); // play the entire game
    public abstract void concludeGame(); // conclude the game by announcing winner based on the score, etc.

    public PokerGame getPokerGame() { // accessor
        return pokerGame;
    }

    public void setPokerGame(PokerGame pokerGame) { // mutator
        this.pokerGame = pokerGame;
    }
}
