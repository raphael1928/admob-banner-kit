package br.com.raphael.admobbannerkit.api

import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AdEventListenerTest {

    @Test
    fun `onEvent should be triggered with correct event`() {
        val listener = mockk<AdEventListener>(relaxed = true)
        val event = AdEvent.Loaded

        listener.onEvent(event)

        verify { listener.onEvent(AdEvent.Loaded) }
    }
}
