package com.jukco.waitforme.ui.notice.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jukco.waitforme.R
import com.jukco.waitforme.data.mock.MockDataSource
import com.jukco.waitforme.data.network.model.NoticeDetailResponse
import com.jukco.waitforme.ui.notice.NoticeItem
import com.jukco.waitforme.ui.theme.GreyEEE
import com.jukco.waitforme.ui.theme.GreyF5
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun NoticeDetailScreen(
    onBackButtonClicked: () -> Unit,
) {
    val viewModel: NoticeDetailViewModel = viewModel(factory = NoticeDetailViewModel.Factory)

    NoticeDetailLayout(
        notice = viewModel.notice.collectAsState().value,
        onBackButtonClicked = onBackButtonClicked,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticeDetailLayout(
    notice: NoticeDetailResponse,
    onBackButtonClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.notice)) },
                navigationIcon = {
                    IconButton(onClick = { onBackButtonClicked() }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                modifier = Modifier.drawBehind {
                    drawLine(
                        color = GreyEEE,
                        strokeWidth = 1.dp.toPx(),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                    )
                }
            )
        }
    ) { scaffoldPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(scaffoldPadding)
        ) {
            NoticeItem(title = notice.title, createdAt = notice.createdDate)
            Text(
                text = notice.contents,
                style = TextStyle(
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = MainBlack,
                    lineHeight = (17.55).sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    letterSpacing = (-0.05).em,
                ),
                modifier = Modifier
                    .background(color = GreyF5)
                    .weight(1f)
                    .padding(20.dp)
                ,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NoticeDetailLayoutPreview() {
    WaitForMeTheme {
        NoticeDetailLayout(
            notice = MockDataSource.noticeDetailList[0],
            onBackButtonClicked = {},
        )
    }
}