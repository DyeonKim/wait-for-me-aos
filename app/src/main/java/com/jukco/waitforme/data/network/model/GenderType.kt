package com.jukco.waitforme.data.network.model

import androidx.annotation.StringRes
import com.jukco.waitforme.R

enum class GenderType(@StringRes val stringId: Int) {
    FEMALE(R.string.gender_female),
    MALE(R.string.gender_male),
    OTHER(R.string.gender_other),
}