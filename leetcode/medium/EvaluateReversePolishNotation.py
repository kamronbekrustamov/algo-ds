from typing import List
import operator

class Solution:
    def evalRPN(self, tokens: List[str]) -> int:
        # The stack will store numbers (operands) as we encounter them.
        stack = []
        
        # Define the operations. Using a dictionary is a clean and extensible
        # way to handle different operators.
        # Note: For subtraction and division, the order of operands matters.
        # The lambda functions take `b` then `a` to match the stack pop order.
        operations = {
            "+": operator.add,
            "-": lambda b, a: a - b,
            "*": operator.mul,
            "/": lambda b, a: int(a / b) # Truncates toward zero as required.
        }

        for token in tokens:
            # If the token is an operator, perform the calculation.
            if token in operations:
                # Pop the second operand first, then the first operand.
                operand1 = stack.pop()
                operand2 = stack.pop()
                
                # Look up the correct function and apply it.
                result = operations[token](operand1, operand2)
                
                # Push the result back onto the stack.
                stack.append(result)
            else:
                # If the token is not an operator, it must be a number.
                # Convert it to an integer and push it onto the stack.
                stack.append(int(token))
        
        # After processing all tokens, the final result is the only item on the stack.
        return stack.pop()