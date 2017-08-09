package com.laushkina.anastasia.testyoursavvy.presenter;

import android.content.Context;

import com.laushkina.anastasia.testyoursavvy.db.StatisticsRepository;
import com.laushkina.anastasia.testyoursavvy.domain.Statistics;

public class SelectTestPresenter {
    public Statistics getLatestStatistics(Context context) {
        return StatisticsRepository.getStatistics(context);
    }
}
