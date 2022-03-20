package com.application.cardle;

public class CardModel {

    private String question;
    private String response;

    // Constructor
    public CardModel(String question, String response) {
        this.question = question;
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionName(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponseName(String response) {
        this.response = response;
    }

}

