public class OperationNotFoundException extends InvalidUserInputException {
    public OperationNotFoundException() {
        super("Operation not found!");
    }
    public OperationNotFoundException(String message) {
        super(message);
    }
}
