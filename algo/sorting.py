from typing import List


def bubble_sort(arr: List[int]) -> List[int]:
    """
    Implements the Bubble Sort algorithm.

    In each pass, it compares adjacent elements and swaps them if they are in the
    wrong order. The largest unsorted element "bubbles" to its correct position
    at the end of the array. This implementation returns a new sorted list.

    Time Complexity: O(n^2) in the average and worst cases. O(n) in the best case
                     (if the array is already sorted and an optimization is used).
    Space Complexity: O(n) to store the copy of the array. The sort itself is O(1) in-place on the copy.
    """
    n = len(arr)
    arr_copy = arr[:]  # Create a copy to avoid modifying the original list
    for i in range(n):
        # A flag to optimize if the inner loop doesn't make any swaps
        swapped = False
        for j in range(0, n - i - 1):
            if arr_copy[j] > arr_copy[j + 1]:
                arr_copy[j], arr_copy[j + 1] = arr_copy[j + 1], arr_copy[j]
                swapped = True
        # If no two elements were swapped by inner loop, then break
        if not swapped:
            break
    return arr_copy


def selection_sort(arr: List[int]) -> List[int]:
    """
    Implements the Selection Sort algorithm.

    In each pass, it finds the minimum element from the unsorted part of the
    array and swaps it with the element at the beginning of the unsorted part.
    This implementation returns a new sorted list.

    Time Complexity: O(n^2) in all cases (best, average, and worst).
    Space Complexity: O(n) to store the copy of the array. The sort itself is O(1) in-place on the copy.
    """
    n = len(arr)
    arr_copy = arr[:]  # Create a copy to avoid modifying the original list
    for i in range(n):
        # Find the minimum element in the remaining unsorted array
        min_idx = i
        for j in range(i + 1, n):
            if arr_copy[j] < arr_copy[min_idx]:
                min_idx = j
        # Swap the found minimum element with the first element
        arr_copy[i], arr_copy[min_idx] = arr_copy[min_idx], arr_copy[i]
    return arr_copy


def insertion_sort(arr: List[int]) -> List[int]:
    """
    Implements the Insertion Sort algorithm.

    It builds the final sorted array one item at a time. It iterates through the
    input elements and inserts each element into its correct position in the
    sorted part of the array. This implementation returns a new sorted list.

    Time Complexity: O(n^2) in the average and worst cases. O(n) in the best case
                     (if the array is already sorted).
    Space Complexity: O(n) to store the copy of the array. The sort itself is O(1) in-place on the copy.
    """
    arr_copy = arr[:]  # Create a copy to avoid modifying the original list
    for i in range(1, len(arr_copy)):
        key = arr_copy[i]
        j = i - 1
        # Move elements of arr[0..i-1], that are greater than key,
        # to one position ahead of their current position
        while j >= 0 and key < arr_copy[j]:
            arr_copy[j + 1] = arr_copy[j]
            j -= 1
        arr_copy[j + 1] = key
    return arr_copy


def merge_sort(arr: List[int]) -> List[int]:
    """
    Implements the Merge Sort algorithm using a recursive approach.

    It's a divide-and-conquer algorithm. It divides the input array into two
    halves, calls itself for the two halves, and then merges the two sorted halves.

    Time Complexity: O(n log n) in all cases (best, average, and worst).
    Space Complexity: O(n) due to the space required for the temporary arrays
                      during the merge process.
    """
    if len(arr) <= 1:
        return arr

    mid = len(arr) // 2
    left_half = arr[:mid]
    right_half = arr[mid:]

    # Recursive calls to sort the two halves
    sorted_left = merge_sort(left_half)
    sorted_right = merge_sort(right_half)

    # Merge the sorted halves
    merged_arr = []
    left_ptr, right_ptr = 0, 0
    while left_ptr < len(sorted_left) and right_ptr < len(sorted_right):
        if sorted_left[left_ptr] < sorted_right[right_ptr]:
            merged_arr.append(sorted_left[left_ptr])
            left_ptr += 1
        else:
            merged_arr.append(sorted_right[right_ptr])
            right_ptr += 1

    # Append remaining elements
    merged_arr.extend(sorted_left[left_ptr:])
    merged_arr.extend(sorted_right[right_ptr:])

    return merged_arr


def quick_sort(arr: List[int]) -> List[int]:
    """
    Implements the Quick Sort algorithm.

    It's a divide-and-conquer algorithm. It picks an element as a pivot and
    partitions the given array around the picked pivot. This is a simple,
    non-in-place implementation for clarity.

    Time Complexity: O(n log n) on average, O(n^2) in the worst case.
    Space Complexity: O(n) in the worst case due to list comprehensions and recursion depth.
    """
    if len(arr) <= 1:
        return arr
    else:
        pivot = arr[len(arr) // 2]
        left = [x for x in arr if x < pivot]
        middle = [x for x in arr if x == pivot]
        right = [x for x in arr if x > pivot]
        return quick_sort(left) + middle + quick_sort(right)


def heap_sort(arr: List[int]) -> List[int]:
    result = arr[:]
    size = len(result)
    def heapify(heap: List[int], parent: int, right_bound: int):
            while True:
                largest = parent
                left = parent * 2 + 1
                right = parent * 2 + 2
                
                if right <= right_bound and heap[largest] < heap[right]:
                    largest = right
                
                if left <= right_bound and heap[largest] < heap[left]:
                    largest = left
                
                if parent == largest:
                    break

                heap[parent], heap[largest] = heap[largest], heap[parent]
                parent = largest
    
    for i in range(size // 2 - 1, -1, -1):
        heapify(result, i, size - 1)
    
    for i in range(size - 1, 0, -1):
        result[0], result[i] = result[i], result[0]
        heapify(result, 0, i - 1)

    return result
