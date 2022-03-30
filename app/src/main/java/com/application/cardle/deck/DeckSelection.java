package com.application.cardle.deck;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.application.cardle.DataBase;
import com.application.cardle.R;

import java.util.ArrayList;

public class DeckSelection extends AppCompatActivity {

    /*
     * Activity of deck selection course
     */

    // component for viewpager2
    ArrayList<Integer> deckNbCard;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_selection);

        // get all the components id from deck_selection
        ViewPager2 viewPager2Deck = findViewById(R.id.viewpagerDeck);
        TextView nbCards = findViewById(R.id.numberCards);

        // creating a new database class and passing our context to it
        DataBase dbCardle = new DataBase(DeckSelection.this);

        // get all decks and count his number of cards
        ArrayList<DeckModal> deckList = dbCardle.readDecks();
        deckNbCard = new ArrayList<>();
        for (int i = 0; i < deckList.size(); i++){
            deckNbCard.add(dbCardle.getNbCard(deckList.get(i).getDeckName()));
            System.out.println(deckNbCard.get(i));
        }

        // put all these data in viewpager2
        DeckAdapter deckAdapter = new DeckAdapter(this,deckList, R.layout.deck_info);
        viewPager2Deck.setAdapter(deckAdapter);

        // viewpager2Deck listener when page changed
        viewPager2Deck.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                nbCards.setText("card number : " + deckNbCard.get(position).toString());
            }
        });
    }
}
