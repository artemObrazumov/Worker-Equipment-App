package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestListResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RequestListScreenViewModel(
    private val requestRepository: RequestRepository,
    private val credentialsRepository: CredentialsRepository
): ViewModel() {

    private val _state: MutableStateFlow<RequestListScreenState> =
        MutableStateFlow(RequestListScreenState.Loading)
    val state = _state
        .onStart { loadRequests() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RequestListScreenState.Loading
        )

    private fun loadRequests() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = requestRepository.getRequests(
                credentialsRepository.getToken() ?: ""
            )
            when (result) {
                is RequestListResult.Success -> {
                    val currentRequests = result.requests.filter { it.state == RequestState.CURRENT }
                    val futureRequests = result.requests.filter { it.state == RequestState.FUTURE }
                    val finishedRequests = result.requests.filter { it.state == RequestState.FINISHED }
                    _state.update {
                        RequestListScreenState.Content(
                            currentRequests = currentRequests,
                            futureRequests = futureRequests,
                            finishedRequests = finishedRequests
                        )
                    }
                }
            }
        }
    }
}