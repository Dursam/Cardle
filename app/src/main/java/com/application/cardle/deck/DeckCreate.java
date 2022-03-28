package com.application.cardle.deck;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.application.cardle.card.CardAdapter;
import com.application.cardle.card.CardModal;
import com.application.cardle.card.CardCreate;
import com.application.cardle.DataBase;
import com.application.cardle.MainActivity;
import com.application.cardle.R;
import java.util.ArrayList;

public class DeckCreate extends AppCompatActivity {

    /*
     * Activity of deck and cards creating
     */

    // component for viewpager2
    ViewPager2 viewPager2Card;
    ArrayList<CardModal> VPCards;

    // component for updating cards that already existed
    ArrayList<Integer> allIdCard;
    ArrayList<CardModal> allCard, newCardFromAlready;

    // component for deck and cards creating
    String question, response, nameDeck ;

    // cards counter
    Integer cntCards = 1;

    // Activity launcher and binding from card_create
    ActivityResultLauncher<Intent> aCards = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // activity from card_create is done without problem : code 78 checked
                    if (result.getResultCode() == 78) {
                        // create intent of card creating
                        Intent data = result.getData();
                        if(data != null){

                            // add the new card in the viewpager2
                            question = data.getStringExtra("Question");
                            response = data.getStringExtra("Response");
                            VPCards.add(new CardModal(cntCards,question,response));

                            // store the new cards added
                            newCardFromAlready.add(new CardModal(cntCards,question,response));

                            // update numbers cards
                            for(int i = 0; i < VPCards.size(); i++){
                                VPCards.get(i).setId_card(i+1);
                            }

                            // refresh the viewpager2 and counter
                            viewPager2Card.setAdapter(new CardAdapter(DeckCreate.this,VPCards));
                            viewPager2Card.setCurrentItem(cntCards, true);
                            cntCards++;
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_create);

        // get all the components id from deck_create
        Button addCard = findViewById(R.id.buttonAddCardDeck);
        Button delCard = findViewById(R.id.buttonDelCardDeck);
        Button createDeck = findViewById(R.id.buttonAddDeck);
        EditText editDeckName = findViewById(R.id.textViewSetDeck);
        viewPager2Card = findViewById(R.id.viewpagerCard);

        // create a new database class and passing our context to it.
        DataBase dbCardle = new DataBase(DeckCreate.this);

        // create new arraylist of card object
        // VPCards if for the view and newCardFromAlready
        // is to add new card in a deck that already existed
        VPCards = new ArrayList<>();
        newCardFromAlready = new ArrayList<>();

        // get the previous activity
        Bundle extras = getIntent().getExtras();
        String activity = extras.getString("activity");

        // activity from "empty" (DeckEmpty) DO
        // add a the first card
        if(activity.equals("empty")){
            question = extras.getString("Question");
            response = extras.getString("Response");
            VPCards.add(new CardModal(cntCards,question,response));
            cntCards++;

        // activity from "already" (MainActivity) DO
        // add a new card from a deck that already existed
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

        // refresh viewpager2
        viewPager2Card.setAdapter(new CardAdapter(DeckCreate.this,VPCards));

        // card creating listener
        addCard.setOnClickListener(v -> {
            // opening a new activity creating card via a intent
            Intent i = new Intent(DeckCreate.this, CardCreate.class);
            i.putExtra("activity","adding");
            aCards.launch(i);
        });

        // delete card listener
        delCard.setOnClickListener(v ->{
            // delete current card listener
            Toast.makeText(DeckCreate.this, "Card has been deleted", Toast.LENGTH_SHORT).show();
            CardModal CardElm = VPCards.remove(viewPager2Card.getCurrentItem());
            if(activity.equals("already")){
                dbCardle.delCard(allIdCard.get(CardElm.getIdCard()-1).toString());
            }
            // update numbers cards
            for(int i = 0; i < VPCards.size(); i++){
                VPCards.get(i).setId_card(i+1);
            }
            // refresh viewpager2
            viewPager2Card.setAdapter(new CardAdapter(DeckCreate.this,VPCards));
            cntCards--;
            // go back to the previous activity if 0 card in the deck
            if(cntCards == 1){
                DeckCreate.super.onBackPressed();
            }
        });

        // deck creating listener
        createDeck.setOnClickListener(v -> {

            // get the new name of the deck
            String deckName = editDeckName.getText().toString();

            // check empty edit text box
            if (deckName.isEmpty()) {
                Toast.makeText(DeckCreate.this, "Please enter a deck name", Toast.LENGTH_SHORT).show();
            // DO
            }else {
                // activity from "empty" (DeckEmpty) DO
                // add the new deck in database
                if(activity.equals("empty")){
                    dbCardle.addNewDeck(deckName);
                // activity from "already" (MainActivity) DO
                // replace the name deck by another
                } else if(activity.equals("already")){
                    dbCardle.replaceDeckName(dbCardle.getIdDeck(nameDeck).toString(),deckName);
                }

                // get the id deck
                Integer idDeck = dbCardle.getIdDeck(deckName);

                // activity from "empty" (DeckEmpty) DO
                // add the all cards of the emerged deck
                if(activity.equals("empty")){
                    // add all cards
                    for (int i = 0; i < VPCards.size(); i++){
                        dbCardle.addNewCard(VPCards.get(i).getQuestion(),VPCards.get(i).getResponse(),idDeck);
                    }
                // activity from "already" (MainActivity) DO
                // add the all cards of the existed deck
                } else if(activity.equals("already")){
                    for (int i = 0; i < newCardFromAlready.size(); i++){
                        dbCardle.addNewCard(newCardFromAlready.get(i).getQuestion(),newCardFromAlready.get(i).getResponse(),idDeck);
                    }
                }

                // after adding the data we are displaying a toast message.
                Toast.makeText(DeckCreate.this, "Deck has been added", Toast.LENGTH_SHORT).show();
                editDeckName.setText("");

                // come to the main activity
                Intent i = new Intent(DeckCreate.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}