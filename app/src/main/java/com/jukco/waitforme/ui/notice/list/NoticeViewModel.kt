package com.jukco.waitforme.ui.notice.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jukco.waitforme.config.ApplicationClass
import com.jukco.waitforme.data.network.model.NoticeResponse
import com.jukco.waitforme.data.repository.NoticeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class NoticeViewModel(
    private val noticeRepository: NoticeRepository,
) : ViewModel() {
    private val _notices = MutableStateFlow<List<NoticeResponse>>(emptyList())
    val notices: StateFlow<List<NoticeResponse>> get() = _notices.asStateFlow()


    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            try {
                _notices.value = noticeRepository.getNoticeList().body() ?: emptyList()
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
                val application = (this[APPLICATION_KEY] as ApplicationClass)
                val noticeRepository = application.container.noticeRepository
                NoticeViewModel(noticeRepository)
            }
        }
    }
}