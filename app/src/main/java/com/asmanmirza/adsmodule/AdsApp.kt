package com.asmanmirza.adsmodule

import android.app.Application
import com.asmanmirza.easyads.admob.AdMobAdsManager.initAdMobAdsManager
import com.asmanmirza.easyads.facebook.FacebookAdsManager.initFBAds

class AdsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initFBAds()
        initAdMobAdsManager()
    }
}