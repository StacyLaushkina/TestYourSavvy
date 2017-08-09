package com.laushkina.anastasia.testyoursavvy.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laushkina.anastasia.testyoursavvy.R;
import com.laushkina.anastasia.testyoursavvy.domain.ReverseWordCalculator;

public class AnagramActivity extends ParentActivity {
    private static final String trueWord = "Kingdom";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram);

        initializeScreen();
    }

    private void initializeScreen(){
        TextView mixedWordView = (TextView)findViewById(R.id.mixed_word);
        String mixedWord = ReverseWordCalculator.reverseWord(trueWord);
        mixedWordView.setText(mixedWord);
    }

    public void checkRestoredWord(View view){
        TextView restoredWordView = (TextView)findViewById(R.id.restored_word);
        CharSequence restoredWord = restoredWordView.getText();
        if(restoredWord == null || restoredWord.length() == 0){
            showToast(getResources().getString(R.string.empty_string_warning));
            return;
        }
        if (trueWord.equals(restoredWord.toString())) {
            // TODO count amount of correct/incorrect answers
            showToast(getResources().getString(R.string.success_message));
            finish();
        }
        showToast(getResources().getString(R.string.incorrect_message));
    }
}
