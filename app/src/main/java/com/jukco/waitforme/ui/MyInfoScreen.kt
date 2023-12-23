package com.jukco.waitforme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
fun MyInfoScreen(
    onSignInButtonClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "내 정보",
            fontSize = 60.sp
        )
        Button(onClick = onSignInButtonClicked) {
            Text(stringResource(R.string.sign_in))
        }
        Button(onClick = onSignUpButtonClicked) {
            Text(stringResource(R.string.sign_up))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyInfoScreenPreview() {
    MyInfoScreen(
        onSignInButtonClicked = {},
        onSignUpButtonClicked = {}
    )
}