package xyz.cssxsh.bangumi

import java.time.*

/**
 * 条目
 * @property id 条目 ID
 * @property url 条目地址
 * @property type 条目类型
 * @property name 条目名称
 * @property cn 条目中文名称
 * @property summary 条目简介
 * @property images 封面, 无图片时可取 (https://lain.bgm.tv/img/no_icon_subject.png)
 * @property rating 评分
 * @property rank 排名
 * @property collection 收藏人数
 * @property date 日期
 */
public interface BangumiSubject {
    public val collection: BangumiCollection
    public val date: LocalDate?
    public val id: Int
    public val images: Images?
    public val name: String
    public val cn: String
    public val rank: Int?
    public val rating: Rating?
    public val summary: String
    public val type: BangumiSubjectType
    public val url: String
}