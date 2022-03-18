package com.application.cardle;

public class CardModel {

    private Integer number;
    private String question;
    private String reponse;

    // Constructor
    public CardModel(Integer number, String question, String reponse) {
        this.number = number;
        this.question = question;
        this.reponse = reponse;
    }

    // Getter and Setter
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestionName(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponseName(String reponse) {
        this.reponse = reponse;
    }

}

