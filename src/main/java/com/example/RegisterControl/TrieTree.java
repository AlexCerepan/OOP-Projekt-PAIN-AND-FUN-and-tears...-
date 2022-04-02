package com.example.RegisterControl;

public class TrieTree {

   public TrieNode rootNode = new TrieNode();

    public void insert(String key){
        int level;
        int lenght = key.length();
        int index;

        TrieNode begin = rootNode;

        for (level = 0; level < lenght; level++){

            index = key.charAt(level) - ' ';
            if(begin.children[index] == null){
                begin.children[index] = new TrieNode();
            }
            begin = begin.children[index];

        }
        begin.isEndOfWord = true;
    }

    public boolean search(String key) {
        int level;
        int lenght = key.length();
        int index;

        TrieNode begin = rootNode;

        for (level = 0; level < lenght; level++) {

            index = key.charAt(level) - ' ';
            if (begin.children[index] == null) {
                return false;
            }

            begin = begin.children[index];

        }
    return begin.isEndOfWord;
    }



}
