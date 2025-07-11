package br.com.raphael.admobbannerkit.core.ui

import android.content.Context
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.*

@Composable
fun AdBanner(
    adUnitId: String = "ca-app-pub-3940256099942544/6300978111"
) {
    AndroidView(
        factory = { context: Context ->
            MobileAds.initialize(context)

            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                this.adUnitId = adUnitId
                loadAd(AdRequest.Builder().build())
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        }
    )
}
