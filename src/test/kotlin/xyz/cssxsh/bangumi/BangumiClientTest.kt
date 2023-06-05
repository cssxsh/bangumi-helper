package xyz.cssxsh.bangumi

import kotlinx.coroutines.*
import org.junit.jupiter.api.*

// fRCJpdLU9V30LwgQEo8lJ7VpLdtG5TowTi4adSdz
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
        println(client.calendar())
    }
}