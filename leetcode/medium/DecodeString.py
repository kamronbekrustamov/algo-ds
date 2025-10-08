class Solution:
    """
    Provides an alternative iterative, single-stack solution to the Decode String problem.
    This approach evaluates the string by pushing most elements onto a stack and
    processing a segment only when a closing bracket is found.
    """
    def decodeString(self, s: str) -> str:
        """
        Decodes the given string using a single stack.

        Args:
            s: The encoded string.

        Returns:
            The decoded string.
        """
        stack = []
        
        for char in s:
            if char != ']':
                # Push all characters and numbers onto the stack until a ']' is found.
                stack.append(char)
            else:
                # Found a ']', which triggers a decoding operation.
                
                # Step 1: Pop characters to form the encoded substring until we find a '['.
                encoded_substring = ''
                while stack[-1] != '[':
                    encoded_substring = stack.pop() + encoded_substring
                
                # Pop the '[' marker from the stack.
                stack.pop()

                # Step 2: Pop digits to form the repetition number `k`.
                k_str = ''
                while stack and stack[-1].isdigit():
                    k_str = stack.pop() + k_str
                
                k = int(k_str)

                # Step 3: Push the decoded string (substring repeated k times) back onto the stack.
                stack.append(encoded_substring * k)

        # The final result is the concatenation of all items remaining in the stack.
        return "".join(stack)