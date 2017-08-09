package com.laushkina.anastasia.testyoursavvy.domain;

public class Statistics {
    private TestResult anagramResults;
    private TestResult visionResults;
    private TestResult gpsResults;

    public TestResult getAnagramResults() {
        return anagramResults;
    }

    public void setAnagramResults(TestResult anagramResults) {
        this.anagramResults = anagramResults;
    }

    public TestResult getGpsResults() {
        return gpsResults;
    }

    public void setGpsResults(TestResult gpsResults) {
        this.gpsResults = gpsResults;
    }

    public TestResult getVisionResults() {
        return visionResults;
    }

    public void setVisionResults(TestResult visionResults) {
        this.visionResults = visionResults;
    }
}
