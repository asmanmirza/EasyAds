package com.asmanmirza.easyads.admob

import android.app.Application
import com.google.android.gms.ads.MobileAds

object AdMobAdsManager {
    var admobManagerInitialized = false

    fun Application.initAdMobAdsManager() {
        MobileAds.initialize(this) {}
        admobManagerInitialized = true
    }
}