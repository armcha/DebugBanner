package io.armcha.debugbanner

import android.annotation.SuppressLint
import android.os.Build
import android.view.View

fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

val isAtLeastLollipop: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP


var View.safeElevation: Float
    @SuppressLint("NewApi")
    get() {
        return if (isAtLeastLollipop)
            elevation
        else 0f
    }
    @SuppressLint("NewApi")
    set(value) {
        if (isAtLeastLollipop)
            elevation = value
    }
