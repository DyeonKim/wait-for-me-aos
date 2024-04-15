package com.jukco.waitforme.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jukco.waitforme.R
import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.ui.theme.MainBlue

@Composable
fun GenderDialog(
    selected: GenderType,
    onSelectGender: (GenderType) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .widthIn(max = 400.dp)
                .aspectRatio(0.9f)
            ,
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 36.dp, bottom = 24.dp)
                    .padding(horizontal = 24.dp)
                ,
            ) {
                Text(
                    text = stringResource(R.string.dialog_gender_title),
                    modifier = Modifier.padding(bottom = 16.dp),
                )
                Text(
                    text = stringResource(R.string.dialog_gender_desc),
                )
                Spacer(modifier = Modifier
                    .heightIn(min = 48.dp)
                    .weight(1f))
                Row (
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                ){
                    GenderType.values().forEach { gender ->
                        val opacity = if (selected == gender) 1f else 0.3f

                        Card(
                            border = BorderStroke(1.dp, MainBlue),
                            modifier = Modifier
                                .clickable { onSelectGender(gender) }
                                .aspectRatio(0.75f)
                                .weight(1f)
                                .alpha(opacity)
                            ,
                        ) {
                            Text(text = stringResource(gender.stringId))
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 36.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(stringResource(R.string.close))
                    }
                    Button(
                        onClick = { onConfirmation() },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(stringResource(R.string.save))
                    }
                }
            }
        }
    }
}