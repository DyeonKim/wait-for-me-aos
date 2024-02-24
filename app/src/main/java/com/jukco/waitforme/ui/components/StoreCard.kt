package com.jukco.waitforme.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.data.network.model.StoreResponse
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun RectStoreCard(
    store: StoreResponse,
    onItemClicked: (id: Int) -> Unit,
    onBookmarkChecked: (storeResponse: StoreResponse) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onItemClicked(store.id) },
    ) {
        Box(modifier = modifier) {
            RectThumbnail(store.imagePath)
            Image(
                painter = painterResource(
                    if (store.isFavorite) {
                        R.drawable.ic_bookmark_fill
                    } else {
                        R.drawable.ic_bookmark_line_white
                    },
                ),
                contentDescription = stringResource(R.string.btn_bookmark),
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .clickable { onBookmarkChecked(store) },
            )
        }
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
fun SquareStoreCard(
    storeResponse: StoreResponse,
    onClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable { onClicked(storeResponse.id) },
    ) {
        SquareThumbnail(imagePath = storeResponse.imagePath)
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = storeResponse.title,
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

// ======================================= Preview ===============================================================

private val exStoreResponse = StoreResponse(0, "", "팝스토어 이름", "팝스토어 주최자", 0, true)

@Preview(showBackground = true)
@Composable
private fun RectStoreCardPreview() {
    WaitForMeTheme {
        RectStoreCard(store = exStoreResponse, onItemClicked = {}, onBookmarkChecked = {})
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallStoreCardPreview() {
    WaitForMeTheme {
        SquareStoreCard(storeResponse = exStoreResponse, onClicked = {})
    }
}
