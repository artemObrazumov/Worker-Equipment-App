package com.quackAboutIt.workingequipmentapp.requests.presentation.request_details

import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetails

sealed class RequestDetailsScreenState {
    data object Loading: RequestDetailsScreenState()
    data class Content(
        val requestDetails: RequestDetails
    ): RequestDetailsScreenState()
}
