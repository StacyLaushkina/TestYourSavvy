package com.laushkina.anastasia.uiexperiments.domain.stats;

public class Statistics {
    private TestResult dragAndDropResults;
    private TestResult visionResults;

    public TestResult getResults(StatisticsType type) {
        switch (type) {
            case DragAndDrop: return dragAndDropResults;
            case Vision: return visionResults;
            default: return null;
        }
    }

    public void setResults(TestResult results, StatisticsType type) {
        switch (type) {
            case DragAndDrop:
                this.dragAndDropResults = results;
                break;
            case Vision:
                this.visionResults = results;
                break;
        }
    }

    public TestResult getDragAndDropResults() {
        return dragAndDropResults;
    }

    public void setDragAndDropResults(TestResult dragAndDropResults) {
        this.dragAndDropResults = dragAndDropResults;
    }

    public TestResult getVisionResults(){
        return visionResults;
    }

    public void setVisionResults(TestResult visionResults) {
        this.visionResults = visionResults;
    }
}
