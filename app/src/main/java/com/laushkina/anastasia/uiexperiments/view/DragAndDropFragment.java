package com.laushkina.anastasia.uiexperiments.view;

import android.app.Fragment;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laushkina.anastasia.uiexperiments.R;
import com.laushkina.anastasia.uiexperiments.presenter.DragAndDropPresenter;

public class DragAndDropFragment extends Fragment implements View.OnTouchListener, View.OnDragListener {
    private DragAndDropPresenter presenter;

    private TextView emptyLetter;
    private boolean wasDropOnCorrectPosition;
    private boolean wasDragEventHandled;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.drag_and_drop_content, container, false);
        presenter = new DragAndDropPresenter(getActivity());

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        initializeScreen();
    }

    @Override
    public boolean onTouch(View v, MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDrag(null, shadowBuilder, v, 0);
                v.setVisibility(View.INVISIBLE);
                return true;
        }
        return false;
    }

    @Override
    public boolean onDrag(View mainView, DragEvent event) {
        TextView view = (TextView) event.getLocalState();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                view.setVisibility(View.INVISIBLE);
                wasDropOnCorrectPosition = false;
                wasDragEventHandled = false;
                return true;

            case DragEvent.ACTION_DRAG_ENDED:
                // Delayed method is needed for avoiding concurrent exception
                view.post(() -> {
                    if (wasDragEventHandled) return;

                    view.setVisibility(View.VISIBLE);
                    if (wasDropOnCorrectPosition) {
                        emptyLetter.setText((view).getText());
                        emptyLetter.invalidate();
                        checkIfWordCorrect();
                    }
                    mainView.invalidate();
                    wasDragEventHandled = true;
                });
                return true;
            case DragEvent.ACTION_DROP:
                wasDropOnCorrectPosition = isViewOverlapping(emptyLetter, event);
                Log.e("isViewOverlapping", String.valueOf(wasDropOnCorrectPosition));
        }
        return true;
    }

    private void initializeScreen() {
        String word = presenter.getNextWord();
        int indexOfMissing = presenter.getIndexOfMissing();
        Character[] suggestionCharacters = presenter.getSuggestions(indexOfMissing);

        TextView textView;
        for (int i = 0; i < presenter.getAmountOfSuggestionLetters(); i++) {
            textView = getSuggestionTextViewByIndex(i);
            if (textView == null) continue;

            textView.setText(suggestionCharacters[i].toString());
            textView.setOnTouchListener(this);
            // On rootView drag listener is needed to handle position of dragging view
            textView.getRootView().setOnDragListener(this);
            textView.setOnDragListener(this);
        }

        for (int i = 0; i < presenter.getAnswerWordLength(); i++) {
            textView = getAnswerTextViewByIndex(i);
            textView.setBackground(getResources().getDrawable(R.drawable.drag_and_drop_letter_background));
            boolean isMissingLetter = i == indexOfMissing;
            textView.setText(isMissingLetter ? "" : String.valueOf(word.charAt(i)));
            if (!isMissingLetter) continue;
            emptyLetter = getAnswerTextViewByIndex(i);
        }
    }

    private void checkIfWordCorrect(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < presenter.getAnswerWordLength(); i++) {
            sb.append(getAnswerTextViewByIndex(i).getText());
        }
        if (presenter.isWordCorrect(sb.toString())) {
            onCorrectAnswer();
        } else {
            onIncorrectAnswer();
        }
    }

    private void onCorrectAnswer(){
        emptyLetter.setBackground(getResources().getDrawable(R.drawable.drag_and_drop_letter_success_background));
        ((MainActivity)getActivity()).showToast(getResources().getString(R.string.success_message));
        (new Handler()).postDelayed(() -> {
            presenter.saveSuccess(getActivity());
            initializeScreen();
        }, 1000);
    }

    private void onIncorrectAnswer(){
        emptyLetter.setBackground(getResources().getDrawable(R.drawable.drag_and_drop_letter_error_background));
        presenter.saveFailure(getActivity());
        ((MainActivity)getActivity()).showToast(getResources().getString(R.string.incorrect_message));
    }

    // TODO proper testing.Not working when top order of dragging view is higher then static section
    private boolean isViewOverlapping(View staticView, DragEvent event) {
        View draggingView = (View) event.getLocalState();

        int[] staticCenter = new int[2];
        staticView.getLocationOnScreen(staticCenter);
        staticCenter[1] += 110;

        int staticViewHeight = staticView.getHeight();
        int staticViewWidth = staticView.getWidth();
        int[] staticTopStart = new int[]{staticCenter[0] - staticViewWidth / 2, staticCenter[1] - staticViewHeight / 2};
        int[] staticBottomEnd = new int[]{staticCenter[0] + staticViewWidth / 2, staticCenter[1] + staticViewHeight / 2};


        int[] draggingCenter = new int[]{(int) event.getX(), (int) event.getY()};
        int draggingViewHeight = draggingView.getHeight();
        int draggingViewWidth = draggingView.getWidth();

        int[] draggingTopStart = new int[]{draggingCenter[0] - draggingViewWidth / 2, draggingCenter[1] - draggingViewHeight / 2};
        int[] draggingBottomEnd = new int[]{draggingCenter[0] + draggingViewWidth / 2, draggingCenter[1] + draggingViewHeight / 2};

        boolean isYFitting = staticTopStart[1] < draggingBottomEnd[1] && draggingBottomEnd[1] < staticBottomEnd[1];
        boolean isXFitting = staticTopStart[0] < draggingBottomEnd[0] && draggingBottomEnd[0] < staticBottomEnd[0] ||
                staticTopStart[0] < draggingTopStart[0] && draggingTopStart[0] < staticBottomEnd[0];

        return isYFitting && isXFitting;
    }

    private TextView getSuggestionTextViewByIndex(int index) {
        switch (index) {
            case 0:
                return (TextView)  getActivity().findViewById(R.id.suggested_letter_1);
            case 1:
                return (TextView)  getActivity().findViewById(R.id.suggested_letter_2);
            case 2:
                return (TextView)  getActivity().findViewById(R.id.suggested_letter_3);
            case 3:
                return (TextView)  getActivity().findViewById(R.id.suggested_letter_4);
        }
        return null;
    }

    private TextView getAnswerTextViewByIndex(int index) {
        switch (index) {
            case 0:
                return (TextView)  getActivity().findViewById(R.id.answer_letter_1);
            case 1:
                return (TextView)  getActivity().findViewById(R.id.answer_letter_2);
            case 2:
                return (TextView)  getActivity().findViewById(R.id.answer_letter_3);
            case 3:
                return (TextView)  getActivity().findViewById(R.id.answer_letter_4);
            case 4:
                return (TextView)  getActivity().findViewById(R.id.answer_letter_5);
        }
        return null;
    }

}
