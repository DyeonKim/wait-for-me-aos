package com.jukco.waitforme.ui.sign.sign_up

import androidx.annotation.StringRes

data class CustomerOwner(
    @StringRes val title: Int,
    @StringRes val description: Int,
    val value: Boolean
)
