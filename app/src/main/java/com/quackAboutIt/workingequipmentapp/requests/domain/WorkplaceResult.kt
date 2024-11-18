package com.quackAboutIt.workingequipmentapp.requests.domain

sealed class WorkplaceResult {
    data class Success(
        val workplaces: List<Workplace>
    ): WorkplaceResult()
}