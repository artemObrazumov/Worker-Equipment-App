package com.quackAboutIt.workingequipmentapp.requests.domain

sealed class RequestSendResult {
    data object Success: RequestSendResult()
}
