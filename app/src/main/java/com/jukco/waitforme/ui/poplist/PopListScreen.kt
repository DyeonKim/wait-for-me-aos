package com.jukco.waitforme.ui.poplist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.Store
import com.jukco.waitforme.ui.components.FilterButtonGroup
import com.jukco.waitforme.ui.components.LargePopCard
import com.jukco.waitforme.ui.components.MIN_WIDTH
import com.jukco.waitforme.ui.components.SearchAndNoticeTopBar
import com.jukco.waitforme.ui.components.SmallPopCard
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.MainGreen
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun PopsListScreen(
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    WaitForMeTheme {
        PopsList(onNoticeButtonClicked, onSearchingClicked, onPopItemClicked)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopsList(
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { SearchAndNoticeTopBar(onNoticeButtonClicked, onSearchingClicked) },
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = MIN_WIDTH),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.img_umain_title01), contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        buildAnnotatedString {
                            append(stringResource(R.string.title_ongoing))
                            withStyle(style = SpanStyle(color = MainBlue)) {
                                append(stringResource(R.string.pops))
                            }
                        },
                        style = TextStyle(
                            fontFamily = NotoSansKR,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MainBlack,
                            lineHeight = 18.sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            letterSpacing = (-0.05).em,
                        ),
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                FilterButtonGroup(
                    items = stringArrayResource(R.array.pops_filter_group).toList(),
                    color = MainBlue,
                    onItemSelected = {},
                )
            }
            items(loadStoreList()) { LargePopCard(it, onPopItemClicked) }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painter = painterResource(R.drawable.img_umain_title02), contentDescription = null)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        buildAnnotatedString {
                            append(stringResource(R.string.title_ongoing))
                            withStyle(style = SpanStyle(color = MainBlue)) {
                                append(stringResource(R.string.pops))
                            }
                        },
                        style = TextStyle(
                            fontFamily = NotoSansKR,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = MainBlack,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            lineHeight = 18.sp,
                            letterSpacing = (-0.05).em,
                        ),
                    )
                }
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                UpcomingStoreList(
                    storeList = loadStoreList(),
                    onPopItemClicked = onPopItemClicked,
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun UpcomingStoreList(
    storeList: List<Store>,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(storeList) { store ->
            UpcomingStore(store, onPopItemClicked)
        }
    }
}

@Composable
fun UpcomingStore(
    store: Store,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        SmallPopCard(store = store, onClicked = { onPopItemClicked(store.id) })
        Text(
            text = stringResource(R.string.d_day, store.dDay),
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 11.sp,
                lineHeight = 11.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            color = MainGreen,
            modifier = Modifier
                .padding(top = 6.dp, end = 6.dp)
                .align(Alignment.TopEnd)
                .drawBehind {
                    drawRoundRect(
                        color = MainBlack,
                        alpha = 0.6f,
                        cornerRadius = CornerRadius(4.dp.toPx()),
                    )
                }
                .padding(top = 4.dp, bottom = 4.dp, start = 6.dp, end = 6.dp),
        )
    }
}

// =================================== Preview =============================================
@Preview(name = "LandScape Mode", showBackground = true, device = Devices.AUTOMOTIVE_1024p, heightDp = 640)
@Preview(name = "Portrait Mode", showBackground = true, device = Devices.PHONE)
@Preview(name = "Foldable Mode", showBackground = true, device = Devices.FOLDABLE)
@Preview(name = "Tablet Mode", showBackground = true, device = Devices.TABLET)
@PreviewFontScale
@Composable
private fun PopListScreenPreview() {
    WaitForMeTheme {
        PopsListScreen(
            onNoticeButtonClicked = {},
            onSearchingClicked = {},
            onPopItemClicked = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UpcomingStoreListPreview() {
    WaitForMeTheme {
        UpcomingStoreList(loadStoreList(), {})
    }
}

@Preview(showBackground = true)
@Composable
private fun UpcomingStorePreview() {
    WaitForMeTheme {
        UpcomingStore(Store(0, R.drawable.img_store_example, "핑크 홀리데이", "야놀자"), {})
    }
}

// ============================ Data ===================================================

private fun loadStoreList(): List<Store> {
    return listOf(
        Store(0, R.drawable.img_store_example, "핑크 홀리데이", "야놀자"),
        Store(1, R.drawable.img_store_example, "코카콜라", "코카콜라"),
        Store(2, R.drawable.img_store_example, "홀리데이", "여기어때"),
        Store(3, R.drawable.img_store_example, "사이다", "칠성"),
        Store(4, R.drawable.img_store_example, "어쩌구", "저쩌구"),
        Store(5, R.drawable.img_store_example, "야호", "무"),
        Store(6, R.drawable.img_store_example, "스마일", "주말이다"),
        Store(7, R.drawable.img_store_example, "핑크", "Pink"),
        Store(8, R.drawable.img_store_example, "떠나요", "둘이서"),
        Store(9, R.drawable.img_store_example, "I만 다섯", "mbti"),
    )
}
