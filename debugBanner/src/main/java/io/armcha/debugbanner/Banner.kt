package io.armcha.debugbanner

import androidx.annotation.ColorRes

data class Banner(val bannerGravity: BannerGravity = BannerGravity.START,
                  @ColorRes val bannerColorRes: Int = android.R.color.holo_red_dark,
                  @ColorRes val textColorRes: Int = android.R.color.white,
                  val bannerText: String = "DEBUG") {
}