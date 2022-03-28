package com.application.cardle.deck;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.application.cardle.R;
import com.application.cardle.card.CardCreate;

public class DeckEmpty extends AppCompatActivity {

    /*
     * Activity of an empty deck of card
     */
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_empty);

        // get the component id from deck_empty
        Button createCard = findViewById(R.id.buttonNewCard);

        // card creating listener
        createCard.setOnClickListener(v -> {
            // opening a new activity (CardCreate) via a intent
            Intent i = new Intent(DeckEmpty.this, CardCreate.class);
            i.putExtra("activity","empty");
            startActivity(i);
            finish();
        });
    }
}