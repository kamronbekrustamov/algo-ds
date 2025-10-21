import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Solution class for simplifying a canonical file system path (e.g., converting "/home//foo/." to "/home/foo").
 * <p>
 * The algorithm works by tokenizing the path using a state machine-like approach with helper functions
 * and processing components with a Deque (acting as a stack).
 */
class Solution {

    /**
     * Simplifies a given absolute Unix-style path string to its canonical form.
     * <p>
     * The canonical path must be the shortest, unique path to the file or directory,
     * starting with a single slash '/', and not ending with a trailing slash
     * (unless it's the root directory itself).
     *
     * @param path The absolute path string (e.g., "/home//foo/.").
     * @return The simplified canonical path string (e.g., "/home/foo").
     */
    public String simplifyPath(String path) {
        // Use a Deque as a stack to store the valid directory names encountered.
        // ArrayDeque is generally more efficient than Stack.
        Deque<String> canonicalStack = new ArrayDeque<>();

        int currentIndex = 0;
        int pathLength = path.length();

        // 1. Process the path string segment by segment.
        while (currentIndex < pathLength) {
            // First, skip all consecutive slashes.
            currentIndex = skipSlashes(path, currentIndex);

            // If we've reached the end of the string, break the loop.
            if (currentIndex >= pathLength) {
                break;
            }

            // Find the index of the next slash, which marks the end of the current directory name.
            int nextSlashIndex = findNextSeparatorIndex(path, currentIndex);

            // Extract the current path component (e.g., "home", ".", "..").
            String component = path.substring(currentIndex, nextSlashIndex);

            // 2. Process the path component based on its value.
            switch (component) {
                case "." -> {
                }
                case ".." ->
                    // Go up one directory level: remove the last element from the stack.
                    // pollLast() safely returns null if the stack is empty (at the root),
                    // which is the correct behavior for going "above" the root.
                    canonicalStack.pollLast();
                default ->
                    // A valid directory name: push it onto the stack.
                    canonicalStack.offerLast(component);
            }

            // Move the index to the position of the next slash found (or end of string).
            currentIndex = nextSlashIndex;
        }

        // 3. Reconstruct the simplified path.
        if (canonicalStack.isEmpty()) {
            // If the stack is empty, the path is the root directory.
            return "/";
        }

        // Join the directory names in the stack with a '/' delimiter and prefix with '/'.
        // The elements are already in the correct order (top-to-bottom of the directory structure).
        return "/" + String.join("/", canonicalStack);
    }

    // ------------------- Helper Methods for Parsing -------------------

    /**
     * Skips over any sequence of one or more consecutive forward slashes ('/').
     *
     * @param path The path string.
     * @param startIndex The index to start searching from.
     * @return The index of the first character that is NOT a slash, or the path length if the end is reached.
     */
    private int skipSlashes(String path, int startIndex) {
        int length = path.length();
        int index = startIndex;
        while (index < length && path.charAt(index) == '/') {
            index++;
        }
        return index;
    }

    /**
     * Finds the index of the next forward slash ('/') starting from a given index.
     * This marks the end of the current path component (directory name, '.', or '..').
     *
     * @param path The path string.
     * @param startIndex The index to start searching from (must be a non-slash character).
     * @return The index of the next slash, or the path length if no more slashes are found.
     */
    private int findNextSeparatorIndex(String path, int startIndex) {
        int length = path.length();
        int index = startIndex;
        // Search until a slash is found or the end of the string is reached.
        while (index < length && path.charAt(index) != '/') {
            index++;
        }
        return index;
    }
}