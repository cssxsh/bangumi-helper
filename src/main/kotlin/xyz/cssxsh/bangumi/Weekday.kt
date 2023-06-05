package xyz.cssxsh.bangumi

import kotlinx.serialization.*
import java.time.*

/**
 * 星期几
 *
 * @param id 序号
 */
@Serializable
public data class Weekday(
    @SerialName("cn")
    val cn: String = "",
    @SerialName("en")
    val en: String = "",
    @SerialName("id")
    val id: Int = 0,
    @SerialName("ja")
    val ja: String = ""
) {
    public val isToday: Boolean get() = LocalDate.now().dayOfWeek.value == id
}