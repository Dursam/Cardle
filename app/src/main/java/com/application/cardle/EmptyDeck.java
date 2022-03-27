package com.application.cardle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmptyDeck extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_state_card);

        // initializing all our variables.
        Button createCard = findViewById(R.id.buttonNewCard);

        createCard.setOnClickListener(v -> {
            // opening a new activity via a intent.
            Intent i = new Intent(EmptyDeck.this, CreateCard.class);
            i.putExtra("activity","empty");
            startActivity(i);
            finish();
        });
    }
}
