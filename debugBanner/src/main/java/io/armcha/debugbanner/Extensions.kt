package io.armcha.debugbanner

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.WindowManager


internal fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

internal val isAtLeastLollipop: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

internal var View.safeElevation: Float
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

internal fun Context.dip(value: Int): Float = value * resources.displayMetrics.density

fun Context.getScreenWidth(): Float {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x.toFloat()
}

