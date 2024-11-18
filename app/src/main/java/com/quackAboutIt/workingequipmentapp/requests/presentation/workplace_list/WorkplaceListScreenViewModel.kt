package com.quackAboutIt.workingequipmentapp.requests.presentation.workplace_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.WorkplaceRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.WorkplaceResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkplaceListScreenViewModel(
    private val credentialsRepository: CredentialsRepository,
    private val workplaceRepository: WorkplaceRepository
): ViewModel() {

    private val _state: MutableStateFlow<WorkplaceListScreenState> =
        MutableStateFlow(WorkplaceListScreenState())
    val state = _state
        .onStart { loadWorkplaces() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            WorkplaceListScreenState()
        )

    private fun loadWorkplaces() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = workplaceRepository.getWorkplaces(
                credentialsRepository.getToken() ?: ""
            )

            when (result) {
                is WorkplaceResult.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            workplaces = result.workplaces
                        )
                    }
                }
            }
        }
    }
}