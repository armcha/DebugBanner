package io.armcha.debugbanner

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.ViewGroup

class DebugBanner private constructor(app: Application, private var banner: Banner) : Application.ActivityLifecycleCallbacks by ActivityEmptyLifecycleCallbacks() {

    companion object {
        fun init(application: Application, banner: Banner = Banner()) {
            DebugBanner(application, banner)
        }
    }

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        if (activity == null)
            return

        val decorView = activity.window.decorView as ViewGroup
        fun dip(value: Int): Int = (value * activity.resources.displayMetrics.density).toInt()
        val params = ViewGroup.LayoutParams(dip(70), dip(70))
        if (activity is BannerView) {
            banner = activity.createBanner()
        }
        val debugBannerView = DebugBannerView(activity).apply {
            updateText(banner.bannerText, banner.textColor)
            updateBannerColor(banner.bannerColor)
        }
        decorView.addView(debugBannerView, params)
    }
}