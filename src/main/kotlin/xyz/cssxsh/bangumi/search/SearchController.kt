package xyz.cssxsh.bangumi.search

import io.ktor.client.call.*
import io.ktor.client.request.*
import xyz.cssxsh.bangumi.*

/**
 * [搜索](https://bangumi.github.io/api/#/%E6%90%9C%E7%B4%A2)
 */
public class SearchController(private val client: BangumiClient) {

    /**
     * [条目搜索](https://bangumi.github.io/api/#/%E6%90%9C%E7%B4%A2/searchSubjectByKeywords)
     *
     * @param keywords 关键词
     */
    public suspend fun search(keywords: String, block: SearchBuilder.() -> Unit = {}): List<BangumiSubject> {
        val info = SearchBuilder().apply(block)
        val response = client.http.get("https://api.bgm.tv/search/subject/${keywords}") {
            parameter("type", info.type.id)
            parameter("responseGroup", info.format.type)
            parameter("start", info.start)
            parameter("max_results", info.max)
        }
        val wrapper = when (info.format) {
            BangumiSubjectFormat.SMALL -> response.body<ListWrapper<BangumiSubjectSmall>>()
            BangumiSubjectFormat.MEDIUM -> response.body<ListWrapper<BangumiSubjectMedium>>()
            BangumiSubjectFormat.LARGE -> response.body<ListWrapper<BangumiSubjectLarge>>()
            else -> response.body<ListWrapper<BangumiSubjectDetail>>()
        }
        return wrapper.list
    }
}