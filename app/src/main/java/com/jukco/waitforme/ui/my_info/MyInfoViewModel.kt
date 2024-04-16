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
import com.jukco.waitforme.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class MyInfoViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _myInfo = MutableStateFlow(UserInfo())
    var state by mutableStateOf<MyInfoState>(MyInfoState.Loading)
        private set
    var myInfo by mutableStateOf(UserInfo())
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
                    nameSubmitted = (event.name == _myInfo.value.name)
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
                    passwordSubmitted = event.password.isNotBlank() && (event.password == myInfo.confirmPassword)
                )
                /* TODO : 비밀번호 규칙 확인 및 확인과 일치 여부 확인 */
            }
            is MyInfoEvent.InputConfirmPassword -> {
                myInfo = myInfo.copy(
                    confirmPassword = event.confirmPassword,
                    passwordSubmitted = (event.confirmPassword == myInfo.password)
                )
                /* TODO : 비밀번호와 일치 여부 확인 */
            }
            is MyInfoEvent.ShowGenderDialog -> { openGenderDialog = true }
            is MyInfoEvent.CloseGenderDialog -> {
                myInfo = myInfo.copy(genderType = _myInfo.value.genderType)
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

    private fun save() {
        if (myInfo.nameSubmitted == false) {
            // TODO : 이름 중복 확인을 해달라는 오류 메시지
            return
        }
        if (myInfo.passwordSubmitted == false){
            return
        }

        val userInfoReq = UserInfoRequest(
            name = if (myInfo.nameSubmitted == true) myInfo.name else _myInfo.value.name,
            birthedAt = myInfo.birthedAt,
            genderType = myInfo.genderType,
            profileImage = myInfo.profileImage,
            password = if (myInfo.passwordSubmitted == true) myInfo.password else null
        )
        editMyInfo(userInfoReq)
    }

    private fun reset() {
        myInfo = _myInfo.value
    }

    private fun getMyInfo() {
        viewModelScope.launch {
            state = MyInfoState.Loading

            try {
                val response = userRepository.getUserInfo()

                if (response.isSuccessful) {
                    val userInfo = UserInfo(response.body()!!)
                    _myInfo.value = userInfo
                    myInfo = userInfo
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

    private fun editMyInfo(userInfoReq: UserInfoRequest) {
        viewModelScope.launch {
            state = MyInfoState.Loading

            try {
                val response = userRepository.editUserInfo(userInfoReq)

                if (response.isSuccessful) {
                    val userInfo = UserInfo(response.body()!!)
                    _myInfo.value = userInfo
                    myInfo = userInfo
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