package org.bsheehan.data_structure.array;

import org.bsheehan.data_structure.array.algorithms.sort.MergeSort;
import org.bsheehan.data_structure.array.algorithms.Shuffle;

import java.util.*;

public class Array {

    public enum ArrayType {
        RANDOM_UNSORTED,
        RANDOM_SORTED,
        LINEAR_SORTED,
        LINEAR_UNSORTED,
        RANDOM_UNSORTED_UNIQUE,
        RANDOM_SORTED_UNIQUE
    }

    public static int[] create(Array.ArrayType type, int bound, int size){
        return create(type, 0, bound, size);
    }


    public static int[] create(Array.ArrayType type, int start, int bound, int size){
        int arr[] = new int[size];

        switch (type) {
            case RANDOM_UNSORTED:
                Random rand = new Random();
                for (int i=0; i < size; ++i){
                    arr[i] = start + rand.nextInt(bound-start);
                }
                break;
            case RANDOM_SORTED:
                rand = new Random();
                for (int i=0; i < size; ++i){
                    arr[i] =  start + rand.nextInt(bound-start);
                }
                Arrays.parallelSort(arr);
                break;
            case LINEAR_SORTED:
                for (int i=0; i < size; ++i){
                    arr[i] = i + start;
                }
                break;
            case LINEAR_UNSORTED:
                for (int i=0; i < size; ++i){
                    arr[i] = i + start;
                }
                Shuffle.shuffle(arr);
                break;
            case RANDOM_UNSORTED_UNIQUE:
                Set<Integer> unique = new LinkedHashSet<>();
                if (bound < size)
                    throw new RuntimeException("bound < size for RANDOM_UNSORTED_UNIQUE");
                rand = new Random();
                while(unique.size() < size)
                    unique.add( start + rand.nextInt(bound-start));
                int i = 0;
                for (int val: unique){
                    arr[i++]=val;
                }
                break;
            case RANDOM_SORTED_UNIQUE:
                unique = new HashSet<>();
                if (bound < size)
                    throw new RuntimeException("bound < size for RANDOM_UNSORTED_UNIQUE");
                rand = new Random();
                while(unique.size() < size)
                    unique.add( start + rand.nextInt(bound-start));
                MergeSort.sort(arr);
                break;
        }
        return arr;
    }

    // median of medians pivot selection guarantees O(n) instead of just average (which has worst O(n2)
    // whenever arr[i] is <= pivotVal swap !!!
    public static int partition(int arr[], int pivotIndex, int lo, int hi){
        int pivotVal = arr[pivotIndex];
        Array.swap(arr, pivotIndex, hi); // save pivot at hi for later to swap back !

        // 1. save position where SMALLER value will be swapped to
        int lt = lo;
        for (int i = lo; i < hi; i++){
            // 2. if we find a smaller value than pivot at some point then store it at saved position
            if (arr[i] <= pivotVal){
                Array.swap(arr, i, lt);
                lt++; // 3. increment lt to maintain invariant that left part of array is partitioned
            }
        }
        Array.swap(arr, lt, hi); // put back in place pivot

        return lt; //partition index used for next recursion
    }

    // The values equal to the pivot are already sorted, so only the less-than and greater-than partitions need to be recursively sorted.
    // In pseudocode, the quicksort algorithm becomes
    public static int partition3way(int arr[], int pivotIndex, int lo, int hi){
        int pivotVal = arr[pivotIndex];
        Array.swap(arr, pivotIndex, hi); // save pivot at hi for later to swap back !

        // 1. save position where SMALLER value will be swapped to
        int lt = lo;
        int gt = hi;
        for (int i = lo; i < hi; i++){
            if (arr[i] < pivotVal){
                Array.swap(arr, i, lt);
                lt++; // 3. increment lt to maintain invariant that left part of array is partitioned
            } else if (arr[i] < pivotVal){
                Array.swap(arr, i, gt);
                gt--; // 3. increment lt to maintain invariant that left part of array is partitioned
            }
        }
        Array.swap(arr, lt, hi); // put back in place pivot

        return lt; //partition index used for next recursion
    }

    public static void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void print(Integer  arr[]){
        print(arr);
    }

    public static void print(int arr[]){
        System.out.print("{ ");
        Arrays.stream(arr).forEach((val)->System.out.print(val + " "));
        System.out.println('}');
    }

    public static boolean isSorted(int arr[], boolean ascending){
        final int[] last = {Integer.MIN_VALUE};
        if (ascending) {
            return Arrays.stream(arr).allMatch(x -> {
                boolean b = x >= last[0];
                last[0] = x;
                return b;
            });
        } else {
            return Arrays.stream(arr).allMatch(x -> {
                boolean b = x <= last[0];
                last[0] = x;
                return b;
            });
        }
    }

    public static void shiftLeft(int[] arr, int pos){

        for (int i = pos; i<arr.length-1; ++i) {
            arr[i] = arr[i+1];
        }
    }

    public static void shiftRight(int[] arr, int pos){

        for (int i = arr.length-1; i>pos; i--) {
            arr[i] = arr[i-1];
        }
    }

    public static int[] addFront(int arr[], int val){
        int copy[] = Arrays.copyOf(arr, arr.length+1);
        shiftRight(copy, 0);
        copy[0] = val;

        return copy;
    }

    public static int[] removeFront(int arr[]){

        shiftLeft(arr, 0);
        int copy[] = Arrays.copyOf(arr, arr.length-1);

        return copy;
    }

    public static int[] insertAt(int arr[], int pos, int val){
        int copy[] = Arrays.copyOf(arr, arr.length+1);
        shiftRight(copy, pos);
        copy[pos] = val;

        return copy;
    }

    public static int[] removeAt(int arr[], int pos){

        shiftLeft(arr, pos);
        int copy[] = Arrays.copyOf(arr, arr.length-1);

        return copy;
    }

    // shift by one and swap
    public static void rotateRight(int[] arr, int n){
        for (int i = 0; i < n; i++) {
            int tmp = arr[arr.length-1];
            shiftRight(arr, 0); // shift by 1
            arr[0] = tmp;
        }
    }

    // shift by one and swap
    public static void rotateLeft(int[] arr, int n){
        for (int i = 0; i < n; i++) {
            int tmp = arr[0];
            shiftLeft(arr, 0); // shift by 1
            arr[arr.length-1] = tmp;
        }
    }

    // 1. reverse entire array
    // 2. reverse each sub array at shift pos
    public static void rotateRight2(int arr[], int shift) {
        shift %= arr.length;

        //reverse entire array
        reverse(arr, 0, arr.length - 1);
        //reverse again first sub array
        reverse(arr, 0, shift - 1);
        //reverse again second sub array
        reverse(arr, shift, arr.length - 1);
    }

    public static void reverse(int[] arr, int start, int end){
        for (int i = start, j = 0; i <= start+(end-start)/2; i++,j++) {
            swap(arr, i, end-j);
        }
    }
}
