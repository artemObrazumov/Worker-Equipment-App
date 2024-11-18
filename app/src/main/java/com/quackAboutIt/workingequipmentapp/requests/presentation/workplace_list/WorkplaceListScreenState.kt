package com.quackAboutIt.workingequipmentapp.requests.presentation.workplace_list

import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace

data class WorkplaceListScreenState(
    val isLoading: Boolean = true,
    val workplaces: List<Workplace> = emptyList()
)