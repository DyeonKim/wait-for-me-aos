package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class StoreResponse(
    @SerialName(value = "id") val id: Int,
    @SerialName(value = "imagePath") val imagePath: String,
    @SerialName(value = "title") val title: String,
    @SerialName(value = "host") val host: String,
    @SerialName(value = "dDay") val dDay: Int,
    @SerialName(value = "isFavorite") val isFavorite: Boolean
)
