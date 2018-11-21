package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.data_structure.hashmap.HashMap;

public class TwoSum {

    public static boolean findSumSorted(int arr[], int targetSum) {
        int left = 0;
        int right = arr.length-1;
        for (int i = 0; i < arr.length; i++) {

            if (left >= right)
                return false;

            if (arr[left] + arr[right] == targetSum)
                return true;

            if (arr[left] + arr[right] > targetSum)
                right--;
            else if (arr[left] + arr[right] < targetSum)
                left++;
        }
        return false;
    }

    public static boolean findSumUnsorted(int arr[], int targetSum) {

        HashMap<Integer, Integer> map = new HashMap<>(arr.length);

        for (int i = 0; i < arr.length; i++)
            map.put(targetSum-arr[i], i);

        for (int i = 0; i < arr.length; i++){
            if (map.contains(arr[i]))
                return true;
        }

        return false;

    }
}
