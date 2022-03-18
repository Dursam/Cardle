package com.application.cardle;

import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        RecyclerView deckRV = findViewById(R.id.Deck);

        // here we have created new array list and added data to it.
        // Arraylist for storing data
        ArrayList<DeckModel> deckModelArrayList = new ArrayList<>();
        deckModelArrayList.add(new DeckModel("DSA in Java"));
        deckModelArrayList.add(new DeckModel("Java Deck"));
        deckModelArrayList.add(new DeckModel("C++ Deck"));
        deckModelArrayList.add(new DeckModel("DSA in C++"));
        deckModelArrayList.add(new DeckModel("Kotlin for Android"));
        deckModelArrayList.add(new DeckModel("Java for Android"));
        deckModelArrayList.add(new DeckModel("HTML and CSS"));

        // we are initializing our adapter class and passing our arraylist to it.
        DeckAdapter DeckAdapter = new DeckAdapter(this, deckModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        deckRV.setLayoutManager(linearLayoutManager);
        deckRV.setAdapter(DeckAdapter);
    }
}
