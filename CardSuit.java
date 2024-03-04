import java.awt.*;

public enum CardSuit {
    CLUBS("♣", "\u001b[30m", new Color(0x000000)), SPADES("♠", "\u001b[30m", new Color(0x000000)),
    HEARTS("♥", "\u001B[31m", new Color(0xFF0000)), DIAMONDS("♦", "\u001B[31m", new Color(0xFF0000));

    private String symbol;
    private String consoleColor;
    private Color GUIColor;

    private CardSuit(String symbol, String consoleColor, Color GUIColor) {
        this.symbol = symbol;
        this.consoleColor = consoleColor;
        this.GUIColor = GUIColor;
    }

    public String getSymbol() {
        return symbol;
    }
    public String getConsoleColor() {
        return consoleColor;
    }
    public Color getGUIColor() {
        return GUIColor;
    }

    public String toConsoleString() {
        return consoleColor + symbol + "\u001b[0m"; // the third string is for resetting the color
    }
}
