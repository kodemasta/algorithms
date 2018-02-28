package org.sheehan.algorithm.hackerrank;


import java.util.*;

public class Test2Arrays {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++){
            int n = in.nextInt();
            int k = in.nextInt();

            Integer array1[] = new Integer[n];
            for (int j = 0; j < n; ++j){
                array1[j]= in.nextInt();
            }
            Integer array2[] = new Integer[n];
            for (int j = 0; j < n; ++j){
                array2[j]= in.nextInt();
            }

            Arrays.sort(array1);
            Arrays.sort(array2, Collections.reverseOrder());

            boolean valid = true;
            for (int j = 0; j < n; ++j) {
                if (array1[j] + array2[j] < k) {
                    valid = false;
                    break;
                }
            }
            if (valid)
                System.out.println("YES");
            else
                System.out.println("NO");
        }

     }


    public static void getPermutations(List<Integer> prefix, List<Integer> array, Set<List<Integer>> cache) {
        int n = array.size();
        if (n == 0) {
             cache.add(prefix);
        }
        else {
            for (int i = 0; i < n; i++) {
                List<Integer> array2 = new ArrayList<Integer>();
                array2.addAll(array.subList(0, i));
                array2.addAll(array.subList(i + 1, n));
                List<Integer> prefix2 = new ArrayList<Integer>();
                prefix2.addAll(prefix);
                prefix2.add(array.get(i));
                getPermutations(prefix2, array2, cache);
            }
        }
    }

    private static boolean testPermutations(int[] array1, int[] array2, int n, int k) {
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        for (int j = 0; j < n; ++j) {
            list1.add(array1[j]);
            list2.add(array2[j]);
        }

        List<Integer> prefix1 = new ArrayList<Integer>();
        Set<List<Integer>> cache1 = new HashSet<List<Integer>>();
        getPermutations(prefix1, list1, cache1);

        List<Integer> prefix2 = new ArrayList<Integer>();
        Set<List<Integer>> cache2 = new HashSet<List<Integer>>();
        getPermutations(prefix2, list2, cache2);

        boolean valid = true;
        for (List<Integer> list11 : cache1) {
            for (List<Integer> list22 : cache2) {
                valid = true;
                for (int j = 0; j < n; ++j) {
                    if (list11.get(j) + list22.get(j) < k) {
                        valid = false;
                        break;
                    }
                }
                if (valid)
                    return true;
            }
        }

        return false;

    }
}

/*
5
2 4
1 3
3 1
5 5
2 3 1 1 1
1 3 4 3 3
10 9
1 5 1 4 4 2 7 1 2 2
8 7 1 7 7 4 4 3 6 7
10 9
3 6 8 5 9 9 4 8 4 7
5 1 0 1 6 4 1 7 4 3
10 4
4 4 3 2 1 4 4 3 2 4
2 3 0 1 1 3 1 0 0 2

YES
NO
NO
YES
YES
1
10 9
1 5 1 4 4 2 7 1 2 2
8 7 1 7 7 4 4 3 6 7
 */
