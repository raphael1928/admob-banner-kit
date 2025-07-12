package br.com.raphael.admobbannerkit.core.model

sealed class AdEvent {
    object Loading : AdEvent()
    object Loaded : AdEvent()
    data class FailedToLoad(val errorCode: Int) : AdEvent()
    object Opened : AdEvent()
    object Clicked : AdEvent()
    object Closed : AdEvent()
    object Impression : AdEvent()
    object NotInitialized : AdEvent()
}

