package org.sheehan.algorithm;

import org.junit.Assert;
import org.junit.Test;
import org.sheehan.algorithm.data_structures.array.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayTest {

    @Test
    public void testRemoveElements() {
        Integer array1[] = {1,2,4,4,4,5};
        Array.print(array1);
        int len =  Array.removeElements(array1, 4);
        for (int i =0; i < len; ++i)
            System.out.print(array1[i] + " ");
        System.out.println();

        Integer array2[] = {4,4,4};
        Array.print(array2);
        len = Array.removeElements(array2, 4);
        for (int i =0; i < len; ++i)
            System.out.print(array2[i] + " ");
        System.out.println();


        Integer array3[] = {1,2,4};
        Array.print(array3);
        len = Array.removeElements(array3, 4);
        for (int i =0; i < len; ++i)
            System.out.print(array3[i] + " ");
        System.out.println();

        Integer array4[] = {4,2,1};
        Array.print(array4);
        len = Array.removeElements(array4, 4);
        for (int i =0; i < len; ++i)
            System.out.print(array4[i] + " ");
        System.out.println();

        Integer array5[] = {4};
        Array.print(array5);
        len = Array.removeElements(array5, 4);
        for (int i =0; i < len; ++i)
            System.out.print(array5[i] + " ");
        System.out.println();

    }

    @Test
    public void testInsertAt() {
        Integer array1[] =  Array.create(Array.ArrayType.RANDOM_SORTED, 10, 10);
        Array.print(array1);

        Integer copy[] = Array.insertAt(array1, 6, 100);
        Array.print(copy);

        Integer copy2[] = Array.removeAt(copy, 6);
        Array.print(copy2);

    }

    @Test
    public void testRotate90() {
        final Integer N = 5;
        Integer [][]array = new Integer[N][N];

        int cnt = 0;
        for (int i = 0; i<N; ++i){
            for (int j = 0; j<N; ++j){
                array[i][j] = cnt++;
            }
        }
        print2DArray(array);
        Array.rotateCW90(array);
        print2DArray(array);
    }

    void print2DArray(Integer array[][]){
        for (int i = 0; i<array.length; ++i){
            for (int j = 0; j<array[i].length; ++j){
                System.out.print(String.format("%2d ", array[i][j]));
            }
            System.out.println();
        }

    }



        @Test
    public void testMaxDiff() {
        Integer array1[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 10);
        Array.print(array1);
        System.out.print(Array.maxDiff(array1));
        array1 = Array.create(Array.ArrayType.RANDOM_SORTED, 5, 100);
        Array.print(array1);
        System.out.print(Array.maxDiff(array1));
    }

    @Test
    public void testKnuthShuffle() {
        Integer array1[] = Array.create(Array.ArrayType.RANDOM_SORTED_UNIQUE, 100, 100);
        Array.print(array1);
        Array.shuffle(array1);
    }

    @Test
    public void testFindLongestRun() throws Exception {

        Integer array[] = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, 6, 7, 8, 9, 10, 10};
        //Arrays.stream(array).forEach(System.out::print);
       // System.out.println();
        //IntStream.rangeClosed(1, array.length).forEach(System.out::print);
        Assert.assertEquals(5, Array.findLongestRun(array));

        Integer array2[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 7, 8, 9, 10, 10};
        Array.findLongestRun(array2);
        Assert.assertEquals(1, Array.findLongestRun(array2));

        Integer array3[] = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 7, 8, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        Array.findLongestRun(array3);
        Assert.assertEquals(10, Array.findLongestRun(array3));

    }

    @Test
    public void testFindLongestRisingRun() throws Exception {

        Integer array[] = {1, 2, 3, 22, 4, 5, 4, 3, 2, 1};

        Array.print(array);
        Array.findLongestIncreasingRun(array);

        Array.rotateArray(array, 3);
        Array.print(array);
        Array.findLongestIncreasingRun(array);
    }

    @Test
    public void testFindFirstNonRepeater() throws Exception {

        Integer array[] = {1, 2, 3, 22, 4, 5, 4, 3, 2, 1};

        Array.print(array);
        Integer firstNonrepeater = Array.findFirstNonrepeater(array);
        System.out.println(firstNonrepeater);

        Array.rotateArray(array, 5);
        Array.print(array);
        firstNonrepeater = Array.findFirstNonrepeater(array);
        System.out.println(firstNonrepeater);
    }



    @Test
    public void testFindDuplicates() throws Exception {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9);

        Set<Integer> duplicates2 = Array.findDuplicatesMap(integers.toArray(new Integer[0]));

        duplicates2.forEach((Integer i) -> System.out.print(i + " "));
    }

    @Test
    public void testRemoveDuplicates() throws Exception {

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 6, 6, 7, 8, 9, 1, 2, 3);

        Integer[] integers1 = integers.toArray(new Integer[0]);

        Array.removeDuplicatesNoMem(integers1);

        for (int i: integers1)
            System.out.print(i + " ");

        integers1 = integers.toArray(new Integer[0]);

        Array.removeDuplicatesNoMem(integers1);

        System.out.println();
        for (int i: integers1)
            System.out.print(i + " ");

        integers1 = integers.toArray(new Integer[0]);

        Array.removeDuplicatesMap(integers1);

        System.out.println();
        for (int i: integers1)
            System.out.print(i + " ");

    }

    @Test
    public void testRotateArray() {
        Integer array[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 100);

        Array.print(array);
        Array.rotateArray(array, 5);

        Array.print(array);
    }

    @Test
    public void testMergeSortedArrays() {
        Integer array1[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 100);
        Integer array2[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 100);
        Array.print(array1);
        Array.print(array2);

        Integer[] merged = (Integer[]) new Integer[array1.length+array2.length];

        Array.mergeSortedArrays(array1, array2, merged);

        Array.print(merged);
    }

    @Test
    public void testTwoSum() {

//        boolean test = false;
//        while(!test){
//            Integer array1[] = Array.create(Array.ArrayType.RANDOM_SORTED, 10, 10);
//
//            test = Array.isTwoSum(array1, 80);
//            if (test){
//                Array.print(array1);
//            }
//        }


    }

