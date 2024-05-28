package com.jukco.waitforme.ui.sign.sign_up

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jukco.waitforme.R
import com.jukco.waitforme.data.mock.MockAuthProvider
import com.jukco.waitforme.data.mock.MockSignRepository
import com.jukco.waitforme.data.mock.MockTokenManager
import com.jukco.waitforme.ui.LoadingDialogContainer
import com.jukco.waitforme.ui.sign.SignViewModel
import com.jukco.waitforme.ui.sign.StepIndicators
import com.jukco.waitforme.ui.theme.GreyDDD
import com.jukco.waitforme.ui.theme.MainBlack
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.NotoSansKR
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun SelectCustomerOwnerScreen(
    onSignUpButtonClicked: () -> Unit,
    dto: SignUpDto,
    customerOwner: Array<CustomerOwner>,
    isLoading: Boolean,
    onEvent: (SignUpEvent) -> Unit,
) {
    LoadingDialogContainer(isLoading = isLoading) {
        SelectCustomerOwnerLayout(
            onSignUpButtonClicked = onSignUpButtonClicked,
            dto = dto,
            customerOwner = customerOwner,
            onEvent = onEvent,
        )
    }
}

@Composable
fun SelectCustomerOwnerLayout(
    onSignUpButtonClicked: () -> Unit,
    dto: SignUpDto,
    customerOwner: Array<CustomerOwner>,
    onEvent: (SignUpEvent) -> Unit,
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 36.dp),
    ) {
        StepIndicators(
            currentStep = 2,
            endStep = 2,
        )
        Text(
            text = stringResource(R.string.i_am),
            style = TextStyle(
                fontFamily = NotoSansKR,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = MainBlack,
                lineHeight = 28.sp,
                letterSpacing = (-0.05).sp,
                platformStyle = PlatformTextStyle(includeFontPadding = false),
            ),
        )
        Spacer(modifier = Modifier.height(24.dp))
        CustomerOrOwner(
            items = customerOwner,
            defaultSelected = dto.isOwner,
            onItemSelected = { choice -> onEvent(SignUpEvent.ChooseCustomerOrOwner(choice)) },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                onEvent(
                    SignUpEvent.SignUp(onSignUpButtonClicked)
                )
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(R.string.sign_up))
        }
    }
}

@Composable
private fun CustomerOrOwner(
    items: Array<CustomerOwner>,
    defaultSelected: Boolean,
    onItemSelected: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items.forEach { item ->
            OutlinedCard(
                border = BorderStroke(2.dp, if (defaultSelected == item.value) MainBlue else GreyDDD),
                shape = RoundedCornerShape(12.dp),
                modifier = modifier
                    .clickable { onItemSelected(item.value) }
                    .defaultMinSize(minHeight = 120.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                        .padding(top = 24.dp, bottom = 22.dp)
                ) {
                    Text(
                        text = stringResource(item.title),
                        style = TextStyle(
                            fontFamily = NotoSansKR,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = MainBlack,
                            lineHeight = (23.68).sp,
                            letterSpacing = (-0.05).sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                    Text(
                        text = stringResource(item.description),
                        style = TextStyle(
                            fontFamily = NotoSansKR,
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            color = MainBlack,
                            lineHeight = (19.24).sp,
                            letterSpacing = (-0.05).sp,
                            platformStyle = PlatformTextStyle(includeFontPadding = false),
                        ),
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SelectCustomerOwnerLayoutPreview() {
    val viewModel = remember {
        SignViewModel(MockSignRepository, MockAuthProvider, MockAuthProvider, MockAuthProvider, MockTokenManager)
    }

    WaitForMeTheme {
        SelectCustomerOwnerLayout(
            onSignUpButtonClicked = {},
            dto = viewModel.signUpDto,
            customerOwner = SignViewModel.CUSTOMER_OWNER,
            onEvent = viewModel::onSignUpEvent
        )
    }
}
