public enum Operation {

    LEFT_PARENTHESIS("(", 0),
    // will never have right parenthesis operation
    // number of input is irrelevant for parentheses
    ADD("+", 2), SUBTRACT("-", 2),
    MULTIPLY("*", 2), DIVIDE("/", 2), MODULUS("%", 2),
    EXPONENT("^", 2),
    SINE("sin", 1), COSINE("cos", 1), TANGENT("tan", 1),
    COSECANT("csc", 1), SECANT("sec", 1), COTANGENT("cot", 1),
    HYPERBOLIC_SINE("sinh", 1), HYPERBOLIC_COSINE("cosh", 1), HYPERBOLIC_TANGENT("tanh", 1),
    HYPERBOLIC_COSECANT("csch", 1), HYPERBOLIC_SECANT("sech", 1), HYPERBOLIC_COTANGENT("coth", 1),
    INVERSE_SINE("arcsin", 1), INVERSE_COSINE("arccos", 1), INVERSE_TANGENT("arctan", 1),
    INVERSE_COSECANT("arccsc", 1), INVERSE_SECANT("arcsec", 1), INVERSE_COTANGENT("arccot", 1),
    NATURAL_LOG("ln", 1), LOG_BASE_TEN("log", 1),
    FACTORIAL("!", 1);
    // more can be added above

    private String symbol; // string representation of the operation
    private int numInputsNeeded;
    // numInputsNeeded = the minimum number of input needed to exclusively perform this operation
    // (i.e. the simplest form of addition is a+b -> addition's numInput is 2)
    private int precedence;
    private static final int PARENTHESIS_PRECEDENCE_SPECIAL = 0;
    private static final int ADD_SUBTRACT_PRECEDENCE = 1;
    private static final int MULTIPLY_DIVIDE_MODULUS_PRECEDENCE = 2;
    private static final int EXPONENT_TRIG_LOG_FACTORIAL_PRECEDENCE = 3;

    private Operation(String symbol, int numInputsNeeded) {
        this.symbol = symbol;
        this.numInputsNeeded = numInputsNeeded;
        assignPrecedence(symbol);
    }

    // helper method for the constructor
    // assign precedence based on expected symbols
    // throw exception to remind the programmer if forget to update the Operation enum methods after adding more operations
    private void assignPrecedence(String symbol) throws OperationNotFoundException {
        switch (symbol) {
            case "(" -> precedence = PARENTHESIS_PRECEDENCE_SPECIAL;
            case "+", "-" -> precedence = ADD_SUBTRACT_PRECEDENCE;
            case "*", "/", "%" -> precedence = MULTIPLY_DIVIDE_MODULUS_PRECEDENCE;
            case "^",
                    "sin", "cos", "tan",
                    "csc", "sec", "cot",
                    "sinh", "cosh", "tanh",
                    "csch", "sech", "coth",
                    "arcsin", "arccos", "arctan",
                    "arccsc", "arcsec", "arccot",
                    "ln", "log", "!" -> precedence = EXPONENT_TRIG_LOG_FACTORIAL_PRECEDENCE;
            // more operations can be added above
            default -> throw new OperationNotFoundException("The symbol \"" + symbol + "\" provided " +
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
            throws OperationNotFoundException {
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
            case "sinh" -> {
                return HYPERBOLIC_SINE;
            }
            case "cosh" -> {
                return HYPERBOLIC_COSINE;
            }
            case "tanh" -> {
                return HYPERBOLIC_TANGENT;
            }
            case "csch" -> {
                return HYPERBOLIC_COSECANT;
            }
            case "sech" -> {
                return HYPERBOLIC_SECANT;
            }
            case "coth" -> {
                return HYPERBOLIC_COTANGENT;
            }
            case "arcsin" -> {
                return INVERSE_SINE;
            }
            case "arccos" -> {
                return INVERSE_COSINE;
            }
            case "arctan" -> {
                return INVERSE_TANGENT;
            }
            case "arccsc" -> {
                return INVERSE_COSECANT;
            }
            case "arcsec" -> {
                return INVERSE_SECANT;
            }
            case "arccot" -> {
                return INVERSE_COTANGENT;
            }
            case "ln" -> {
                return NATURAL_LOG;
            }
            case "log" -> {
                return LOG_BASE_TEN;
            }
            case "!" -> {
                return FACTORIAL;
            }
            // more operations can be added above
            default -> throw new OperationNotFoundException("The symbol \"" + symbol + "\" provided " +
                            "is not detected in the createOperation method of the Operation enum.");

        }
    }

    // this static method helps to calculate an Operation enum with given operands
    // throw exception to remind the programmer if forget to update the Operation enum methods after adding more operations
    public static double calculateOperation(double firstOperand, Operation operation, double secondOperand)
            throws InvalidUserInputException {
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
            case HYPERBOLIC_SINE -> result = Math.sinh(secondOperand);
            case HYPERBOLIC_COSINE -> result = Math.cosh(secondOperand);
            case HYPERBOLIC_TANGENT -> result = Math.tanh(secondOperand);
            case HYPERBOLIC_COSECANT -> result = 1.0 / Math.sinh(secondOperand);
            case HYPERBOLIC_SECANT -> result = 1.0 / Math.cosh(secondOperand);
            case HYPERBOLIC_COTANGENT -> result = 1.0 / Math.tanh(secondOperand);
            case INVERSE_SINE -> result = Math.asin(secondOperand);
            case INVERSE_COSINE -> result = Math.acos(secondOperand);
            case INVERSE_TANGENT -> result = Math.atan(secondOperand);
            case INVERSE_COSECANT -> result = 1.0 / Math.asin(secondOperand);
            case INVERSE_SECANT -> result = 1.0 / Math.acos(secondOperand);
            case INVERSE_COTANGENT -> result = 1.0 / Math.atan(secondOperand);
            case NATURAL_LOG -> result = Math.log(secondOperand);
            case LOG_BASE_TEN -> result = Math.log10(secondOperand);
            case FACTORIAL -> result = factorial((int) secondOperand);
            // more operations can be added above
            default -> throw new OperationNotFoundException("Provided operation \"" + operation.getSymbol() +
                            "\" is not detected in the calculateOperation method of the Operation enum.");
        }
        return result;
    }

    // helper method, since Math does not have a factorial method
    // pre-condition: n >= 0
    private static int factorial(int n) throws InvalidUserInputException {
        if (n < 0)
            throw new InvalidUserInputException("Invalid input! The factorial operation only takes in 0 or a positive integer as input.");
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    // inefficient, but better practice than hardcoding in CalculatorUtilityCalculatorUtility (when tracking operator length)
    public static int getTheLengthOfTheLongestPossibleOperator() {
        int maxLength = 0;
        for (Operation operation : Operation.values())
            if (operation.getSymbol().length() > maxLength)
                maxLength = operation.getSymbol().length();
        return maxLength;
    }
}
