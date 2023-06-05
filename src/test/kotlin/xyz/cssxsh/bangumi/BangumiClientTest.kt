package xyz.cssxsh.bangumi

import kotlinx.coroutines.*
import org.junit.jupiter.api.*

internal class BangumiClientTest {
    private val config = object : BangumiClientConfig {
        override val proxy: String = ""
        override val doh: String = "https://public.dns.iij.jp/dns-query"
        override val ipv6: Boolean = true
        override val timeout: Long = 60_000L
        override val token: String = System.getenv("BANGUMI_TOKEN")
    }
    private val client = BangumiClient(config = config)


    @Test
    fun calendar(): Unit = runBlocking {
        val calendars = client.calendar()
        Assertions.assertNotNull(calendars.find { it.weekday.isToday })
    }
}