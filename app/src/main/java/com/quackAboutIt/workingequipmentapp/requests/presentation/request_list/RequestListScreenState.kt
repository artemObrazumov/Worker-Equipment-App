package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list

import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState

sealed class RequestListScreenState {
    data object Loading: RequestListScreenState()
    data class Content(
        val currentRequests: List<Request>,
        val futureRequests: List<Request>,
        val finishedRequests: List<Request>
    ): RequestListScreenState()
}