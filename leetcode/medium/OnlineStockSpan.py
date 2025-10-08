from collections import deque


class StockSpanner:
    """
    StockSpanner class calculates the span of a stock's price for each day.
    The span is defined as the maximum number of consecutive days (starting from today and going backward)
    for which the stock price was less than or equal to today's price.
    """

    def __init__(self):
        """
        Initializes the StockSpanner object.
        It uses a monotonic stack to keep track of prices and their corresponding spans.
        The stack stores tuples of (price, span).
        """
        self.stack = deque()

    def next(self, price: int) -> int:
        """
        Calculates the span for the given price.

        Args:
            price: The stock price for the current day.

        Returns:
            The span of the stock for the current day.
        """
        # Initialize the span for the current price as 1 (for the price itself).
        current_span = 1

        # While the stack is not empty and the price at the top of the stack is less than or equal to the current price,
        # it means the current price's span includes the span of the previous smaller or equal prices.
        # So, we pop from the stack and add the popped element's span to the current span.
        while self.stack and self.stack[-1][0] <= price:
            current_span += self.stack.pop()[1]

        # Push the current price and its calculated span onto the stack.
        self.stack.append((price, current_span))

        # Return the calculated span for the current price.
        return current_span


# Your StockSpanner object will be instantiated and called as such:
# obj = StockSpanner()
# param_1 = obj.next(price)