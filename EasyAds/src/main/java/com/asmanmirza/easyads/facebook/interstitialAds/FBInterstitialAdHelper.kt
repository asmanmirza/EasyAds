package com.asmanmirza.easyads.facebook.interstitialAds

import android.content.Context
import com.asmanmirza.easyads.facebook.FacebookAdsManager.adManagerInitialized
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.InterstitialAd
import com.facebook.ads.InterstitialAdListener


object FBInterstitialAdHelper {
    private var mListener: FBInterstitialAdsListener? = null
    private var mAutoReload = false
    private var mInterstitialAdPlacementId = ""
    private var mInterstitialAdEnabled = true

    private var interstitialAd: InterstitialAd? = null
    private var interstitialAdListener: InterstitialAdListener? = null

    fun Context.loadFBInterstitialAd(interstitialAdPlacementId: String, isFBInterstitialAdsEnabled: Boolean = true, listener: FBInterstitialAdsListener? = null, autoReload: Boolean = false) {
        if (adManagerInitialized) {
            mListener = listener
            mAutoReload = autoReload
            mInterstitialAdPlacementId = interstitialAdPlacementId
            mInterstitialAdEnabled = isFBInterstitialAdsEnabled

            interstitialAd = InterstitialAd(this, mInterstitialAdPlacementId)
            interstitialAdListener = object : InterstitialAdListener {
                override fun onError(p0: Ad?, p1: AdError?) {
                    mListener?.onError(p1?.errorMessage ?: "")
                }

                override fun onAdLoaded(p0: Ad?) {
                    mListener?.onAdLoaded()
                }

                override fun onAdClicked(p0: Ad?) {

                }

                override fun onLoggingImpression(p0: Ad?) {

                }

                override fun onInterstitialDisplayed(p0: Ad?) {
                    if (mAutoReload) loadFBInterstitialAd(mInterstitialAdPlacementId, mInterstitialAdEnabled, mListener, mAutoReload)
                }

                override fun onInterstitialDismissed(p0: Ad?) {

                }
            }
            interstitialAd?.loadAd(
                interstitialAd?.buildLoadAdConfig()?.withAdListener(
                    interstitialAdListener
                )?.build())
        } else {
            throw Exception("Looks like, AdsManager is not initialized yet. Please call 'initFBAds()' in your Application class to initialize it.")
        }
    }

    fun Context.showFBInterstitialAds(loadIfNotLoaded: Boolean = false) {
        if (interstitialAd?.isAdLoaded == true) {
            interstitialAd?.show()
        } else {
            mListener?.onError("Not Loaded yet. Trying to load again!")
            if (loadIfNotLoaded) loadFBInterstitialAd(mInterstitialAdPlacementId, mInterstitialAdEnabled, mListener, mAutoReload)
        }
    }

    fun Context.destroyFBInterstitialAds() {
        interstitialAd?.destroy()
        mListener = null
        mAutoReload = false
        mInterstitialAdPlacementId = ""
        mInterstitialAdEnabled = true
    }
}