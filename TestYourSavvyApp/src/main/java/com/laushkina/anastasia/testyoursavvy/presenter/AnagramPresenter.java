package com.laushkina.anastasia.testyoursavvy.presenter;

import android.content.Context;

import com.laushkina.anastasia.testyoursavvy.db.StatisticsRepository;
import com.laushkina.anastasia.testyoursavvy.domain.Statistics;

public class AnagramPresenter {

    public void saveFailue(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getAnagramResults().getAmountOfAssumptions();
        currentStatistics.getAnagramResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }

    public void saveSucess(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getAnagramResults().getAmountOfAssumptions();
        Integer amountOfCorrect= currentStatistics.getAnagramResults().getAmountOfCorrectAnswers();
        currentStatistics.getAnagramResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        currentStatistics.getAnagramResults().setAmountOfCorrectAnswers(amountOfCorrect + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }
}
