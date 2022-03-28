package com.application.cardle.card;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.application.cardle.R;
import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.Viewholder> {

    private final Context context;
    private final ArrayList<CardModal> CardList;

    /**
     * Pattern Card Adapter : Represents the card modal adapter.
     * @param context   name of activity context
     * @param CardList  list of cards modal
     */
    public CardAdapter(Context context, ArrayList<CardModal> CardList) {
        this.context = context;
        this.CardList = CardList;
    }

    /**
     * To inflate the layout for each card item of viewpager2.
     * @param parent    group of view parent
     * @param viewType  view type
     * @return          the layout for each card item
     */
    @NonNull
    @Override
    public CardAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_modal, parent, false);
        return new Viewholder(view);
    }

    /**
     * To set data to textview of each card layout.
     * @param holder    holder view of the card adapter
     * @param position  position of the card
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardAdapter.Viewholder holder, int position) {
        CardModal model = CardList.get(position);
        holder.CardIdCardTV.setText(model.getIdCard().toString());
        holder.CardQuestionTV.setText(model.getQuestion());
        holder.CardResponseTV.setText(model.getResponse());
    }

    /**
     * Show the number of cards
     * @return size of card list
     */
    @Override
    public int getItemCount() {
        return CardList.size();
    }

    /**
     * View holder class for initializing views
     */
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