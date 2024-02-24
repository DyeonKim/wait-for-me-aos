package com.jukco.waitforme.ui.sign

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.NotoSansKR

@Composable
fun SignAnnouncement(
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = Modifier.height(72.dp))
    Text(
        text = buildAnnotatedString {
            append(stringResource(R.string.sign_announcement_1))
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MainBlue)) {
                append(stringResource(R.string.sign_announcement_2))
            }
            append(stringResource(R.string.sign_announcement_3))
        },
        style = TextStyle(
            fontFamily = NotoSansKR,
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = MainBlack,
            lineHeight = 28.sp,
            platformStyle = PlatformTextStyle(includeFontPadding = false),
        ),
    )
    Spacer(modifier = Modifier.height(70.dp))
}