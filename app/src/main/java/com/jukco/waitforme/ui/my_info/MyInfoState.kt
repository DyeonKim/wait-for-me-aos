package com.jukco.waitforme.ui.my_info

sealed interface MyInfoState {
    object Success : MyInfoState
    object Fail : MyInfoState
//    object Error : MyInfoState // FIXME: 스낵바로 띄울 것이므로 따로 변수 분리해서 작성하면 제거할 것.
}