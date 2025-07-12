package br.com.raphael.admobbannerkit.ui

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import br.com.raphael.admobbannerkit.core.AdMobManager
import br.com.raphael.admobbannerkit.api.AdEvent
import br.com.raphael.admobbannerkit.api.AdEventListener
import com.google.android.gms.ads.*

@Composable
fun AdBanner(
    adUnitId: String = DEFAULT_AD_UNIT_ID,
    adEventListener: AdEventListener? = null
) {
    AndroidView(
        factory = { context: Context ->
            if (!AdMobManager.isInitialized) {
                adEventListener?.onEvent(AdEvent.NotInitialized)
                return@AndroidView FrameLayout(context)
            }

            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = if (adUnitId.isBlank()) DEFAULT_AD_UNIT_ID else adUnitId
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )

                adListener = object : AdListener() {
                    override fun onAdLoaded() {
                        adEventListener?.onEvent(AdEvent.Loaded)
                    }

                    override fun onAdFailedToLoad(error: LoadAdError) {
                        adEventListener?.onEvent(AdEvent.FailedToLoad(error.code))
                    }

                    override fun onAdOpened() {
                        adEventListener?.onEvent(AdEvent.Opened)
                    }

                    override fun onAdClicked() {
                        adEventListener?.onEvent(AdEvent.Clicked)
                    }

                    override fun onAdClosed() {
                        adEventListener?.onEvent(AdEvent.Closed)
                    }

                    override fun onAdImpression() {
                        adEventListener?.onEvent(AdEvent.Impression)
                    }
                }

                adEventListener?.onEvent(AdEvent.Loading)
                loadAd(AdRequest.Builder().build())
            }
        }
    )
}

private const val DEFAULT_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"
