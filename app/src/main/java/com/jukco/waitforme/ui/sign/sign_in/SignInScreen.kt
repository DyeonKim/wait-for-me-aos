package com.jukco.waitforme.ui.sign.sign_in

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jukco.waitforme.R
import com.jukco.waitforme.data.mock.MockAuthProvider
import com.jukco.waitforme.data.mock.MockSignRepository
import com.jukco.waitforme.data.repository.AuthProvider
import com.jukco.waitforme.ui.components.SocialSignIconButton
import com.jukco.waitforme.ui.navi.Route
import com.jukco.waitforme.ui.sign.ErrorMessage
import com.jukco.waitforme.ui.sign.SignGuide
import com.jukco.waitforme.ui.sign.SignViewModel
import com.jukco.waitforme.ui.theme.GreyAAA
import com.jukco.waitforme.ui.theme.KakaoYellow
import com.jukco.waitforme.ui.theme.MainWhite
import com.jukco.waitforme.ui.theme.NaverGreen
import com.jukco.waitforme.ui.theme.WaitForMeTheme
import com.jukco.waitforme.ui.util.PhoneNumberVisualTransformation

@Composable
fun SignInScreen(
    state: SignInState,
    form: SignInForm,
    socialSignIn: (SocialService) -> AuthProvider,
    onEvent: (SignInEvent) -> Unit,
    moveScreen: (Route) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        SignInState.Init -> {
            SignInLayout(
                form = form,
                socialSignIn = socialSignIn,
                onEvent = onEvent,
                moveScreen = moveScreen,
                modifier = modifier,
            )
        }
        SignInState.Loading -> {
            LoadingSignInLayout(form = form, modifier = modifier)
        }
        SignInState.MovingMain -> {
            LaunchedEffect(Unit) {
                moveScreen(Route.StoreList)
            }
        }
    }
}

@Composable
fun LoadingSignInLayout(
    form: SignInForm,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxWidth()) {
        SignInLayout(
            form = form,
            socialSignIn = { MockAuthProvider },
            onEvent = {},
            moveScreen = {},
            modifier = modifier,
        )
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false),
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(100.dp),
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun SignInLayout(
    form: SignInForm,
    socialSignIn: (SocialService) -> AuthProvider,
    onEvent: (SignInEvent) -> Unit,
    moveScreen: (Route) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 56.dp),
    ) {
        SignGuide(Modifier.padding(top = 72.dp, bottom = 70.dp))
        SignInForm(
            form,
            onEvent,
            modifier = modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.weight(1f))
        SocialSignInButtons(
            socialSignIn,
            onEvent,
            modifier = modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedButton(
            onClick = {
                moveScreen(Route.SignUpInputCredentials)
                onEvent(SignInEvent.Reset)
            },
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
                .clickable { moveScreen(Route.StoreList) },
        )
    }
}

@Composable
fun SignInForm(
    form: SignInForm,
    onEvent: (SignInEvent) -> Unit,
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
            ErrorMessage(
                message = stringResource(R.string.error_id_password),
                modifier = Modifier.padding(top = 12.dp),
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { onEvent(SignInEvent.OnSignInClicked) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.sign_in))
        }
    }
}

@Composable
fun SocialSignInButtons(
    socialSignIn: (SocialService) -> AuthProvider,
    onEvent: (SignInEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier,
    ) {
        SocialSignIconButton(
            authProvider = socialSignIn(SocialService.Kakao),
            onSignInClicked = { user -> onEvent(SignInEvent.OnSocialSignInClicked(user)) },
            buttonColors = ButtonDefaults.buttonColors(containerColor = KakaoYellow),
            modifier = Modifier.size(48.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        SocialSignIconButton(
            authProvider = socialSignIn(SocialService.Naver),
            onSignInClicked = { user -> onEvent(SignInEvent.OnSocialSignInClicked(user)) },
            buttonColors = ButtonDefaults.buttonColors(containerColor = NaverGreen),
            modifier = Modifier.size(48.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        SocialSignIconButton(
            authProvider = socialSignIn(SocialService.Google),
            onSignInClicked = { user -> onEvent(SignInEvent.OnSocialSignInClicked(user)) },
            buttonBorder = BorderStroke(1.dp, GreyAAA),
            buttonColors = ButtonDefaults.buttonColors(containerColor = MainWhite),
            modifier = Modifier.size(48.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignInLayoutPreview() {
    val viewModel = remember {
        SignViewModel(MockSignRepository, MockAuthProvider, MockAuthProvider, MockAuthProvider)
    }

    WaitForMeTheme {
        SignInLayout(
            form = viewModel.signInform,
            socialSignIn = viewModel::getSocialSign,
            onEvent = viewModel::onSignInEvent,
            moveScreen = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingSignInLayoutPreview() {
    val viewModel = remember {
        SignViewModel(MockSignRepository, MockAuthProvider, MockAuthProvider, MockAuthProvider)
    }

    WaitForMeTheme {
        LoadingSignInLayout(form = viewModel.signInform)
    }
}
