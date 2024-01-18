package com.jukco.waitforme.ui.store_list

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.data.network.model.StoreResponse
import com.jukco.waitforme.ui.components.RectStoreCard
import com.jukco.waitforme.ui.components.SearchAndNoticeTopBar
import com.jukco.waitforme.ui.components.SquareStoreCard
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.MainGreen
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun StoreListScreen(
    uiState: StoreListUiState,
    refreshAction: () -> Unit,
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    when(uiState) {
        is StoreListUiState.Loading -> LoadingScreen(modifier)
        is StoreListUiState.Error -> ErrorScreen(refreshAction, modifier)
        is StoreListUiState.Success -> {
            PopsList(
                ongoingStores = uiState.ongoingStores,
                upcomingStores = uiState.upcomingStores,
                onNoticeButtonClicked = onNoticeButtonClicked,
                onSearchingClicked = onSearchingClicked,
                onPopItemClicked = onPopItemClicked,
                modifier = modifier
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Loading....",
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
fun ErrorScreen(
    refreshAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Text(text = "오류 발생")
        Button(onClick = refreshAction) {
            Text(text = "재시도")
        }
    }
}


@Composable
fun PopsList(
    ongoingStores: List<StoreResponse>,
    upcomingStores: List<StoreResponse>,
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { SearchAndNoticeTopBar(onNoticeButtonClicked, onSearchingClicked) },
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 154.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                ){
                    Title(
                        img = R.drawable.img_umain_title01,
                        text = buildAnnotatedString {
                            append(stringResource(R.string.title_ongoing))
                            withStyle(style = SpanStyle(color = MainBlue)) {
                                append(stringResource(R.string.pops))
                            }
                        },
                        modifier = modifier.weight(1f)
                    )
                    Image(
                        imageVector = Icons.Rounded.KeyboardArrowRight,
                        contentDescription = stringResource(R.string.btn_more),
                        Modifier.clickable { onSearchingClicked }
                    )
                }
            }
            items(items = ongoingStores, key = { it.id }) { RectStoreCard(it, onPopItemClicked) }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Title(
                    img = R.drawable.img_umain_title02,
                    text = buildAnnotatedString {
                        append(stringResource(R.string.title_ongoing))
                        withStyle(style = SpanStyle(color = MainBlue)) {
                            append(stringResource(R.string.pops))
                        }
                    }
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                UpcomingStoreList(
                    storeResponseList = upcomingStores,
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
private fun Title(
    @DrawableRes img: Int,
    text: AnnotatedString,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(painter = painterResource(img), contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
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

@Composable
private fun UpcomingStoreList(
    storeResponseList: List<StoreResponse>,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(
            items = storeResponseList,
            key = { store -> store.id }
        ) { store ->
            UpcomingStore(store, onPopItemClicked)
        }
    }
}

@Composable
private fun UpcomingStore(
    storeResponse: StoreResponse,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box {
        SquareStoreCard(storeResponse = storeResponse, onClicked = { onPopItemClicked(storeResponse.id) })
        Text(
            text = stringResource(R.string.d_day, storeResponse.dDay),
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
@Preview(
    name = "LandScape Mode",
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p,
    heightDp = 640
)
@Preview(name = "Portrait Mode", showBackground = true, device = Devices.PHONE)
@Preview(name = "Foldable Mode", showBackground = true, device = Devices.FOLDABLE)
@Preview(name = "Tablet Mode", showBackground = true, device = Devices.TABLET)
@Composable
private fun PopListPreview() {
    val mockList = listOf(
        StoreResponse(0, "", "핑크 홀리데이", "야놀자", 0, false),
        StoreResponse(1, "", "코카콜라", "코카콜라", 1, true),
        StoreResponse(2, "", "홀리데이", "여기어때", -1, true),
        StoreResponse(3, "", "사이다", "칠성", 0, false),
        StoreResponse(4, "", "어쩌구", "저쩌구", 0, false),
        StoreResponse(5, "", "야호", "무", 0, false),
        StoreResponse(6, "", "스마일", "주말이다", 0, false),
        StoreResponse(7, "", "핑크", "Pink", 0, false),
        StoreResponse(8, "", "떠나요", "둘이서", 0, false),
        StoreResponse(9, "", "I만 다섯", "mbti", 0, false),
    )

    WaitForMeTheme {
        PopsList(
            ongoingStores = mockList,
            upcomingStores = mockList,
            onNoticeButtonClicked = {},
            onSearchingClicked = {},
            onPopItemClicked = {},
        )
    }
}

