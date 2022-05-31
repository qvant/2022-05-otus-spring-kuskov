package ru.otus.spring.domain;

public class Question {

    private final String text;
    private final String[] answers;

    public Question(String text, String[] answers){
        this.text = text;
        this.answers = answers;
    }

    public String getText(){
        return this.text;
    }

    public String[] getAnswers(){
        return this.answers;
    }

}
