package com.jukco.waitforme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R


@Composable
fun PopsListScreen(
    onNoticeButtonClicked: () -> Unit,
    onSearchingButtonClicked: () -> Unit,
    onPopItemClicked: (id: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = onNoticeButtonClicked) {
            Text(stringResource(R.string.notice))
        }
        Button(onClick = onSearchingButtonClicked) {
            Text(stringResource(R.string.searching))
        }
        Text(
            text = "팝 리스트",
            fontSize = 60.sp
        )
        Button(onClick = { onPopItemClicked(0) }) {
            Text("팝업 상세")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopListScreenPreview() {
    PopsListScreen(
        onNoticeButtonClicked = {},
        onSearchingButtonClicked = {},
        onPopItemClicked = {}
    )
}