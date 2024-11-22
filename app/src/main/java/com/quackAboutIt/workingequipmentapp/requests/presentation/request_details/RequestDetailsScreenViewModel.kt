package com.quackAboutIt.workingequipmentapp.requests.presentation.request_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetailsResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor.RequestEditorScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RequestDetailsScreenViewModel(
    private val id: Long,
    private val credentialsRepository: CredentialsRepository,
    private val requestRepository: RequestRepository
): ViewModel() {

    private val _state: MutableStateFlow<RequestDetailsScreenState> =
        MutableStateFlow(RequestDetailsScreenState.Loading)
    val state = _state
        .onStart { loadRequestDetails() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RequestDetailsScreenState.Loading
        )

    private fun loadRequestDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            val requestDetailsResult = requestRepository.getRequestDetails(
                token = credentialsRepository.getToken() ?: "",
                requestId = id
            )
            when(requestDetailsResult) {
                is RequestDetailsResult.Success -> {
                    _state.update {
                        RequestDetailsScreenState.Content(
                            requestDetails = requestDetailsResult.requestDetails
                        )
                    }
                }
            }
        }
    }
}