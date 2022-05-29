package com.asmanmirza.easyads.facebook.bannerAds

import android.content.Context
import android.widget.LinearLayout
import com.asmanmirza.easyads.facebook.FacebookAdsManager
import com.facebook.ads.AdSize
import com.facebook.ads.AdView


object FBBannerAdHelper {
    private var mPlacementId = "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID"
    private var mSize = AdSize.BANNER_HEIGHT_50
    private var adView: AdView? = null

    fun Context.loadFBBannerAd(adContainer: LinearLayout, bannerPlacementId: String, size: FBBannerAdSize = FBBannerAdSize.BANNER_HEIGHT_50) {
        if (FacebookAdsManager.adManagerInitialized) {
            mPlacementId = bannerPlacementId

            mSize = when (size) {
                FBBannerAdSize.BANNER_HEIGHT_50 -> AdSize.BANNER_HEIGHT_50
                FBBannerAdSize.BANNER_HEIGHT_90 -> AdSize.BANNER_HEIGHT_90
                FBBannerAdSize.RECTANGLE_HEIGHT_250 -> AdSize.RECTANGLE_HEIGHT_250
            }
            adView = AdView(this, mPlacementId, mSize)
            adContainer.addView(adView);
            adView!!.loadAd()
        } else {
            throw Exception("Looks like, AdsManager is not initialized yet. Please call 'initFBAds()' in your Application class to initialize it.")
        }
    }

    fun destroyBannerAd() {
        adView?.destroy()

    }
}