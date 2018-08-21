package io.armcha.debugbanner

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView


class DebugBannerView(context: Context, attrs: AttributeSet? = null)
    : FrameLayout(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.RED }
    private val path: Path = Path()
    private val textView: TextView = TextView(context)
    private var bannerHeight: Float = 0f

    init {
        textView.text = "DEBUG"
        textView.setTextColor(Color.BLACK)
        textView.includeFontPadding = false
        textView.rotation = -45f
        textView.typeface = Typeface.defaultFromStyle(Typeface.BOLD)
        textView.textSize = 15f
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        addView(textView, layoutParams)
        setBackgroundColor(Color.TRANSPARENT)
        elevation = 30f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        bannerHeight = dip(30).toFloat()
        textView.x = (height / 2f - bannerHeight / 4)
        textView.y = (height / 2f - bannerHeight / 4)
    }

    fun dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.moveTo(width - bannerHeight, 0f)
        path.lineTo(width.toFloat(), 0f)
        path.lineTo(0f, height.toFloat())
        path.lineTo(0f, height - bannerHeight)
        path.close()
        canvas?.drawPath(path, paint)
    }
}