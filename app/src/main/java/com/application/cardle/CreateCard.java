package com.application.cardle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_card);

        // initializing all our variables.
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

            // validating if the text fields are empty or not.
            if (cardQuestion.isEmpty() || cardResponse.isEmpty() ) {
                Toast.makeText(CreateCard.this, "Please enter a question and response", Toast.LENGTH_SHORT).show();
            }else {
                // after adding the data we are displaying a toast message.
                Toast.makeText(CreateCard.this, "Card has been added", Toast.LENGTH_SHORT).show();
                editCardQuestion.setText("");
                editCardResponse.setText("");

                // get the previous activity
                String activity = intent.getStringExtra("activity");

                // intent


                // EmptyDeck or CreateDeck
                if(activity.equals("empty")){
                    Intent i = new Intent(CreateCard.this, CreateDeck.class);
                    i.putExtra("Question",cardQuestion);
                    i.putExtra("Response",cardResponse);
                    startActivity(i);
                }else{
                    Intent i = new Intent();
                    i.putExtra("Question",cardQuestion);
                    i.putExtra("Response",cardResponse);
                    setResult(RESULT_OK,i);
                    //finish();
                }
            }
        });


    }
}
