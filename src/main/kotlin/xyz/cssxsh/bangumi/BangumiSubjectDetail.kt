package xyz.cssxsh.bangumi

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.time.*

@Serializable
public data class BangumiSubjectDetail(
    @SerialName("collection")
    override val collection: BangumiCollection = BangumiCollection(),
    @SerialName("date")
    @Serializable(LocalDateKSerializer::class)
    override val date: LocalDate? = null,
    @SerialName("eps")
    val eps: Int = 0,
    @SerialName("id")
    override val id: Int = 0,
    @SerialName("images")
    override val images: Images = Images(),
    @SerialName("infobox")
    @Serializable(InfoboxKSerializer::class)
    val infobox: Map<String, List<String>> = mapOf(),
    @SerialName("locked")
    val locked: Boolean = false,
    @SerialName("name")
    override val name: String = "",
    @SerialName("name_cn")
    override val cn: String = "",
    @SerialName("nsfw")
    val nsfw: Boolean = false,
    @SerialName("platform")
    val platform: String = "",
    @SerialName("rating")
    override val rating: Rating = Rating(),
    @SerialName("summary")
    override val summary: String = "",
    @SerialName("tags")
    @Serializable(TagsKSerializer::class)
    val tags: Map<String, Int> = emptyMap(),
    @SerialName("total_episodes")
    val totalEpisodes: Int = 0,
    @SerialName("type")
    override val type: BangumiSubjectType = BangumiSubjectType.UNKNOWN,
    @SerialName("url")
    override val url: String = "",
    @SerialName("volumes")
    val volumes: Int = 0
) : BangumiSubject {
    override val rank: Int?
        get() = rating.rank
}