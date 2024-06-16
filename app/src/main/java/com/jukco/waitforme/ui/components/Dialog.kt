package com.jukco.waitforme.ui.components

import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.jukco.waitforme.R
import com.jukco.waitforme.data.network.model.GenderType
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.WaitForMeTheme
import com.jukco.waitforme.ui.util.DATE_FORMAT
import java.time.LocalDate
import java.util.Date
import java.util.Locale

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    selectedDate: String?,
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
) {
    val currentYear = LocalDate.now().year
    val datePickerState = rememberDatePickerState(
        yearRange = (currentYear - 100)..currentYear,
        initialDisplayMode = DisplayMode.Picker,
        initialSelectedDateMillis = when (selectedDate) {
            null -> System.currentTimeMillis()
            else -> {
                val formatter = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).apply {
                    timeZone = TimeZone.getTimeZone("UTC")
                }
                formatter.parse(selectedDate)?.time ?: System.currentTimeMillis()
            }
        },
    )

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = {
                    datePickerState.selectedDateMillis?.let { selectedDateMillis ->
                        val formattedDate = SimpleDateFormat(
                            DATE_FORMAT,
                            Locale.getDefault()
                        ).format(Date(selectedDateMillis))
                        onConfirmation(formattedDate)
                    }
                }
            ) {
                Text(text = stringResource(R.string.ok))
        } },
        dismissButton = { TextButton(onClick = onDismissRequest) {
            Text(text = stringResource(R.string.cancel))
        } },
    ) {
        DatePicker(
            state = datePickerState,
            title = {},
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun GenderDialogPreview() {
    WaitForMeTheme {
        val selectedGender = remember {
            mutableStateOf(GenderType.FEMALE)
        }

        GenderDialog(
            selected = selectedGender.value,
            onSelectGender = { gender -> selectedGender.value = gender },
            onDismissRequest = {},
            onConfirmation = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DatePickerDialogPreview() {
    WaitForMeTheme {
        CustomDatePickerDialog(
            selectedDate = "2024-12-01",
            onDismissRequest = {},
            onConfirmation = {},
        )
    }
}

