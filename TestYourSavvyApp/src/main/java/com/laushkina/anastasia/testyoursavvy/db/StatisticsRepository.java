package com.laushkina.anastasia.testyoursavvy.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.laushkina.anastasia.testyoursavvy.domain.Statistics;
import com.laushkina.anastasia.testyoursavvy.domain.TestResult;

public class StatisticsRepository {

    private static final String preferenceFileKey = "com.laushkina.anastasia.testyoursavvy.PREFERENCE_FILE_KEY";

    private static final String anagramAmountOfCorrectAnswers = "AnagramAmountOfCorrectAnswers";
    private static final String anagramAmountOfAssumptions = "AnagramAmountOfAssumptions";

    public static Statistics getStatistics(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        Integer anagramCorrect = sharedPref.getInt(anagramAmountOfCorrectAnswers, 0);
        Integer anagramAssupt = sharedPref.getInt(anagramAmountOfAssumptions, 0);
        Statistics statistics = new Statistics();
        statistics.setAnagramResults(new TestResult(anagramCorrect, anagramAssupt));
        return statistics;
    }

    public static void saveStatistics(Context context, Statistics statistics) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(anagramAmountOfCorrectAnswers, statistics.getAnagramResults().getAmountOfCorrectAnswers());
        editor.putInt(anagramAmountOfAssumptions, statistics.getAnagramResults().getAmountOfAssumptions());
        editor.commit();
    }
}
