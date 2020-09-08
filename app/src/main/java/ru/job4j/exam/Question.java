package ru.job4j.exam;

import java.util.List;

public class Question {
    private int id;
    private String text;
    private List<Option> options;
    private int answer;
    private int userAnswer;

    public Question(int id, String text, List<Option> options, int answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.userAnswer = -1;
    }

    public int getId() {
        return this.id;
    }

    public String getText() {
        return this.text;
    }

    public List<Option> getOptions() {
        return this.options;
    }

    public int getAnswer() {
        return this.answer;
    }

    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }
}