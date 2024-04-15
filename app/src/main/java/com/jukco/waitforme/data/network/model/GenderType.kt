package com.jukco.waitforme.data.network.model

import androidx.annotation.StringRes
import com.jukco.waitforme.R

enum class GenderType(@StringRes val stringId: Int) {
    MALE(R.string.gender_male),
    FEMALE(R.string.gender_female),
    OTHER(R.string.gender_other),
}