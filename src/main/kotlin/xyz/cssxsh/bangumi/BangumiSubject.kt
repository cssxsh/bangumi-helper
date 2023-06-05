package xyz.cssxsh.bangumi

import kotlinx.serialization.*

/**
 * 条目
 *
 * @param id 条目 ID
 * @param url 条目地址
 * @param type 条目类型 (没有 5)
 *  * 1 为 书籍
 *  * 2 为 动画
 *  * 3 为 音乐
 *  * 4 为 游戏
 *  * 6 为 三次元
 *
 * @param name 条目名称
 * @param nameCN 条目中文名称
 * @param summary 条目简介
 * @param airDate 放送开始日期
 * @param airWeekday 放送星期
 * @param images 封面
 * @param rating 评分
 * @param rank 排名
 * @param collection 收藏人数
 */
@Serializable
public data class BangumiSubject(
    @SerialName("air_date")
    val airDate: String = "",
    @SerialName("air_weekday")
    val airWeekday: Int = 0,
    @SerialName("collection")
    val collection: Collection = Collection(),
    @SerialName("id")
    val id: Int = 0,
    @SerialName("images")
    val images: Images? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("name_cn")
    val nameCN: String = "",
    @SerialName("rank")
    val rank: Int? = null,
    @SerialName("rating")
    val rating: Rating? = null,
    @SerialName("summary")
    val summary: String = "",
    @SerialName("type")
    val type: Int = 0,
    @SerialName("url")
    val url: String = ""
)