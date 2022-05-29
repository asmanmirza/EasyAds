package com.asmanmirza.easyads.admob.interstitial

import android.app.Activity
import android.content.Context
import android.util.Log
import com.asmanmirza.easyads.admob.AdMobAdsManager.admobManagerInitialized
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

object AdmobInterstitialAdHelper {
    private var mInterstitialId = "ca-app-pub-3940256099942544/1033173712"
    private var mListener: AdmobInterstitialAdListener? = null

    private var mInterstitialAd: InterstitialAd? = null

    fun Context.loadAdmobInterstitialAd(interstitialId: String, listener: AdmobInterstitialAdListener? = null) {
        if (admobManagerInitialized) {
            mInterstitialId = interstitialId
            mListener = listener
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(this, mInterstitialId, adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    mInterstitialAd = null
                    mListener?.onError(adError.message)
                    Log.d("ads___", "onAdFailedToLoad: ${adError.message}")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    mInterstitialAd = interstitialAd
                    mListener?.onLoaded()
                    Log.d("ads___", "onAdLoaded: Loaded")
                }
            })
        } else {
            throw Exception("Looks like, AdsManager is not initialized yet. Please call 'initAdMobAdsManager()' in your Application class to initialize it.")
        }
    }

    fun Activity.showAdmobInterstitialAd(loadNextAd: Boolean = true) {
        mInterstitialAd?.show(this)
        if (loadNextAd) loadAdmobInterstitialAd(mInterstitialId, mListener)
    }
}