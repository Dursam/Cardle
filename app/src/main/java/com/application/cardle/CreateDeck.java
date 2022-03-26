package com.application.cardle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class CreateDeck extends AppCompatActivity {

    // ViewPager2 for cards
    ViewPager2 viewPager2Card;
    ArrayList<CardModel> VPCards;
    // cards counter
    Integer cntCards = 1;

    // Activity launcher and binding
    ActivityResultLauncher<Intent> aCards;
    
    //
    String question, response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_deck);

        // initializing all our variables.
        Button addCard = findViewById(R.id.buttonAddCardDeck);
        Button delCard = findViewById(R.id.buttonDelCardDeck);
        Button createDeck = findViewById(R.id.buttonAddDeck);
        EditText editDeckName = findViewById(R.id.textViewSetDeck);
        viewPager2Card = findViewById(R.id.viewpagerCard);

        // create a new database class and passing our context to it.
        DatabaseCardle dbCardle = new DatabaseCardle(CreateDeck.this);

        // create a new arraylist of card object
        VPCards = new ArrayList<>();

        // recuperate Extra of cards (for empty activity)
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            question = extras.getString("Question");
            response = extras.getString("Response");
            VPCards.add(new CardModel(cntCards,question,response));
            cntCards++;
        }

        // card creating listener
        addCard.setOnClickListener(v -> {
            // opening a new activity via a intent.
            Intent i = new Intent(CreateDeck.this, CreateCard.class);
            aCards.launch(i);
        });

        // deck creating listener
        createDeck.setOnClickListener(v -> {

            // below line is to get data from all edit text fields.
            String deckName = editDeckName.getText().toString();

            // validating if the text fields are empty or not.
            if (deckName.isEmpty()) {
                Toast.makeText(CreateDeck.this, "Please enter a deck name", Toast.LENGTH_SHORT).show();
            }else {
                // add the deck
                dbCardle.addNewDeck(deckName);

                // after adding the data we are displaying a toast message.
                Toast.makeText(CreateDeck.this, "Deck has been added", Toast.LENGTH_SHORT).show();
                editDeckName.setText("");

                // opening a new activity via a intent.
                Intent i = new Intent(CreateDeck.this, Menu.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        // get data from CreateCard activity
        aCards = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // create intent of creating card
                        Intent data = result.getData();
                        if(data != null){
                            question = data.getStringExtra("Question");
                            response = data.getStringExtra("Response");
                            VPCards.add(new CardModel(cntCards,question,response));
                            cntCards++;
                        }
                    }
                });
        viewPager2Card.setAdapter(new CardAdapter(CreateDeck.this,VPCards));
    }
}