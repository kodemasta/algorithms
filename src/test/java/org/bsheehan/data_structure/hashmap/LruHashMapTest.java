package org.bsheehan.data_structure.hashmap;

import org.bsheehan.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class LruHashMapTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        Random r = new Random();
        final int cacheSize = 10;
        final int dataSize = 15;


        LruHashMap<String, Integer> map = new LruHashMap<String, Integer>(cacheSize);
        map.put("oldest" , 1000);
        for (int i =0; i < dataSize; ++i) {
            map.get("oldest");
            String key = Character.toString((char)('a' + r.nextInt(25))) + Character.toString((char)('a' + r.nextInt(25)))+ Character.toString((char)('a' + r.nextInt(25)));
            map.put(key , i);
            map.print();
        }

//        map.get("oldest");
//        map.put("newest" , 1001);

        map.clear();
        map.print();
    }
}