package com.application.cardle.deck;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.application.cardle.DataBase;
import com.application.cardle.MainActivity;
import com.application.cardle.R;
import com.application.cardle.course.CourseActivity;

import java.util.ArrayList;

public class DeckAdapter extends RecyclerView.Adapter<DeckAdapter.Viewholder> {

    private final ArrayList<DeckModal> DeckList;
    private final int layoutModal;
    private final Context context;

    /**
     * Pattern Deck Adapter : Represents the deck modal adapter.
     * @param DeckList  list of decks modal
     */
    public DeckAdapter(Context context,ArrayList<DeckModal> DeckList, int layoutModal) {
        this.context = context;
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
                } else if(layoutModal == R.layout.deck_info){
                    Intent i = new Intent(itemView.getContext(), CourseActivity.class);
                    i.putExtra("activity","course");
                    i.putExtra("deck",deckNameTV.getText());
                    itemView.getContext().startActivity(i);
                }
            });

            // open a dialog to give the choice to delete the deck
            itemView.setOnLongClickListener(w -> {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.deck_dialog);

                final Button buttonYes = dialog.findViewById(R.id.buttonYes);
                final Button buttonNo = dialog.findViewById(R.id.buttonNo);

                // button no listener : deny
                buttonNo.setOnClickListener(v -> dialog.dismiss());

                // button yes listener : delete deck + cards
                buttonYes.setOnClickListener(v ->{
                    DataBase dbCardle = new DataBase(context);
                    dbCardle.delAllCard(deckNameTV.getText().toString());
                    dialog.dismiss();
                    Intent i = new Intent(itemView.getContext(), MainActivity.class);
                    context.startActivity(i);
                });
                dialog.show();
                return true;
            });
        }
    }
}