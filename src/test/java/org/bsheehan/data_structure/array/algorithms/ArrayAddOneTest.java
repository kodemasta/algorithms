package org.bsheehan.data_structure.array.algorithms;

import org.bsheehan.BaseTest;
import org.bsheehan.data_structure.array.Array;
import org.junit.Test;

import javax.xml.ws.soap.Addressing;

public class ArrayAddOneTest extends BaseTest {

    @Test
    public void test() {
        super.test();

        int arr0[] = {0};
        Array.print(arr0);
        arr0 = ArrayAddOne.addOne(arr0);
        Array.print(arr0);


        int arr[] = {1,2,3};
        Array.print(arr);
        arr = ArrayAddOne.addOne(arr);
        Array.print(arr);

        int arr2[] = {8,9,9};
        Array.print(arr2);
        arr2 = ArrayAddOne.addOne(arr2);
        Array.print(arr2);

        int arr3[] = {9,9,9};
        Array.print(arr3);
        arr3 = ArrayAddOne.addOne(arr3);
        Array.print(arr3);

        int arr4[] = {1,2,3,4,5,6,7,8,9};
        Array.print(arr4);
        arr3 = ArrayAddOne.addOne(arr4);
        Array.print(arr4);
    }

}