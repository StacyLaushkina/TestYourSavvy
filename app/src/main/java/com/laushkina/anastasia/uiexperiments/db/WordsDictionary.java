package com.laushkina.anastasia.uiexperiments.db;

import java.util.ArrayList;
import java.util.List;

public class WordsDictionary {
    public static final int wordLength = 5;
    public static final int wordsCount = 6;
    private List<String> words;
    private int indexOfLast;
    private static WordsDictionary instance;

    private WordsDictionary() {
        this.words = new ArrayList<>();
        this.words.add("BRAVE");
        this.words.add("PIECE");
        this.words.add("DREAM");
        this.words.add("TRUST");
        this.words.add("WORLD");
        this.words.add("CLOCK");

        indexOfLast = -1;
    }

    public static synchronized WordsDictionary getInstance() {
        if (instance == null) {
            instance = new WordsDictionary();
        }
        return instance;
    }

    public synchronized String getNext(){
        indexOfLast += 1;
        if (indexOfLast >= words.size()) {
            indexOfLast = 0;
        }
        return words.get(indexOfLast);
    }

    public void setIndexOfLast(int indexOfLast){
        this.indexOfLast = indexOfLast;
    }

    public void reset() {
        this.indexOfLast = -1;
    }
}
