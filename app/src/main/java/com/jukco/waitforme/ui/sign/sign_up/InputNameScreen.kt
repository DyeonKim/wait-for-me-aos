package com.jukco.waitforme.ui.sign.sign_up

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.data.repository.MockAuthProvider
import com.jukco.waitforme.data.repository.MockSignRepository
import com.jukco.waitforme.ui.sign.ErrorMessage
import com.jukco.waitforme.ui.sign.SignViewModel
import com.jukco.waitforme.ui.sign.StepIndicators
import com.jukco.waitforme.ui.theme.GreyEEE
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun InputNameScreen(
    onNextButtonClicked: () -> Unit,
    form: SignUpForm,
    @StringRes errorMessage: Int?,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp, vertical = 36.dp),
    ) {
        StepIndicators(
            currentStep = 1,
            endStep = 2,
            modifier = modifier.padding(bottom = 96.dp),
        )
        Text(
            text = stringResource(R.string.guide_sign_up_nickname),
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MainBlack,
                lineHeight = (26.64).sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = form.name,
            onValueChange = { name -> onEvent(SignUpEvent.InputName(name)) },
            placeholder = {
                Text(text = stringResource(R.string.placeholder_input_nickname))
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        errorMessage?.let {
            ErrorMessage(message = stringResource(it), Modifier.padding(top = 12.dp))
        }
        Text(
            text = stringResource(R.string.skip_input),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
                .clickable { onNextButtonClicked() }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .defaultMinSize(minHeight = 48.dp)
                .fillMaxWidth()
                .background(color = GreyEEE, shape = RoundedCornerShape(8.dp))
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = MainBlue, fontWeight = FontWeight.Bold)) {
                        append(stringResource(R.string.description_nickname_1))
                    }
                    append(stringResource(R.string.description_nickname_2))
                },
                style = TextStyle(
                    fontFamily = NotoSansKR,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp,
                    color = MainBlack,
                    lineHeight = (19.24).sp,
                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                )
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onNextButtonClicked,
            enabled = form.nameSubmitted,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = stringResource(R.string.next_step),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputNameLayoutPreview() {
    val viewModel = remember {
        SignViewModel(MockSignRepository, MockAuthProvider, MockAuthProvider, MockAuthProvider)
    }

    WaitForMeTheme {
        InputNameScreen(
            onNextButtonClicked = {},
            form = viewModel.signUpForm,
            errorMessage = viewModel.errorMessage,
            onEvent = viewModel::onSignUpEvent
        )
    }
}