package com.application.cardle;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"},
        d2 = {"Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactModel;", "", "cardTop", "Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;", "cardBottom", "(Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;)V", "getCardBottom", "()Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;", "getCardTop", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}
)
public final class TinderContactModel {
    @NotNull
    private final TinderContactCardModel cardTop;
    @NotNull
    private final TinderContactCardModel cardBottom;

    @NotNull
    public final TinderContactCardModel getCardTop() {
        return this.cardTop;
    }

    @NotNull
    public final TinderContactCardModel getCardBottom() {
        return this.cardBottom;
    }

    public TinderContactModel(@NotNull TinderContactCardModel cardTop, @NotNull TinderContactCardModel cardBottom) {
        Intrinsics.checkParameterIsNotNull(cardTop, "cardTop");
        Intrinsics.checkParameterIsNotNull(cardBottom, "cardBottom");
        //super();
        this.cardTop = cardTop;
        this.cardBottom = cardBottom;
    }

    @NotNull
    public final TinderContactCardModel component1() {
        return this.cardTop;
    }

    @NotNull
    public final TinderContactCardModel component2() {
        return this.cardBottom;
    }

    @NotNull
    public final TinderContactModel copy(@NotNull TinderContactCardModel cardTop, @NotNull TinderContactCardModel cardBottom) {
        Intrinsics.checkParameterIsNotNull(cardTop, "cardTop");
        Intrinsics.checkParameterIsNotNull(cardBottom, "cardBottom");
        return new TinderContactModel(cardTop, cardBottom);
    }

    // $FF: synthetic method
    public static TinderContactModel copy$default(TinderContactModel var0, TinderContactCardModel var1, TinderContactCardModel var2, int var3, Object var4) {
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
        return "TinderContactModel(cardTop=" + this.cardTop + ", cardBottom=" + this.cardBottom + ")";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int hashCode() {
        TinderContactCardModel var10000 = this.cardTop;
        int var1 = (var10000 != null ? var10000.hashCode() : 0) * 31;
        TinderContactCardModel var10001 = this.cardBottom;
        return var1 + (var10001 != null ? var10001.hashCode() : 0);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof TinderContactModel) {
                TinderContactModel var2 = (TinderContactModel)var1;
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