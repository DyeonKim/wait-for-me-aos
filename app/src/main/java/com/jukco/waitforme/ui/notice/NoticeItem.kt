package com.jukco.waitforme.ui.notice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.GreyEEE
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.NotoSansKR

@Composable
fun NoticeItem(
    modifier: Modifier = Modifier,
    title: String,
    createdAt: String,
    onItemClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = GreyEEE,
                    strokeWidth = 1.dp.toPx(),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                )
            }
            .clickable { onItemClicked() }
            .padding(start = 20.dp, end = 20.dp, top = 19.dp, bottom = 16.dp)
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MainBlack,
                lineHeight = 14.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = createdAt,
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = GreyAAA,
                lineHeight = 12.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}