package br.com.raphael.admobbannerkit.core

import com.google.android.gms.ads.initialization.OnInitializationCompleteListener
import android.content.Context
import com.google.android.gms.ads.MobileAds
import io.mockk.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class AdMobManagerTest {

    private lateinit var context: Context

    @Before
    fun setup() {
        context = mockk(relaxed = true)
        mockkStatic(MobileAds::class)
    }

    @Test
    fun `initialize should mark as initialized`() {
        val initializationListener = slot<OnInitializationCompleteListener>()

        every {
            MobileAds.initialize(any(), capture(initializationListener))
        } answers {
            initializationListener.captured.onInitializationComplete(mockk(relaxed = true))
        }

        AdMobManager.initialize(context)

        assertTrue(AdMobManager.isInitialized)
        verify { MobileAds.initialize(context, any()) }
    }
}
