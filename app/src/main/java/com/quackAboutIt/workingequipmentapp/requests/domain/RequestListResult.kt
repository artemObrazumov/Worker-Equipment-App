package com.quackAboutIt.workingequipmentapp.requests.domain

sealed class RequestListResult {

    data class Success(
        val requests: List<Request>
    ): RequestListResult()
}