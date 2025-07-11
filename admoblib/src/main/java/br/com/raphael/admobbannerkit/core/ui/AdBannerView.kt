package br.com.raphael.admobbannerkit.core.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class AdBannerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val adView: AdView = AdView(context).apply {
        setAdSize(AdSize.BANNER)
        adUnitId = "ca-app-pub-3940256099942544/6300978111"
    }

    init {
        MobileAds.initialize(context)
        addView(adView, LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        ))
        adView.loadAd(AdRequest.Builder().build())
    }

    override fun onDetachedFromWindow() {
        adView.destroy()
        super.onDetachedFromWindow()
    }
}
