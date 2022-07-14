package ru.otus.spring.domain;

public class Question {

    private final String text;
    private final String[] answers;

    private final Integer correctAnswerIndex;

    public Question(String text, String[] answers, Integer correctAnswerIndex){
        this.text = text;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public Question(String text){
        this.text = text;
        this.answers = new String[0];
        this.correctAnswerIndex = -1;
    }

    public String getText(){
        return this.text;
    }

    public String[] getAnswers(){
        return this.answers;
    }

    public Integer getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }


}
