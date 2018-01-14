package com.laushkina.anastasia.uiexperiments.domain;

public class Statistics {
    private TestResult dragAndDropResults;
    private TestResult visionResults;
    private TestResult gpsResults;

    public TestResult getDragAndDropResults() {
        return dragAndDropResults;
    }

    public void setDragAndDropResults(TestResult dragAndDropResults) {
        this.dragAndDropResults = dragAndDropResults;
    }

    public TestResult getGpsResults() {
        return gpsResults;
    }

    public void setGpsResults(TestResult gpsResults) {
        this.gpsResults = gpsResults;
    }

    //TODO Enum and getresults(Enum)
    public TestResult getVisionResults() {
        return visionResults;
    }

    public void setVisionResults(TestResult visionResults) {
        this.visionResults = visionResults;
    }
}
