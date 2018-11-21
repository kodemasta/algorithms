package org.bsheehan.data_structure.array.algorithms;

public class LongestRun {
    public static int find(int arr[]) {
        int s=0;
        int e=0;

        int maxVal=Integer.MIN_VALUE;
        int maxLength = Integer.MIN_VALUE;
        int maxRange[] = new int[2];

        for (int i = 0; i < arr.length-1; ++i){
            while (i<arr.length-1 && arr[i] == arr[i+1])
                i++;

            e=i;

            if ((e-s+1) > maxLength) {
                maxLength = e-s+1;
                maxVal=arr[s];
                maxRange[0]=s;
                maxRange[1]=e;
            }

            s=e+1;
        }

        System.out.println("s to e: " + maxRange[0]+ " " + maxRange[1]);
        System.out.println("max length " + maxLength);
        System.out.println("max val " + maxVal);
        return maxVal;
    }
}
