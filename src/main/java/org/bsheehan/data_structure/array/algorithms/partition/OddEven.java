package org.bsheehan.data_structure.array.algorithms.partition;

public class OddEven {

    public static int partition(int arr[]){
        int left = 0;
        int right = arr.length-1;

        for (int i = 0; i < arr.length; ++i){
            while (arr[left]%2==0 ){
                ++left;
            }
            while (arr[right]%2==1 ){
                --right;
            }

            if (left < right){
                int tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
            }
        }

        return right;
    }
}
