package com.asmanmirza.easyads.facebook.rewardedVideoAds

interface FBRewardedAdsListener {
    fun onError(message: String?)
    fun onLoaded()
    fun onCompleted()
}