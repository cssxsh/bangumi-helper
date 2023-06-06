package xyz.cssxsh.bangumi.search

import xyz.cssxsh.bangumi.*

/**
 * @property type 条目类型
 * @property format 返回数据大小
 * @property start 开始条数
 * @property max 每页条数，最多 25
 */
public class SearchBuilder {
    public var type: BangumiSubjectType = BangumiSubjectType.ANIME
    public var format: BangumiSubjectFormat = BangumiSubjectFormat.SMALL
    public var start: Int = 0
    public var max: Int = 25
}