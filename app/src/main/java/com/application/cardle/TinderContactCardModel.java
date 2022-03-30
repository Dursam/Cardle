package com.application.cardle;

import android.os.Build;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(
        mv = {1, 1, 18},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0003\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0019"},
        d2 = {"Lcom/rodrigodominguez/tindermotionlayout/scenes/TinderContactCardModel;", "", "name", "", "age", "", "description", "backgroundColor", "(Ljava/lang/String;ILjava/lang/String;I)V", "getAge", "()I", "getBackgroundColor", "getDescription", "()Ljava/lang/String;", "getName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"}
)
public final class TinderContactCardModel {
    @NotNull
    private final String name;
    private final int age;
    @NotNull
    private final String description;
    private final int backgroundColor;

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final int getAge() {
        return this.age;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public final int getBackgroundColor() {
        return this.backgroundColor;
    }

    public TinderContactCardModel(@NotNull String name, int age, @NotNull String description, @ColorInt int backgroundColor) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(description, "description");
        //super();
        this.name = name;
        this.age = age;
        this.description = description;
        this.backgroundColor = backgroundColor;
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.age;
    }

    @NotNull
    public final String component3() {
        return this.description;
    }

    public final int component4() {
        return this.backgroundColor;
    }

    @NotNull
    public final TinderContactCardModel copy(@NotNull String name, int age, @NotNull String description, @ColorInt int backgroundColor) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(description, "description");
        return new TinderContactCardModel(name, age, description, backgroundColor);
    }

    // $FF: synthetic method
    public static TinderContactCardModel copy$default(TinderContactCardModel var0, String var1, int var2, String var3, int var4, int var5, Object var6) {
        if ((var5 & 1) != 0) {
            var1 = var0.name;
        }

        if ((var5 & 2) != 0) {
            var2 = var0.age;
        }

        if ((var5 & 4) != 0) {
            var3 = var0.description;
        }

        if ((var5 & 8) != 0) {
            var4 = var0.backgroundColor;
        }

        return var0.copy(var1, var2, var3, var4);
    }

    @NotNull
    public String toString() {
        return "TinderContactCardModel(name=" + this.name + ", age=" + this.age + ", description=" + this.description + ", backgroundColor=" + this.backgroundColor + ")";
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int hashCode() {
        String var10000 = this.name;
        int var1 = ((var10000 != null ? var10000.hashCode() : 0) * 31 + Integer.hashCode(this.age)) * 31;
        String var10001 = this.description;
        return (var1 + (var10001 != null ? var10001.hashCode() : 0)) * 31 + Integer.hashCode(this.backgroundColor);
    }

    public boolean equals(@Nullable Object var1) {
        if (this != var1) {
            if (var1 instanceof TinderContactCardModel) {
                TinderContactCardModel var2 = (TinderContactCardModel)var1;
                if (Intrinsics.areEqual(this.name, var2.name) && this.age == var2.age && Intrinsics.areEqual(this.description, var2.description) && this.backgroundColor == var2.backgroundColor) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }
}