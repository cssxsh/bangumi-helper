package xyz.cssxsh.bangumi

import kotlinx.serialization.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*
import java.time.*

@Serializable
internal data class ListWrapper<T : Any>(
    @SerialName("list")
    val list: List<T>,
    @SerialName("results")
    val total: Int = 0
)

@Serializable
internal data class Tag(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("name")
    val name: String = ""
)

@Serializable
public data class Infobox(
    @SerialName("key")
    val key: String = "",
    @SerialName("value")
    val value: JsonElement = JsonNull
)

internal object LocalDateKSerializer : KSerializer<LocalDate?> {

    override val descriptor: SerialDescriptor get() = String.serializer().descriptor

    override fun deserialize(decoder: Decoder): LocalDate? {
        return LocalDate.parse(decoder.decodeString().ifEmpty { return null })
    }

    override fun serialize(encoder: Encoder, value: LocalDate?) {
        encoder.encodeString(value?.toString().orEmpty())
    }
}

internal object DayOfWeekKSerializer : KSerializer<DayOfWeek?> {
    override val descriptor: SerialDescriptor get() = Int.serializer().descriptor

    override fun deserialize(decoder: Decoder): DayOfWeek? {
        return DayOfWeek.of(decoder.decodeInt().also { if (it == 0) return null })
    }

    override fun serialize(encoder: Encoder, value: DayOfWeek?) {
        encoder.encodeInt(value?.value ?: 0)
    }
}

internal object TagsKSerializer : KSerializer<Map<String, Int>> {
    private val inline = ListSerializer(Tag.serializer())

    override val descriptor: SerialDescriptor get() = inline.descriptor

    override fun deserialize(decoder: Decoder): Map<String, Int> {
        return decoder.decodeSerializableValue(inline).associate { tag ->
            tag.name to tag.count
        }
    }

    override fun serialize(encoder: Encoder, value: Map<String, Int>) {
        val tags = value.entries.map { Tag(name = it.key, count = it.value) }
        encoder.encodeSerializableValue(inline, tags)
    }
}

internal object InfoboxKSerializer : KSerializer<Map<String, List<String>>> {
    private val inline = ListSerializer(Infobox.serializer())

    override val descriptor: SerialDescriptor get() = inline.descriptor

    override fun deserialize(decoder: Decoder): Map<String, List<String>> {
        return decoder.decodeSerializableValue(inline).associate { infobox ->
            val list = when (infobox.value) {
                is JsonNull -> emptyList()
                is JsonPrimitive -> listOf(infobox.value.content)
                is JsonArray -> infobox.value.map { it.jsonObject.getValue("v").jsonPrimitive.content }
                else -> throw IllegalArgumentException(infobox.toString())
            }
            infobox.key to list
        }
    }

    override fun serialize(encoder: Encoder, value: Map<String, List<String>>) {
        val tags = value.entries.map { (key, value) ->
            Infobox(key = key, value = when (value.size) {
                0 -> JsonNull
                1 -> JsonPrimitive(value.single())
                else -> JsonArray(value.map { JsonPrimitive(it) })
            })
        }
        encoder.encodeSerializableValue(inline, tags)
    }
}