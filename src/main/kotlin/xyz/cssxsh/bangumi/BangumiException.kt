package xyz.cssxsh.bangumi

public class BangumiException(public val info: BangumiErrorInfo) : IllegalStateException() {
    override val message: String get() = info.description.ifEmpty { info.toString() }
}