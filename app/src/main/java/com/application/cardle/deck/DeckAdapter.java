package com.application.cardle.deck;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.application.cardle.R;

import java.util.ArrayList;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.Viewholder> {

    private final ArrayList<DeckModal> DeckList;
    private final int layoutModal;

    /**
     * Pattern Deck Adapter : Represents the deck modal adapter.
     * @param DeckList  list of decks modal
     */
    public DeckAdapter(ArrayList<DeckModal> DeckList, int layoutModal) {
        this.DeckList = DeckList;
        this.layoutModal = layoutModal;
    }

    /**
     * To inflate the layout for each card item of recycler view.
     * @param parent    group of view parent
     * @param viewType  view type
     * @return          the layout for each deck item
     */
    @NonNull
    @Override
    public DeckAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutModal, parent, false);
        return new Viewholder(view);
    }

    /**
     * To set data to textview of each card layout.
     * @param holder    holder view of the deck adapter
     * @param position  position of the deck
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DeckAdapter.Viewholder holder, int position) {
        DeckModal model = DeckList.get(position);
        holder.deckNameTV.setText(model.getDeckName());
    }

    /**
     * Show the number of decks
     * @return size of deck list
     */
    @Override
    public int getItemCount() { return DeckList.size();}

    /**
     * View holder class for initializing views
     */
    public class Viewholder extends RecyclerView.ViewHolder {

        private final TextView deckNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            deckNameTV = itemView.findViewById(R.id.DeckName);

            // start the activity of already existing deck of card(s)
            // it's used for editing
            itemView.setOnClickListener(v -> {

                if(layoutModal == R.layout.deck_modal){
                    Intent i = new Intent(itemView.getContext(), DeckCreate.class);
                    i.putExtra("activity","already");
                    i.putExtra("deck",deckNameTV.getText());
                    itemView.getContext().startActivity(i);
                }
            });
        }
    }
}