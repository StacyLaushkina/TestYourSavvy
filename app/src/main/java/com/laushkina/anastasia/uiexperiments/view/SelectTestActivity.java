package com.laushkina.anastasia.uiexperiments.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.laushkina.anastasia.uiexperiments.R;
import com.laushkina.anastasia.uiexperiments.domain.Statistics;
import com.laushkina.anastasia.uiexperiments.domain.TestResult;
import com.laushkina.anastasia.uiexperiments.presenter.SelectTestPresenter;
import com.laushkina.anastasia.uiexperiments.view.vision.VisionActivity;

public class SelectTestActivity extends Activity {
    private SelectTestPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);

        presenter = new SelectTestPresenter();
    }

    @Override
    public void onResume(){
        super.onResume();
        initializeScreen(presenter.getLatestStatistics(this));
    }

    private void initializeScreen(Statistics statistics){
        initializeAnagram(statistics.getAnagramResults());
        initializeVision(statistics.getVisionResults());
        initializeGPS(statistics.getGpsResults());
    }

    private void initializeAnagram(TestResult testResult){
        TextView anagramStats = (TextView)findViewById(R.id.anagram_stats);
        anagramStats.setText(getResultingString(testResult));
    }

    private void initializeVision(TestResult testResult){
        TextView visionStats = (TextView)findViewById(R.id.vision_stats);
        visionStats.setText(getResultingString(testResult));
    }

    private void initializeGPS(TestResult testResult){
        //TODO
    }

    private Spannable getResultingString(TestResult testResult){
        String correct = testResult.getAmountOfCorrectAnswers() == null ? "" : testResult.getAmountOfCorrectAnswers().toString();
        String assump = testResult.getAmountOfAssumptions() == null ? "" : testResult.getAmountOfAssumptions().toString();
        String result = correct + " / " +  assump;
        Spannable spannable = new SpannableString(result);
        spannable.setSpan(new ForegroundColorSpan(Color.GREEN), 0, correct.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }

    public void navigateToAnagram(View view) {
        Intent intent = new Intent(this, AnagramActivity.class);
        startActivity(intent);
    }

    public void navigateToVision(View view) {
        Intent intent = new Intent(this, VisionActivity.class);
        startActivity(intent);
    }

    public void navigateToGPS(View view) {
        // TODO
    }
}
