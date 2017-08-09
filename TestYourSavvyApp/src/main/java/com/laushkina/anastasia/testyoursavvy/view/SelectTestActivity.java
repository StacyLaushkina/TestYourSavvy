package com.laushkina.anastasia.testyoursavvy.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.laushkina.anastasia.testyoursavvy.R;

public class SelectTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_test);
    }

    public void navigateToAnagram(View view) {
        Intent intent = new Intent(this, AnagramActivity.class);
        startActivity(intent);
    }

    public void navigateToVision(View view) {
        // TODO
    }

    public void navigateToGPS(View view) {
        // TODO
    }
}
