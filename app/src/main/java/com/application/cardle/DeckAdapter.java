package com.application.cardle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.Viewholder> {

    // variable for our array list and context
    private final Context context;
    private final ArrayList<DeckModel> DeckList;

    // Constructor
    public DeckAdapter(Context context, ArrayList<DeckModel> DeckList) {
        this.context = context;
        this.DeckList = DeckList;
    }

    @NonNull
    @Override
    public DeckAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deck, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DeckAdapter.Viewholder holder, int position) {
        // to set data to textview of each card layout
        DeckModel model = DeckList.get(position);
        holder.deckNameTV.setText(model.getDeckName());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return DeckList.size();
    }

    // View holder class for initializing of
    // your views such as Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView deckNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            deckNameTV = itemView.findViewById(R.id.DeckName);
        }
    }
}


