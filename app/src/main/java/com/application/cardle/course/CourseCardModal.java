package com.application.cardle.course;

import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;

public final class CourseCardModal {

    // component class
    private final String question, response;
    private final int numberCard, backgroundColor;

    /**
     * Constructor CourseCardModal : Represents the card modal in course session.
     * @param question          question
     * @param numberCard        card number
     * @param response          response
     * @param backgroundColor   background color
     */
    public CourseCardModal(String question, int numberCard,String response, @ColorInt int backgroundColor) {
        Intrinsics.checkParameterIsNotNull(question, "question");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.question = question;
        this.numberCard = numberCard;
        this.response = response;
        this.backgroundColor = backgroundColor;
    }

    /**
     * Get question of card.
     * @return question
     */
    public final String getQuestion() {
        return this.question;
    }

    /**
     * Get card number.
     * @return card number
     */
    public final int getNumberCard() {
        return this.numberCard;
    }

    /**
     * Get response.
     * @return response
     */
    public final String getResponse() {
        return this.response;
    }

    /**
     * Get background color.
     * @return background color
     */
    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    /**
     * Print object class information.
     * @return information
     */
    @NonNull
    public String toString() {
        return "CourseCardModel(question=" + this.question + ", numberCard=" + this.numberCard + ", response=" + this.response + ", backgroundColor=" + this.backgroundColor + ")";
    }

    /**
     * Hash question and response in code for compatibility.
     * @return hash code
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int hashCode() {
        String hashQuestion = this.question;
        int elem = ((hashQuestion != null ? hashQuestion.hashCode() : 0) * 31 + Integer.hashCode(this.numberCard)) * 31;
        String hashResponse = this.response;
        return (elem + (hashResponse != null ? hashResponse.hashCode() : 0)) * 31 + Integer.hashCode(this.backgroundColor);
    }

    /**
     * Compare object with this class.
     * @param elem Object element
     * @return boolean
     */
    public boolean equals(@Nullable Object elem) {
        if (this != elem) {
            if (elem instanceof CourseCardModal) {
                CourseCardModal cardModal = (CourseCardModal)elem;
                return Intrinsics.areEqual(this.question, cardModal.question) && this.numberCard == cardModal.numberCard && Intrinsics.areEqual(this.response, cardModal.response) && this.backgroundColor == cardModal.backgroundColor;
            }
            return false;
        } else {
            return true;
        }
    }
}