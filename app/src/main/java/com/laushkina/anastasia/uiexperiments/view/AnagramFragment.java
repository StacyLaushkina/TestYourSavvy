package com.laushkina.anastasia.uiexperiments.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.laushkina.anastasia.uiexperiments.R;
import com.laushkina.anastasia.uiexperiments.domain.ReverseWordCalculator;
import com.laushkina.anastasia.uiexperiments.presenter.AnagramPresenter;

public class AnagramFragment extends Fragment {
    private AnagramPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.anagram_content, container, false);
        presenter = new AnagramPresenter(getActivity());

        initializeScreen(view);
        return view;
    }

    private void initializeScreen(View view){
        TextView mixedWordView = (TextView)view.findViewById(R.id.mixed_word);
        String mixedWord = ReverseWordCalculator.reverseWord(presenter.getNextWord());
        mixedWordView.setText(mixedWord);

        Button button = (Button)view.findViewById(R.id.submit_button);
        button.setOnClickListener(this::checkRestoredWord);
    }

    public void checkRestoredWord(View view){
        TextView restoredWordView = (TextView)view.findViewById(R.id.restored_word);
        CharSequence restoredWord = restoredWordView.getText();
        if(restoredWord == null || restoredWord.length() == 0){
            ((MainActivity)getActivity()).showToast(getResources().getString(R.string.empty_string_warning));
            return;
        }
        if (presenter.getTrueWord().equals(restoredWord.toString())) {
            presenter.saveSuccess(getActivity());
            ((MainActivity)getActivity()).showToast(getResources().getString(R.string.success_message));
//            finish();
            return;
        }
        presenter.saveFailure(getActivity());
        ((MainActivity)getActivity()).showToast(getResources().getString(R.string.incorrect_message));
    }

}
