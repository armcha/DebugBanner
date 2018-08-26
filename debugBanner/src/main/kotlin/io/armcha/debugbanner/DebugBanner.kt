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
        val localBanner = if (activity is BannerView) {
            activity.createBanner()
        } else {
            banner
        }
        val debugBannerView = DebugBannerView(activity).apply {
            updateText(localBanner.bannerText, localBanner.textColorRes)
            updateBannerColor(localBanner.bannerColorRes)
            bannerGravity = localBanner.bannerGravity
        }
        val bannerSize = activity.resources.getDimension(R.dimen.banner_default_size_debug).toInt()
        val params = ViewGroup.MarginLayoutParams(bannerSize, bannerSize)
        decorView.addView(debugBannerView, params)

        //Small hack for pre lollipop devices,
        //there is no problem for getting status bar height in this way,
        //because we use it only for pre lollipop devices, and I know about windowInstets :D
        if (!isAtLeastLollipop)
            debugBannerView.translationY = activity.getStatusBarHeight()
    }
}