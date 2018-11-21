package org.bsheehan.data_structure.array.algorithms.partition;

import org.bsheehan.data_structure.array.Array;

public class ThreeWayPartition {

    public static void partition(int arr[], int p1, int p2) {
        if (p1 > p2)
            throw new RuntimeException("bad values");

        int start = 0;
        int end = arr.length-1;
        //*** CHECK OUT THIS TRICK !!
        for (int i = 0; i < end;) {
            if (arr[i] < p1) {
                System.out.println(i+" swap "  + arr[i] + " " + arr[start]);
                int tmp = arr[start];
                arr[start] = arr[i];
                arr[i] = tmp;
                start++;
                ++i;
            } else if (arr[i] > p2) {
                System.out.println(i+" swap "  + arr[i] + " " + arr[end]);
                int tmp = arr[end];
                arr[end] = arr[i];
                arr[i] = tmp;
                end--;
            } else {
                ++i;
            }
            System.out.println(i+" "  + start + " " + end);
            Array.print(arr);
        }

    }

    // a[1..Lo-1]=0 &&
    // a[Lo..Mid-1]=1 &&
    // a[Hi+1..N]=2,
    // a[Mid..Hi] unknown
    public static void partition2(int a[], int loVal, int midVal)
    {

        int lo = 0, i = 0;
        int hi = a.length-1;
        while( i <= hi )
        {
            if(a[i] == loVal) // ==0
            {
                int temp = a[i];
                a[i] = a[lo];
                a[lo] = temp;
                lo++;
                i++;
            }
            else if(a[i] == midVal)  // ==1
                i++;
            else // ==2
            {
                int temp = a[hi];
                a[hi] = a[i];
                a[i] = temp;
                hi--;
            }
        }
    }
}
