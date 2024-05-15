package com.jukco.waitforme.ui.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.MainWhite
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme
import com.jukco.waitforme.ui.util.dpToSp

@Preview(showBackground = true)
@Composable
fun SignGuide(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.local_sign_announcement_1))
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = MainBlue)) {
                    append(stringResource(R.string.phone_number_is_id))
                }
                append(stringResource(R.string.local_sign_announcement_2))
                append(stringResource(R.string.sign_announcement))
            },
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = MainBlack,
                lineHeight = 28.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SocialSignGuide(
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        Text(
            text = buildAnnotatedString {
                append(stringResource(R.string.social_sign_announcement))
                append(stringResource(R.string.sign_announcement))
            },
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = MainBlack,
                lineHeight = 28.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        )
    }
}

@Composable
fun StepIndicators(
    modifier: Modifier = Modifier,
    currentStep: Int,
    endStep: Int,
    paddingValues: PaddingValues = PaddingValues(start = 0.dp, end = 0.dp, top = 0.dp, bottom = 96.dp),
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(paddingValues)
    ) {
        for (step in 1..endStep) {
            if (step <= currentStep) {
                PrevStepIndicator()
            } else {
                NextStepIndicator(step)
            }
            if (step != endStep) {
                Ellipsis()
            }
        }
    }
}

@Composable
private fun PrevStepIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFE3FB73)),
    ) {
        Icon(
            imageVector = Icons.Rounded.Check,
            contentDescription = null,
            tint = MainWhite,
            modifier = Modifier.width(14.dp),
        )
    }
}

@Composable
private fun NextStepIndicator(step: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(Color(0xFFC2ED00)),
    ) {
        Text(
            text = step.toString(),
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MainBlack,
                lineHeight = 14.sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
            fontSize = dpToSp(dp = 14.dp),
        )
    }
}

@Composable
private fun Ellipsis() {
    Row {
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(Color(0xFFC2ED00)),
        )
        Spacer(modifier = Modifier.width(3.dp))
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(Color(0xFFC2ED00)),
        )
        Spacer(modifier = Modifier.width(3.dp))
        Box(
            modifier = Modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(Color(0xFFC2ED00)),
        )
    }
}

@Composable
fun TextFieldButtonForm(
    modifier: Modifier = Modifier,
    textField: @Composable RowScope.() -> Unit,
    buttons: @Composable RowScope.() -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
        content = {
            textField()
            buttons()
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun StepIndicatorsPreview() {
    WaitForMeTheme {
        StepIndicators(currentStep = 1, endStep = 2)
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldButtonFormPreview() {
    WaitForMeTheme {
        TextFieldButtonForm(
            textField = {
                OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.defaultMinSize(minWidth = 84.dp, minHeight = 48.dp))
            },
            buttons = {
                Button(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .defaultMinSize(minWidth = 104.dp, minHeight = 48.dp),
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "인증번호받기")
                }
                OutlinedButton(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.Center)
                        .defaultMinSize(minWidth = 104.dp, minHeight = 48.dp),
                    onClick = {},
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text(text = "인증번호받기")
                }
            },
        )
    }
}
