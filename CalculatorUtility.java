import java.util.EmptyStackException;
import java.util.Locale;
import java.util.Stack;

public class StackUtility {
    // static method library
    // consider....do we need to require parentheses after trigonometry or logarithm? no
    // consider....what if this expression is not balanced in terms of parentheses
    // consider....what if there is negative number?
    // throws: EmptyStackException, NoCorrespondingOperationException, InvalidUserInputException
    public static double calculateInfix(String infix) throws EmptyStackException, InvalidUserInputException {
        infix = reformatExpression(infix);
        double result = 0; // keeps track of result (constantly updated and pushed into operandStack)
        Stack<Double> operandStack = new Stack<>(); // constantly pushes operands into the stack until there is a need to calculate an operation
        Stack<Operation> operationStack = new Stack<>(); // constantly pushes Operation enum objects into the stack until there is a need to calculate an operation
        StringBuilder operandBuilder = new StringBuilder(); // keeps track of multi-digit operands
        StringBuilder operationBuilder = new StringBuilder(); // keeps track of multi-letter operation (trig, log...)
        double firstOperand = 0;
        double secondOperand = 0; // operands are used when there is a need to calculate an operation
        for (int i = 0; i < infix.length(); i++) {
            String character = infix.substring(i, i+1);
            switch (character.toLowerCase()) { // case-insensitive for operator input
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> { // if it is a number, add to StringBuilder
                    operandBuilder.append(character);
                }
                case "+", "-", "*", "/", "%", "^" -> { // if it is a normal operator, temporarily conclude operand building and push
                    double operand = Integer.parseInt(operandBuilder.toString()); // parse operand
                    operandBuilder.delete(0, operandBuilder.length()); // clear operandBuilder
                    operandStack.push(operand); // push operand
                    Operation operation = Operation.createOperation(character); // create an Operation enum object
                    while (!operationStack.isEmpty() && operation.getPrecedence() <= operationStack.peek().getPrecedence()) {
                        // while operation stack is not empty, and the operations on top of the stack has higher/same precedence than this just-created operation
                        Operation operationToPerform = operationStack.pop();
                        secondOperand = operandStack.pop(); // first pop = second operand
                        if (operationToPerform.getNumInputsNeeded() == 2) // if applicable, pop another operand
                            firstOperand = operandStack.pop(); // second pop = first operand
                        result = Operation.calculateOperation(firstOperand, operationStack.pop(), secondOperand);
                        operandStack.push(result); // always push the result so the next operation will involve result as an operand
                    }
                    operationStack.push(operation); // push operation after all/no calculation is completed
                }
                case "(" -> {
                    Operation operation = Operation.createOperation(character);
                    operationStack.push(operation); // always push left parenthesis
                }
                case ")" -> { // pop everything above and including left parenthesis when a right parenthesis is reached
                    while (operationStack.peek().getSymbol().toCharArray()[0] != '(') {
                        Operation operationToPerform = operationStack.pop();
                        secondOperand = operandStack.pop();
                        if (operationToPerform.getNumInputsNeeded() == 2)
                            firstOperand = operandStack.pop();
                        result = Operation.calculateOperation(firstOperand, operationStack.pop(), secondOperand);
                        operandStack.push(result); // always push the result so the next operation will involve result as an operand
                    }
                    operationStack.pop();
                }
                case "s", "i", "n", "c", "o", "t", "a", "e", "l", "g" -> { // for trig and log (multi-character operation)
                    boolean isOperationCreated = false;
                    operationBuilder.append(character);
                    if (operationBuilder.length() >= 2) {
                        for (Operation possibleOperation : Operation.values()) {
                            if (operationBuilder.toString().equals(possibleOperation.getSymbol())) {
                                Operation operation = Operation.createOperation(operationBuilder.toString());
                                operationStack.push(operation);
                                isOperationCreated = true;
                            }
                        }
                    }
                    if (!isOperationCreated && operationBuilder.length() > Operation.getTheLengthOfTheLongestPossibleOperator())
                        throw new NoCorrespondingOperationException("Invalid operation entered!");
                }
                default -> { // if any weird character, ignore and warn
                    throw new InvalidUserInputException("Invalid infix expression entered!");
                }
            }
        }
        return result;
    }

    // adjust user input in possible ways
    private static String reformatExpression(String expression) {

    }

}
