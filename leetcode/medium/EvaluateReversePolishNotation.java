import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    public int evalRPN(String[] tokens) {
        // Use Deque as a Stack.
        Deque<Integer> stack = new ArrayDeque<>();
        
        // 

        for (String token : tokens) {
            
            if (token.length() == 1 && "+-*/".indexOf(token) != -1) {
                // The token is a recognized operator.
                
                // Pop the second operand first.
                int operand2 = stack.pop(); 
                // Pop the first operand.
                int operand1 = stack.pop(); 
                
                int result = 0;

                switch (token) {
                    case "+" -> result = operand1 + operand2;
                    case "-" -> result = operand1 - operand2;
                    case "*" -> result = operand1 * operand2;
                    case "/" -> result = operand1 / operand2;
                }

                // Push the result back onto the stack.
                stack.push(result);
            } else {
                // The token must be an operand (a number).
                // Assuming valid input, Integer.parseInt will succeed.
                stack.push(Integer.parseInt(token));
            }
        }

        // The final result is the only element remaining on the stack.
        return stack.pop();
    }
}