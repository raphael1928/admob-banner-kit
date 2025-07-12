package br.com.raphael.admobbannerkit.ui.legacy

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.google.android.gms.ads.*
import br.com.raphael.admobbannerkit.core.AdMobManager
import br.com.raphael.admobbannerkit.api.AdEvent
import br.com.raphael.admobbannerkit.api.AdEventListener

class AdBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    companion object {
        private const val DEFAULT_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111"
    }

    private val adView: AdView? = if (AdMobManager.isInitialized) {
        AdView(context).apply {
            setAdSize(AdSize.BANNER)
            adUnitId = DEFAULT_AD_UNIT_ID
        }
    } else {
        null
    }

    private var adEventListener: AdEventListener? = null

    init {
        if (AdMobManager.isInitialized) {
            adView?.adListener = object : AdListener() {
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

            addView(adView, LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
            ))
        } else {
            post {
                adEventListener?.onEvent(AdEvent.NotInitialized)
            }
        }
    }

    fun loadAd() {
        if (adView == null) {
            adEventListener?.onEvent(AdEvent.NotInitialized)
            return
        }
        adEventListener?.onEvent(AdEvent.Loading)
        adView.loadAd(AdRequest.Builder().build())
    }

    fun setAdUnitId(adUnitId: String?) {
        if (!adUnitId.isNullOrBlank()) {
            adView?.adUnitId = adUnitId
        } else {
            adView?.adUnitId = DEFAULT_AD_UNIT_ID
        }
    }

    fun setAdEventListener(listener: AdEventListener) {
        this.adEventListener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        loadAd()
    }

    override fun onDetachedFromWindow() {
        adView?.destroy()
        super.onDetachedFromWindow()
    }
}
