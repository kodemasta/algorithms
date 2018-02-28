package org.sheehan.algorithm;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * Created by bsheehan on 2/12/16.
 */
public class IntegerAlgsTest extends TestCase {

    @Test
    public void testPascal() {
        IntegerAlgs.getPascalRow(0);
    }

    @Test
    public void testSubsets() {

        int array[] = {1, 2, 3, 4, 5};
        //ArrayList<ArrayList<Integer>> subsets = IntegerAlgs.subsets(array);
        //for (List<Integer> subset:subsets)
        //   System.out.println(subset);

        List<List<Integer>> subsets2 = IntegerAlgs.getAllSubsets(array);
        for (List<Integer> subset : subsets2)
            System.out.println(subset);

    }

    @Test
    public void testReverseInt() {

        System.out.println(IntegerAlgs.reverseInt(1234));
        System.out.println(IntegerAlgs.reverseInt(-1234));
    }

    @Test
    public void testStr2Int() throws Exception {
        System.out.println(IntegerAlgs.str2int("1234"));
        System.out.println(IntegerAlgs.str2int("-1234"));
    }

    @Test
    public void testInt2Str() throws Exception {
        System.out.println(IntegerAlgs.int2Str(1234));
        System.out.println(IntegerAlgs.int2Str(-1234));
    }

    @Test
    public void testGetPerms() throws Exception {
        Integer nums[] = {1, 1, 3};
        Set<Integer[]> perms = IntegerAlgs.getPermutationsRecursive(nums);
        for (Integer[] perm:perms) {
            for (Integer val : perm)
                System.out.print(val + " ");
            System.out.println();
        }

    }
}