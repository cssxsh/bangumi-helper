package xyz.cssxsh.bangumi

import kotlinx.serialization.*

/**
 * 数据大小
 */
@JvmInline
@Serializable
public value class BangumiSubjectFormat(public val type: String) {

    public override fun toString(): String = type

    public companion object {

        @JvmStatic
        public val SMALL: BangumiSubjectFormat = BangumiSubjectFormat("small")

        @JvmStatic
        public val MEDIUM: BangumiSubjectFormat = BangumiSubjectFormat("medium")

        @JvmStatic
        public val LARGE: BangumiSubjectFormat = BangumiSubjectFormat("large")
    }
}