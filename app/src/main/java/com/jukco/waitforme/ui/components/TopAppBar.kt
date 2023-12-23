package com.jukco.waitforme.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.GrayEEE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndNoticeTopBar(
    onNoticeButtonClicked: () -> Unit,
    onSearchingClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
        title = {
            Box(
                modifier = Modifier
                    .clickable { onSearchingClicked() }
                    .background(GrayEEE)
                    .height(36.dp)
                    .fillMaxWidth(),
            ) {
                Text(text = stringResource(R.string.searching_hint))
            }
        },
        actions = {
            IconButton(onClick = { onNoticeButtonClicked() }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = stringResource(R.string.searching),
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState()),
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchAndNoticeTopBarPreview() {
    SearchAndNoticeTopBar(
        onNoticeButtonClicked = {},
        onSearchingClicked = {},
    )
}
