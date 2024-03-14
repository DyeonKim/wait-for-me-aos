package com.jukco.waitforme.config

import android.app.Application
import androidx.credentials.CredentialManager
import com.jukco.waitforme.data.repository.AppContainer
import com.jukco.waitforme.data.repository.DefaultContainer

class ApplicationClass : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(this)
    }
}