package com.laushkina.anastasia.uiexperiments.view.statistics;

import com.laushkina.anastasia.uiexperiments.domain.stats.Statistics;

public class StatisticsViewModelMapper{
    public static StatisticsViewModel map(Statistics statistics, int amountOfWords) {
        TestResultViewModel dragAndDropResults = TestResultViewModelMapper.map(statistics.getDragAndDropResults(), amountOfWords);
        TestResultViewModel visionResults = TestResultViewModelMapper.map(statistics.getVisionResults(), amountOfWords);
        return new StatisticsViewModel(dragAndDropResults, visionResults);
    }
}
