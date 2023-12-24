package com.jukco.waitforme.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.GreyEEE
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndNoticeTopBar(
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.searching_hint),
                color = GreyAAA,
                style = TextStyle(
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    lineHeight = 13.sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                    letterSpacing = (-0.05).em,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable { onSearchingClicked() }
                    .fillMaxWidth()
                    .background(
                        color = GreyEEE,
                        shape = RoundedCornerShape(
                            topStart = 4.dp,
                            topEnd = 24.dp,
                            bottomStart = 4.dp,
                            bottomEnd = 4.dp,
                        ),
                    )
                    .padding(start = 12.dp, top = 11.dp, bottom = 12.dp),
            )
        },
        actions = {
            IconButton(onClick = { onNoticeButtonClicked() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
                    contentDescription = stringResource(R.string.searching),
                    tint = Color.Unspecified,
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchAndNoticeTopBarPreview() {
    WaitForMeTheme {
        SearchAndNoticeTopBar(
            onNoticeButtonClicked = {},
            onSearchingClicked = {},
        )
    }
}

/* TopAppBar에서는 간격을 조절할 수 없어서 만들어 본 건데 또 큰 차이는 없어서 고민 중입니다... */
@Composable
fun ExTopBar(
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 12.dp, bottom = 12.dp),
    ) {
        Text(
            text = stringResource(R.string.searching_hint),
            color = GreyAAA,
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp,
                lineHeight = 13.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
                letterSpacing = (-0.05).em,
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.clickable { onSearchingClicked() }
                .weight(1f)
                .background(
                    color = GreyEEE,
                    shape = RoundedCornerShape(
                        topStart = 4.dp,
                        topEnd = 24.dp,
                        bottomStart = 4.dp,
                        bottomEnd = 4.dp,
                    ),
                )
                .padding(start = 12.dp, top = 11.dp, bottom = 12.dp),
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_notification),
            contentDescription = stringResource(R.string.searching),
            tint = Color.Unspecified,
            modifier = Modifier.clickable { onNoticeButtonClicked() }
                .padding(start = 8.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ExTopBarPreview() {
    WaitForMeTheme {
        ExTopBar(
            onNoticeButtonClicked = {},
            onSearchingClicked = {},
        )
    }
}
