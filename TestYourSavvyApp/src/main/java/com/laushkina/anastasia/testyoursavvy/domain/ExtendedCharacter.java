package com.laushkina.anastasia.testyoursavvy.domain;

public class ExtendedCharacter {
    private int originalPosition;
    private int newPosition;
    private Character value;

    ExtendedCharacter(int originalPosition, int newPosition, Character value){
        this.originalPosition = originalPosition;
        this.newPosition = newPosition;
        this.value = value;
    }

    public void setValue(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public void setNewPosition(int newPosition) {
        this.newPosition = newPosition;
    }

    public int getNewPosition() {
        return newPosition;
    }

    public void setOriginalPosition(int originalPosition) {
        this.originalPosition = originalPosition;
    }

    public int getOriginalPosition() {
        return originalPosition;
    }
}
