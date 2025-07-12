package br.com.raphael.admobbannerkit.core

import android.content.Context
import com.google.android.gms.ads.MobileAds

object AdMobManager {
    internal var isInitialized = false
        private set

    fun initialize(context: Context) {
        MobileAds.initialize(context) {
            isInitialized = true
        }
    }
}
