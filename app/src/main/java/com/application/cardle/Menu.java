package com.application.cardle;

import android.os.Bundle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    private RecyclerView DeckRV;

    // Arraylist for storing data
    private ArrayList<DeckModel> DeckModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        DeckRV = findViewById(R.id.Deck);

        // here we have created new array list and added data to it.
        DeckModelArrayList = new ArrayList<>();
        DeckModelArrayList.add(new DeckModel("DSA in Java"));
        DeckModelArrayList.add(new DeckModel("Java Deck"));
        DeckModelArrayList.add(new DeckModel("C++ Deck"));
        DeckModelArrayList.add(new DeckModel("DSA in C++"));
        DeckModelArrayList.add(new DeckModel("Kotlin for Android"));
        DeckModelArrayList.add(new DeckModel("Java for Android"));
        DeckModelArrayList.add(new DeckModel("HTML and CSS"));

        // we are initializing our adapter class and passing our arraylist to it.
        DeckAdapter DeckAdapter = new DeckAdapter(this, DeckModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        DeckRV.setLayoutManager(linearLayoutManager);
        DeckRV.setAdapter(DeckAdapter);
    }
}
