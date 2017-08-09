package com.laushkina.anastasia.testyoursavvy.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.Toast;

public class ParentActivity extends Activity {

    private float startingPoint;
    private float MIN_DISTANCE = 300;
    private static final int toastDuration = Toast.LENGTH_LONG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float dpWidth  = outMetrics.widthPixels;
        MIN_DISTANCE = dpWidth/2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                startingPoint = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                float endingPoint = event.getX();
                float deltaX = endingPoint - startingPoint;
                if (Math.abs(deltaX) > MIN_DISTANCE) {
                    showToast("Dismissing.");
                    finish();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    public void showToast(CharSequence text){
        Toast toast = Toast.makeText(this, text, toastDuration);
        toast.show();
    }
}
