/**
 * Solution class for the "Flipping an Image" problem.
 */
class Solution {
    /**
     * Flips an image horizontally and then inverts it.
     *
     * To flip an image horizontally means that each row of the image is reversed.
     * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
     *
     * This implementation cleverly performs both operations in a single pass for each row.
     *
     * @param image The input n x n binary matrix.
     * @return The transformed matrix, modified in-place.
     */
    public int[][] flipAndInvertImage(int[][] image) {
        // Handle edge case for a null or empty image.
        if (image == null || image.length == 0) {
            return image;
        }

        // Iterate over each row in the image.
        for (int[] row : image) {
            // Use two pointers, 'low' starting from the left and 'high' from the right.
            int low = 0;
            int high = row.length - 1;

            // Process the row from the outside-in, swapping and inverting elements.
            while (low <= high) {
                // This block of code simultaneously swaps the elements at 'low' and 'high'
                // and inverts them.
                // Let original values be a = row[low] and b = row[high].
                // After the operation, we want row[low] to be (1-b) and row[high] to be (1-a).
                // The XOR operator (^) is used for inversion (x^1 is the same as 1-x for binary values).

                // Store the original value of the left element.
                int temp = row[low];
                
                // Set the left element to the inverted value of the right element.
                row[low] = row[high] ^ 1;
                
                // Set the right element to the inverted value of the original left element.
                row[high] = temp ^ 1;

                // Move the pointers towards the center.
                low++;
                high--;
            }
        }
        // Return the same image matrix, which has been modified in-place.
        return image;
    }
}
