package com.laushkina.anastasia.testyoursavvy.presenter;


import com.laushkina.anastasia.testyoursavvy.db.WordsDictionary;

public class VisionPresenter {
    private String trueWord;

    public VisionPresenter() {
        WordsDictionary.getInstance().reset();
        trueWord =  WordsDictionary.getInstance().getNext();
    }

    public String getTrueWord(){
        return trueWord;
    }
}