//    @Test
//    public void testPermutationOfIntegers2() {
//        java.util.List<Integer> prefix = new ArrayList<Integer>();
//
//        java.util.List<Integer> array = new ArrayList<Integer>();
//        array.add(3);
//        array.add(5);
//        array.add(7);
//        array.add(9);
//        Set<java.util.List<Integer>> cache = new HashSet<java.util.List<Integer>>();
//        Array.getPermutations2(array, 0);
//
//        Array.getPermutationsArr(array.toArray(new Integer[0]), 0);
//    }

//    @Test
//    public void testPermutationOfIntegers() {
//        java.util.List<Integer> prefix = new ArrayList<Integer>();
//
//        java.util.List<Integer> array = new ArrayList<Integer>();
//        array.add(1);
//        array.add(2);
//        array.add(3);
//
//        Set<java.util.List<Integer>> cache = new HashSet<java.util.List<Integer>>();
//        Array.getPermutations(prefix, array, cache);
//
//        for (List<Integer> a : cache) {
//            for (int i : a) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
//    }

    @Test
    public void testSubArraysOfIntegers() {
        Integer array[] = {1, 2, 3};
        Set<java.util.List<Integer>> cache = new HashSet<java.util.List<Integer>>();

        Array.getSubArrays(array, cache);

        for (List<Integer> a : cache) {
            for (int i : a) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    @Test
    public void testMaxSubArrayOfIntegers() {
        Integer array[] = {1, -2, -6, 3, 5, -2, 3, 10, -3};
        Array.print(array);

        System.out.println(Array.getMaxAndMinSumSubArray(array));
       // System.out.println(Array.getMaxSubArraySum2(array));
        System.out.println(Array.getMaxSubArraySum3(array));


        System.out.println(Array.findLargestSumSequence(array));
    }

    @Test
    public void testMaxSubArrayOfIntegers2() {
        Integer array[] = {1, 2, -6, 3, 5, -9, 30, 10, -3};
        Array.print(array);

        //System.out.println(Array.getMaxSubArraySum2(array));
        System.out.println(Array.findLargestSumSequence(array));
    }



}