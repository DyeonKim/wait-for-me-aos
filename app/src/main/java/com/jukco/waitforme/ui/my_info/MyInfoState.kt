package com.jukco.waitforme.ui.my_info

sealed interface MyInfoState {
    object Read : MyInfoState
    object Edit : MyInfoState
    object Loading : MyInfoState
    object Fail : MyInfoState
    object Error : MyInfoState
}