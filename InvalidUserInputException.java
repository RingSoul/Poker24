public class InvalidUserInputException extends IllegalArgumentException {
    public InvalidUserInputException() {
        super("Invalid user input!");
    }
    public InvalidUserInputException(String message) {
        super(message);
    }
}
