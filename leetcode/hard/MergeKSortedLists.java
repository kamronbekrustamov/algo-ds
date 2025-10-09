// class ListNode {
//     int val;
//     ListNode next;
//     ListNode() {}
//     ListNode(int val) { this.val = val; }
//     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
// }

class Solution {
    /**
     * Merges k sorted linked lists into one single sorted linked list using a
     * divide and conquer approach.
     *
     * Algorithm:
     * 1. Handle edge cases: If the input list of lists is empty or null, return null.
     * 2. The main loop continues as long as there is more than one list to merge.
     * 3. In each iteration of the main loop, we merge adjacent pairs of lists.
     *    a. Iterate through the current list of lists, taking them two at a time.
     *    b. For each pair `lists[i]` and `lists[i+1]` (if `i+1` is within bounds),
     *       call the `mergeTwoLists` helper function to merge them.
     *    c. Store the result of the merge back into the `lists` array at index `i / 2`.
     *       This effectively reduces the number of lists by half in each pass.
     * 4. Update `num_lists` to reflect the new count of lists (approximately `ceil(num_lists / 2)`).
     * 5. After the loop, the final merged list will be at `lists[0]`.
     *
     * @param lists An array of k sorted linked lists.
     * @return The head of the merged sorted linked list.
     *
     * Time Complexity: O(N log K), where N is the total number of nodes across all lists,
     *                  and K is the number of linked lists. Each merge operation takes O(length of merged list),
     *                  and there are log K levels of merging.
     * Space Complexity: O(1) (excluding the output list), or O(K) for the recursion stack if using recursive merge.
     *                   For the iterative approach with in-place modification of the `lists` array, it's O(1).
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int numLists = lists.length;

        while (numLists > 1) {
            for (int i = 0; i < numLists; i += 2) {
                ListNode list1 = lists[i];
                ListNode list2 = (i + 1) < numLists ? lists[i + 1] : null;
                lists[i / 2] = mergeTwoLists(list1, list2);
            }
            numLists = (numLists + 1) / 2;
        }

        return lists[0];
    }

    /**
     * Helper function to merge two sorted linked lists into one single sorted linked list.
     *
     * Algorithm:
     * 1. Handle base cases: if one list is null, return the other.
     * 2. Create a `dummy` node to serve as the starting point of the merged list.
     * 3. Create a `current` pointer, initialized to `dummy`, to track the tail of the merged list.
     * 4. Loop as long as both `list1` and `list2` have nodes.
     * 5. In each iteration, compare the values of the current nodes of `list1` and `list2`.
     * 6. Append the smaller node to `current.next` and advance the pointer of the list from which the node was taken.
     * 7. Move the `current` pointer to the newly added node.
     * 8. After the loop, one of the lists may still have remaining nodes. Append the
     *    non-empty list to the end of the merged list.
     * 9. Return `dummy.next`, which is the head of the merged list.
     *
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode();
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        } else if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }
}
