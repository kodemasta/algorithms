package org.bsheehan.algorithms.backtracking;

import org.bsheehan.BaseTest;
import org.junit.Test;

import java.util.List;

public class RobotPathsTest extends BaseTest {

    @Test
    public void test() {
        super.test();

        List<String> paths = RobotPaths.findPathsRecurse(1);
        for (String path: paths)
            System.out.println(path.toString());
        System.out.println("Total Paths:" + paths.size());
        System.out.println("Total Paths:" + RobotPaths.findPathNumberDp(1,1));
        System.out.println();

        paths = RobotPaths.findPathsRecurse(2);
        for (String path: paths)
            System.out.println(path.toString());
        System.out.println("Total Paths:" + paths.size());
        System.out.println("Total Paths:" + RobotPaths.findPathNumberDp(2,2));
        System.out.println();

        paths = RobotPaths.findPathsRecurse(3);
        for (String path: paths)
            System.out.println(path.toString());
        System.out.println("Total Paths:" + paths.size());
        System.out.println("Total Paths:" + RobotPaths.findPathNumberDp(3,3));
        System.out.println();

        paths = RobotPaths.findPathsRecurse(4);
        for (String path: paths)
            System.out.println(path.toString());
        System.out.println("Total Paths:" + paths.size());
        System.out.println("Total Paths:" + RobotPaths.findPathNumberDp(4,4));
        System.out.println();
    }
}