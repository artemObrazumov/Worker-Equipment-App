package com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationResult
import com.quackAboutIt.workingequipmentapp.notifications.domain.NotificationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationListScreenViewModel(
    private val notificationsRepository: NotificationsRepository,
    private val credentialsRepository: CredentialsRepository
): ViewModel() {

    private val _state: MutableStateFlow<NotificationListScreenState> =
        MutableStateFlow(NotificationListScreenState.Loading)
    val state = _state
        .onStart { loadNotifications() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            NotificationListScreenState.Loading
        )

    private fun loadNotifications() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                NotificationListScreenState.Loading
            }

            val result = notificationsRepository.getNotifications(
                credentialsRepository.getToken() ?: ""
            )

            when (result) {
                is NotificationResult.Success -> {
                    _state.update {
                        NotificationListScreenState.Content(
                            result.notifications
                        )
                    }
                }
                is NotificationResult.Failure -> {

                }
            }
        }
    }
}