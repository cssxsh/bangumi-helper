package xyz.cssxsh.bangumi.subject

import io.ktor.client.call.*
import io.ktor.client.request.*
import xyz.cssxsh.bangumi.*

/**
 * [条目](https://bangumi.github.io/api/#/%E6%9D%A1%E7%9B%AE)
 */
public class SubjectController(private val client: BangumiClient) {

    /**
     * [获取条目](https://bangumi.github.io/api/#/%E6%9D%A1%E7%9B%AE/getSubjectById)
     *
     * @param id 条目 ID
     */
    public suspend fun fetch(id: Int): BangumiSubjectDetail {
        val response = client.http.get("https://api.bgm.tv/v0/subjects/${id}")
        return response.body()
    }
}