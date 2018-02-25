package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.db.WordsDictionary;
import com.laushkina.anastasia.uiexperiments.domain.stats.Statistics;
import com.laushkina.anastasia.uiexperiments.view.statistics.StatisticsViewModel;
import com.laushkina.anastasia.uiexperiments.view.statistics.StatisticsViewModelMapper;
import com.laushkina.anastasia.uiexperiments.view.statistics.TestResultViewModelMapper;

public class StatisticsPresenter {

    private StatisticsViewModel currentStatistics;

    public StatisticsPresenter(){}

    public synchronized StatisticsViewModel getCurrentStatistics(Context context){
        if (currentStatistics == null) {
            Statistics statistics = StatisticsRepository.getStatistics(context);
            currentStatistics = StatisticsViewModelMapper.map(statistics, WordsDictionary.wordsCount);
        }
        return currentStatistics;
    }

    public void clearStatistics(Context context){
        StatisticsRepository.clearStatistics(context);
        Statistics statistics = StatisticsRepository.getStatistics(context);
        currentStatistics.setDragAndDropResults(TestResultViewModelMapper.map(statistics.getDragAndDropResults(),
                                                WordsDictionary.wordsCount));
        currentStatistics.setVisionResults(TestResultViewModelMapper.map(statistics.getVisionResults(),
                                           WordsDictionary.wordsCount));
    }

}
