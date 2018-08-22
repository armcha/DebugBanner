package io.armcha.debugbanner

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat


class DebugBannerView(context: Context, attrs: AttributeSet? = null)
    : FrameLayout(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.RED }
    private val path: Path = Path()
    private val textView: TextView by lazy { TextView(context) }
    private val bannerHeight: Float by lazy { dip(30).toFloat() }

    init {
        textView.apply {
            setTextColor(Color.BLACK)
            includeFontPadding = false
            rotation = -45f
            typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            textSize = 15f
        }
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        addView(textView, layoutParams)
        setBackgroundColor(Color.TRANSPARENT)
        safeElevation = 30f
    }

    fun updateText(text: String, textColor: Int) {
        textView.text = text
        textView.setTextColor(ContextCompat.getColor(context, textColor))
    }

    fun updateBannerColor(bannerColor: Int) {
        paint.color = ContextCompat.getColor(context, bannerColor)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (textView.x == 0f) {
            textView.x = (height / 2f - bannerHeight / 4)
            textView.y = (height / 2f - bannerHeight / 4)
        }
    }

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