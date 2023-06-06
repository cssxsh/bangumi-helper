package xyz.cssxsh.bangumi

import kotlinx.serialization.*

/**
 * 日程表
 *
 * @param weekday 星期几
 * @param items 条目
 */
@Serializable
public data class BangumiCalendar(
    @SerialName("items")
    val items: List<BangumiSubjectSmall> = emptyList(),
    @SerialName("weekday")
    val weekday: Weekday
)