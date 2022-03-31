package com.application.cardle.course;

import android.os.Build;
import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kotlin.jvm.internal.Intrinsics;

public final class CourseCardModal {
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

    public CourseCardModal(@NotNull String name, int age, @NotNull String description, @ColorInt int backgroundColor) {
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
    public final CourseCardModal copy(@NotNull String name, int age, @NotNull String description, @ColorInt int backgroundColor) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(description, "description");
        return new CourseCardModal(name, age, description, backgroundColor);
    }

    // $FF: synthetic method
    public static CourseCardModal copy$default(CourseCardModal var0, String var1, int var2, String var3, int var4, int var5, Object var6) {
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
        return "CourseCardModel(name=" + this.name + ", age=" + this.age + ", description=" + this.description + ", backgroundColor=" + this.backgroundColor + ")";
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
            if (var1 instanceof CourseCardModal) {
                CourseCardModal var2 = (CourseCardModal)var1;
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