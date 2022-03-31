package com.application.cardle.course;

import android.os.Build;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;

public final class CourseModal {
    @NotNull
    private final CourseCardModal cardTop;
    @NotNull
    private final CourseCardModal cardBottom;
    @NotNull
    public final CourseCardModal getCardTop() {
        return this.cardTop;
    }
    @NotNull
    public final CourseCardModal getCardBottom() {
        return this.cardBottom;
    }

    public CourseModal(@NotNull CourseCardModal cardTop, @NotNull CourseCardModal cardBottom) {
        Intrinsics.checkParameterIsNotNull(cardTop, "cardTop");
        Intrinsics.checkParameterIsNotNull(cardBottom, "cardBottom");
        //super();
        this.cardTop = cardTop;
        this.cardBottom = cardBottom;
    }

    @NotNull
    public final CourseCardModal component1() {
        return this.cardTop;
    }

    @NotNull
    public final CourseCardModal component2() {
        return this.cardBottom;
    }

    @NotNull
    public final CourseModal copy(@NotNull CourseCardModal cardTop, @NotNull CourseCardModal cardBottom) {
        Intrinsics.checkParameterIsNotNull(cardTop, "cardTop");
        Intrinsics.checkParameterIsNotNull(cardBottom, "cardBottom");
        return new CourseModal(cardTop, cardBottom);
    }

    // $FF: synthetic method
    public static CourseModal copy$default(CourseModal var0, CourseCardModal var1, CourseCardModal var2, int var3, Object var4) {
        if ((var3 & 1) != 0) {
            var1 = var0.cardTop;
        }

        if ((var3 & 2) != 0) {
            var2 = var0.cardBottom;
        }

        return var0.copy(var1, var2);
    }

    @NotNull
    public String toString() {
        return "CourseModel(cardTop=" + this.cardTop + ", cardBottom=" + this.cardBottom + ")";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int hashCode() {
        CourseCardModal var10000 = this.cardTop;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        CourseCardModal var10001 = this.cardBottom;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof CourseModal) {
                CourseModal var2 = (CourseModal)var1;
                if (Intrinsics.areEqual(this.cardTop, var2.cardTop) && Intrinsics.areEqual(this.cardBottom, var2.cardBottom)) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}