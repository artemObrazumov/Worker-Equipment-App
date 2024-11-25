package com.quackAboutIt.workingequipmentapp.requests.presentation.equipment_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentRepository
import com.quackAboutIt.workingequipmentapp.requests.data.EquipmentResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EquipmentListScreenViewModel(
    private val credentialsRepository: CredentialsRepository,
    private val equipmentRepository: EquipmentRepository
): ViewModel() {

    private val _state: MutableStateFlow<EquipmentListScreenState> =
        MutableStateFlow(EquipmentListScreenState())
    val state = _state
        .onStart { loadWorkplaces() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            EquipmentListScreenState()
        )

    private fun loadWorkplaces() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = equipmentRepository.getEquipment(
                credentialsRepository.getToken() ?: ""
            )

            when (result) {
                is EquipmentResult.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            equipment = result.equipment
                        )
                    }
                }
            }
        }
    }
}