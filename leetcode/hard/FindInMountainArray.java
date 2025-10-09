/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */

class Solution {
    /**
     * Finds the index of the target value in a mountain array.
     *
     * The algorithm consists of three main steps:
     * 1. Find the peak of the mountain array. The peak is the element with the largest value.
     * 2. Perform a binary search for the target in the increasing part of the array (from the start to the peak).
     * 3. If the target is not found, perform another binary search for the target in the decreasing part of the array (from the peak to the end).
     *
     * @param target The integer value to search for.
     * @param mountainArr An object that represents the mountain array and provides `get` and `length` methods.
     * @return The index of the target value if found, otherwise -1.
     *
     * Time Complexity: O(log N), where N is the length of the mountain array, due to three binary searches.
     * Space Complexity: O(1).
     */
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int length = mountainArr.length();

        // 1. Find the peak of the mountain array.
        int low = 0;
        int high = length - 1;
        int peak = -1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        peak = low;

        // 2. Search for the target in the increasing part of the array (from the start to the peak).
        low = 0;
        high = peak;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int m = mountainArr.get(mid);
            if (m == target) {
                return mid;
            } else if (m < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // 3. If not found, search for the target in the decreasing part of the array (from the peak to the end).
        low = peak;
        high = length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int m = mountainArr.get(mid);
            if (m == target) {
                return mid;
            } else if (m > target) {
                // In a decreasing array, if the mid element is greater than the target,
                // the target must be in the right part of the array.
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Target was not found in either part of the array.
        return -1;
    }
}
