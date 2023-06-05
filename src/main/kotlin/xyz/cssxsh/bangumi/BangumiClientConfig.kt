package xyz.cssxsh.bangumi


public interface BangumiClientConfig {
    public val proxy: String
    public val doh: String
    public val ipv6: Boolean
    public val timeout: Long
    public val token: String
}