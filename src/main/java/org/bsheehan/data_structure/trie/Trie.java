package org.bsheehan.data_structure.trie;

/**
This is an implementation of a java trie. It is a tree structure representing words. Each node in the tree represents a single character, as shown below;

Trie with the words car, cat and dog added.
               root
              /    \
             c      d
            /         \
           a           o
          /  \          \
         r    t          g

 The trie can be searched by prefix, returning a list of words which begin with the prefix.
 There are two classes, Trie and TrieNode. I've done a moderate amount of testing, but any improvements welcome.
*/

import java.util.ArrayList;
import java.util.List;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    public void addWord(String word) {
        root.addWord(word.toLowerCase());
    }

    // Find the node which represents the last letter of the prefix
    // Return the words which eminate from the last node
    // could be empty list
    public List getWordsWithPrefix(String prefix) {
        TrieNode lastNodeOfPrefix = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNodeOfPrefix = lastNodeOfPrefix.getNode(prefix.charAt(i));

            //If no node matches, then no words exist, return empty list
            if (lastNodeOfPrefix == null)
                return new ArrayList();
        }

        //Return the words which eminate from the last node
        return lastNodeOfPrefix.getFullWords();
    }

    // ---------------------------------------------------------------------------------
    class TrieNode {
        private TrieNode parent;
        private TrieNode[] children;
        private boolean isLeaf;     // Quick way to check if any children exist
        private boolean isWord;     // Does this node represent the last character of a word
        private char character;     // The character this node represents

        public TrieNode() {
            children = new TrieNode[26]; // 26 alphanumeric chars
            isLeaf = true;
            isWord = false;
        }

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
            int childNodePos = word.charAt(0) - 'a';

            // init child not for character if needed
            if (children[childNodePos] == null) {
                children[childNodePos] = new TrieNode(word.charAt(0));
                children[childNodePos].parent = this;
            }

            // recursive call to add word one char at a time !
            if (word.length() > 1) {
                children[childNodePos].addWord(word.substring(1));
            } else {
                children[childNodePos].isWord = true;
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
         * recursive
         * find all children marked as words then use top down toString to get word and add to list
         */
        protected List getFullWords() {
            //Create a list to return
            List words = new ArrayList();

            //If this node represents a word, add it
            if (isWord) {
                words.add(toString());
            }

            // recurse into children
            if (!isLeaf) {
                //Add any words belonging to any children
                for (int i = 0; i < children.length; i++) {
                    if (children[i] != null) {
                        words.addAll(children[i].getFullWords());

                    }
                }
            }
            return words;
        }



        // return word built from top down to this node (partial or complete word at node depth)
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




