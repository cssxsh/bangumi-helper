package xyz.cssxsh.bangumi

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
public data class BangumiErrorInfo(
    @SerialName("title")
    val title: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("details")
    val details: String = ""
)