package org.sheehan.algorithm.data_structures;

public class Node {
    public static Integer idCnt = 0;
    public Integer id = 0;

    public Node() {
        id = idCnt++;
    }
}
