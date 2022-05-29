package com.asmanmirza.easyads.facebook

import android.app.Application
import com.facebook.ads.AudienceNetworkAds


object FacebookAdsManager {
    var adManagerInitialized = false

    fun Application.initFBAds() {
        if (!adManagerInitialized) {
            AudienceNetworkAds.initialize(this)
            adManagerInitialized = true
        }
    }
}