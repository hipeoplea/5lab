package dragon_settings

import kotlinx.serialization.Serializable


@Serializable
data class Coordinates(
    private val x: Double,
    private val y: Long
) {}
