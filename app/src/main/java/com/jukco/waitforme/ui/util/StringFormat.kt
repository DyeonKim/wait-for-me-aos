package com.jukco.waitforme.ui.util

fun Int.convertSecToMinSecFormat() ="%02d:%02d".format(this / 60, this % 60)
