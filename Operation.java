public enum Operation {

    LEFT_PARENTHESIS("(", 0),
    // will never have right parenthesis operation
    // number of input is irrelevant for parentheses
    ADD("+", 2), SUBTRACT("-", 2),
    MULTIPLY("*", 2), DIVIDE("/", 2), MODULUS("%", 2),
    EXPONENT("^", 2),
    SINE("sin", 1), COSINE("cos", 1), TANGENT("tan", 1),
    COSECANT("csc", 1), SECANT("sec", 1), COTANGENT("cot", 1),
    NATURAL_LOG("ln", 1), LOG_BASE_TEN("log", 1); // more can be added

    private String symbol; // string representation of the operation
    private int numInputsNeeded;
    // numInputsNeeded = the minimum number of input needed to exclusively perform this operation
    // (i.e. the simplest form of addition is a+b -> addition's numInput is 2)
    private int precedence;
    private static final int PARENTHESIS_PRECEDENCE_SPECIAL = 0;
    private static final int ADD_SUBTRACT_PRECEDENCE = 1;
    private static final int MULTIPLY_DIVIDE_MODULUS_PRECEDENCE = 2;
    private static final int EXPONENT_TRIG_LOG_PRECEDENCE = 3;

    private Operation(String symbol, int numInputsNeeded) {
        this.symbol = symbol;
        this.numInputsNeeded = numInputsNeeded;
        assignPrecedence();
    }

    // helper method for the constructor
    // throw exception to remind the programmer if forget to update the Operation enum methods after adding more operations
    private void assignPrecedence() throws NoCorrespondingOperationException {
        switch (this) {
            case LEFT_PARENTHESIS -> precedence = PARENTHESIS_PRECEDENCE_SPECIAL;
            case ADD, SUBTRACT -> precedence = ADD_SUBTRACT_PRECEDENCE;
            case MULTIPLY, DIVIDE, MODULUS -> precedence = MULTIPLY_DIVIDE_MODULUS_PRECEDENCE;
            case EXPONENT,
                    SINE, COSINE, TANGENT,
                    COSECANT, SECANT, COTANGENT,
                    NATURAL_LOG, LOG_BASE_TEN -> precedence = EXPONENT_TRIG_LOG_PRECEDENCE;
            // more operations can be added above
            default -> throw new NoCorrespondingOperationException("The symbol \"" + symbol + "\" provided " +
                            "is not detected in the assignPrecedence method of the Operation enum.");
        }
    }

    public String getSymbol() {
        return symbol;
    }
    public int getNumInputsNeeded() {
        return numInputsNeeded;
    }
    public int getPrecedence() {
        return precedence;
    }

    // this static method helps to create an Operation enum object based on the symbol of the operation
    // throw exception to remind the programmer if forget to update the Operation enum methods after adding more operations
    public static Operation createOperation(String symbol)
            throws NoCorrespondingOperationException {
        switch (symbol) {
            case "(" -> {
                return LEFT_PARENTHESIS;
            }
            case "+" -> {
                return ADD;
            }
            case "-" -> {
                return SUBTRACT;
            }
            case "*" -> {
                return MULTIPLY;
            }
            case "/" -> {
                return DIVIDE;
            }
            case "^" -> {
                return EXPONENT;
            }
            case "sin" -> {
                return SINE;
            }
            case "cos" -> {
                return COSINE;
            }
            case "tan" -> {
                return TANGENT;
            }
            case "csc" -> {
                return COSECANT;
            }
            case "sec" -> {
                return SECANT;
            }
            case "cot" -> {
                return COTANGENT;
            }
            case "ln" -> {
                return NATURAL_LOG;
            }
            case "log" -> {
                return LOG_BASE_TEN;
            }
            // more operations can be added above
            default -> throw new NoCorrespondingOperationException("The symbol \"" + symbol + "\" provided " +
                            "is not detected in the createOperation method of the Operation enum.");

        }
    }

    // this static method helps to calculate an Operation enum with given operands
    // throw exception to remind the programmer if forget to update the Operation enum methods after adding more operations
    public static double calculateOperation(double firstOperand, Operation operation, double secondOperand)
            throws NoCorrespondingOperationException {
        double result = 0;
        switch (operation) {
            case ADD -> result = firstOperand + secondOperand;
            case SUBTRACT -> result = firstOperand - secondOperand;
            case MULTIPLY -> result = firstOperand * secondOperand;
            case DIVIDE -> result = firstOperand / secondOperand;
            case MODULUS -> result = firstOperand % secondOperand;
            case EXPONENT -> result = Math.pow(firstOperand, secondOperand);
            case SINE -> result = Math.sin(secondOperand);
            case COSINE -> result = Math.cos(secondOperand);
            case TANGENT -> result = Math.tan(secondOperand);
            case COSECANT -> result = 1.0 / Math.sin(secondOperand);
            case SECANT -> result = 1.0 / Math.cos(secondOperand);
            case COTANGENT -> result = 1.0 / Math.tan(secondOperand);
            case NATURAL_LOG -> result = Math.log(secondOperand);
            case LOG_BASE_TEN -> result = Math.log10(secondOperand);
            // more operations can be added above
            default -> throw new NoCorrespondingOperationException("Provided operation \"" + operation.getSymbol() +
                            "\" is not detected in the calculateOperation method of the Operation enum.");
        }
        return result;
    }

    // inefficient, but better practice than hardcoding in StackUtility (when tracking operator length)
    public int getTheLengthOfTheLongestPossibleOperator() {
        int maxLength = 0;
        for (Operation operation : Operation.values())
            if (operation.getSymbol().length() > maxLength)
                maxLength = operation.getSymbol().length();
        return maxLength;
    }
}
