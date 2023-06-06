package xyz.cssxsh.bangumi

import kotlinx.serialization.Serializable

/**
 * 条目类型 (没有 5)
 *  * 1 为 书籍
 *  * 2 为 动画
 *  * 3 为 音乐
 *  * 4 为 游戏
 *  * 6 为 三次元
 */
@JvmInline
@Serializable
public value class BangumiSubjectType(public val id: Int) {

    public override fun toString(): String {
        return when (id) {
            1 -> "BOOK"
            2 -> "ANIME"
            3 -> "MUSIC"
            4 -> "GAME"
            5 -> "REAL"
            else -> "UNKNOWN(${id})"
        }
    }

    public companion object {

        @JvmStatic
        public val UNKNOWN: BangumiSubjectType = BangumiSubjectType(0)

        @JvmStatic
        public val BOOK: BangumiSubjectType = BangumiSubjectType(1)

        @JvmStatic
        public val ANIME: BangumiSubjectType = BangumiSubjectType(2)

        @JvmStatic
        public val MUSIC: BangumiSubjectType = BangumiSubjectType(3)

        @JvmStatic
        public val GAME: BangumiSubjectType = BangumiSubjectType(4)

        @JvmStatic
        public val REAL: BangumiSubjectType = BangumiSubjectType(6)
    }
}