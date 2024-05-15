package com.jukco.waitforme.ui.my_info

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jukco.waitforme.R
import com.jukco.waitforme.data.mock.MockAuthProvider
import com.jukco.waitforme.data.mock.MockSignRepository
import com.jukco.waitforme.data.mock.MockUserRepository
import com.jukco.waitforme.data.network.model.Provider
import com.jukco.waitforme.ui.LoadingDialogContainer
import com.jukco.waitforme.ui.components.CustomDatePickerDialog
import com.jukco.waitforme.ui.components.ErrorMessage
import com.jukco.waitforme.ui.components.GenderDialog
import com.jukco.waitforme.ui.theme.GreyDDD
import com.jukco.waitforme.ui.theme.MainBlue
import com.jukco.waitforme.ui.theme.WaitForMeTheme

@Composable
fun MyInfoScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: MyInfoViewModel = viewModel(factory = MyInfoViewModel.Factory)

    when (viewModel.state) {
        MyInfoState.Fail -> {
            /* TODO : 오류 페이지를 띄움 */
        }
        MyInfoState.Success -> {
            LoadingDialogContainer(isLoading = viewModel.showLoadingDialog) {
                MyInfoLayout(
                    isEdit = viewModel.isEdit,
                    myInfo = viewModel.myInfo,
                    errorMessage = viewModel.errorMessage,
                    onEvent = viewModel::onEvent,
                )
                if (viewModel.isEdit && viewModel.openGenderDialog) {
                    GenderDialog(
                        selected = viewModel.myInfo.genderType,
                        onSelectGender = { gender -> viewModel.onEvent(MyInfoEvent.SelectGender(gender)) },
                        onDismissRequest = { viewModel.onEvent(MyInfoEvent.CloseGenderDialog) },
                        onConfirmation = { viewModel.onEvent(MyInfoEvent.ConfirmGender) },
                    )
                }
                if (viewModel.isEdit && viewModel.openBirthDayPickerDialog) {
                    CustomDatePickerDialog(
                        selectedDate = viewModel.myInfo.birthedAt,
                        onDismissRequest = { viewModel.onEvent(MyInfoEvent.CloseBirthDayPickerDialog) },
                        onConfirmation = { date -> viewModel.onEvent(MyInfoEvent.ConfirmBirthDayPickerDialog(date)) }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyInfoLayout(
    isEdit: Boolean,
    myInfo: UserInfoDto,
    @StringRes errorMessage: Int?,
    onEvent: (MyInfoEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.navi_my_info)) },
                navigationIcon = {
                    if (isEdit) {
                        IconButton(onClick = { onEvent(MyInfoEvent.Cancel) }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_arrow_back),
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    }
                },
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .padding(bottom = 36.dp)
                .verticalScroll(rememberScrollState())
        ) {
            ProfileImage(
                isEdit = isEdit,
                image = myInfo.profileImage,
                inputProfileImage = { imageUri -> onEvent(MyInfoEvent.InputProfileImage(imageUri)) },
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .size(72.dp)
            )
            ProfileInfoForm(
                isEdit = isEdit,
                form = myInfo,
                errorMessage = errorMessage,
                onEvent = onEvent,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.weight(1f))
            when (isEdit) {
                true -> {
                    Button(
                        onClick = { onEvent(MyInfoEvent.Save) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.save))
                    }
                }
                false -> {
                    Button(
                        onClick = { onEvent(MyInfoEvent.Edit) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(R.string.edit))
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileImage(
    isEdit: Boolean,
    image: String?,
    inputProfileImage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            if (uri != null)
                inputProfileImage(uri.toString())
        }
    )

    Box(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(image)
                .build(),
            contentDescription = stringResource(R.string.profile_img),
            placeholder = painterResource(R.drawable.img_default_profile),
            error = painterResource(R.drawable.img_default_profile),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .border(
                    BorderStroke(1.dp, GreyDDD),
                    CircleShape
                )
                .padding(1.dp)
                .clip(CircleShape)
        )
        if (isEdit) {
            SmallFloatingActionButton(
                onClick = { singlePhotoPickerLauncher.launch(
                    PickVisualMediaRequest(
                        ActivityResultContracts.PickVisualMedia.ImageOnly
                    )
                ) },
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxSize(0.25f)
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.change_profile_image),
                    modifier = Modifier.fillMaxSize(0.7f)
                )
            }
        }
    }
}

@Composable
fun ProfileInfoForm(
    isEdit: Boolean,
    form: UserInfoDto,
    @StringRes errorMessage: Int?,
    onEvent: (MyInfoEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        OutlinedTextField(
            value = form.name,
            onValueChange = { name ->
                onEvent(MyInfoEvent.InputName(name))
                Log.d("ProfileInfoForm: ", "$name : ${form.name}")
                            },
            placeholder = { Text(text = stringResource(R.string.nickname)) },
            singleLine = true,
            enabled = isEdit,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = form.phoneNumber,
            onValueChange = {},
            enabled = false,
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = stringResource(form.genderType.stringId),
                onValueChange = {},
                placeholder = { Text(text = stringResource(R.string.gender)) },
                enabled = isEdit,
                readOnly = true,
                trailingIcon = {
                    if (isEdit) {
                        IconButton(onClick = { onEvent(MyInfoEvent.ShowGenderDialog) }) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = stringResource(R.string.select_gender),
                                tint = MainBlue
                            )
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = form.birthedAt ?: "",
                onValueChange = {},
                placeholder = { Text(text = stringResource(R.string.birthed_at)) },
                enabled = isEdit,
                readOnly = true,
                trailingIcon = {
                    if (isEdit) {
                        IconButton(onClick = { onEvent(MyInfoEvent.ShowBirthDayPickerDialog) }) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = stringResource(R.string.input_birthday),
                                tint = MainBlue
                            )
                        }
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
        if (isEdit && form.provider == Provider.LOCAL) {
            OutlinedTextField(
                value = form.password,
                onValueChange = { password -> onEvent(MyInfoEvent.InputPassword(password)) },
                placeholder = { Text(text = stringResource(R.string.placeholder_input_sign_up_password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = form.confirmPassword,
                onValueChange = { confirmPW -> onEvent(MyInfoEvent.InputConfirmPassword(confirmPW)) },
                placeholder = { Text(text = stringResource(R.string.placeholder_input_confirm_password)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            errorMessage?.let {
                ErrorMessage(message = stringResource(errorMessage))
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            // TODO : 아이콘 추가, 제공자에 따라 변경
            when (form.provider) {
                Provider.GOOGLE -> Text(text = stringResource(R.string.desc_login_with_google))
                Provider.KAKAO -> Text(text = stringResource(R.string.desc_login_with_kakao))
                Provider.NAVER -> Text(text = stringResource(R.string.desc_login_with_naver))
                else -> {}
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyInfoLayoutPreview() {
    val viewModel = remember {
        MyInfoViewModel(MockUserRepository, MockSignRepository, MockAuthProvider, MockAuthProvider, MockAuthProvider)
    }

    WaitForMeTheme {
        MyInfoLayout(
            isEdit = viewModel.isEdit,
            myInfo = viewModel.myInfo,
            errorMessage = viewModel.errorMessage,
            onEvent = viewModel::onEvent,
        )
    }
}