package br.com.raphael.admobbannerkit.api

fun interface AdEventListener {
    fun onEvent(event: AdEvent)
}