package com.jukco.waitforme.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Text(
        text = "Loading....",
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxSize(),
    )
}

@Composable
fun LoadingDialogContainer(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
    content: @Composable () -> Unit,
) {
    Box(modifier.fillMaxSize()) {
        content()
        if (isLoading) {
            Dialog(onDismissRequest, properties) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(100.dp),
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingDialogContainerPreview() {
    WaitForMeTheme {
        LoadingDialogContainer(isLoading = true) {
            Column(
                Modifier
                    .fillMaxSize()
                    .background(color = Color.Yellow),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "test")
            }
        }
    }
}