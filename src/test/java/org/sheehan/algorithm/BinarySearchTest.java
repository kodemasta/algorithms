package org.sheehan.algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: bsheehan
 * Date: 5/19/14
 * Time: 1:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class BinarySearchTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testBinarySearch() throws Exception {
        Integer[] array = Array.createArray(100, 200, true);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }

        for (int key: array) {
            int index = BinarySearch.binarySearch(array, key, 0, array.length - 1);
            assertEquals(key, array[index].intValue());
        }
    }

    @Test
    public void testRotatedBinarySearchRecursive() throws Exception {
        Integer[] sortedArray = Array.createArray(20, 100, true);
        Array.print(sortedArray);
        Array.rotateArray(sortedArray, 5);
        Array.print(sortedArray);
        //System.out.println(sortedArray.length);

        for (int key: sortedArray) {
            int index = BinarySearch.rotatedBinarySearch(sortedArray, key, 0, sortedArray.length - 1);
            assertEquals(key, sortedArray[index].intValue());
        }
    }

}
