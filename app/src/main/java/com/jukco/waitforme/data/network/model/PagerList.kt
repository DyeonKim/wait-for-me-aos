package com.jukco.waitforme.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PagerList<T>(
    @SerialName("content") val content: List<T>,
    @SerialName("empty") val empty: Boolean,
    @SerialName("first") val first: Boolean,
    @SerialName("last") val last: Boolean,
    @SerialName("number") val number: Int,
    @SerialName("numberOfElements") val numberOfElements: Int,
    @SerialName("pageable") val pageable: Pageable,
    @SerialName("size") val size: Int,
    @SerialName("sort") val sort: Sort,
    @SerialName("totalElements") val totalElements: Int,
    @SerialName("totalPages") val totalPages: Int
)

@Serializable
data class Sort(
    @SerialName("empty") val empty: Boolean,
    @SerialName("sorted") val sorted: Boolean,
    @SerialName("unsorted") val unsorted: Boolean
)

@Serializable
data class Pageable(
    @SerialName("offset") val offset: Int,
    @SerialName("pageNumber") val pageNumber: Int,
    @SerialName("pageSize") val pageSize: Int,
    @SerialName("paged") val paged: Boolean,
    @SerialName("sort") val sort: Sort,
    @SerialName("unpaged") val unPaged: Boolean
)

