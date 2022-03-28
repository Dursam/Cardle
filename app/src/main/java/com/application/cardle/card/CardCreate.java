package com.application.cardle.card;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.application.cardle.R;
import com.application.cardle.deck.DeckCreate;

public class CardCreate extends AppCompatActivity {

    /*
     * Activity of cards creating
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_create);

        // get all the components id from card_create
        Button createCard = findViewById(R.id.buttonAddCard);
        EditText editCardQuestion = findViewById(R.id.textViewSetQuestion);
        EditText editCardResponse = findViewById(R.id.textViewSetResponse);

        // pick the previous Intent
        Intent intent = getIntent();

        // card creating listener
        createCard.setOnClickListener(v -> {

            // below line is to get data from all edit text fields.
            String cardQuestion = editCardQuestion.getText().toString();
            String cardResponse = editCardResponse.getText().toString();

            // check empty edit text box
            if (cardQuestion.isEmpty() || cardResponse.isEmpty() ) {
                Toast.makeText(CardCreate.this, "Please enter a question and response", Toast.LENGTH_SHORT).show();
            // DO
            }else {
                // display a toast message that's card is adding
                Toast.makeText(CardCreate.this, "Card has been added", Toast.LENGTH_SHORT).show();

                // reset edit text box
                editCardQuestion.setText("");
                editCardResponse.setText("");

                // get the previous activity
                String activity = intent.getStringExtra("activity");
                System.out.println(activity);

                // activity from "empty" (DeckEmpty) DO
                // add the first card in the deck before he was an empty deck
                if(activity.equals("empty")){
                    Intent i = new Intent(CardCreate.this, DeckCreate.class);
                    i.putExtra("Question",cardQuestion);
                    i.putExtra("Response",cardResponse);
                    i.putExtra("activity","empty");
                    startActivity(i);
                    finish();
                }

                // activity from "adding" (DeckCreate) DO
                // add the card below existing cards in the deck
                if(activity.equals("adding")) {
                    Intent i = new Intent();
                    i.putExtra("Question", cardQuestion);
                    i.putExtra("Response", cardResponse);
                    // when activity is done without problems, we send our personal code 78
                    // to ask of DeckCreate to add a new card
                    setResult(78, i);
                    CardCreate.super.onBackPressed();
                }
            }
        });
    }
}