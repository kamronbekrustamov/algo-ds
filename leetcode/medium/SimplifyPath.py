class Solution:
    """
    Solution class for the Simplify Path problem.
    """
    def simplifyPath(self, path: str) -> str:
        """
        Simplifies a given Unix-style absolute path to its canonical form.

        The canonical path has the following properties:
        - It starts with a single slash '/'.
        - Any two directories are separated by a single slash '/'.
        - It does not end with a trailing '/'.
        - It contains only the directories on the path from the root directory to the target
          directory (i.e., no '.' or '..').

        Args:
            path: The input Unix-style absolute path.

        Returns:
            The simplified canonical path.
        """
        # A stack to keep track of the directory names in the canonical path.
        stack = []

        # Split the path by slashes. This handles multiple slashes between directories,
        # e.g., "/a//b" -> ["", "a", "", "b"].
        components = path.split('/')

        # Process each component of the path.
        for component in components:
            if component == '..':
                # If the component is '..', it means go up one level.
                # So, we pop from the stack if it's not empty.
                if stack:
                    stack.pop()
            elif component and component != '.':
                # If the component is not an empty string and not '.',
                # it's a valid directory name, so push it onto the stack.
                # Empty strings (from multiple slashes) and '.' (current directory) are ignored.
                stack.append(component)

        # Join the components in the stack with a single slash in between
        # and prepend a slash to form the final canonical path.
        return "/" + "/".join(stack)
