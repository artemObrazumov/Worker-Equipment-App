package com.quackAboutIt.workingequipmentapp.requests.presentation.request_editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.core.domain.UserDataRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestSendResult
import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import com.quackAboutIt.workingequipmentapp.requests.utils.toRequestDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class RequestEditorScreenViewModel(
    private val credentialsRepository: CredentialsRepository,
    private val userDataRepository: UserDataRepository,
    private val requestRepository: RequestRepository
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
            val equipmentState = equipment.toState()
            val equipmentList = content.equipment.toMutableList().apply {
                add(equipmentState)
            }
            content.copy(
                isEquipmentDialogOpened = false,
                equipment = equipmentList,
                editingEquipmentListId = equipmentState.listId
            )
        }
    }

    fun changeEquipment(equipment: EquipmentInRequestState) {
        _state.update {
            val content = it as RequestEditorScreenState.Content
            val equipmentList = content.equipment.toMutableList().apply {
                this[this.indexOfFirst { item ->
                    item.listId == equipment.listId
                }] = equipment
            }
            content.copy(
                isEquipmentDialogOpened = false,
                equipment = equipmentList
            )
        }
    }

    fun changeEditingEquipment(equipment: EquipmentInRequestState) {
        _state.update {
            val content = it as RequestEditorScreenState.Content
            content.copy(
                //editingEquipment = equipment
            )
        }
    }

    fun changeDistanceString(distanceString: String) {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                distanceString = distanceString
            )
        }
    }

    fun openCalendarDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDetailsCalendarOpened = true
            )
        }
    }

    fun closeCalendarDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDetailsCalendarOpened = false
            )
        }
    }

    fun openEquipmentMenuDetails() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDetailsDialogOpened = true
            )
        }
    }

    fun closeEquipmentMenuDetails() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDetailsDialogOpened = false
            )
        }
    }

    fun deleteEquipment(equipment: EquipmentInRequestState) {
        _state.update {
            val content = it as RequestEditorScreenState.Content
            val equipmentList = content.equipment.toMutableList().apply {
                this.removeIf { item -> item.listId == equipment.listId }
            }
            content.copy(
                isEquipmentDialogOpened = false,
                equipment = equipmentList
            )
        }
    }

    fun openWorkTimeCalendarDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDetailsCalendarWorkDetailsOpened = true
            )
        }
    }

    fun closeWorkTimeCalendarDialog() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isEquipmentDetailsCalendarWorkDetailsOpened = false
            )
        }
    }

    fun openArrivalDatePicker() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isArrivalDateCalendarOpened = true
            )
        }
    }

    fun closeArrivalDatePicker() {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                isArrivalDateCalendarOpened = false
            )
        }
    }

    fun sentForm() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update {
                (it as RequestEditorScreenState.Content).copy(
                    isLoading = true
                )
            }
            val content = _state.value as RequestEditorScreenState.Content
            val requestDTO = content.toRequestDTO()
            val result = requestRepository.sendRequest(requestDTO)
            when (result) {
                is RequestSendResult.Success -> {
                    _state.update {
                        (it as RequestEditorScreenState.Content).copy(
                            hasUploaded = true
                        )
                    }
                }
            }
        }
    }

    fun changeArrivalDate(date: ZonedDateTime) {
        _state.update {
            (it as RequestEditorScreenState.Content).copy(
                arrivalDate = date
            )
        }
    }
}