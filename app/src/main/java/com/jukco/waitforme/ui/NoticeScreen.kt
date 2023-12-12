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
fun NoticeScreen(
    onCloseButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = onCloseButtonClicked
        ) {
            Text(stringResource(R.string.close))
        }
        Text(
            text = "공지",
            fontSize = 60.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NoticeScreenPreview() {
    NoticeScreen(
        onCloseButtonClicked = {}
    )
}