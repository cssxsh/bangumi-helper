package xyz.cssxsh.bangumi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.compression.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.charsets.*
import kotlinx.serialization.json.*
import xyz.cssxsh.bangumi.search.*
import xyz.cssxsh.bangumi.subject.*

public open class BangumiClient(@PublishedApi internal val config: BangumiClientConfig) {
    public open val http: HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(json = Json)
        }
        install(HttpTimeout) {
            socketTimeoutMillis = config.timeout
            connectTimeoutMillis = config.timeout
            requestTimeoutMillis = null
        }
        install(UserAgent) {
            agent = "cssxsh/bangumi-helper (https://github.com/cssxsh/bangumi-helper)"
        }
        Auth {
            bearer {
                loadTokens {
                    BearerTokens(config.token, "")
                }
                refreshTokens {
                    BearerTokens(config.token, "")
                }
                sendWithoutRequest { builder ->
                    builder.url.host == "api.bgm.tv"
                }
            }
        }
        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                val originCall = response.call
                if (statusCode < 400) return@validateResponse

                val exceptionCall = originCall.save()
                val exceptionResponse = exceptionCall.response

                throw try {
                    val error = exceptionResponse.body<BangumiErrorInfo>()
                    BangumiException(info = error)
                } catch (_: ContentConvertException) {
                    val exceptionResponseText = try {
                        exceptionResponse.bodyAsText()
                    } catch (_: MalformedInputException) {
                        "<body failed decoding>"
                    }
                    when (statusCode) {
                        in 400..499 -> {
                            ClientRequestException(response, exceptionResponseText)
                        }
                        in 500..599 -> {
                            ServerResponseException(response, exceptionResponseText)
                        }
                        else -> ResponseException(response, exceptionResponseText)
                    }
                }
            }
        }
        ContentEncoding()
        engine {
            config {
                apply(config = config)
            }
        }
    }
    public open val subject: SubjectController by lazy { SubjectController(this) }
    public open val search: SearchController by lazy { SearchController(this) }

    public suspend fun calendar(): List<BangumiCalendar> {
        return http.get("https://api.bgm.tv/calendar").body()
    }

    public open fun clearToken() {
        for (provider in http.plugin(Auth).providers) {
            if (provider !is BearerAuthProvider) continue
            provider.clearToken()
            break
        }
    }
}