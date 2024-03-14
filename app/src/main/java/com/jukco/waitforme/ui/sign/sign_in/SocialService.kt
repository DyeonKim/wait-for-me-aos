package com.jukco.waitforme.ui.sign.sign_in

sealed interface SocialService {
    object Google : SocialService
}