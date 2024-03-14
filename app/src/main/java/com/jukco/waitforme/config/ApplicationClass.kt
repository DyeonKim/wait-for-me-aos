package com.jukco.waitforme.config

import android.app.Application
import com.jukco.waitforme.BuildConfig
import com.jukco.waitforme.data.repository.AppContainer
import com.jukco.waitforme.data.repository.DefaultContainer
import com.kakao.sdk.common.KakaoSdk

class ApplicationClass : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        container = DefaultContainer(this)
    }
}