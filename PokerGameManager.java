import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public abstract class PokerGameManager { // subclasses = the intersection between front end and back end
    // abstract superclass of the two supported gaming Managers, thus allowing them to inherit fields and methods

    private PokerGame pokerGame; // required inputs: number of decks, number of players, and players' names
    private String message;
    /* message utility:
     * welcomes the players
     * tells whose turn it is
     * warns if an exception/error is detected (invalid user input, for example)
     * etc.
     */
    private String prompt; // different from message since it asks the user to enter something

    public PokerGameManager() { // default constructor that solely initialize message
        pokerGame = null;
        message = "";
        prompt = "";
    }

    private void createPokerGame(Player[] players, int numDecks) { // method to initialize the PokerGame object
        pokerGame = new PokerGame(players, numDecks);
    } // create the actual PokerGame object, used by the initializeGameSetting method

    /* game play methods */
    private void introduceRules() { // introduce the rules of Poker24 (helper of play)
        String textToDisplay = "";
        setAndDisplayMessage(textToDisplay);
    }
    private void initializeGameSetting() { // initialize the game setting (number of players, number of decks, player names, etc.) (helper of play)
        int numPlayers = 0;
        int numDecks = 0;
        numPlayers = askForNumPlayers();
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            String name = askForPlayerName();
            Player player = new Player(name);
            players[i] = player;
        }
        numDecks = askForNumDecks();
        createPokerGame(players, numDecks);
    }
    public void play() { // the only method that the Runner needs access to, since this method does everything
        /* Introduce the rules */
        introduceRules();
        /* Initialize the game setting */
        initializeGameSetting();
        /* Play the game */
        while (!pokerGame.isGameEnded())
            turnOf(pokerGame.getNextPlayer());
        /* Conclude the game */
        concludeGame();
    }
    private void turnOf(Player player) { // let the current player make their moves (helper of play)
        boolean isPlayerTurnOver = false;
        setAndDisplayMessage(player.getName() + "'s Card Decks:");
        for (CardDeck cardDeck : player.getCardDecks()) {

        }
    }
    private void concludeGame() { // conclude the game by announcing winner based on the score, etc. (helper of play)

    }
    private void setAndDisplayMessage(String message) { // combines set and display for convenience
        setMessage(message);
        displayMessage(); // relies on subclass implementation
    }
    private void setAndDisplayPrompt(String prompt) {
        setPrompt(prompt);
        displayPrompt();
    }

    // ask for inputs; rely on the subclasses' implementations of askForInt, askForString, cleanFormat and discardUnneededInput
    private int askForNumDecks() { // ask for the number of decks per player
        boolean isNumDecksValid = false;
        int numDecks = 0;
        while (!isNumDecksValid) {
            try {
                numDecks = askForInt("Enter the number of decks per player: ");
                if (numDecks < 4) {
                    setAndDisplayMessage("Invalid input! The number of decks per player must be greater than or equal to 4.");
                } else {
                    isNumDecksValid = true;
                }
            } catch (InputMismatchException e) {
                setAndDisplayMessage("Invalid input! The number of decks per player must be an integer.");
                discardUnneededInput();
            }
        }
        cleanFormat();
        return numDecks;
    }
    private int askForNumPlayers() { // ask for the number of players
        boolean isNumPlayersValid = false;
        int numPlayers = 0;
        while (!isNumPlayersValid) {
            try {
                numPlayers = askForInt("Enter the number of players: ");
                if (numPlayers < 2) {
                    setAndDisplayMessage("Invalid input! The number of players must be greater than or equal to 2.");
                } else {
                    isNumPlayersValid = true;
                }
            } catch (InputMismatchException e) {
                setAndDisplayMessage("Invalid input! The number of players must be an integer.");
                discardUnneededInput();
            }
        }
        cleanFormat();
        return numPlayers;
    }
    private String askForPlayerName() { // ask for the name of a player
        boolean isPlayerNameValid = false;
        String playerName = "";
        while (!isPlayerNameValid) {
            try {
                playerName = askForString("Enter the name of a player: ");
                isPlayerNameValid = true;
            } catch (NoSuchElementException e) {
                setAndDisplayMessage("Invalid input for player name!");
                discardUnneededInput();
            }
        }
        cleanFormat();
        return playerName;
    }


    /* accessors and mutators of fields */

    public String getMessage() { // accessor
        return message;
    }

    public void setMessage(String message) { // mutator
        this.message = message;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public PokerGame getPokerGame() { // accessor
        return pokerGame;
    }

    public void setPokerGame(PokerGame pokerGame) { // mutator
        this.pokerGame = pokerGame;
    }


    /* differing implementations of these methods for console and GUI (due to different ways to display),
                             abstract keyword forces subclasses to override them */
    public abstract void displayMessage(); // display message relating to the status of the game
    public abstract void displayPrompt(); // display prompt for the user to enter what is asked of
    // prompts for user's answers based on the desired data type
    public abstract int askForInt(String prompt) throws InputMismatchException; // helper of askForInt
    public abstract String askForString(String prompt) throws NoSuchElementException; // helper of askForString
    // troubleshooting, if necessary (scanner might be the only one who needs this?)
    public abstract void cleanFormat(); // adjust format if necessary after any interaction that may disrupt the user interface (scanner's nextInt, etc.)
    public abstract void discardUnneededInput(); // abandon what the user just entered if input is invalid
}
