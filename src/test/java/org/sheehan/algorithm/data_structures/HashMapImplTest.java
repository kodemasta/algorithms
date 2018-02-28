package org.sheehan.algorithm.data_structures;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Random;

/**
 * Created by bsheehan on 2/22/16.
 */
public class HashMapImplTest extends TestCase {

    @Test
    public void testIntegerHashMap(){

        HashMapImpl<Integer, Integer> map = new HashMapImpl<>();

        for (int i = 0; i < 16; ++i){
            map.put(i,i*10);
        }
        for (int i = 16; i < 32; ++i){
            map.put(i,i*10);
        }
        for (int i = 16; i < 32; ++i){
            map.put(i,i*100);
        }
        map.print();


        map.remove(10);
        map.remove(25);
        map.remove(26);
        map.remove(9);
        map.print();

    }

    @Test
    public void testStringHashMap(){

        HashMapImpl<String, Integer> map = new HashMapImpl<>();

        Random r = new Random();

        for (int i = 0; i < 100; ++i){
            map.put(new Integer(r.nextInt()).toString(), i);
        }



        map.print();


    }


}