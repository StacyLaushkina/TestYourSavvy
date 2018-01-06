package com.laushkina.anastasia.uiexperiments.domain;

public class TestResult {

    private Integer amountOfCorrectAnswers;
    private Integer amountOfAssumptions;

    public TestResult(int amountOfCorrectAnswers, int amountOfAssumptions) {
        if (amountOfAssumptions < amountOfCorrectAnswers) return;

        this.amountOfCorrectAnswers = amountOfCorrectAnswers;
        this.amountOfAssumptions = amountOfAssumptions;
    }

    public Integer getAmountOfCorrectAnswers() {
        return amountOfCorrectAnswers;
    }

    public void setAmountOfCorrectAnswers(Integer amountOfCorrectAnswers) {
        if (amountOfCorrectAnswers > amountOfAssumptions) return;

        this.amountOfCorrectAnswers = amountOfCorrectAnswers;
    }

    public Integer getAmountOfAssumptions() {
        return amountOfAssumptions;
    }

    public void setAmountOfAssumptions(Integer amountOfAssumptions) {
        if (amountOfAssumptions < amountOfCorrectAnswers) return;

        this.amountOfAssumptions = amountOfAssumptions;
    }
}
