package com.application.cardle.course;

import android.os.Build;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;

public final class CourseModal {

    // component class
    private final CourseCardModal cardTop, cardBottom;

    /**
     * Constructor CourseModal : Represents the course modal session.
     * @param cardTop       card top    (to see the card that contain only question)
     * @param cardBottom    card bottom (to see the response)
     */
    public CourseModal(@NotNull CourseCardModal cardTop, @NotNull CourseCardModal cardBottom) {
        Intrinsics.checkParameterIsNotNull(cardTop, "cardTop");
        Intrinsics.checkParameterIsNotNull(cardBottom, "cardBottom");
        this.cardTop = cardTop;
        this.cardBottom = cardBottom;
    }

    /**
     * Get the top card.
     * @return top card
     */
    public final CourseCardModal getCardTop() {
        return this.cardTop;
    }

    /**
     * Get the bottom card.
     * @return bottom card
     */
    public final CourseCardModal getCardBottom() {
        return this.cardBottom;
    }

    /**
     * Print object class information.
     * @return information
     */
    @NotNull
    public String toString() {
        return "CourseModel(cardTop=" + this.cardTop + ", cardBottom=" + this.cardBottom + ")";
    }

    /**
     * Hash question and response in code for compatibility.
     * @return hash code
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int hashCode() {
        CourseCardModal hashCT = this.cardTop;
        int elem = (hashCT != null ? hashCT.hashCode() : 0) * 31;
        CourseCardModal hashCB = this.cardBottom;
        return elem + (hashCB != null ? hashCB.hashCode() : 0);
    }

    /**
     * Compare object with this class.
     * @param elem Object element
     * @return boolean
     */
    public boolean equals(@Nullable Object elem) {
        if (this != elem) {
            if (elem instanceof CourseModal) {
                CourseModal var2 = (CourseModal)elem;
                return Intrinsics.areEqual(this.cardTop, var2.cardTop) && Intrinsics.areEqual(this.cardBottom, var2.cardBottom);
            }
            return false;
        } else {
            return true;
        }
    }
}