package com.jukco.waitforme.ui.sign.sign_up

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jukco.waitforme.ui.sign.SignAnnouncement
import com.jukco.waitforme.ui.theme.NotoSansKR

@Composable
fun InputPhoneNumAndPwScreen(
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: SignUpViewModel = viewModel()
    InputPhoneNumAndPw(
        viewModel = viewModel,
        onNextButtonClicked = onNextButtonClicked,
        modifier = modifier,
    )
}

@Composable
fun InputPhoneNumAndPw(
    viewModel: SignUpViewModel,
    onNextButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 36.dp),
    ) {
        SignAnnouncement()
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier,
        ) {
            if (viewModel.verificationSubmitted) {
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = viewModel::inputPassword,
                    placeholder = { Text(text = "비밀번호를 입력해주세요") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                )
                OutlinedTextField(
                    value = viewModel.verificationPW,
                    onValueChange = viewModel::inputVerificationPW,
                    placeholder = { Text(text = "비밀번호를 다시 입력해주세요") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            if (viewModel.phoneNumberSubmitted) {
                VerificationCodeInput(
                    verificationCode = viewModel.verificationCode,
                    inputVerificationCode = viewModel::inputVerificationCode,
                    submitVerificationCode = viewModel::submitVerificationCode,
                    modifier = modifier,
                )
            }
            PhoneNumberInput(
                phoneNumber = viewModel.phoneNumber,
                inputPhoneNumber = viewModel::inputPhoneNumber,
                submitPhoneNumber = viewModel::submitPhoneNumber,
                modifier = modifier,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        if (viewModel.verificationSubmitted) {
            Button(
                onClick = {
                    if (viewModel.submitPassword()) {
                        onNextButtonClicked()
                    }
                },
                modifier = Modifier.fillMaxWidth(),

            ) {
                Text(
                    text = "계속하기",
                )
            }
        }
    }
}

@Composable
private fun PhoneNumberInput(
    phoneNumber: String,
    inputPhoneNumber: (String) -> Unit,
    submitPhoneNumber: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = inputPhoneNumber,
            placeholder = { Text(text = "핸드폰 번호를 입력해주세요") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.weight(2f),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = submitPhoneNumber,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 17.dp, horizontal = 14.dp),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = "인증번호받기",
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

@Composable
private fun VerificationCodeInput(
    verificationCode: String,
    inputVerificationCode: (String) -> Unit,
    submitVerificationCode: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth(),
    ) {
        OutlinedTextField(
            value = verificationCode,
            onValueChange = inputVerificationCode,
            placeholder = {
                Text(
                    text = "인증번호를 입력해주세요",
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
            modifier = Modifier.weight(2f),
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = submitVerificationCode,
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(vertical = 17.dp, horizontal = 14.dp),
            modifier = Modifier.weight(1f),
        ) {
            Text(
                text = "확인하기",
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

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    val viewModel: SignUpViewModel = viewModel()

    InputPhoneNumAndPw(
        viewModel = viewModel,
        onNextButtonClicked = {},
    )
}
