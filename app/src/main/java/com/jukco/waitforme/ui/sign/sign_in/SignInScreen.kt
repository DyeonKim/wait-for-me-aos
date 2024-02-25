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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jukco.waitforme.R
import com.jukco.waitforme.ui.sign.SignAnnouncement
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.KakaoYellow
import com.jukco.waitforme.ui.theme.MainWhite
import com.jukco.waitforme.ui.theme.NaverGreen

@Composable
fun SignInScreen(
    onSignInClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    goMain: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val viewModel: SignInViewModel = viewModel()

    SignContainer(
        viewModel = viewModel,
        onSignInClicked = onSignInClicked,
        onSignUpClicked = onSignUpClicked,
        onNoSignClicked = goMain,
        modifier = modifier,
    )
}

@Composable
fun SignContainer(
    viewModel: SignInViewModel,
    onSignInClicked: () -> Unit,
    onSignUpClicked: () -> Unit,
    onNoSignClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(bottom = 56.dp),
    ) {
        SignAnnouncement(modifier)
        SignIn(
            viewModel = viewModel,
            onSignInClicked = onSignInClicked,
            modifier = modifier,
        )
        Spacer(modifier = Modifier.weight(1f))
        SocialSignInButtons(modifier)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = onSignUpClicked,
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
private fun SignIn(
    viewModel: SignInViewModel,
    onSignInClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        OutlinedTextField(
            value = viewModel.id,
            onValueChange = viewModel::inputId,
            placeholder = { Text(text = stringResource(R.string.placeholder_input_id)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = viewModel::inputPassword,
            placeholder = { Text(text = stringResource(R.string.placeholder_input_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onSignInClicked,
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
private fun SignInScreenPreview() {
    val viewModel: SignInViewModel = viewModel()

    SignContainer(
        viewModel = viewModel,
        onSignInClicked = {},
        onSignUpClicked = {},
        onNoSignClicked = {},
    )
}
