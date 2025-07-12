package br.com.raphael.admobbannerkit.core.model

fun interface AdEventListener {
    fun onEvent(event: AdEvent)
}