package com.laushkina.anastasia.uiexperiments.view.statistics;

public class TestResultViewModel {
    private String amountOfCorrect;
    private String amountOfIncorrect;
    private String total;

    public TestResultViewModel(String amountOfIncorrect, String amountOfCorrect, String total) {
        this.amountOfIncorrect = amountOfIncorrect;
        this.amountOfCorrect = amountOfCorrect;
        this.total = total;
    }

    public String getAmountOfCorrect() {
        return amountOfCorrect;
    }

    public void setAmountOfCorrect(String amountOfCorrect) {
        this.amountOfCorrect = amountOfCorrect;
    }

    public String getAmountOfIncorrect() {
        return amountOfIncorrect;
    }

    public void setAmountOfIncorrect(String amountOfIncorrect) {
        this.amountOfIncorrect = amountOfIncorrect;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
