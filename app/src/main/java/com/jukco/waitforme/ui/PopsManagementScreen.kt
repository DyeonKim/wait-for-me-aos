package com.jukco.waitforme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun PopsManagementScreen(
    onPopItemClicked: (id: Int) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "팝업 관리",
            fontSize = 60.sp
        )
        Button(onClick = { onPopItemClicked(0) }) {
            Text("팝업 상세")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PopsManagementScreenPreview() {
    PopsManagementScreen (
        onPopItemClicked = {}
    )
}