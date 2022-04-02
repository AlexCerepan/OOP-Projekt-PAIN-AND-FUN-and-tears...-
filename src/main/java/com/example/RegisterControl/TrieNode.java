package com.example.RegisterControl;

public class TrieNode {
    int sizeOfAlphabet = 93;
    boolean isEndOfWord;
    TrieNode[] children = new TrieNode[sizeOfAlphabet];
    public TrieNode () {
        isEndOfWord = false;
        for (int i = 0; i < sizeOfAlphabet; i++){
            children[i] = null;
        }
    }

}
