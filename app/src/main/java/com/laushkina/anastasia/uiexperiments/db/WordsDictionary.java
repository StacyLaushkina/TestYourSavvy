package com.laushkina.anastasia.uiexperiments.db;

import java.util.ArrayList;
import java.util.List;

public class WordsDictionary {
    private List<String> words;
    private int indexOfLast;
    private static WordsDictionary instance;

    private WordsDictionary() {
        this.words = new ArrayList<>();
        this.words.add("AND");
        this.words.add("BALL");
        this.words.add("PIE");
        this.words.add("FORM");
        this.words.add("GOAL");
        this.words.add("LEAF");
        this.words.add("MUST");
        this.words.add("RAIN");
        this.words.add("BRAVE");
        this.words.add("PIECE");
        this.words.add("DREAM");
        this.words.add("TRUST");
        this.words.add("FRIEND");
        this.words.add("ORANGE");
        this.words.add("BELIEVE");

        indexOfLast = -1;
    }

    public static synchronized WordsDictionary getInstance() {
        if (instance == null) {
            instance = new WordsDictionary();
        }
        return instance;
    }

    public String getNext(){
        indexOfLast += 1;
        return words.get(indexOfLast);
    }

    public void setIndexOfLast(int indexOfLast){
        this.indexOfLast = indexOfLast;
    }

    public void reset() {
        this.indexOfLast = -1;
    }
}
