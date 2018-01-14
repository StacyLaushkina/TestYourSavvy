package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.db.WordsDictionary;
import com.laushkina.anastasia.uiexperiments.domain.Statistics;

public class VisionPresenter {

    private String trueWord;

    public VisionPresenter(Context context) {
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfCorrect = currentStatistics.getDragAndDropResults().getAmountOfCorrectAnswers();
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
        Integer amountOfAssumptions = currentStatistics.getVisionResults().getAmountOfAssumptions();
        currentStatistics.getVisionResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }

    public void saveSuccess(Context context){
        Statistics currentStatistics = StatisticsRepository.getStatistics(context);
        Integer amountOfAssumptions = currentStatistics.getVisionResults().getAmountOfAssumptions();
        Integer amountOfCorrect= currentStatistics.getVisionResults().getAmountOfCorrectAnswers();
        currentStatistics.getVisionResults().setAmountOfAssumptions(amountOfAssumptions + 1);
        currentStatistics.getVisionResults().setAmountOfCorrectAnswers(amountOfCorrect + 1);
        StatisticsRepository.saveStatistics(context, currentStatistics);
    }
}
