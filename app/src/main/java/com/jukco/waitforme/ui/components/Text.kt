package com.jukco.waitforme.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.ErrorRed

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ){
        Icon(
            painter = painterResource(R.drawable.ic_error),
            contentDescription = null,
            tint = ErrorRed,
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = message,
            color = ErrorRed,
        )
    }
}