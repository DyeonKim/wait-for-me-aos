package com.jukco.waitforme.ui.notice.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.NoticeDetailResponse
import com.jukco.waitforme.data.repository.NoticeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.LocalDateTime

class NoticeDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val noticeRepository: NoticeRepository,
) : ViewModel() {
    private val noticeId: Int? by lazy { savedStateHandle["noticeId"] }
    private val emptyResponse = NoticeDetailResponse(-1, "", "", LocalDateTime.now())
    private val _notice = MutableStateFlow(emptyResponse)
    val notice: StateFlow<NoticeDetailResponse> get() = _notice.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            try {
                if (noticeId != null) {
                    _notice.value = noticeRepository.getNotice(noticeId!!).body() ?: emptyResponse
                }
            } catch (e: IOException) {
                // TODO: 네트워크 오류
            } catch (e: HttpException) {
                // TODO : 30X, 40X 오류
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = this.createSavedStateHandle()
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ApplicationClass)
                val noticeRepository = application.container.noticeRepository
                NoticeDetailViewModel(savedStateHandle, noticeRepository)
            }
        }
    }
}