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

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        if(!(dbCardle.checkTableEmpty("Deck"))){
            for (int i = 0; i < deckModelArrayList.size(); i++){
                dbCardle.addNewDeck(deckModelArrayList.get(i).getDeckName());
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
