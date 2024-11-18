package com.quackAboutIt.workingequipmentapp.requests.domain

interface WorkplaceRepository {

    suspend fun getWorkplaces(token: String): WorkplaceResult
}