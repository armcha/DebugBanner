package io.armcha.debugbannersampleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.armcha.debugbanner.Banner
import io.armcha.debugbanner.BannerView
import io.armcha.debugbanner.banner
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BannerView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        secondActivityButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        val banner = banner {
            setAA()
        }
    }

    override fun createBanner() = Banner()
}
