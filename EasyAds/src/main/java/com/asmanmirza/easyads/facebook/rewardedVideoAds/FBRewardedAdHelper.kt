package com.asmanmirza.easyads.facebook.rewardedVideoAds

import android.content.Context
import com.asmanmirza.easyads.facebook.FacebookAdsManager.adManagerInitialized
import com.facebook.ads.Ad
import com.facebook.ads.AdError
import com.facebook.ads.RewardedVideoAd
import com.facebook.ads.RewardedVideoAdListener


object FBRewardedAdHelper {
    private var mPlacementId = ""
    private var mAutoReload = false
    private var mRewardedAdsEnabled = true
    private var mListener: FBRewardedAdsListener? = null

    private var rewardedVideoAd: RewardedVideoAd? = null
    private var rewardedVideoAdListener: RewardedVideoAdListener? = null

    fun Context.loadFBRewardedAds(placemenId: String, rewardedAdsEnabled: Boolean = true, listener: FBRewardedAdsListener? = null, autoReload: Boolean = false) {
        if (adManagerInitialized) {
            mPlacementId = placemenId
            mListener = listener
            mRewardedAdsEnabled = rewardedAdsEnabled
            mAutoReload = autoReload

            rewardedVideoAd = RewardedVideoAd(this, mPlacementId)
            rewardedVideoAdListener = object : RewardedVideoAdListener {
                override fun onError(ad: Ad, error: AdError) {
                    mListener?.onError(error.errorMessage)
                }

                override fun onAdLoaded(ad: Ad) {
                    mListener?.onLoaded()
                }

                override fun onAdClicked(ad: Ad) {

                }

                override fun onLoggingImpression(ad: Ad) {

                }

                override fun onRewardedVideoCompleted() {
                    mListener?.onCompleted()
                    if (mAutoReload) loadFBRewardedAds(mPlacementId, mRewardedAdsEnabled, mListener, mAutoReload)
                }

                override fun onRewardedVideoClosed() {

                }
            }
            rewardedVideoAd?.loadAd(
                rewardedVideoAd!!.buildLoadAdConfig().withAdListener(
                    rewardedVideoAdListener
                ).build())
        } else {
            throw Exception("Looks like, AdsManager is not initialized yet. Please call 'initFBAds()' in your Application class to initialize it.")
        }
    }

    fun Context.showFBRewardedAd(loadIfNotLoaded: Boolean = false) {
        if (rewardedVideoAd?.isAdLoaded == true) {
            rewardedVideoAd?.show()
        } else {
            mListener?.onError("Not Loaded yet. Trying to load again!")
            if (loadIfNotLoaded) loadFBRewardedAds(mPlacementId, mRewardedAdsEnabled, mListener, mAutoReload)
        }
    }

    fun Context.destroyFBRewardedAds() {
        rewardedVideoAd?.destroy()
    }
}