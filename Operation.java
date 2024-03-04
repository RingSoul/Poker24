public enum Operation {
    LEFT_PARENTHESIS("("), // will never have right parenthesis operation because
    ADD("+"), SUBTRACT("-"),
    MULTIPLY("*"), DIVIDE("/"), MODULUS("%"),
    EXPONENT("^"),
    SINE("sin"), COSINE("cos"), TANGENT("tan"),
    COSECANT("csc"), SECANT("sec"), COTANGENT("cot"),
    NATURAL_LOG("ln"); // more to be added

    private String symbol;
    private int precedence;
    private static final int PARENTHESIS_PRECEDENCE_SPECIAL = 0;
    private static final int ADD_SUBTRACT_PRECEDENCE = 1;
    private static final int MULTIPLY_DIVIDE_MODULUS_PRECEDENCE = 2;
    private static final int EXPONENT_TRIG_LOG_PRECEDENCE = 3;

    private Operation(String symbol) {
        this.symbol = symbol;
        assignPrecedence();
    }

    private void assignPrecedence() {
        switch (this) {
            case LEFT_PARENTHESIS -> precedence = Operation.PARENTHESIS_PRECEDENCE_SPECIAL;
            case ADD, SUBTRACT -> precedence = Operation.ADD_SUBTRACT_PRECEDENCE;
            case MULTIPLY, DIVIDE, MODULUS -> precedence = Operation.MULTIPLY_DIVIDE_MODULUS_PRECEDENCE;
            case EXPONENT,
                    SINE, COSINE, TANGENT,
                    COSECANT, SECANT, COTANGENT,
                    NATURAL_LOG -> precedence = Operation.EXPONENT_TRIG_LOG_PRECEDENCE;
            // more operations can be added above
            default -> {}
        }
    }

    public String getSymbol() {
        return symbol;
    }
    public int getPrecedence() {
        return precedence;
    }

    // this static method helps to create an Operation enum based on the symbol of the operation
    // throw exception to remind the programmer if forget to update the Operation enum instantiations and constructor
    public static Operation createOperation(String symbol) {
        for (Operation operation : Operation.values())
            if (operation.getSymbol().equals(symbol))
                return operation;
        throw new NoCorrespondingOperationException("The symbol \"" + symbol + "\" provided " +
                "is not detected in the Operation enum.");
    }

    // this static method helps to calculate an Operation enum with given operands
    public static int calculateOperation(int firstOperand, Operation operation, int secondOperand) {
        int result = 0;
        switch (operation) {
            case ADD -> result = firstOperand + secondOperand;
            case SUBTRACT -> result = firstOperand - secondOperand;
            case MULTIPLY -> result = firstOperand * secondOperand;
            case DIVIDE -> result = firstOperand / secondOperand;
            // more operations can be added above
            default -> throw new NoCorrespondingOperationException("Provided operation is not detected " +
                    "in the calculateOperation method of the Operation enum.");
        }
        return result;
    }
}
