import java.util.EmptyStackException;
import java.util.Stack;

public class StackUtility {
    // static method library
    public static int calculateInfix(String infix) throws EmptyStackException, InvalidUserInputException {
        int result = 0; // keeps track of result (constantly updated and pushed into operandStack)
        Stack<Integer> operandStack = new Stack<>(); // constantly pushes operands into the stack until there is a need to calculate an operation
        Stack<Operation> operationStack = new Stack<>(); // constantly pushes Operation enums into the stack until there is a need to calculate an operation
        StringBuilder numBuilder = new StringBuilder(); // keeps track of multi-digit operands
        int firstOperand = 0;
        int secondOperand = 0; // operands are used when there is a need to calculate an operation
        for (Character character : infix.toCharArray()) {
            switch (character) {
                case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> { // if it is a number, add to StringBuilder
                    numBuilder.append(character);
                }
                case '+', '-', '*', '/' -> { // if it is a normal operator, temporarily conclude string building and push
                    int operand = Integer.parseInt(numBuilder.toString());
                    numBuilder.delete(0, numBuilder.length());
                    operandStack.push(operand); // push operand
                    Operation operation = Operation.createOperation(character.toString()); // create an Operation enum
                    while (!operationStack.isEmpty() && operation.getPrecedence() <= operationStack.peek().getPrecedence()) {
                        // while operation stack is not empty, and the operations in the stack has higher/same precedence than this just-created operation
                        secondOperand = operandStack.pop(); // first pop = second operand
                        firstOperand = operandStack.pop(); // second pop = first operand
                        result = Operation.calculateOperation(firstOperand, operationStack.pop(), secondOperand);
                        operandStack.push(result); // always push the result so the next operation will involve result as an operand
                    }
                    operationStack.push(operation); // push operation after all/no calculation is completed
                }
                case '(' -> {
                    Operation operation = Operation.createOperation(character.toString());
                    operationStack.push(operation); // always push left parenthesis
                }
                case ')' -> { // pop everything above and including left parenthesis when a right parenthesis is reached
                    while (operationStack.peek().getSymbol().toCharArray()[0] != '(') {
                        secondOperand = operandStack.pop();
                        firstOperand = operandStack.pop();
                        result = Operation.calculateOperation(firstOperand, operationStack.pop(), secondOperand);
                    }
                    operationStack.pop();
                }
                default -> { // if any weird character, ignore and warn
                    throw new InvalidUserInputException("Invalid infix expression entered!");
                }
            }
        }
        return result;
    }

}
