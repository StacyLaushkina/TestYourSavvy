package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.db.WordsDictionary;
import com.laushkina.anastasia.uiexperiments.domain.Statistics;

public class AnagramPresenter {

    private String trueWord;

    public AnagramPresenter(Context context) {
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfCorrect = currentStatistics.getAnagramResults().getAmountOfCorrectAnswers();
        WordsDictionary.getInstance().setIndexOfLast(amountOfCorrect == null ? -1 : amountOfCorrect - 1);
    }

    public String getTrueWord(){
       return trueWord;
    }

    public String getNextWord(){
        trueWord = WordsDictionary.getInstance().getNext();
        return trueWord;
    }

    public void saveFailure(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getAnagramResults().getAmountOfAssumptions();
        currentStatistics.getAnagramResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }

    public void saveSuccess(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getAnagramResults().getAmountOfAssumptions();
        Integer amountOfCorrect= currentStatistics.getAnagramResults().getAmountOfCorrectAnswers();
        currentStatistics.getAnagramResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        currentStatistics.getAnagramResults().setAmountOfCorrectAnswers(amountOfCorrect + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }
}
