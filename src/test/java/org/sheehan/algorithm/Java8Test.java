package org.sheehan.algorithm;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by bsheehan on 2/16/16.
 */
public class Java8Test extends TestCase {
    @Test
    public void testStreams() {


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> integers = Java8.evenSquares(numbers, x -> x % 2 == 0, x -> x * x);
        System.out.println(integers.toString());

    }
}