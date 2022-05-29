package com.asmanmirza.adsmodule

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.easyads.admob.interstitial.AdmobInterstitialAdHelper.loadAdmobInterstitialAd
import com.asmanmirza.easyads.admob.interstitial.AdmobInterstitialAdHelper.showAdmobInterstitialAd
import com.asmanmirza.easyads.admob.interstitial.AdmobInterstitialAdListener
import com.asmanmirza.easyads.facebook.bannerAds.FBBannerAdHelper.loadFBBannerAd
import com.asmanmirza.easyads.facebook.interstitialAds.FBInterstitialAdHelper.loadFBInterstitialAd
import com.asmanmirza.easyads.facebook.interstitialAds.FBInterstitialAdHelper.showFBInterstitialAds
import com.asmanmirza.easyads.facebook.rewardedVideoAds.FBRewardedAdHelper.loadFBRewardedAds
import com.asmanmirza.easyads.facebook.rewardedVideoAds.FBRewardedAdHelper.showFBRewardedAd
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), AdmobInterstitialAdListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFBInterstitialAd("IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID")
        loadFBRewardedAds("VID_HD_9_16_39S_APP_INSTALL#YOUR_PLACEMENT_ID")
        loadAdmobInterstitialAd("ca-app-pub-3940256099942544/1033173712", this)

        findViewById<FloatingActionButton>(R.id.fab_show).setOnClickListener {
            showFBInterstitialAds(true)
            showFBRewardedAd(true)
            showAdmobInterstitialAd()
        }

        loadFBBannerAd(findViewById(R.id.fbBanner), "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID")
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }

    override fun onLoaded() {
        Toast.makeText(this, "loaded", Toast.LENGTH_SHORT).show()
    }
}