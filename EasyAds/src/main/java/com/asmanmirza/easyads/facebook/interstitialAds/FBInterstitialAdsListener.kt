package com.asmanmirza.easyads.facebook.interstitialAds

interface FBInterstitialAdsListener {
    fun onAdLoaded()
    fun onError(message: String?)
}