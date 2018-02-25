package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.db.WordsDictionary;
import com.laushkina.anastasia.uiexperiments.domain.stats.Statistics;
import com.laushkina.anastasia.uiexperiments.domain.SuggestionLettersGenerator;
import com.laushkina.anastasia.uiexperiments.domain.stats.StatisticsType;

import java.util.Random;

public class DragAndDropPresenter {

    private static final int amountOfSuggestionLetters = 4;

    private String trueWord;
    private Random random;
    private SuggestionLettersGenerator suggestionLettersGenerator;

    public DragAndDropPresenter(Context context) {
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfCorrect = currentStatistics.getResults(StatisticsType.DragAndDrop).getAmountOfCorrectAnswers();
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

    // TODO refactor
    public void saveSuccess(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getResults(StatisticsType.DragAndDrop).getAmountOfAssumptions();
        Integer amountOfCorrect= currentStatistics.getResults(StatisticsType.DragAndDrop).getAmountOfCorrectAnswers();
        currentStatistics.getResults(StatisticsType.DragAndDrop).setAmountOfAssumptions(amountOfAssumptions + 1);
        currentStatistics.getResults(StatisticsType.DragAndDrop).setAmountOfCorrectAnswers(amountOfCorrect + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }

    public void saveFailure(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getResults(StatisticsType.DragAndDrop).getAmountOfAssumptions();
        currentStatistics.getResults(StatisticsType.DragAndDrop).setAmountOfAssumptions(amountOfAssumptions + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }
}
