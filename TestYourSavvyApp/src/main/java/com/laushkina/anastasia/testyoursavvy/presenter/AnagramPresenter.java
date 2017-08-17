package com.laushkina.anastasia.testyoursavvy.presenter;

import android.content.Context;

import com.laushkina.anastasia.testyoursavvy.db.StatisticsRepository;
import com.laushkina.anastasia.testyoursavvy.db.WordsDictionary;
import com.laushkina.anastasia.testyoursavvy.domain.Statistics;

public class AnagramPresenter {
    private String trueWord;

    public AnagramPresenter() {
        WordsDictionary.getInstance().reset();
        trueWord =  WordsDictionary.getInstance().getNext();
    }

    public String getTrueWord(){
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
