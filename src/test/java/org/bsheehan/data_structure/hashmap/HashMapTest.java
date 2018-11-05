package org.bsheehan.data_structure.hashmap;

import org.bsheehan.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class HashMapTest extends BaseTest {

    @Test
    public void test() {
        super.test();
        Random r = new Random();
        final int cacheSize = 15;
        final int dataSize = 60;

        Map<String,Integer> testMap = new LinkedHashMap<>();
        HashMap<String, Integer> map = new HashMap<String, Integer>(cacheSize);
        for (int i =0; i < dataSize; ++i) {
            String key = Character.toString((char)('a' + r.nextInt(25))) + Character.toString((char)('a' + r.nextInt(25)))+ Character.toString((char)('a' + r.nextInt(25)));
            testMap.put(key, i);
            map.put(key , i);
        }

        map.print();

        Iterator<String> iterator = testMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            Assert.assertEquals(map.get(key), testMap.get(key));
            System.out.println("removing :" + key + " "  + map.remove(key));
            map.print();
        }

        map.clear();
        map.print();
    }
}