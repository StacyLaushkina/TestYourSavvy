package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.db.WordsDictionary;
import com.laushkina.anastasia.uiexperiments.domain.stats.Statistics;
import com.laushkina.anastasia.uiexperiments.domain.stats.StatisticsType;

public class VisionPresenter {

    private String trueWord;

    public VisionPresenter(Context context) {
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfCorrect = currentStatistics.getResults(StatisticsType.Vision).getAmountOfCorrectAnswers();
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
        Integer amountOfAssumptions = currentStatistics.getResults(StatisticsType.Vision).getAmountOfAssumptions();
        currentStatistics.getResults(StatisticsType.Vision).setAmountOfAssumptions(amountOfAssumptions + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }

    public void saveSuccess(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getResults(StatisticsType.Vision).getAmountOfAssumptions();
        Integer amountOfCorrect= currentStatistics.getResults(StatisticsType.Vision).getAmountOfCorrectAnswers();
        currentStatistics.getResults(StatisticsType.Vision).setAmountOfAssumptions(amountOfAssumptions + 1);
        currentStatistics.getResults(StatisticsType.Vision).setAmountOfCorrectAnswers(amountOfCorrect + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }
}
