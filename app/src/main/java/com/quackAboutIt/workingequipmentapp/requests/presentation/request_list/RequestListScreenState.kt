package com.quackAboutIt.workingequipmentapp.requests.presentation.request_list

import androidx.compose.runtime.Immutable
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState

@Immutable
sealed class RequestListScreenState {
    data object Loading: RequestListScreenState()
    @Immutable
    data class Content(
        val currentRequests: List<Request>,
        val futureRequests: List<Request>,
        val finishedRequests: List<Request>
    ): RequestListScreenState()
}