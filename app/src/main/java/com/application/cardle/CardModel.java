package com.application.cardle;

public class CardModel {

    private Integer id_card;
    private String question;
    private String response;

    // Constructor
    public CardModel(Integer id_card,String question, String response) {
        this.id_card = id_card;
        this.question = question;
        this.response = response;
    }

    public Integer getIdCard() {
        return id_card;
    }

    public void setId_card(Integer id_card) {
        this.id_card = id_card;
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

