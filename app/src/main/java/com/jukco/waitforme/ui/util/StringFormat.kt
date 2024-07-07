package com.jukco.waitforme.ui.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Int.convertSecToMinSecFormat() ="%02d:%02d".format(this / 60, this % 60)

fun LocalDateTime.convertDefaultFormat() = this.format(DateTimeFormatter.ofPattern("y.MM.dd HH:mm"))!!