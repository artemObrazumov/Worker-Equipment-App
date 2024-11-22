package com.quackAboutIt.workingequipmentapp.requests.domain

sealed class RequestDetailsResult {

    data class Success(
        val requestDetails: RequestDetails
    ): RequestDetailsResult()
}