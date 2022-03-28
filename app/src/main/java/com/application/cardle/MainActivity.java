package com.application.cardle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.application.cardle.card.CardModal;
import com.application.cardle.deck.DeckAdapter;
import com.application.cardle.deck.DeckModal;
import com.application.cardle.deck.DeckEmpty;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
     * Activity of deck and cards creating
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // get all the components id from menu
        RecyclerView deckRV = findViewById(R.id.Deck);
        Button createDeck = findViewById(R.id.ButtonCreate);

        // creating a new database class and passing our context to it
        DataBase dbCardle = new DataBase(MainActivity.this);

        // Uncomment for reset database
        //dbCardle.reset();

        // deck examples contents
        ArrayList<DeckModal> deckModalArrayList = new ArrayList<>();
        deckModalArrayList.add(new DeckModal("Capital city"));
        deckModalArrayList.add(new DeckModal("Programming Language"));
        deckModalArrayList.add(new DeckModal("Savannah Animals"));
        deckModalArrayList.add(new DeckModal("French Translation"));
        deckModalArrayList.add(new DeckModal("Stars !"));
        deckModalArrayList.add(new DeckModal("Video Games"));
        deckModalArrayList.add(new DeckModal("Famous Buildings"));

        // card examples contents
        ArrayList<CardModal> cardModalArrayList = new ArrayList<>();
        cardModalArrayList.add(new CardModal(1,"German capital city ?","Berlin"));
        cardModalArrayList.add(new CardModal(1,"UK capital city ? ","Londres"));
        cardModalArrayList.add(new CardModal(1,"French capital city ?","Paris"));
        cardModalArrayList.add(new CardModal(1,"USA capital city ? ","Washington"));
        cardModalArrayList.add(new CardModal(2,"What Dennis Ritchie created ? ","C"));
        cardModalArrayList.add(new CardModal(2,"The most PL used in France ?","Java"));
        cardModalArrayList.add(new CardModal(2,"Big Data PL ?","Python"));
        cardModalArrayList.add(new CardModal(2,"What Matsumoto created ?","Ruby"));
        cardModalArrayList.add(new CardModal(2,"Quote a functionel","Haskell"));
        cardModalArrayList.add(new CardModal(3,"The king of Savannah ?","Lion"));
        cardModalArrayList.add(new CardModal(3,"The biggest animals ?","Elephant"));
        cardModalArrayList.add(new CardModal(3,"The longest neck ?","Giraffe"));
        cardModalArrayList.add(new CardModal(3,"The fastest ?","Cheetah"));
        cardModalArrayList.add(new CardModal(4,"Computer ? ","Ordinateur"));
        cardModalArrayList.add(new CardModal(4,"Screen ?","Ecran"));
        cardModalArrayList.add(new CardModal(4,"Keyboard ?","Clavier"));
        cardModalArrayList.add(new CardModal(5,"The most shining ? ","Sirius"));
        cardModalArrayList.add(new CardModal(5,"2nde most shining ?","Canopus"));
        cardModalArrayList.add(new CardModal(5,"Alpha Ursae Minoris ?","Polaris"));
        cardModalArrayList.add(new CardModal(6,"Mine***** ?","Minecraft"));
        cardModalArrayList.add(new CardModal(6,"The most famous FPS of Xbox ?","Halo"));
        cardModalArrayList.add(new CardModal(6,"Famous MOBA ?","League of Legends"));
        cardModalArrayList.add(new CardModal(6,"A man with mustache and Italian name ? ","Mario"));
        cardModalArrayList.add(new CardModal(6,"Catch'em all !","Pokemon"));
        cardModalArrayList.add(new CardModal(7,"Paris monument ?","Eiffel Tower"));
        cardModalArrayList.add(new CardModal(7,"Chinese landmark ? ","Great Wall of China"));
        cardModalArrayList.add(new CardModal(7,"Rio de Janeiro statue","Christ the Redeemer"));
        cardModalArrayList.add(new CardModal(7,"Istanbul religious building ?","Hagia Sophia"));
        cardModalArrayList.add(new CardModal(7,"New York statue","Statue of Freedom"));
        cardModalArrayList.add(new CardModal(7,"Egypt best monument ?","Giza pyramid"));

        // on below line we are calling a method to add new
        // cards and decks to sqlite database and pass all our values to it.
        if(!(dbCardle.checkTableEmpty("Deck"))){
            for (int i = 0; i < deckModalArrayList.size(); i++){
                dbCardle.addNewDeck(deckModalArrayList.get(i).getDeckName());
            }
            for (int i = 0; i < cardModalArrayList.size(); i++){
                System.out.println(i);
                dbCardle.addNewCard(cardModalArrayList.get(i).getQuestion(),
                                    cardModalArrayList.get(i).getResponse(),
                                    cardModalArrayList.get(i).getIdCard());
            }
        }

        // we are initializing our adapter class and passing our arraylist to it.
        DeckAdapter DeckAdapter = new DeckAdapter(this, dbCardle.readDecks());

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        deckRV.setLayoutManager(linearLayoutManager);
        deckRV.setAdapter(DeckAdapter);

        // card creating listener
        createDeck.setOnClickListener(v -> {
            // opening a new activity via a intent.
            Intent i = new Intent(MainActivity.this, DeckEmpty.class);
            startActivity(i);
        });

        // course listener

        // calendar listener

        // settings listener
    }
}