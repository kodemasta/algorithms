package org.bsheehan.data_structure.trie;

import org.bsheehan.BaseTest;
import org.junit.Test;

public class TrieTest  extends BaseTest {

    @Test
    public void testAddWord() throws Exception {
        super.test();
        Trie t = new Trie();

        t.addWord("dog");
        t.addWord("door");
        t.addWord("dangit");
        t.addWord("danish");

        java.util.List words = t.getWordsWithPrefix("");
        for (Object word : words) {
            System.out.println((String) word);
        }
    }

    @Test
    public void testFindWordsWithPrefix() throws Exception {
        super.test();
        Trie t = new Trie();

        t.addWord("dog");
        t.addWord("door");
        t.addWord("dangit");
        t.addWord("danish");

        java.util.List words = t.getWordsWithPrefix("do");
        for (Object word : words) {
            System.out.println((String) word);
        }
    }

    @Test
    public void testSubstringSearch() throws Exception {
        super.test();
        Trie t = new Trie();

        String str = "mississippi";

        for (int i = 0; i < str.length(); ++i) {
            t.addWord(str.substring(i));
        }

        String[] list = {"is", "sip", "bob"};
        for (String word : list) {
            System.out.println(t.getWordsWithPrefix(word));
        }
    }

}