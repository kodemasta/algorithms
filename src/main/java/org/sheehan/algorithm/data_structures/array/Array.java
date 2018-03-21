package org.sheehan.algorithm.data_structures.array;

import java.util.*;

public class Array {

    public enum ArrayType {
        RANDOM_UNSORTED, RANDOM_SORTED, LINEAR_SORTED, LINEAR_UNSORTED,
        RANDOM_UNSORTED_UNIQUE, RANDOM_SORTED_UNIQUE
    }

    public static Integer[] create(ArrayType type, int bound, int size){
        Integer []array = new Integer[size];

        Set<Integer> unique = new HashSet<>();
        switch (type) {
            case RANDOM_UNSORTED:
                Random rand = new Random();
                for (int i=0; i < size; ++i){
                    array[i] = rand.nextInt(bound);
                }
                return array;
            case RANDOM_SORTED:
                rand = new Random();
                for (int i=0; i < size; ++i){
                    array[i] = rand.nextInt(bound);
                }
                Arrays.parallelSort(array);
                return array;
            case LINEAR_SORTED:
                for (int i=0; i < size; ++i){
                    array[i] = i;
                }
                return array;
            case LINEAR_UNSORTED:
                for (int i=0; i < size; ++i){
                    array[i] = i;
                }
                List<Integer> list = new ArrayList<>();
                Collections.addAll(list, array);
                Collections.shuffle(list);
                list.toArray(array);
                return array;
            case RANDOM_UNSORTED_UNIQUE:
                rand = new Random();
                while(unique.size() < size)
                    unique.add(rand.nextInt(bound));
                return unique.toArray(array);
            case RANDOM_SORTED_UNIQUE:
                rand = new Random();
                while(unique.size() < size)
                    unique.add(rand.nextInt(bound));

                Arrays.parallelSort(unique.toArray(array));
                return array;

        }

        return null;
    }

    public static void print(Integer array[]){
        System.out.print("{ ");
        Arrays.stream(array).forEach((val)->System.out.print(val + " "));
        System.out.println('}');
    }

    public static boolean isSorted(Integer array[]){
        int[] last = {Integer.MIN_VALUE};
        return Arrays.stream(array).allMatch(x -> {
            boolean b = x >= last[0]; last[0] = x; return b;
        });
    }

    public static int partition(Integer array[], int firstIndex, int lastIndex){
        int pivotIndex = firstIndex + new Random().nextInt(lastIndex-firstIndex);
        return partition2(array, pivotIndex, firstIndex, lastIndex);
    }

    public static int partition(Integer array[], int pivotIndex, int firstIndex, int lastIndex){
        int pivot = array[pivotIndex];
        int left = firstIndex;
        int right = lastIndex;
        while(left < right){
            while (left < array.length && array[left] <= pivot ){
                ++left;
            }
            while (right >= 0 && array[right] > pivot){
                --right;
            }
            if (left < right){
                swap(array, left, right);
                if (left > 0) left--;
                if (right < lastIndex) right++;
            }
        }

        return right;

    }

    // median of medians pivot selection guarantees O(n) instead of just average (which has worst O(n2)
    public static int partition2(Integer array[], int pivotIndex, int firstIndex, int lastIndex){
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, lastIndex); // save pivot

        // only swap to left if smaller than pivot
        int storeIndex = firstIndex;
        for (int i = firstIndex; i < lastIndex; i++){
            if (array[i] <= pivot){
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }

        swap(array, storeIndex, lastIndex); // put back in place pivot

        return storeIndex;

    }

    public static int partitionOddEven(Integer array[], int firstIndex, int lastIndex){
        int left = firstIndex;
        int right = lastIndex;
        while(left < right){
            while (left < array.length && array[left]%2==0 ){
                ++left;
            }
            while (right >= 0 && array[right]%2==1){
                --right;
            }
            if (left < right){
                swap(array, left++, right--);
            }
        }

        return right;

    }

    public static void swap(Integer[] array, int i, int j) {
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
    }

    public static Integer[] merge(Integer[] array1, Integer[] array2) {
        Integer merged[] = new Integer[array1.length + array2.length];

        int cnt1 = 0, cnt2 = 0;
        int index = 0;

        while(cnt1 < array1.length && cnt2 < array2.length){
            if (array1[cnt1] < array2[cnt2])
                merged[index++] = array1[cnt1++];
            else
                merged[index++] = array2[cnt2++];
        }

        while(cnt1 < array1.length ){
            merged[index++] = array1[cnt1++];
        }

        while(cnt2 < array2.length ){
            merged[index++] = array2[cnt2++];
        }
        return merged;

    }

    public static int[] longestRun(Integer[] array) {
        int val = array[0];
        int maxLength = 0;
        int maxVal = array[0];
        for (int i = 1; i < array.length; ++i){
            int cnt = 1;
            while (val == array[i]) {
                ++i;
                ++cnt;
            }
            if (cnt > maxLength) {
                maxLength = cnt;
                maxVal = array[i-1];
            }
            val = array[i];
        }

        int soln[] = {maxVal,maxLength};

        return soln;
    }
}
