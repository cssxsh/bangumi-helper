package xyz.cssxsh.bangumi

import kotlinx.serialization.*

/**
 * 收藏人数
 *
 * @param wish 想看
 * @param collect 看过
 * @param doing 在看
 * @param onHold 搁置
 * @param dropped 抛弃
 */
@Serializable
public data class BangumiCollection(
    @SerialName("wish")
    val wish: Int = 0,
    @SerialName("collect")
    val collect: Int = 0,
    @SerialName("doing")
    val doing: Int = 0,
    @SerialName("on_hold")
    val onHold: Int = 0,
    @SerialName("dropped")
    val dropped: Int = 0
) {
    /**
     * 总计
     */
    public val total: Int get() = wish + collect + doing + onHold + dropped
}