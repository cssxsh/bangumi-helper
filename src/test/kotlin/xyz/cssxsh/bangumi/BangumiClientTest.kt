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

    @Test
    fun subject(): Unit = runBlocking {
        val subject = client.subject.fetch(id = 207195)
        Assertions.assertEquals(207195, subject.id)
    }

    @Test
    fun search(): Unit = runBlocking {
        val small = client.search.search(keywords = "露营") {
            format = BangumiSubjectFormat.SMALL
        }
        println(small)

        val medium = client.search.search(keywords = "偶像") {
            format = BangumiSubjectFormat.MEDIUM
        }
        println(medium)

        val large = client.search.search(keywords = "魔") {
            format = BangumiSubjectFormat.LARGE
        }
        println(large)
    }
}