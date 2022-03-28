package com.application.cardle.deck;

public class DeckModal {

    private String deckName;

    /**
     * Constructor DeckModal : Represents the deck modal.
     * @param deckName deck name
     */
    public DeckModal(String deckName) {
        this.deckName = deckName;
    }

    /**
     * Get the deck name.
     * @return deck name
     */
    public String getDeckName() {
        return this.deckName;
    }

    /**
     * Set a new name of the deck.
     * @param deckName deck name
     */
    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }
}