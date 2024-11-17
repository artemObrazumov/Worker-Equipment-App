package com.quackAboutIt.workingequipmentapp.requests.domain

interface RequestRepository {

    suspend fun getRequests(token: String): RequestListResult
}