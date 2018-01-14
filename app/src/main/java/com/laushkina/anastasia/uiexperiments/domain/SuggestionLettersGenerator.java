package com.laushkina.anastasia.uiexperiments.domain;

import java.util.Random;

public class SuggestionLettersGenerator {

    private static Character[] alphabet;
    private Random random;

    public SuggestionLettersGenerator(){
        random = new Random();
        alphabet = new Character[] {'A', 'E', 'I', 'O', 'Y',
                                    'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
                                    'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Z'};
    }

    public Character[] getCharacterSuggestions(Character trueLetter, int amountOfSuggestions) {
        Character[] result = new Character[amountOfSuggestions];

        // Insert true letter into array of suggestions
        int indexOfTrueLetter = random.nextInt(amountOfSuggestions);
        result[indexOfTrueLetter] = trueLetter;

        //Fill rest of array with random values
        for (int i = 0; i < amountOfSuggestions; i++) {
            if (i == indexOfTrueLetter) continue;

            result[i] = getRandomLetter();
        }
        return result;
    }

    private Character getRandomLetter(){
        return alphabet[random.nextInt(alphabet.length)];
    }
}
