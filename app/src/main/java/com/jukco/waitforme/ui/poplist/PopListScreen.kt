package com.jukco.waitforme.ui.poplist

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.Store
import com.jukco.waitforme.ui.components.FilterButtonGroup
import com.jukco.waitforme.ui.components.LargePopCard
import com.jukco.waitforme.ui.components.SearchAndNoticeTopBar
import com.jukco.waitforme.ui.components.SmallPopCard
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.MainGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopsListScreen(
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { SearchAndNoticeTopBar(onNoticeButtonClicked, onSearchingClicked) },
    ) { paddingValues ->
        val gridColumnCount = 2

        LazyVerticalGrid(
            columns = GridCells.Fixed(gridColumnCount),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(start = 20.dp, end = 20.dp),
        ) {
            item(span = { GridItemSpan(gridColumnCount) }) {
                Row {
                    Icon(imageVector = Icons.Outlined.Face, contentDescription = null)
                    Text(text = stringResource(R.string.title_ongoing))
                    Text(text = stringResource(R.string.pops))
                }
            }
            item(span = { GridItemSpan(gridColumnCount) }) {
                FilterButtonGroup(
                    items = loadFilterGroup(),
                    color = MainBlue,
                    onItemSelected = {},
                )
            }
            items(loadStoreList()) { LargePopCard(it, onPopItemClicked) }
            item(span = { GridItemSpan(gridColumnCount) }) {
                Row {
                    Icon(imageVector = Icons.Outlined.Face, contentDescription = null)
                    Text(text = stringResource(R.string.title_upcoming))
                    Text(text = stringResource(R.string.pops))
                }
            }
            item(span = { GridItemSpan(gridColumnCount) }) {
                UpcomingStoreList(
                    storeList = loadStoreList(),
                    onPopItemClicked = onPopItemClicked,
                )
            }
            item(span = { GridItemSpan(gridColumnCount) }) {
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
    LazyRow() {
        items(storeList) { store ->
            UpcomingStore(store, onPopItemClicked)
            Spacer(modifier = modifier.width(10.dp))
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

@Preview(showBackground = true)
@Composable
private fun PopListScreenPreview() {
    PopsListScreen(
        onNoticeButtonClicked = {},
        onSearchingClicked = {},
        onPopItemClicked = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun UpcomingStoreListPreview() {
    UpcomingStoreList(loadStoreList(), {})
}

@Preview(showBackground = true)
@Composable
private fun UpcomingStorePreview() {
    UpcomingStore(Store(0, R.drawable.img_store_example, "핑크 홀리데이", "야놀자"), {})
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

private fun loadFilterGroup(): List<String> {
    return listOf(
        "최신순",
        "마감임박순",
        "거리순",
    )
}
