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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import xyz.cssxsh.bangumi.chat.ChatController
import xyz.cssxsh.bangumi.completion.CompletionController
import xyz.cssxsh.bangumi.edit.EditController
import xyz.cssxsh.bangumi.embedding.EmbeddingController
import xyz.cssxsh.bangumi.file.FileController
import xyz.cssxsh.bangumi.finetune.FineTuneController
import xyz.cssxsh.bangumi.image.ImageController
import xyz.cssxsh.bangumi.model.ModelController
import xyz.cssxsh.bangumi.moderation.ModerationController

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
    public open val model: ModelController by lazy { ModelController(this) }
    public open val completion: CompletionController by lazy { CompletionController(this) }
    public open val edit: EditController by lazy { EditController(this) }
    public open val image: ImageController by lazy { ImageController(this) }
    public open val embedding: EmbeddingController by lazy { EmbeddingController(this) }
    public open val file: FileController by lazy { FileController(this) }
    public open val finetune: FineTuneController by lazy { FineTuneController(this) }
    public open val moderation: ModerationController by lazy { ModerationController(this) }
    public open val chat: ChatController by lazy { ChatController(this) }

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