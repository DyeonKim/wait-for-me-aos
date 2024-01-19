package com.jukco.waitforme.ui.store_detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.GreyDDD
import com.jukco.waitforme.ui.theme.GreyEEE
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme
import kotlinx.coroutines.launch

@Composable
fun PopupStoreScreen(
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    WaitForMeTheme {
        StoreDetail(onBackButtonClicked, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoreDetail(
    onBackButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.detail_page),
                        style = TextStyle(
                            color = MainBlack,
                            fontFamily = NotoSansKR,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            letterSpacing = (-0.05).em,
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClicked) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.back),
                            tint = Color.Unspecified,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_share),
                            contentDescription = stringResource(R.string.share),
                            tint = Color.Unspecified,
                        )
                    }
                },
            )
        },
        bottomBar = {
            Button(
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(4.dp),
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
            ) {
                Text(
                    text = stringResource(R.string.btn_on_site_reservation),
                    style = TextStyle(
                        fontFamily = NotoSansKR,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        letterSpacing = 0.em,
                    ),
                    modifier = modifier.padding(horizontal = 64.dp, vertical = (13.5).dp),
                )
            }
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            item { ImagePager() }
            item { Title() }
            item {
                Spacer(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(1.dp)
                        .background(color = GreyEEE),
                )
            }
            item { BasicInformation(modifier.padding(horizontal = 20.dp).padding(top = 20.dp)) }
            item { SNSInformation(modifier.padding(horizontal = 20.dp).padding(top = 34.dp)) }
            item {
                Spacer(
                    modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .height(1.dp)
                        .background(color = GreyEEE),
                )
            }
            item {
                Text(
                    text = "핑크 홀리데이는 야놀자에서 2023년 12월에 진행하는  팝업입니다! 선물이 가득하므로 친구/연인 등과 함께 방문해 팝업 스토어에서 선물도 받고 함께 즐겁게 놀아보세요!",
                    style = TextStyle(
                        color = MainBlack,
                        fontFamily = NotoSansKR,
                        fontWeight = FontWeight.Normal,
                        fontSize = 13.sp,
                        lineHeight = (17.55).sp,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        letterSpacing = (-0.05).em,
                    ),
                    modifier = modifier.padding(horizontal = 20.dp).padding(bottom = 12.dp),
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ImagePager(
    modifier: Modifier = Modifier,
) {
    /*TODO : viewModel */
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box {
        HorizontalPager(pageCount = 5, state = pagerState) { page ->
            Text(
                text = "Page: $page",
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(GreyAAA),
            )
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(5) { iteration ->
                val color = if (pagerState.currentPage == iteration) MainBlue else GreyDDD
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(bottom = 12.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(
                                    iteration,
                                )
                            }
                        },
                )
            }
        }
    }
}

@Composable
private fun Title(
    storeTitle: String = "스토어명",
    storeHost: String = "주최명",
    isFavorite: Boolean = true,
    onBookmarkClicked: () -> Unit = {}, // TODO: viewModel function
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 15.dp),
    ) {
        Column {
            Text(
                text = storeTitle,
                style = TextStyle(
                    color = MainBlack,
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    lineHeight = 14.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    letterSpacing = (-0.05).em,
                ),
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = storeHost,
                style = TextStyle(
                    color = GreyAAA,
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 12.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    letterSpacing = (-0.05).em,
                ),
            )
        }
        Spacer(modifier = modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.clickable { onBookmarkClicked },
        ) {
            Image(
                painter = painterResource(if (isFavorite) R.drawable.ic_bookmark_fill else R.drawable.ic_bookmark_line),
                contentDescription = stringResource(R.string.btn_bookmark),
            )
            Text(
                text = "20",
                textAlign = TextAlign.Center,
                color = if (isFavorite) MainBlue else MainBlack,
                style = TextStyle(
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Medium,
                    fontSize = 11.sp,
                    lineHeight = 11.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    letterSpacing = (-0.05).em,
                ),
            )
        }
    }
}

@Composable
private fun BasicInformation(
    // TODO : storeDetailUiState
    modifier: Modifier = Modifier,
) {
    val copyId = "copyIcon"
    val place = buildAnnotatedString {
        append("주소가 2줄일 경우 이렇게 표시됩니다................................! 참고 부탁드려요!")
        appendInlineContent(copyId, "[copyIcon]")
    }
    val copyIcon = mapOf(
        Pair(
            copyId,
            InlineTextContent(
                Placeholder(
                    width = 24.sp,
                    height = 16.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter,
                ),
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_data_copy),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp),
                )
            },
        ),
    )

    Column(modifier) {
        Text(
            text = stringResource(R.string.basic_info),
            style = TextStyle(
                color = MainBlack,
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                lineHeight = 13.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconText(icon = R.drawable.img_calendar, text = "날짜")
        Spacer(modifier = Modifier.height(10.dp))
        IconText(icon = R.drawable.img_clock, text = "운영시간")
        Spacer(modifier = Modifier.height(10.dp))
        Row(verticalAlignment = Alignment.Top) {
            Image(
                painter = painterResource(R.drawable.img_location),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = place,
                inlineContent = copyIcon,
                maxLines = 2,
                style = TextStyle(
                    color = MainBlack,
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    letterSpacing = (-0.05).em,
                ),
            )
        }
    }
}

@Composable
private fun SNSInformation(
    // TODO : storeDetailUiState의 SNS 목록?을 이용해서 존재여부에 따라 IconText 보여주거나 안 보여주거나. 아마 Map이 편할 듯
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.sns),
            style = TextStyle(
                color= MainBlack,
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                lineHeight = 13.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconText(icon = R.drawable.img_sns_instar, text = "인스타")
        Spacer(modifier = Modifier.height(10.dp))
//        IconText(icon = R.drawable.ic_nav_waiting, text = "트위터")
//        IconText(icon = R.drawable.ic_nav_waiting, text = "페이스북?")
        IconText(icon = R.drawable.img_homepage, text = "자체 홈페이지")
    }
}

@Composable
private fun IconText(
    @DrawableRes icon: Int,
    text: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = text,
            maxLines = 1,
            style = TextStyle(
                color = MainBlack,
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                lineHeight = 13.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
        )
    }
}

@Preview(name = "Portrait Mode", showBackground = true, device = Devices.PHONE)
@Preview(name = "LandScape Mode", showBackground = true, device = Devices.AUTOMOTIVE_1024p, heightDp = 640)
@Preview(name = "Foldable Mode", showBackground = true, device = Devices.FOLDABLE)
@Preview(name = "Tablet Mode", showBackground = true, device = Devices.TABLET)
@Preview(showBackground = true)
@Composable
fun StoreDetailPreview() {
    PopupStoreScreen(
        onBackButtonClicked = {},
    )
}
