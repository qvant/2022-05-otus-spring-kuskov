package ru.otus.spring.exam.domain;

public class TestResult {
    private int correctAnswers;
    private int totalAnswers;
    private final int passagePercent;

    public TestResult(int passagePercent) {
        correctAnswers = 0;
        totalAnswers = 0;
        this.passagePercent = passagePercent;
    }

    public void AddCorrectAnswer() {
        this.correctAnswers++;
        this.totalAnswers++;
    }

    public void AddIncorrectAnswer() {
        this.totalAnswers++;
    }

    public boolean isPassed() {
        int correctAnswersPercent = 0;
        if (totalAnswers != 0) {
            correctAnswersPercent = correctAnswers * 100 / totalAnswers;
        }
        return correctAnswersPercent >= passagePercent;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }
}
