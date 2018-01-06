package com.laushkina.anastasia.uiexperiments.presenter;

import android.content.Context;

import com.laushkina.anastasia.uiexperiments.db.StatisticsRepository;
import com.laushkina.anastasia.uiexperiments.domain.Statistics;

public class SelectTestPresenter {
    public Statistics getLatestStatistics(Context context) {
        return StatisticsRepository.getStatistics(context);
    }
}
