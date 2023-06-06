package xyz.cssxsh.bangumi

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.time.*

/**
 * 条目
 *
 * @param id 条目 ID
 * @param url 条目地址
 * @param type 条目类型
 * @param name 条目名称
 * @param cn 条目中文名称
 * @param summary 条目简介
 * @param airDate 放送开始日期
 * @param airWeekday 放送星期
 * @param images 封面
 * @param eps 话数
 * @param count 话数
 * @param rating 评分
 * @param rank 排名
 * @param collection 收藏人数
 * @param crt 角色信息
 * @param staff 制作人员信息
 */
@Serializable
public data class BangumiSubjectMedium(
    @SerialName("air_date")
    @Serializable(LocalDateKSerializer::class)
    val airDate: LocalDate? = null,
    @SerialName("air_weekday")
    @Serializable(DayOfWeekKSerializer::class)
    val airWeekday: DayOfWeek? = null,
    @SerialName("collection")
    override val collection: BangumiCollection = BangumiCollection(),
    @SerialName("crt")
    val crt: JsonElement = JsonNull,
    @SerialName("eps")
    val eps: JsonElement = JsonNull,
    @SerialName("eps_count")
    val count: JsonElement = JsonNull,
    @SerialName("id")
    override val id: Int = 0,
    @SerialName("images")
    override val images: Images? = null,
    @SerialName("name")
    override val name: String = "",
    @SerialName("name_cn")
    override val cn: String = "",
    @SerialName("rank")
    override val rank: Int? = null,
    @SerialName("rating")
    override val rating: Rating? = null,
    @SerialName("summary")
    override val summary: String = "",
    @SerialName("staff")
    val staff: JsonElement = JsonNull,
    @SerialName("type")
    override val type: BangumiSubjectType = BangumiSubjectType.UNKNOWN,
    @SerialName("url")
    override val url: String = ""
) : BangumiSubject {
    override val date: LocalDate?
        get() = airDate
}