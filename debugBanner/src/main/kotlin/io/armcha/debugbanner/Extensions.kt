package io.armcha.debugbanner

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.view.View
import android.view.WindowManager


internal fun View.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

internal val isAtLeastLollipop: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

internal fun Context.dip(value: Int): Float = value * resources.displayMetrics.density

fun Context.getScreenWidth(): Float {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = wm.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x.toFloat()
}

fun Context.getStatusBarHeight(): Float {
    var result = 0
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        result = resources.getDimensionPixelSize(resourceId)
    }
    return result.toFloat()
}
