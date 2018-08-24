package io.armcha.debugbanner

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat


internal class DebugBannerView(context: Context, attrs: AttributeSet? = null)
    : FrameLayout(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.RED }
    private val path: Path = Path()
    private val textView: TextView by lazy { TextView(context) }
    private val bannerHeight: Float by lazy { dip(30).toFloat() }
    var bannerGravity: BannerGravity = BannerGravity.START

    init {
        textView.apply {
            setTextColor(Color.BLACK)
            includeFontPadding = false
            rotation = -45f
            typeface = Typeface.defaultFromStyle(Typeface.BOLD)
            textSize = 13f
        }
        val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER)
        addView(textView, layoutParams)
        setBackgroundColor(Color.TRANSPARENT)
        ViewCompat.setElevation(this, 30f)
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
            textView.x = -bannerHeight / 4
            textView.y = -bannerHeight / 4
            if (bannerGravity == BannerGravity.END) {
                rotation = 90f
                translationX = context.getScreenWidth() - measuredHeight
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawPath(path.apply {
            moveTo(width - bannerHeight, 0f)
            lineTo(width.toFloat(), 0f)
            lineTo(0f, height.toFloat())
            lineTo(0f, height - bannerHeight)
            close()
        }, paint)
    }
}