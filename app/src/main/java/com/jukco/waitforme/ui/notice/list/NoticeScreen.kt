package com.jukco.waitforme.ui.notice.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jukco.waitforme.R
import com.jukco.waitforme.data.mock.MockDataSource
import com.jukco.waitforme.data.network.model.NoticeResponse
import com.jukco.waitforme.ui.notice.NoticeItem
import com.jukco.waitforme.ui.theme.GreyEEE
import com.jukco.waitforme.ui.theme.WaitForMeTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun NoticeScreen(
    onNoticeItemClicked: (id: Int) -> Unit,
    onBackButtonClicked: () -> Unit,
) {
    val viewModel: NoticeViewModel = viewModel(factory = NoticeViewModel.Factory)

    NoticeLayout(
        notices = viewModel.notices.collectAsState(),
        onNoticeItemClicked = onNoticeItemClicked,
        onBackButtonClicked = onBackButtonClicked,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoticeLayout(
    notices: State<List<NoticeResponse>>,
    onNoticeItemClicked: (id: Int) -> Unit,
    onBackButtonClicked: () -> Unit
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
            LazyColumn {
                items(
                    items = notices.value,
                    key = { notice -> notice.id }
                ) { notice ->
                    NoticeItem(
                        title = notice.title,
                        createdAt = notice.createdAt,
                        onItemClicked = { onNoticeItemClicked(notice.id) },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NoticeLayoutPreview() {
    WaitForMeTheme {
        NoticeLayout(
            notices = MutableStateFlow(MockDataSource.noticeList).collectAsState(),
            onNoticeItemClicked = {},
            onBackButtonClicked = {}
        )
    }
}