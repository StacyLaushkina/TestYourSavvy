package com.laushkina.anastasia.uiexperiments.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.laushkina.anastasia.uiexperiments.domain.Statistics;
import com.laushkina.anastasia.uiexperiments.domain.TestResult;

public class StatisticsRepository {

    private static final String preferenceFileKey = "com.laushkina.anastasia.uiexperiments.PREFERENCE_FILE_KEY";

    private static final String anagramAmountOfCorrectAnswers = "AnagramAmountOfCorrectAnswers";
    private static final String visionAmountOfCorrectAnswers = "visionAmountOfCorrectAnswers";
    private static final String anagramAmountOfAssumptions = "AnagramAmountOfAssumptions";
    private static final String visionAmountOfAssumptions = "visionAmountOfAssumptions";

    public static Statistics getStatistics(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        Integer anagramCorrect = sharedPref.getInt(anagramAmountOfCorrectAnswers, 0);
        Integer visionCorrect = sharedPref.getInt(visionAmountOfCorrectAnswers, 0);
        Integer anagramAssupt = sharedPref.getInt(anagramAmountOfAssumptions, 0);
        Integer visionAssupt = sharedPref.getInt(visionAmountOfAssumptions, 0);
        Statistics statistics = new Statistics();
        statistics.setAnagramResults(new TestResult(anagramCorrect, anagramAssupt));
        statistics.setVisionResults(new TestResult(visionCorrect, visionAssupt));
        return statistics;
    }

    public static void saveStatistics(Context context, Statistics statistics) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(anagramAmountOfCorrectAnswers, statistics.getAnagramResults().getAmountOfCorrectAnswers());
        editor.putInt(visionAmountOfCorrectAnswers, statistics.getVisionResults().getAmountOfCorrectAnswers());
        editor.putInt(anagramAmountOfAssumptions, statistics.getAnagramResults().getAmountOfAssumptions());
        editor.putInt(visionAmountOfAssumptions, statistics.getVisionResults().getAmountOfAssumptions());
        editor.commit();
    }
}