package com.application.cardle.course;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.application.cardle.DataBase;
import com.application.cardle.R;
import com.application.cardle.R.id;
import com.application.cardle.card.CardModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;

public final class CourseActivity extends AppCompatActivity {

    // compnent for ViewModel
    private HashMap _$_findViewCache;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.course_training);

        // get the previous activity
        Bundle extras = getIntent().getExtras();
        String deckName = extras.getString("deck");

        // creating a new database class and passing our context to it
        DataBase dbCardle = new DataBase(CourseActivity.this);

        // pick all the cards in deck
        ArrayList<CardModal> cardList = dbCardle.readCards(dbCardle.getIdDeck(deckName).toString());

        // get our viewmodal
        ViewModel mainViewM = new ViewModelProvider(this).get(CourseViewModal.class);
        Intrinsics.checkExpressionValueIsNotNull(mainViewM, "ViewModelProviders\n     …actViewModel::class.java)");
        final CourseViewModal viewModel = (CourseViewModal) mainViewM;

        // need a view table for the class CourseViewModal to get these data
        // dbCardle.createView(deckName);


        viewModel.getModelStream().observe((LifecycleOwner)this, (Observer)(new Observer() {
            public void onChanged(Object var1) {
                this.onChanged((CourseModal)var1);
            }

            public final void onChanged(CourseModal it) {
                CourseActivity mainViewM = CourseActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                mainViewM.bindCard(it);
            }
        }));

        ((MotionLayout)this._$_findCachedViewById(id.motionLayout)).setTransitionListener((TransitionListener)(new TransitionAdapter() {
            public void onTransitionCompleted(@NotNull MotionLayout motionLayout, int currentId) {
                Intrinsics.checkParameterIsNotNull(motionLayout, "motionLayout");
                switch(currentId) {
                    case R.id.offScreenUnlike:
                    case R.id.offScreenLike:
                        motionLayout.setProgress(0.0F);
                        motionLayout.setTransition(R.id.start, R.id.detail);
                        viewModel.swipe();
                    default:
                }
            }
        }));
        ((FloatingActionButton)this._$_findCachedViewById(id.likeFloating)).setOnClickListener((OnClickListener)(it -> ((MotionLayout) CourseActivity.this._$_findCachedViewById(id.motionLayout)).transitionToState(id.like)));
        ((FloatingActionButton)this._$_findCachedViewById(id.unlikeFloating)).setOnClickListener((OnClickListener)(it -> ((MotionLayout) CourseActivity.this._$_findCachedViewById(id.motionLayout)).transitionToState(id.unlike)));
    }

    private void bindCard(CourseModal model) {
        ((ConstraintLayout)this._$_findCachedViewById(id.containerCardOne)).setBackgroundColor(model.getCardTop().getBackgroundColor());
        TextView mainViewM = (TextView)this._$_findCachedViewById(id.name);
        Intrinsics.checkExpressionValueIsNotNull(mainViewM, "name");
        mainViewM.setText((CharSequence)(model.getCardTop().getName() + ", " + model.getCardTop().getAge()));
        mainViewM = (TextView)this._$_findCachedViewById(id.description);
        Intrinsics.checkExpressionValueIsNotNull(mainViewM, "description");
        mainViewM.setText((CharSequence)model.getCardTop().getDescription());
        ((ConstraintLayout)this._$_findCachedViewById(id.containerCardTwo)).setBackgroundColor(model.getCardBottom().getBackgroundColor());
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }
        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }
}