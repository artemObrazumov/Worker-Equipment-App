package com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.core.domain.UserDataRepository
import com.quackAboutIt.workingequipmentapp.notifications.presentation.notification_list.NotificationListScreenState
import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RequestEditorScreenViewModel(
    private val credentialsRepository: CredentialsRepository,
    private val userDataRepository: UserDataRepository
): ViewModel() {

    private val _state: MutableStateFlow<RequestEditorScreenState> =
        MutableStateFlow(RequestEditorScreenState.Loading)
    val state = _state
        .onStart { loadUserData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            RequestEditorScreenState.Loading
        )

    private fun loadUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            val userData = userDataRepository.getUserData(
                credentialsRepository.getToken() ?: ""
            )
            if (userData != null) {
                _state.update {
                    RequestEditorScreenState.Content(
                        workerName = userData.name,
                        unitId = userData.unit.id,
                        unitAddress = userData.unit.address
                    )
                }
            }
        }
    }

    fun openWorkplaceDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isWorkplaceDialogOpened = true
            )
        }
    }

    fun closeWorkplaceDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isWorkplaceDialogOpened = false
            )
        }
    }

    fun selectWorkplace(workplace: Workplace) {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isWorkplaceDialogOpened = false,
                workplaceAddress = workplace.address,
                workplaceId = workplace.id
            )
        }
    }

    fun openEquipmentDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDialogOpened = true
            )
        }
    }

    fun closeEquipmentDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDialogOpened = false
            )
        }
    }

    fun selectEquipment(equipment: Equipment) {
        _state.update {
            val content = it as RequestEditorScreenState.Content
            content.copy(
                isEquipmentDialogOpened = false,
                equipment = content.equipment.apply {
                    toMutableList().add(equipment.toState())
                }
            )
        }
    }
}