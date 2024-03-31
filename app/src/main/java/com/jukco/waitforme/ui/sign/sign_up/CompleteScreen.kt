package com.jukco.waitforme.ui.sign.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun CompleteScreen(
    onStartButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 36.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            Image(
                painter = painterResource(R.drawable.baseline_image_24),
                contentDescription = null,
                modifier = Modifier.size(100.dp),
            )
            Spacer(modifier = Modifier.height(36.dp))
            Text(
                text = stringResource(R.string.success_sign_up),
                textAlign = TextAlign.Center,
            )
        }
        Button(
            onClick = onStartButtonClicked,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.start))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CompleteScreenPreview() {
    WaitForMeTheme {
        CompleteScreen(
            onStartButtonClicked = {},
        )
    }
}