package com.application.cardle;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\b\u0010\u0016\u001a\u00020\u0015H\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0006¨\u0006\u0017"},
        d2 = {"Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "bottomCard", "Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;", "getBottomCard", "()Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;", "currentIndex", "", "data", "", "modelStream", "Landroidx/lifecycle/LiveData;", "Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactModel;", "getModelStream", "()Landroidx/lifecycle/LiveData;", "stream", "Landroidx/lifecycle/MutableLiveData;", "topCard", "getTopCard", "swipe", "", "updateCards", "app_debug"}
)
public final class TinderContactViewModel extends ViewModel {
    private final MutableLiveData stream = new MutableLiveData();
    private final int[] cs = new int[]{Color.parseColor("#c50e29"),
                                       Color.parseColor("#c60055"),
                                       Color.parseColor("#aa00c7")};
    private final List data =  loadCards();
//            = CollectionsKt.listOf(new TinderContactCardModel[]
//            {new TinderContactCardModel("Rodrigo Dominguez", 27, "Esto es una descripcion de ejemplo", Color.parseColor("#c50e29")),
//                    new TinderContactCardModel("CerveChat Dominguez", 2, "Esto es una descripcion de ejemplo", Color.parseColor("#c60055")),
//                    new TinderContactCardModel("Sofia Jerez Test", 27, "Esto es una descripcion de ejemplo", Color.parseColor("#aa00c7")),
//                    new TinderContactCardModel("Maria Perez", 34, "Esto es una descripcion de ejemplo", Color.parseColor("#3f1dcb")),
//                    new TinderContactCardModel("Rodrigo Dominguez", 27, "Esto es una descripcion de ejemplo", Color.parseColor("#0043ca")),
//                    new TinderContactCardModel("Rodrigo Dominguez", 222, "Esto es una descripcion de ejemplo", Color.parseColor("#005ecb")),
//                    new TinderContactCardModel("Perez Gonzalez", 45, "Esto es una descripcion de ejemplo", Color.parseColor("#00b686")),
//                    new TinderContactCardModel("Tomas Dominguez", 43, "Esto es una descripcion de ejemplo", Color.parseColor("#00b248")),
//                    new TinderContactCardModel("Rodrigo Dominguez", 44, "Esto es una descripcion de ejemplo", Color.parseColor("#32cb00")),
//                    new TinderContactCardModel("Lopez Jose Jose", 87, "Esto es una descripcion de ejemplo", Color.parseColor("#90cc00")),
//                    new TinderContactCardModel("Felipe Felipe Lopez", 23, "Esto es una descripcion de ejemplo", Color.parseColor("#c7b800")),
//                    new TinderContactCardModel("Nicolas Lucas Test", 27, "Esto es una descripcion de ejemplo", Color.parseColor("#c79400")),
//                    new TinderContactCardModel("John", 237, "Esto es una descripcion de ejemplo", Color.parseColor("#c56200"))});
    private int currentIndex;

    @NotNull
    public final LiveData getModelStream() {
        return (LiveData)this.stream;
    }

    public List loadCards(){
        //lire les base de donnee ici pour les questions et les reponses
        String questions[] = {"Rodrigo Dominguez","CerveChat Dominguez",
                "Sofia Jerez Test","Maria Perez","Rodrigo Dominguez",
                "Perez Gonzalez","Tomas Dominguez","Lopez Jose Jose",
                "Felipe Felipe Lopez","Nicolas Lucas Test","John"};
        String answer[] = {"cccp","kncc","plas","ik","qwe","wd","am","tk","qwd","tfd","iop"};
        TinderContactCardModel[] cards = new TinderContactCardModel[questions.length];
        for(int i = 0;i < questions.length;i++){
            cards[i] = new TinderContactCardModel(questions[i],i,answer[i],cs[i%(cs.length)]);
        }
        List cardsdata = CollectionsKt.listOf(cards);
        return  cardsdata;
    }

    private final TinderContactCardModel getTopCard() {
        return (TinderContactCardModel)this.data.get(this.currentIndex % this.data.size());
    }

    private final TinderContactCardModel getBottomCard() {
        return (TinderContactCardModel)this.data.get((this.currentIndex + 1) % this.data.size());
    }

    public final void swipe() {
        ++this.currentIndex;
        this.updateCards();
    }

    private final void updateCards() {
        this.stream.setValue(new com.application.cardle.TinderContactModel(this.getTopCard(), this.getBottomCard()));
    }

    public TinderContactViewModel() {
        this.updateCards();
    }
}

