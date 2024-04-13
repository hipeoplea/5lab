package dragon_settings

import kotlinx.serialization.Serializable
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import java.time.ZonedDateTime


object ZonedDateTimeSerializer : KSerializer<ZonedDateTime> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ZonedDateTime", PrimitiveKind.STRING)

    override fun serialize(encoder: kotlinx.serialization.encoding.Encoder, value: ZonedDateTime) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: kotlinx.serialization.encoding.Decoder): ZonedDateTime {
        val string = decoder.decodeString()
        return ZonedDateTime.parse(string)
    }
}

@Serializable
data class Dragon(
    private val id: Int,
    private val name: String,
    @Serializable(ZonedDateTimeSerializer::class)
    private val creationDate: ZonedDateTime,
    private val cave: DragonCave,
    private val speaking: Boolean,
    private val age: Long,
    private val coordinates: Coordinates,
    private val character: DragonCharacter,
    private val color: Color
) : Comparable<Dragon> {

    fun getId(): Int {
        return id
    }

    fun getName(): String {
        return name
    }

    fun getCreatioDate(): String {
        return creationDate.toString()
    }

    fun getCave(): String {
        return DragonCave.toString()
    }

    fun getCoordinates(): String {
        return Coordinates.toString()
    }

    fun getSpeaking(): Boolean{
        return speaking
    }

    fun getCharacter(): DragonCharacter{
        return character
    }

    override fun compareTo(other: Dragon): Int {
        return this.name.compareTo(other.name)
    }
}