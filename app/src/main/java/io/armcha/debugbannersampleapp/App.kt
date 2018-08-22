package io.armcha.debugbannersampleapp

import android.app.Application
import io.armcha.debugbanner.Banner
import io.armcha.debugbanner.DebugBanner


/**
 *
 * Created by Arman Chatikyan on 20 Aug 2018
 *
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DebugBanner.init(
                application = this)
    }
}