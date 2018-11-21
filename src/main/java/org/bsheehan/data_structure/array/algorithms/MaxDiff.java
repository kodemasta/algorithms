package org.bsheehan.data_structure.array.algorithms;

public class MaxDiff {
    public static int find(Integer arr[])
    {
        int maxDiff = arr[1] - arr[0];
        int min = arr[0];

        for(int i=1; i < arr.length; i++)
        {
            if (arr[i] - min > maxDiff)
                maxDiff = arr[i] - min;
            if (arr[i] < min)
                min = arr[i];
        }
        return maxDiff;
    }
}
