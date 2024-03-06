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

    public void turnOf(Player player) {
        boolean isPlayerTurnOver = false;
            setAndDisplayMessage(player.getName() + "'s Card Decks:");
            for (CardDeck cardDeck : player.getCardDecks()) {
                System.out.print(cardDeck);
                System.out.println();
            }
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
