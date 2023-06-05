package xyz.cssxsh.bangumi

import kotlinx.serialization.*

/**
 * 评分
 * @param total 总评分人数
 * @param count 各分值评分人数
 * @param score 评分值
 */
@Serializable
public data class Rating(
    @SerialName("count")
    val count: Map<Int, Int> = mapOf(),
    @SerialName("score")
    val score: Double = 0.0,
    @SerialName("total")
    val total: Int = 0
)