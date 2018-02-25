package com.laushkina.anastasia.uiexperiments.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.laushkina.anastasia.uiexperiments.domain.stats.Statistics;
import com.laushkina.anastasia.uiexperiments.domain.stats.TestResult;

public class StatisticsRepository {

    private static final String preferenceFileKey = "com.laushkina.anastasia.uiexperiments.PREFERENCE_FILE_KEY";

    private static final String dragAndDropAmountOfCorrectAnswers = "dragAndDropAmountOfCorrectAnswers";
    private static final String visionAmountOfCorrectAnswers = "visionAmountOfCorrectAnswers";
    private static final String dragAndDropAmountOfAssumptions = "dragAndDropAmountOfAssumptions";
    private static final String visionAmountOfAssumptions = "visionAmountOfAssumptions";

    public static Statistics getStatistics(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        Integer anagramCorrect = sharedPref.getInt(dragAndDropAmountOfCorrectAnswers, 0);
        Integer visionCorrect = sharedPref.getInt(visionAmountOfCorrectAnswers, 0);
        Integer anagramAssupt = sharedPref.getInt(dragAndDropAmountOfAssumptions, 0);
        Integer visionAssupt = sharedPref.getInt(visionAmountOfAssumptions, 0);
        Statistics statistics = new Statistics();
        statistics.setDragAndDropResults(new TestResult(anagramCorrect, anagramAssupt));
        statistics.setVisionResults(new TestResult(visionCorrect, visionAssupt));
        return statistics;
    }

    public static void saveStatistics(Context context, Statistics statistics) {
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(dragAndDropAmountOfCorrectAnswers, statistics.getDragAndDropResults().getAmountOfCorrectAnswers());
        editor.putInt(visionAmountOfCorrectAnswers, statistics.getVisionResults().getAmountOfCorrectAnswers());
        editor.putInt(dragAndDropAmountOfAssumptions, statistics.getDragAndDropResults().getAmountOfAssumptions());
        editor.putInt(visionAmountOfAssumptions, statistics.getVisionResults().getAmountOfAssumptions());
        editor.apply();
    }

    public static void clearStatistics(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(preferenceFileKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(dragAndDropAmountOfCorrectAnswers);
        editor.remove(visionAmountOfCorrectAnswers);
        editor.remove(dragAndDropAmountOfAssumptions);
        editor.remove(visionAmountOfAssumptions);
        editor.apply();
    }
}
