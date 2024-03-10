public class UnbalancedExpressionException extends InvalidUserInputException {
    public UnbalancedExpressionException() {
        super("Unbalanced expression!");
    }
    public UnbalancedExpressionException(String message) {
        super(message);
    }
}
