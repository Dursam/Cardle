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

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Viewholder> {

    // variable for our array list and context
    private final Context context;
    private final ArrayList<CardModel> CardList;

    // Constructor
    public CardAdapter(Context context, ArrayList<CardModel> CardList) {
        this.context = context;
        this.CardList = CardList;
    }

    @NonNull
    @Override
    public CardAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new Viewholder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardAdapter.Viewholder holder, int position) {
        // to set data to textview of each card layout
        CardModel model = CardList.get(position);
        holder.CardIdCardTV.setText(model.getIdCard().toString());
        holder.CardQuestionTV.setText(model.getQuestion());
        holder.CardResponseTV.setText(model.getResponse());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return CardList.size();
    }

    // View holder class for initializing of
    // your views such as Imageview.
    public static class Viewholder extends RecyclerView.ViewHolder {

        private final TextView CardIdCardTV;
        private final TextView CardQuestionTV;
        private final TextView CardResponseTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            CardIdCardTV = itemView.findViewById(R.id.textViewGetIdCard);
            CardQuestionTV = itemView.findViewById(R.id.textViewGetQuestion);
            CardResponseTV = itemView.findViewById(R.id.textViewGetResponse);
        }
    }
}
