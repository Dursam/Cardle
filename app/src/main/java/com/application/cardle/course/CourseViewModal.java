package com.application.cardle.course;

import android.graphics.Color;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import kotlin.collections.CollectionsKt;

public final class CourseViewModal extends ViewModel {

    private final MutableLiveData stream = new MutableLiveData();
    private final int[] cs = new int[]{Color.parseColor("#c50e29"),
                                       Color.parseColor("#c60055"),
                                       Color.parseColor("#aa00c7")};
    private int currentIndex;
    private List data = loadCards();

    @NotNull
    public final LiveData getModelStream() {
        return (LiveData)this.stream;
    }

    public List loadCards(){

        // Sample of questions/responses
        String questions[] = {"What Dennis Ritchie created ?","The most PL used in France ?",
                "Big Data PL ?","What Matsumoto created ?","Quote a functionel ?"};
        String answer[] = {"C","Java","Python","Ruby","Haskell"};

        //String questions[] = new String[cardModals.size()];
        //String answer[] = new String[cardModals.size()];
        //for(int i =0; i < cardModals.size(); i++){
        //    questions[i] = cardModals.get(i).getQuestion();
        //    answer[i] = cardModals.get(i).getResponse();
        //}

        CourseCardModal[] cards = new CourseCardModal[questions.length];
        for(int i = 0;i < questions.length;i++){
            cards[i] = new CourseCardModal(questions[i],i,answer[i],cs[i%(cs.length)]);
        }
        List cardsdata = CollectionsKt.listOf(cards);
        return  cardsdata;
    }

    private CourseCardModal getTopCard() {
        return (CourseCardModal)this.data.get(this.currentIndex % this.data.size());
    }

    private CourseCardModal getBottomCard() {
        return (CourseCardModal)this.data.get((this.currentIndex + 1) % this.data.size());
    }

    public void swipe() {
        ++this.currentIndex;
        this.updateCards();
    }

    private void updateCards() {
        this.stream.setValue(new CourseModal(this.getTopCard(), this.getBottomCard()));
    }

    public CourseViewModal() {
        this.updateCards();
    }
}