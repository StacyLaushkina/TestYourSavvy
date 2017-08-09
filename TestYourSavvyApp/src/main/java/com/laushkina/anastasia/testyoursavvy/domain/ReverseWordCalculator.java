package com.laushkina.anastasia.testyoursavvy.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ReverseWordCalculator {

    public static String reverseWord(String input){
        if (input == null || input.isEmpty()) return null;

        List<ExtendedCharacter> word = restructToPositionedLetters(input);
        for (ExtendedCharacter letter : word) {
            int randomNum;
            do {
                randomNum = ThreadLocalRandom.current().nextInt(0, word.size());
            }
            while (isExist(word, randomNum));
            letter.setNewPosition(randomNum);
        }
        return getStringFromPositionedLetters(word);
    }

    private static boolean isExist(List<ExtendedCharacter> word, int position){
        for (ExtendedCharacter letter : word) {
            if (letter.getNewPosition() == position) return true;
        }
        return false;
    }

    private static List<ExtendedCharacter> restructToPositionedLetters(String input) {
        List<ExtendedCharacter> result = new LinkedList<>();
        int counter = 0;
        for (Character letter : input.toCharArray()) {
            result.add(new ExtendedCharacter(counter, -1, letter));
            counter = counter + 1;
        }
        return result;
    }

    private static String getStringFromPositionedLetters(List<ExtendedCharacter> positionedLetters){
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(positionedLetters, new Comparator<ExtendedCharacter>() {
            @Override
            public int compare(ExtendedCharacter lhs, ExtendedCharacter rhs) {
                return lhs.getNewPosition() > rhs.getNewPosition() ? -1 : (lhs.getNewPosition() < rhs.getNewPosition() ) ? 1 : 0;
            }
        });
        for (ExtendedCharacter letter : positionedLetters) {
            stringBuilder.append(letter.getValue());
        }
        return stringBuilder.toString();
    }

    // TODO use when drag-and-drop will be implemented
    private static List<Character> getListOfCharacters(List<ExtendedCharacter> positionedLetters){
        List<Character> characters = new ArrayList<>();
        Collections.sort(positionedLetters, new Comparator<ExtendedCharacter>() {
            @Override
            public int compare(ExtendedCharacter lhs, ExtendedCharacter rhs) {
                return lhs.getNewPosition() > rhs.getNewPosition() ? -1 : (lhs.getNewPosition() < rhs.getNewPosition() ) ? 1 : 0;
            }
        });
        for (ExtendedCharacter letter : positionedLetters) {
            characters.add(letter.getValue());
        }
        return characters;
    }
}
