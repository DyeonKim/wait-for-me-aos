package com.jukco.waitforme.config

import android.app.Application
import com.jukco.waitforme.BuildConfig
import com.jukco.waitforme.R
import com.jukco.waitforme.data.repository.AppContainer
import com.jukco.waitforme.data.repository.DefaultContainer
import com.kakao.sdk.common.KakaoSdk
import com.navercorp.nid.NaverIdLoginSDK

class ApplicationClass : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
        NaverIdLoginSDK.initialize(
            context = this,
            clientId = BuildConfig.NAVER_CLIENT_ID,
            clientSecret = BuildConfig.NAVER_CLIENT_SECRET,
            clientName = getString(R.string.app_name),
        )
        container = DefaultContainer(this)
    }
}