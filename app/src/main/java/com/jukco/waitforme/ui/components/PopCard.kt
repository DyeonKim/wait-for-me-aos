package com.jukco.waitforme.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.Store
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

/* TODO : 인터넷에서 이미지 로드로 기능 변경 시
    imagePath: Int -> url: String
    로 변경. */
private val exStore = Store(0, R.drawable.img_store_example, "팝스토어 이름", "팝스토어 주최자")

@Composable
fun LargePopCard(
    store: Store,
    onClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClicked(store.id) },
    ) {
        LargeThumbnail(store.imagePath)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = store.title,
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = MainBlack,
                lineHeight = 14.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = store.host,
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                color = GreyAAA,
                lineHeight = 12.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Composable
fun MediumPopCard(
    store: Store,
    onClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
}

@Composable
fun SmallPopCard(
    store: Store,
    onClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClicked(store.id) },
    ) {
        SmallThumbnail(imagePath = store.imagePath)
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = store.title,
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                color = MainBlack,
                lineHeight = 13.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LargePopCardPreview() {
    WaitForMeTheme {
        LargePopCard(store = exStore, onClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun MediumPopCardPreview() {
    WaitForMeTheme {
        MediumPopCard(store = exStore, onClicked = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallPopCardPreview() {
    WaitForMeTheme {
        SmallPopCard(store = exStore, onClicked = {})
    }
}
