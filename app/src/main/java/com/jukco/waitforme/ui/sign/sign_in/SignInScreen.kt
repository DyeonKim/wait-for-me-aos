package com.jukco.waitforme.ui.sign.sign_in

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.sign.SignGuide
import com.jukco.waitforme.ui.theme.ErrorRed
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.KakaoYellow
import com.jukco.waitforme.ui.theme.MainWhite
import com.jukco.waitforme.ui.theme.NaverGreen
import com.jukco.waitforme.ui.util.PhoneNumberVisualTransformation

@Composable
fun SignInScreen(
    onSignInClicked: (String, String) -> Unit,
    goSignUp: () -> Unit,
    goMain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: SignInViewModel = viewModel()

    SignInLayout(
        form = viewModel.form,
        onEvent = viewModel::onEvent,
        onSignInClicked = onSignInClicked,
        goSignUp = goSignUp,
        onNoSignClicked = goMain,
        modifier = modifier,
    )
}

@Composable
private fun SignInLayout(
    form: SignInForm,
    onEvent: (SignInEvent) -> Unit,
    onSignInClicked: (String, String) -> Unit,
    goSignUp: () -> Unit,
    onNoSignClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 56.dp),
    ) {
        SignGuide(modifier)
        SignInForm(
            form = form,
            onEvent = onEvent,
            signIn = onSignInClicked,
            modifier = modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.weight(1f))
        SocialSignInButtons(modifier)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = goSignUp,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
        Spacer(modifier = Modifier.height(39.dp))
        Text(
            text = stringResource(R.string.no_sign_in),
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNoSignClicked() },
        )
    }
}

@Composable
private fun SignInForm(
    form: SignInForm,
    onEvent: (SignInEvent) -> Unit,
    signIn: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OutlinedTextField(
            value = form.id,
            onValueChange = { id -> onEvent(SignInEvent.InputId(id)) },
            placeholder = { Text(text = stringResource(R.string.placeholder_input_id)) },
            singleLine = true,
            visualTransformation = PhoneNumberVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = form.password,
            onValueChange = { password -> onEvent(SignInEvent.InputPassword(password)) },
            placeholder = { Text(text = stringResource(R.string.placeholder_input_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
        )
        if (form.hasError == true) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 12.dp),
            ){
                Icon(
                    painter = painterResource(R.drawable.ic_error),
                    contentDescription = null,
                    tint = ErrorRed,
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.error_id_password),
                    color = ErrorRed,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { onEvent(SignInEvent.CheckSignInValid(signIn)) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.sign_in))
        }
    }
}

@Composable
private fun SocialSignInButtons(
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth(),
    ) {
        Button(
            shape = CircleShape,
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = KakaoYellow),
            modifier = Modifier
                .size(48.dp),
        ) {
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            shape = CircleShape,
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = NaverGreen),
            modifier = Modifier
                .size(48.dp),
        ) {
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            shape = CircleShape,
            onClick = { /*TODO*/ },
            border = BorderStroke(1.dp, GreyAAA),
            colors = ButtonDefaults.buttonColors(containerColor = MainWhite),
            modifier = Modifier
                .size(48.dp),
        ) {
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInLayoutPreview() {
    val viewModel: SignInViewModel = viewModel()

    SignInLayout(
        form = viewModel.form,
        onEvent = viewModel::onEvent,
        onSignInClicked = {s1, s2 -> },
        goSignUp = {},
        onNoSignClicked = {},
    )
}
