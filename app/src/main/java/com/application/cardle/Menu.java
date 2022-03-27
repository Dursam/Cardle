package com.application.cardle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // initializing all our variables.
        RecyclerView deckRV = findViewById(R.id.Deck);
        Button createDeck = findViewById(R.id.ButtonCreate);

        // creating a new database class
        // and passing our context to it
        DatabaseCardle dbCardle = new DatabaseCardle(Menu.this);

        // Uncomment for reset database, but it will crash your app, Comment after, build and lauch it !
        // dbCardle.reset();

        // here we have created new array list and added data to it.
        // Arraylist for storing data
        ArrayList<DeckModel> deckModelArrayList = new ArrayList<>();
        deckModelArrayList.add(new DeckModel("Capital city"));
        deckModelArrayList.add(new DeckModel("Programming Language"));
        deckModelArrayList.add(new DeckModel("Savannah Animals"));
        deckModelArrayList.add(new DeckModel("French Translation"));
        deckModelArrayList.add(new DeckModel("Stars !"));
        deckModelArrayList.add(new DeckModel("Video Games"));
        deckModelArrayList.add(new DeckModel("Famous Buildings"));

        // Arraylist of Cards
        ArrayList<CardModel> cardModelArrayList = new ArrayList<>();
        cardModelArrayList.add(new CardModel(1,"German capital city ?","Berlin"));
        cardModelArrayList.add(new CardModel(1,"UK capital city ? ","Londres"));
        cardModelArrayList.add(new CardModel(1,"French capital city ?","Paris"));
        cardModelArrayList.add(new CardModel(1,"USA capital city ? ","Washington"));
        cardModelArrayList.add(new CardModel(2,"What Dennis Ritchie created ? ","C"));
        cardModelArrayList.add(new CardModel(2,"The most PL used in France ?","Java"));
        cardModelArrayList.add(new CardModel(2,"Big Data PL ?","Python"));
        cardModelArrayList.add(new CardModel(2,"What Matsumoto created ?","Ruby"));
        cardModelArrayList.add(new CardModel(2,"Quote a functionel","Haskell"));
        cardModelArrayList.add(new CardModel(3,"The king of Savannah ?","Lion"));
        cardModelArrayList.add(new CardModel(3,"The biggest animals ?","Elephant"));
        cardModelArrayList.add(new CardModel(3,"The longest neck ?","Giraffe"));
        cardModelArrayList.add(new CardModel(3,"The fastest ?","Cheetah"));
        cardModelArrayList.add(new CardModel(4,"Computer ? ","Ordinateur"));
        cardModelArrayList.add(new CardModel(4,"Screen ?","Ecran"));
        cardModelArrayList.add(new CardModel(4,"Keyboard ?","Clavier"));
        cardModelArrayList.add(new CardModel(5,"The most shining ? ","Sirius"));
        cardModelArrayList.add(new CardModel(5,"2nde most shining ?","Canopus"));
        cardModelArrayList.add(new CardModel(5,"Alpha Ursae Minoris ?","Polaris"));
        cardModelArrayList.add(new CardModel(6,"Mine***** ?","Minecraft"));
        cardModelArrayList.add(new CardModel(6,"The most famous FPS of Xbox ?","Halo"));
        cardModelArrayList.add(new CardModel(6,"Famous MOBA ?","League of Legends"));
        cardModelArrayList.add(new CardModel(6,"A man with mustache and Italian name ? ","Mario"));
        cardModelArrayList.add(new CardModel(6,"Catch'em all !","Pokemon"));
        cardModelArrayList.add(new CardModel(7,"Paris monument ?","Eiffel Tower"));
        cardModelArrayList.add(new CardModel(7,"Chinese landmark ? ","Great Wall of China"));
        cardModelArrayList.add(new CardModel(7,"Rio de Janeiro statue","Christ the Redeemer"));
        cardModelArrayList.add(new CardModel(7,"Istanbul religious building ?","Hagia Sophia"));
        cardModelArrayList.add(new CardModel(7,"New York statue","Statue of Freedom"));
        cardModelArrayList.add(new CardModel(7,"Egypt best monument ?","Giza pyramid"));

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        if(!(dbCardle.checkTableEmpty("Deck"))){
            for (int i = 0; i < deckModelArrayList.size(); i++){
                dbCardle.addNewDeck(deckModelArrayList.get(i).getDeckName());
            }
            for (int i = 0; i < cardModelArrayList.size(); i++){
                System.out.println(i);
                dbCardle.addNewCard(cardModelArrayList.get(i).getQuestion(),
                                    cardModelArrayList.get(i).getResponse(),
                                    cardModelArrayList.get(i).getIdCard());
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

        createDeck.setOnClickListener(v -> {
            // opening a new activity via a intent.
            Intent i = new Intent(Menu.this, EmptyDeck.class);
            startActivity(i);
        });

    }
}
