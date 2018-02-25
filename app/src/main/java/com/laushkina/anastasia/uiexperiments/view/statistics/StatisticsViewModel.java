package com.laushkina.anastasia.uiexperiments.view.statistics;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.laushkina.anastasia.uiexperiments.BR;

public class StatisticsViewModel extends BaseObservable {
    private TestResultViewModel dragAndDropResults;
    private TestResultViewModel visionResults;

    public StatisticsViewModel(TestResultViewModel dragAndDropResults, TestResultViewModel visionResults){
        this.dragAndDropResults = dragAndDropResults;
        this.visionResults = visionResults;
    }

    @Bindable
    public TestResultViewModel getDragAndDropResults() {
        return dragAndDropResults;
    }

    public void setDragAndDropResults(TestResultViewModel dragAndDropResults) {
        this.dragAndDropResults = dragAndDropResults;
        notifyPropertyChanged(BR.dragAndDropResults);
    }

    @Bindable
    public TestResultViewModel getVisionResults() {
        return visionResults;
    }

    public void setVisionResults(TestResultViewModel visionResults) {
        this.visionResults = visionResults;
        notifyPropertyChanged(BR.visionResults);
    }

}
