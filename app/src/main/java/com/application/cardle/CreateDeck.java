package com.application.cardle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDeck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_deck);

        // initializing all our variables.
        Button createDeck = findViewById(R.id.buttonAddDeck);
        EditText editDeckName = findViewById(R.id.textViewSetDeck);

        // creating a new database class and passing our context to it.
        DatabaseCardle dbCardle = new DatabaseCardle(CreateDeck.this);

        createDeck.setOnClickListener(v -> {

            // below line is to get data from all edit text fields.
            String deckName = editDeckName.getText().toString();

            // validating if the text fields are empty or not.
            if (deckName.isEmpty()) {
                Toast.makeText(CreateDeck.this, "Please enter a deck name", Toast.LENGTH_SHORT).show();
            }

            // add the deck
            dbCardle.addNewDeck(deckName);

            // after adding the data we are displaying a toast message.
            Toast.makeText(CreateDeck.this, "Deck has been added", Toast.LENGTH_SHORT).show();
            editDeckName.setText("");

            // opening a new activity via a intent.
            Intent i = new Intent(CreateDeck.this, Menu.class);
            startActivity(i);
        });
    }
}