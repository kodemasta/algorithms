package org.sheehan.algorithm.data_structures;

import org.junit.Test;
import org.sheehan.algorithm.data_structures.tree.Trie;

/**
 * Created by bob on 2/25/16.
 */
public class TrieTest {

    @Test
    public void testAddWord() throws Exception {
        Trie t = new Trie();

        t.addWord("dog");
        t.addWord("door");
        t.addWord("dangit");
        t.addWord("danish");

        java.util.List words = t.getWords("da");
        for (Object word: words){
            System.out.println((String)word);
        }
    }

    @Test
    public void testSubstringSearch() throws Exception {
        Trie t = new Trie();

        String str = "mississippi";

        for (int i=0; i< str.length();++i){
            t.addWord(str.substring(i));
        }

        String [] list = {"is","sip","bob"};
        for (String word: list){
            System.out.println(t.getWords(word));
        }
    }

    @Test
    public void testGetWords() throws Exception {




    }
}