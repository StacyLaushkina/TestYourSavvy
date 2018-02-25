package com.laushkina.anastasia.uiexperiments.domain.stats;

public class TestResult {

    private Integer amountOfCorrectAnswers;
    private Integer amountOfAssumptions;

    public TestResult(int amountOfCorrectAnswers, int amountOfAssumptions) {
        if (amountOfAssumptions < amountOfCorrectAnswers) throw new RuntimeException("Number of assumptions is higher then number of answers");

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
