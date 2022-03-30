package com.application.cardle;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.application.cardle.R.id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

import kotlin.jvm.internal.Intrinsics;


public final class CourseActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.course_training);
        ViewModel var10000 = ViewModelProviders.of((FragmentActivity)this).get(TinderContactViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "ViewModelProviders\n     …actViewModel::class.java)");
        final TinderContactViewModel viewModel = (TinderContactViewModel)var10000;
        //viewModel.loadCards();
        viewModel.getModelStream().observe((LifecycleOwner)this, (Observer)(new Observer() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onChanged(Object var1) {
                this.onChanged((TinderContactModel)var1);
            }

            public final void onChanged(TinderContactModel it) {
                CourseActivity var10000 = CourseActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                var10000.bindCard(it);
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
        ((FloatingActionButton)this._$_findCachedViewById(id.likeFloating)).setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                ((MotionLayout) CourseActivity.this._$_findCachedViewById(id.motionLayout)).transitionToState(R.id.like);
            }
        }));
        ((FloatingActionButton)this._$_findCachedViewById(id.unlikeFloating)).setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                ((MotionLayout) CourseActivity.this._$_findCachedViewById(id.motionLayout)).transitionToState(R.id.unlike);
            }
        }));
    }

    private final void bindCard(TinderContactModel model) {
        ((ConstraintLayout)this._$_findCachedViewById(id.containerCardOne)).setBackgroundColor(model.getCardTop().getBackgroundColor());
        TextView var10000 = (TextView)this._$_findCachedViewById(id.name);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "name");
        var10000.setText((CharSequence)(model.getCardTop().getName() + ", " + model.getCardTop().getAge()));
        var10000 = (TextView)this._$_findCachedViewById(id.description);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "description");
        var10000.setText((CharSequence)model.getCardTop().getDescription());
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
