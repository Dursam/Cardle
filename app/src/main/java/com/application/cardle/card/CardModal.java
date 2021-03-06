package com.application.cardle.card;

public class CardModal {

    private Integer id_card;
    private String question, response;

    /**
     * Constructor CardModal : Represents the card modal.
     * @param id_card   identification
     * @param question  question
     * @param response  response
     */
    public CardModal(Integer id_card, String question, String response) {
        this.id_card = id_card;
        this.question = question;
        this.response = response;
    }

    /**
     * Get the identification card.
     * @return  identification card
     */
    public Integer getIdCard() {
        return id_card;
    }

    /**
     * Set a new identification of the card.
     * @param id_card identification
     */
    public void setIdCard(Integer id_card) {
        this.id_card = id_card;
    }

    /**
     * Get the question card.
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Get the response card.
     * @return response
     */
    public String getResponse() {
        return response;
    }
}