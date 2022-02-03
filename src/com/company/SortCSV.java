/* Implementations of the various algorithms as found in the book Introduction to Algorithms */
package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortCSV {

    //--BEGIN QUICKSORT--//
    public void quicksort(ArrayList<Long> list, int first, int last) {
        int left, right;
        Long pivot;
        left = first;
        right = last;

        // Base Case for recursion
        if (first >= last) {
            return;
        }
        pivot = list.get(((first + last) / 2));
        do {
            while (list.get(left) < pivot) {
                left++;
            }

            while (list.get(right) > pivot) {
                right--;
            }
            // swap the values
            if (left <= right) {
                // swap the values
                Collections.swap(list, left, right);
                left++;
                right--;
            }
        }
        while (left <= right);
        // Recursive call Quick Sort on the left and right sub arrays
        quicksort(list, first, right);
        quicksort(list, left, last);
    }

    //--END QUICKSORT--//

    //--BEGIN HEAPSORT--//
    public void heapsort(ArrayList<Long> list) {
        int heapSize = list.size();

        // Build heap (rearrange array)
        for (int i = heapSize / 2 - 1; i >= 0; i--)
            maxHeapify(list, heapSize, i);

        // One by one extract an element from heap
        for (int i = heapSize - 1; i > 0; i--) {
            // Move current root to end
            Collections.swap(list, 0, i);

            // call max heapify on the reduced heap
            maxHeapify(list, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    void maxHeapify(ArrayList<Long> list, int heapSize, int i) {
        int max = i; // Initialize largest as root
        int leftNode = (2 * i) + 1; // left = 2*i + 1
        int rightNode = (2 * i) + 2; // right = 2*i + 2

        // If left child is larger than root
        if (leftNode < heapSize && list.get(leftNode) > list.get(max))
            max = leftNode;

        // If right child is larger than largest so far
        if (rightNode < heapSize && list.get(rightNode) > list.get(max))
            max = rightNode;

        // If largest is not root
        if (max != i) {
            Collections.swap(list, i, max);

            // Recursively heapify the affected sub-tree
            maxHeapify(list, heapSize, max);
        }
    }
    //--END HEAPSORT--//

    //--BEGIN RADIX SORT--//
    public static void radixsort(Long[] list, int n) {
        Long m = getMaxNum(list, n);
        for (Long exp = (long) 1; m / exp > 0; exp *= 10)
            countingSort(list, n, exp);
    }

    private static Long getMaxNum(Long[] arr, int n) {
        Long maxNum = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > maxNum)
                maxNum = arr[i];
        return maxNum;
    }

    private static void countingSort(Long[] list, int n, Long exp) {
        Long[] output = new Long[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++)
            count[(int) ((list[i] / exp) % 10)] = count[(int) ((list[i] / exp) % 10)] + 1;

        for (int i = 1; i < 10; i++)
            count[i] = count[i] + count[i - 1];

        for (int i = n - 1; i >= 0; i--) {
            output[count[(int) ((list[i] / exp) % 10)] - 1] = list[i];
            count[(int) ((list[i] / exp) % 10)] = count[(int) ((list[i] / exp) % 10)] - 1;
        }

        for (int i = 0; i < n; i++) {
            list[i] = output[i];
        }
    }
    //--END RADIX SORT--//

}

