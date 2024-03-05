import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

// responsible for displaying on console
public class PokerGameConsoleManager extends PokerGameManager {

    private final Scanner scanner; // takes user input

    public PokerGameConsoleManager() {
        super();
        scanner = new Scanner(System.in);
    }

    @Override
    public void play() {
        displayMessage(); // welcome message
        introduceRules(); // introduce the rules
        /* Initialize the game setting */
        initializeGameSetting();
        /* Play the game */
        for (Player player : getPokerGame().getPlayers()) {
                playATurn(player);
        }
        concludeGame();
    }

    @Override
    public void introduceRules() {

    }

    @Override
    public void initializeGameSetting() {
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

    public void playATurn(Player player) {
        boolean isPlayerTurnOver = false;
            setAndDisplayMessage(player.getName() + "'s Card Decks:");
            for (CardDeck cardDeck : player.getCardDecks()) {
                System.out.print(cardDeck);
                System.out.println();
            }
    }

    @Override
    public void concludeGame() {

    }

    @Override
    public void displayMessage() {
        System.out.println(getMessage());
    }

    @Override
    public int askForInt(String prompt) throws InputMismatchException { // use scanner to get int input
        System.out.print(prompt);
        return scanner.nextInt();
    }

    @Override
    public String askForString(String prompt) throws NoSuchElementException { // use scanner to get String input
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public void cleanFormat() { // used after nextInt
        scanner.nextLine();
    }

    @Override
    public void discardUnneededInput() {
        scanner.next(); // NEW: discard invalid token (input) to prevent infinite loop, since the discarding is not automatic
    }
}
