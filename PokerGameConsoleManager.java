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
        /* Initialize the game setting */
        initializeGameSetting();
        /* Play the game */
            for (Player player : getPokerGame().getPlayers()) {
                playATurn(player);
        }
    }

    @Override
    public void initializeGameSetting() {
        int numPlayers = 0;
        int numDecks = 0;
        numPlayers = askForNumPlayers();
        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            String name = askForPlayerName(i);
            Player player = new Player(name);
            players[i] = player;
        }
        numDecks = askForNumDecks();
        createPokerGame(players, numDecks);
    }

    public void playATurn(Player player) {
        boolean isPlayerTurnOver = false;
            System.out.println(player.getName() + "'s Card Decks:");
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

    private int askForNumDecks() { // numDecks has to be greater than or equal to 4
        boolean isNumDecksValid = false;
        int numDecks = 0;
        while (!isNumDecksValid) {
            try {
                numDecks = askForInt("Enter the number of decks per player: ");
                if (numDecks < 4) {
                    System.out.println("Invalid input! The number of decks per player must be greater than or equal to 4.");
                } else {
                    isNumDecksValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! The number of decks per player must be an integer.");
                scanner.next(); // NEW: discard invalid token to prevent infinite loop
            }
        }
        scanner.nextLine(); // for nextInt, used for console output formatting
        return numDecks;
    }

    private int askForNumPlayers() { // numPlayers has to be greater than or equal to 2
        boolean isNumPlayersValid = false;
        int numPlayers = 0;
        while (!isNumPlayersValid) {
            try {
                numPlayers = askForInt("Enter the number of players: ");
                if (numPlayers < 2) {
                    System.out.println("Invalid input! The number of players must be greater than or equal to 2.");
                } else {
                    isNumPlayersValid = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! The number of players must be an integer.");
                scanner.next(); // NEW: discard invalid token to prevent infinite loop
            }
        }
        scanner.nextLine(); // for nextInt, used for console output formatting
        return numPlayers;
    }

    private int askForInt(String prompt) throws InputMismatchException { // use scanner to get int input
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private String askForPlayerName(int playerNumber) {
        boolean isPlayerNameValid = false;
        String playerName = "";
        while (!isPlayerNameValid) {
            try {
                playerName = askForString("Enter the name of Player #" + playerNumber + ": ");
                isPlayerNameValid = true;
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input for player name!");
                scanner.next(); // NEW: discard invalid token to prevent infinite loop
            }
        }
        return playerName;
    }

    private String askForString(String prompt) throws NoSuchElementException {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
