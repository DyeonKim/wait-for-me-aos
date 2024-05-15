package com.jukco.waitforme.ui.my_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.UserInfoRequest
import com.jukco.waitforme.data.network.model.UserInfoRes
import com.jukco.waitforme.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MyInfoViewModel(private val userRepository: UserRepository, ) : ViewModel() {
    private val _oldMyInfo = MutableStateFlow(UserInfoRes())
    var state by mutableStateOf<MyInfoState>(MyInfoState.Loading)
        private set
    var myInfo by mutableStateOf(UserInfoDto())
        private set
    var openGenderDialog by mutableStateOf(false)
        private set
    var openBirthDayPickerDialog by mutableStateOf(false)
        private set

    init {
        getMyInfo()
    }

    fun onEvent(event: MyInfoEvent) {
        when (event) {
            is MyInfoEvent.InputName -> {
                myInfo = myInfo.copy(
                    name = event.name,
                    isValidNameFormat = checkName(event.name)
                )
            }
            is MyInfoEvent.CheckDuplicateName -> {
                /* TODO : 이름 중복 체크 */
            }
            is MyInfoEvent.SelectGender -> { myInfo = myInfo.copy(genderType = event.genderType) }
            is MyInfoEvent.InputProfileImage -> { myInfo = myInfo.copy(profileImage = event.profileUri) }
            is MyInfoEvent.InputPassword -> {
                myInfo = myInfo.copy(
                    password = event.password,
                    isValidPasswordFormat = checkPassword(event.password)
                )
            }
            is MyInfoEvent.InputConfirmPassword -> {
                val passwordConfirmed = (myInfo.password == event.confirmPassword)

                myInfo = myInfo.copy(
                    confirmPassword = event.confirmPassword,
                    passwordSubmitted = (myInfo.isValidPasswordFormat == true) && passwordConfirmed
                )
            }
            is MyInfoEvent.ShowGenderDialog -> { openGenderDialog = true }
            is MyInfoEvent.CloseGenderDialog -> {
                myInfo = myInfo.copy(genderType = _oldMyInfo.value.genderType)
                openGenderDialog = false
            }
            is MyInfoEvent.ConfirmGender -> { openGenderDialog = false }
            is MyInfoEvent.ShowBirthDayPickerDialog -> { openBirthDayPickerDialog = true }
            is MyInfoEvent.CloseBirthDayPickerDialog -> { openBirthDayPickerDialog = false }
            is MyInfoEvent.ConfirmBirthDayPickerDialog -> {
                myInfo = myInfo.copy(birthedAt = event.date)
                openBirthDayPickerDialog = false
            }
            is MyInfoEvent.Edit -> { state = MyInfoState.Edit }
            is MyInfoEvent.Save -> { save() }
            is MyInfoEvent.Cancel -> {
                reset()
                state = MyInfoState.Read
            }
        }
    }

    private fun checkName(name: String): Boolean {
        /* TODO : 이름규칙 확인 */
        return true
    }

    private fun checkPassword(password: String): Boolean {
        /* TODO : 비밀번호 규칙 확인 */
        return true
    }

    private fun reset() {
        myInfo = UserInfoDto(_oldMyInfo.value)
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            state = MyInfoState.Loading

            try {
                val response = userRepository.getUserInfo()

                if (response.isSuccessful) {
                    val userInfoRes = response.body()!!

                    _oldMyInfo.value = userInfoRes
                    myInfo = UserInfoDto(userInfoRes)
                    state = MyInfoState.Read
                    return@launch
                }
                // TODO : 30X, 40X 처리
                // TODO : 로그인이 안 되어 있다면 로그인페이지로 이동
                state = MyInfoState.Error
            } catch (e: IOException) {
                // TODO : 네트워크 오류 처리
                state = MyInfoState.Error
            }

        }
    }

    private fun save() {
        // 1. 이름 변경 있는지 체크 (변경이 있으면 2번, 3번)
        if (myInfo.name != _oldMyInfo.value.name) {
            // 2. 이름 규칙 지켰는지 체크
            if (myInfo.isValidNameFormat) {
                // 3. 이름 중복 체크 (이름 중복 확인을 해달라는 오류 메시지)
            } else {
                return
            }
        }

        // 4. 비밀번호 변경하려는 지 체크 (변경이 있으면 5번, 6번)
        if (myInfo.password.isNotBlank() || myInfo.confirmPassword.isNotBlank()) {
            // 5. 비밀번호 규칙 체크
            // 6. 비밀번호 확인 일치 체크
            if (myInfo.isValidPasswordFormat == false || myInfo.passwordSubmitted == false) {
                return
            }
        }

        val userInfoReq = myInfo.toUserInfoRequest()
        editMyInfo(userInfoReq)
    }

    private fun editMyInfo(userInfoReq: UserInfoRequest) {
        viewModelScope.launch {
            state = MyInfoState.Loading

            try {
                val response = userRepository.editUserInfo(userInfoReq)

                if (response.isSuccessful) {
                    val userInfoRes = response.body()!!
                    _oldMyInfo.value = userInfoRes
                    myInfo = UserInfoDto(userInfoRes)
                    state = MyInfoState.Read
                    return@launch
                }
                // TODO : 다른 오류 처리
                state = MyInfoState.Fail
            } catch (e: IOException) {
                // TODO : 네트워크 오류 처리
                state = MyInfoState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val userRepository = application.container.userRepository

                MyInfoViewModel(userRepository)
            }
        }
    }
}