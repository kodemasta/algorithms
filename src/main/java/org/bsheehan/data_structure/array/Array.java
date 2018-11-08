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
                Set<Integer> unique = new HashSet<>();
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

    public static void swap(int[] array, int i, int j) {
        int temp=array[i];
        array[i]=array[j];
        array[j]=temp;
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
}
