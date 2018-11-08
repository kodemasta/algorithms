package org.bsheehan.data_structure.string.algorithm;

import org.bsheehan.BaseTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RadixSortTest extends BaseTest{

    @Test
    public void bucketTest() {
        super.test();
        String array[] = {"zxc", "aaz", "azz", "ldf", "ior", "oiw", "pwo", "aaa" };
        System.out.println(Arrays.toString(array));
        RadixSort.sortBucket(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void countingTest() {
        super.test();
        String array[] = {"zxc", "aaz", "azz", "ldf", "ior", "oiw", "pwo", "aaa" };
        System.out.println(Arrays.toString(array));
        RadixSort.sortCounting(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    public void bucketSortMsd() throws Exception {
        super.test();
        java.util.List<String> lst = new ArrayList<>( );
        Random r = new Random( );

        //final int LEN = 7;

        for( int i = 0; i < 20; i++ )
        {
            String str = "";
            int len =  1 + r.nextInt( 5 ); // between 3 and 9 characters

            for( int j = 0; j < len; j++ )
                str += (char) ( 'a' + r.nextInt( 26 ) );

            lst.add( str );
        }

        String []arr1 = new String[ lst.size( ) ];

        lst.toArray( arr1 );

        System.out.println("radix lsd lexical");
        System.out.println(Arrays.toString(arr1));

        RadixSort.sortBucketMsd(arr1, 9);
        System.out.println(Arrays.toString(arr1));
        System.out.println();
    }
}