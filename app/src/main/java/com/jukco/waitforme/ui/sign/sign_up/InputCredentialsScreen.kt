package com.jukco.waitforme.ui.sign.sign_up

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.data.mock.MockAuthProvider
import com.jukco.waitforme.data.mock.MockSignRepository
import com.jukco.waitforme.ui.sign.ErrorMessage
import com.jukco.waitforme.ui.sign.SignGuide
import com.jukco.waitforme.ui.sign.SignViewModel
import com.jukco.waitforme.ui.sign.TextFieldButtonForm
import com.jukco.waitforme.ui.theme.GreyDDD
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme
import com.jukco.waitforme.ui.util.PhoneNumberVisualTransformation

@Composable
fun InputCredentialsScreen(
    onNextButtonClicked: () -> Unit,
    form: SignUpForm,
    @StringRes errorMessage: Int?,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    InputPhoneNumAndPasswordLayout(
        onNextButtonClicked = onNextButtonClicked,
        form = form,
        errorMessage = errorMessage,
        onEvent = onEvent,
        modifier = modifier,
    )
}

@Composable
fun InputPhoneNumAndPasswordLayout(
    onNextButtonClicked: () -> Unit,
    form: SignUpForm,
    @StringRes errorMessage: Int?,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 36.dp),
    ) {
        SignGuide(Modifier.padding(top = 72.dp, bottom = 58.dp))
        errorMessage?.let {
            ErrorMessage(stringResource(it))
        }
        if (form.verificationCodeSubmitted) {
            PasswordSetupForm(form, onEvent, modifier)
        }
        PhoneNumberSetupForm(form, onEvent, modifier)
        Spacer(modifier = Modifier.weight(1f))
        if (form.verificationCodeSubmitted) {
            Button(
                onClick = onNextButtonClicked,
                enabled = form.passwordSubmitted,
                modifier = Modifier.fillMaxWidth(),
                ) {
                Text(
                    text = stringResource(R.string.next_step),
                )
            }
        }
    }
}

@Composable
fun InputPhoneNumLayout(
    modifier: Modifier = Modifier,
) {
}

@Composable
fun PhoneNumberSetupForm(
    form: SignUpForm,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        if (form.phoneNumberSubmitted) {
            TextFieldButtonForm(
                textfield = {
                    OutlinedTextField(
                        value = form.verificationCode,
                        onValueChange = { code -> onEvent(SignUpEvent.InputVerificationCode(code)) },
                        readOnly = form.verificationCodeSubmitted,
                        textStyle = TextStyle(
                            fontFamily = NotoSansKR,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            lineHeight = 14.sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                            letterSpacing = (-0.05).em,
                        ),
                        placeholder = {
                            Text(
                                text = stringResource(R.string.placeholder_input_verification_code),
                                style = TextStyle(
                                    fontFamily = NotoSansKR,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                    letterSpacing = (-0.05).em,
                                ),
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        trailingIcon = { Text(text = "3:00") },
                        modifier = Modifier
                            .defaultMinSize(minWidth = 208.dp, minHeight = 48.dp)
                            .weight(2f),
                    )
                },
                buttons = {
                    Button(
                        onClick = { onEvent(SignUpEvent.SubmitVerificationCode) },
                        shape = RoundedCornerShape(8.dp),
                        enabled = !form.verificationCodeSubmitted,
                        modifier = Modifier
                            .defaultMinSize(minWidth = 104.dp, minHeight = 48.dp)
                            .weight(1f),
                    ) {
                        Text(
                            text = stringResource(R.string.confirm),
                            style = TextStyle(
                                fontFamily = NotoSansKR,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                lineHeight = 14.sp,
                                platformStyle = PlatformTextStyle(includeFontPadding = false),
                                letterSpacing = (-0.05).em,
                            ),
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        TextFieldButtonForm(
            textfield = {
                OutlinedTextField(
                    value = form.phoneNumber,
                    onValueChange = {phoneNum -> onEvent(SignUpEvent.InputPhoneNumber(phoneNum)) },
                    readOnly = form.verificationCodeSubmitted,
                    placeholder = { Text(text = stringResource(R.string.placeholder_input_id)) },
                    singleLine = true,
                    visualTransformation = PhoneNumberVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .defaultMinSize(minWidth = 208.dp, minHeight = 48.dp)
                        .weight(2f),
                )
            },
            buttons = {
                when (form.phoneNumberSubmitted) {
                    true -> {
                        val enabled = !form.verificationCodeSubmitted

                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = MainBlack, disabledContentColor = GreyDDD),
                            border = BorderStroke(1.dp, if (enabled) MainBlack else GreyDDD),
                            enabled = enabled,
                            modifier = Modifier
                                .defaultMinSize(minWidth = 104.dp, minHeight = 48.dp)
                                .weight(1f),
                        ) {
                            Text(
                                text = stringResource(R.string.re_request_verification_code),
                                style = TextStyle(
                                    fontFamily = NotoSansKR,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                    letterSpacing = (-0.05).em,
                                ),
                            )
                        }
                    }
                    false -> {
                        Button(
                            onClick = { onEvent(SignUpEvent.SubmitPhoneNumber) },
                            shape = RoundedCornerShape(8.dp),
                            contentPadding = PaddingValues(vertical = (13.5).dp),
                            modifier = Modifier
                                .defaultMinSize(minWidth = 104.dp, minHeight = 48.dp)
                                .weight(1f),
                        ) {
                            Text(
                                text = stringResource(R.string.request_verification_code),
                                style = TextStyle(
                                    fontFamily = NotoSansKR,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp,
                                    lineHeight = 14.sp,
                                    platformStyle = PlatformTextStyle(includeFontPadding = false),
                                    letterSpacing = (-0.05).em,
                                ),
                            )
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun PasswordSetupForm(
    form: SignUpForm,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = form.password,
            onValueChange = { password -> onEvent(SignUpEvent.InputPassword(password)) },
            placeholder = {
                Text(
                    text = stringResource(R.string.placeholder_input_sign_up_password),
                    style = TextStyle(
                        fontFamily = NotoSansKR,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        lineHeight = 14.sp,
                        platformStyle = PlatformTextStyle(includeFontPadding = false),
                        letterSpacing = (-0.05).em,
                    ),
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
        )
        OutlinedTextField(
            value = form.confirmPassword,
            onValueChange = { confirmPassword -> onEvent(SignUpEvent.InputConfirmPassword(confirmPassword)) },
            placeholder = { Text(text = stringResource(R.string.placeholder_input_confirm_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InputPhoneNumAndPwScreenPreview() {
    val viewModel = remember {
        SignViewModel(MockSignRepository, MockAuthProvider, MockAuthProvider, MockAuthProvider)
    }

    WaitForMeTheme {
        InputPhoneNumAndPasswordLayout(
            onNextButtonClicked = {},
            form = viewModel.signUpForm,
            errorMessage = viewModel.errorMessage,
            onEvent = viewModel::onSignUpEvent,
        )
    }
}
