package com.jukco.waitforme.ui.poplist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.components.FilterButtonGroup
import com.jukco.waitforme.ui.components.SearchAndNoticeTopBar
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
            item(span = { GridItemSpan(gridColumnCount) }) { FilterButtonGroup(items = loadFilterGroup(), color = MainBlue, onItemSelected = {}) }
            items(loadStoreList()) { OngoingStore(it, onPopItemClicked) }
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
fun OngoingStore(
    store: Store,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .clickable { onPopItemClicked(store.id) },
    ) {
        // TODO : 인터넷에서 이미지 로드로 코드 변경 시 AsyncImage 로 변경
        Image(
            painter = painterResource(store.imagePath),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .aspectRatio(154f / 192f)
                .clip(
                    RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 24.dp,
                        bottomStart = 4.dp,
                        bottomEnd = 4.dp,
                    ),
                ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = store.title)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = store.host)
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
        }
    }
}

@Composable
fun UpcomingStore(
    store: Store,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .padding(end = 8.dp)
            .clickable { onPopItemClicked(store.id) },
    ) {
        Box {
            // TODO : 인터넷에서 이미지 로드로 코드 변경 시 AsyncImage 로 변경
            Image(
                painter = painterResource(store.imagePath),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(4.dp)),
            )
            Text(
                text = stringResource(R.string.d_day, store.dDay),
                color = MainGreen,
                modifier = Modifier
                    .drawBehind {
                        drawRoundRect(
                            color = Color.Black.copy(0.6f),
                            cornerRadius = CornerRadius(4.dp.toPx()),
                        )
                    }
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = store.title)
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
private fun OngoingStorePreview() {
    OngoingStore(Store(0, R.drawable.img_store_example, "핑크 홀리데이", "야놀자"), {})
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
