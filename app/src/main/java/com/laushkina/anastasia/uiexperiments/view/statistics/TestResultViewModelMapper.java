package com.laushkina.anastasia.uiexperiments.view.statistics;

import com.laushkina.anastasia.uiexperiments.domain.stats.TestResult;

public class TestResultViewModelMapper {
    public static TestResultViewModel map(TestResult testResult, int totalAmount) {
        int incorrectAmount = testResult.getAmountOfAssumptions() - testResult.getAmountOfCorrectAnswers();
       return new TestResultViewModel(String.valueOf(incorrectAmount),
                                      testResult.getAmountOfCorrectAnswers().toString(),
                                      String.valueOf(totalAmount));
    }
}
