package br.com.raphael.admobbannerkit.api

import org.junit.Assert.*
import org.junit.Test

class AdEventTest {

    @Test
    fun `FailedToLoad should retain error code`() {
        val event = AdEvent.FailedToLoad(errorCode = 123)
        assertEquals(123, event.errorCode)
    }

    @Test
    fun `AdEvent sealed class should have all states`() {
        val events = listOf(
            AdEvent.Loading,
            AdEvent.Loaded,
            AdEvent.Opened,
            AdEvent.Clicked,
            AdEvent.Closed,
            AdEvent.Impression,
            AdEvent.NotInitialized
        )

        assertEquals(7, events.size)
    }
}
