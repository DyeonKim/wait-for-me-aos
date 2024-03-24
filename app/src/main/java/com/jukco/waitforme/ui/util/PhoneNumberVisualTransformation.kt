package com.jukco.waitforme.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val phoneNumber = text.toString().convertFormatToPhoneNumber()

        return TransformedText(
            text = AnnotatedString(text = phoneNumber),
            offsetMapping = object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    if (offset < 0) return offset

                    return when (offset) {
                        in 0..3 -> offset
                        in 4..6 -> offset + 1
                        else -> offset + 2
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    if (offset < 0) return offset

                    return when (offset) {
                        in 0..3 -> offset
                        in 4..6 -> offset - 1
                        else -> offset - 2
                    }
                }
            },
        )
    }
}

private fun String.convertFormatToPhoneNumber(): String {
    return when (this.length) {
        in 0..3 -> this
        in 4..6 -> "${this.substring(0, 3)}-${this.substring(3)}"
        in 7..10 -> "${this.substring(0, 3)}-${this.substring(3, 6)}-${this.substring(6)}"
        else -> "${this.substring(0, 3)}-${this.substring(3, 7)}-${this.substring(7)}"
    }
}
