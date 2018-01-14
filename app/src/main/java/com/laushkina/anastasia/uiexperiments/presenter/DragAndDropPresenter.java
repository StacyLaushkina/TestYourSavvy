package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.db.WordsDictionary;
import com.laushkina.anastasia.uiexperiments.domain.Statistics;
import com.laushkina.anastasia.uiexperiments.domain.SuggestionLettersGenerator;

import java.util.Random;

public class DragAndDropPresenter {

    private static final int amountOfSuggestionLetters = 4;

    private String trueWord;
    private Random random;
    private SuggestionLettersGenerator suggestionLettersGenerator;

    public DragAndDropPresenter(Context context) {
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfCorrect = currentStatistics.getDragAndDropResults().getAmountOfCorrectAnswers();
        WordsDictionary.getInstance().setIndexOfLast(amountOfCorrect == null ? -1 : amountOfCorrect - 1);
        random = new Random();
        suggestionLettersGenerator = new SuggestionLettersGenerator();
    }

    public String getTrueWord(){
       return trueWord;
    }

    public String getNextWord(){
        trueWord = WordsDictionary.getInstance().getNext();
        return trueWord;
    }

    public int getIndexOfMissing(){
        return random.nextInt(WordsDictionary.wordLength);
    }

    public Character[] getSuggestions(int indexOfMissing){
        Character missingLetter = trueWord.charAt(indexOfMissing);
        return suggestionLettersGenerator.getCharacterSuggestions(missingLetter, amountOfSuggestionLetters);
    }

    public int getAmountOfSuggestionLetters(){
        return amountOfSuggestionLetters;
    }

    public int getAnswerWordLength(){
        return WordsDictionary.wordLength;
    }

    public boolean isWordCorrect(String suggestedWord){
        return trueWord.equals(suggestedWord);
    }

    public void saveSuccess(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getDragAndDropResults().getAmountOfAssumptions();
        Integer amountOfCorrect= currentStatistics.getDragAndDropResults().getAmountOfCorrectAnswers();
        currentStatistics.getDragAndDropResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        currentStatistics.getDragAndDropResults().setAmountOfCorrectAnswers(amountOfCorrect + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }
}
