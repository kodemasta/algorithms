package org.sheehan.algorithm.data_structures.tree;

/*Oracle Community Directory
    Oracle Community FAQ
    Go Directly To 

        Log in

Search
All Places > Java > Java Essentials > Java Programming > Discussions
This discussion is archived

1 Reply Latest reply: May 14, 2008 3:18 PM by 807591 RSS
Java Trie
611760 Newbie
611760 May 14, 2008 2:53 PM

This post was inspired by a question here.

This is an implementation of a java trie. It is a tree structure representing words. Each node in the tree represents a single character, as shown below;

Trie with the words car, cat and dog added.
               root
              /    \
             c      d
            /         \
           a           o
          /  \          \
         r    t          g

The trie can be searched by prefix, returning a list of words which begin with the prefix. There are two classes, Trie and TrieNode. I've done a moderate amount of testing, but any improvements welcome. If anybody has any very large lists of words I'd be interested in doing some performance testing.

*/

import java.util.ArrayList;
import java.util.List;


public class Trie {

    private TrieNode root;

    /**
     * Constructor
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Adds a word to the Trie
     *
     * @param word
     */
    public void addWord(String word) {
        root.addWord(word.toLowerCase());
    }

    /**
     * Get the words in the Trie with the given
     * prefix
     *
     * @param prefix
     * @return a List containing StringAlgs objects containing the words in
     * the Trie with the given prefix.
     */
    public List getWords(String prefix) {
        //Find the node which represents the last letter of the prefix
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getNode(prefix.charAt(i));

            //If no node matches, then no words exist, return empty list
            if (lastNode == null) return new ArrayList();
        }

        //Return the words which eminate from the last node
        return lastNode.getWords();
    }

    class TrieNode {
        private TrieNode parent;
        private TrieNode[] children;
        private boolean isLeaf;     //Quick way to check if any children exist
        private boolean isWord;     //Does this node represent the last character of a word
        private char character;     //The character this node represents

        /**
         * Constructor for top level root node.
         */
        public TrieNode() {
            children = new TrieNode[26]; // 26 alphanumeric chars
            isLeaf = true;
            isWord = false;
        }

        /**
         * Constructor for child node.
         */
        public TrieNode(char character) {
            this();
            this.character = character;
        }

        /**
         * Adds a word to this node. This method is called recursively and
         * adds child nodes for each successive letter in the word, therefore
         * recursive calls will be made with partial words.
         *
         * @param word the word to add
         */
        protected void addWord(String word) {
            isLeaf = false;
            int charPos = word.charAt(0) - 'a';

            if (children[charPos] == null) {
                children[charPos] = new TrieNode(word.charAt(0));
                children[charPos].parent = this;
            }

            if (word.length() > 1) {
                children[charPos].addWord(word.substring(1));
            } else {
                children[charPos].isWord = true;
            }
        }

        /**
         * Returns the child TrieNode representing the given char,
         * or null if no node exists.
         *
         * @param c
         * @return
         */
        protected TrieNode getNode(char c) {
            return children[c - 'a'];
        }

        /**
         * Returns a List of StringAlgs objects which are lower in the
         * hierarchy that this node.
         *
         * @return
         */
        protected List getWords() {
            //Create a list to return
            List list = new ArrayList();

            //If this node represents a word, add it
            if (isWord) {
                list.add(toString());
            }

            //If any children
            if (!isLeaf) {
                //Add any words belonging to any children
                for (int i = 0; i < children.length; i++) {
                    if (children[i] != null) {
                        list.addAll(children[i].getWords());

                    }
                }
            }
            return list;
        }


        /**
         * Gets the StringAlgs that this node represents.
         * <p/>
         * For example, if this node represents the character t, whose parent
         * <p/>
         * represents the charater a, whose parent represents the character
         * <p/>
         * c, then the StringAlgs would be "cat".
         *
         * @return
         */

        public String toString()
        {
            if (parent == null)
            {
                return "";
            } else {
                return parent.toString() + new String(new char[]{character});
            }
        }
    }


}




