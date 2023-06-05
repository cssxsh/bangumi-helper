package xyz.cssxsh.bangumi

import kotlinx.serialization.*

@Serializable
public data class Images(
    @SerialName("common")
    val common: String = "",
    @SerialName("grid")
    val grid: String = "",
    @SerialName("large")
    val large: String = "",
    @SerialName("medium")
    val medium: String = "",
    @SerialName("small")
    val small: String = ""
)