package com.jukco.waitforme.ui.poplist

import androidx.annotation.DrawableRes

data class Store(
    val id: Int,
    @DrawableRes val imagePath: Int,
    val title: String = "",
    val host: String = "",
    val dDay: Int = 0,
)
