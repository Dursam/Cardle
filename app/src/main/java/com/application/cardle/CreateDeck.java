package com.application.cardle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateDeck extends AppCompatActivity {

    // ViewPager2 for cards
    ViewPager2 viewPager2Card;
    ArrayList<CardModel> VPCards;
    // cards counter
    Integer cntCards = 1;

    // Updating data already
    ArrayList<CardModel> allCard;
    ArrayList<Integer> allIdCard;
    ArrayList<CardModel> newCardFromAlready;
    String nameDeck;

    // Activity launcher and binding
    ActivityResultLauncher<Intent> aCards = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.d("CreateDeckTest", "onActivityResult : ...");
                    if (result.getResultCode() == 78) {
                        // create intent of creating card
                        Intent data = result.getData();
                        if(data != null){
                            question = data.getStringExtra("Question");
                            response = data.getStringExtra("Response");
                            VPCards.add(new CardModel(cntCards,question,response));
                            newCardFromAlready.add(new CardModel(cntCards,question,response));

                            // Update numbers cards
                            for(int i = 0; i < VPCards.size(); i++){
                                VPCards.get(i).setId_card(i+1);
                            }

                            viewPager2Card.setAdapter(new CardAdapter(CreateDeck.this,VPCards));
                            viewPager2Card.setCurrentItem(cntCards, true);
                            cntCards++;
                        }
                    }
                }
            });
    
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
        newCardFromAlready = new ArrayList<>();

        // recuperate Extra of cards (for empty activity)
        Bundle extras = getIntent().getExtras();
        String activity = extras.getString("activity");

        if(activity.equals("empty")){
            question = extras.getString("Question");
            response = extras.getString("Response");
            VPCards.add(new CardModel(cntCards,question,response));
            cntCards++;
        } else if(activity.equals("already")){
            nameDeck = extras.getString("deck");
            allCard = dbCardle.readCards(dbCardle.getIdDeck(nameDeck).toString());
            allIdCard = new ArrayList<>();
            for (int i = 0; i < allCard.size(); i++){
                allIdCard.add(allCard.get(i).getIdCard());
                allCard.get(i).setId_card(i+1);
            }
            editDeckName.setText(nameDeck);
            VPCards = allCard;
            cntCards = VPCards.size()+1;
        }
        viewPager2Card.setAdapter(new CardAdapter(CreateDeck.this,VPCards));

        // card creating listener
        addCard.setOnClickListener(v -> {
            // opening a new activity via a intent.
            Intent i = new Intent(CreateDeck.this, CreateCard.class);
            i.putExtra("activity","adding");
            aCards.launch(i);
        });

        // delete card listener
        delCard.setOnClickListener(v ->{
            // delete current card listener
            Toast.makeText(CreateDeck.this, "Card has been deleted", Toast.LENGTH_SHORT).show();
            // System.out.println(viewPager2Card.getCurrentItem());
            CardModel CardElm = VPCards.remove(viewPager2Card.getCurrentItem());

            if(activity.equals("already")){
                dbCardle.delCard(allIdCard.get(CardElm.getIdCard()-1).toString());
            }

            // Update numbers cards
            for(int i = 0; i < VPCards.size(); i++){
                VPCards.get(i).setId_card(i+1);
            }

            viewPager2Card.setAdapter(new CardAdapter(CreateDeck.this,VPCards));
            cntCards--;
            if(cntCards == 1){
                CreateDeck.super.onBackPressed();
            }
        });

        // deck creating listener
        createDeck.setOnClickListener(v -> {

            // below line is to get data from all edit text fields.
            String deckName = editDeckName.getText().toString();

            // validating if the text fields are empty or not.
            if (deckName.isEmpty()) {
                Toast.makeText(CreateDeck.this, "Please enter a deck name", Toast.LENGTH_SHORT).show();
            }else {
                if(activity.equals("empty")){
                    // add the deck
                    dbCardle.addNewDeck(deckName);
                } else if(activity.equals("already")){
                    dbCardle.replaceDeckName(dbCardle.getIdDeck(nameDeck).toString(),deckName);
                }

                // get id deck to put as foreign key to all cards
                Integer idDeck = dbCardle.getIdDeck(deckName);

                // Update VPCards and avoid a duplication data that already exist in database
                if(activity.equals("empty")){
                    // add all cards
                    for (int i = 0; i < VPCards.size(); i++){
                        dbCardle.addNewCard(VPCards.get(i).getQuestion(),VPCards.get(i).getResponse(),idDeck);
                    }
                } else if(activity.equals("already")){
                    for (int i = 0; i < newCardFromAlready.size(); i++){
                        dbCardle.addNewCard(newCardFromAlready.get(i).getQuestion(),newCardFromAlready.get(i).getResponse(),idDeck);
                    }
                }

                // after adding the data we are displaying a toast message.
                Toast.makeText(CreateDeck.this, "Deck has been added", Toast.LENGTH_SHORT).show();
                editDeckName.setText("");

                // start new activity
                Intent i = new Intent(CreateDeck.this, Menu.class);
                startActivity(i);
            }
        });
    }
}