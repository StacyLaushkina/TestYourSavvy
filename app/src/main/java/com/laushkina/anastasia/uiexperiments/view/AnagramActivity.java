package com.laushkina.anastasia.uiexperiments.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.laushkina.anastasia.uiexperiments.R;
import com.laushkina.anastasia.uiexperiments.domain.ReverseWordCalculator;
import com.laushkina.anastasia.uiexperiments.presenter.AnagramPresenter;

public class AnagramActivity extends ParentActivity {

    private AnagramPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anagram);

        presenter = new AnagramPresenter(this);
        initializeScreen();
    }

    private void initializeScreen(){
        TextView mixedWordView = (TextView)findViewById(R.id.mixed_word);
        String mixedWord = ReverseWordCalculator.reverseWord(presenter.getNextWord());
        mixedWordView.setText(mixedWord);
    }

    public void checkRestoredWord(View view){
        TextView restoredWordView = (TextView)findViewById(R.id.restored_word);
        CharSequence restoredWord = restoredWordView.getText();
        if(restoredWord == null || restoredWord.length() == 0){
            showToast(getResources().getString(R.string.empty_string_warning));
            return;
        }
        if (presenter.getTrueWord().equals(restoredWord.toString())) {
            presenter.saveSuccess(this);
            showToast(getResources().getString(R.string.success_message));
            finish();
            return;
        }
        presenter.saveFailure(this);
        showToast(getResources().getString(R.string.incorrect_message));
    }
}
