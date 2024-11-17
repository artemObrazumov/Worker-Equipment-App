package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestListResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import java.time.ZonedDateTime

class RequestRemoteRepository : RequestRepository {

    override suspend fun getRequests(token: String): RequestListResult {
        return RequestListResult.Success(
            listOf(
                Request(
                    id = 1,
                    state = RequestState.CURRENT,
                    workplaceAddress = "куда-то",
                    equipmentCount = 4,
                    date = ZonedDateTime.now(),
                    progress = 2,
                    total = 8
                ),
                Request(
                    id = 1,
                    state = RequestState.CURRENT,
                    workplaceAddress = "куда-то",
                    equipmentCount = 3,
                    date = ZonedDateTime.now(),
                    progress = 5,
                    total = 6
                ),
                Request(
                    id = 1,
                    state = RequestState.FINISHED,
                    workplaceAddress = "куда-то",
                    equipmentCount = 4,
                    date = ZonedDateTime.now()
                ),
                Request(
                    id = 1,
                    state = RequestState.FUTURE,
                    workplaceAddress = "куда-то",
                    equipmentCount = 8,
                    date = ZonedDateTime.now().plusDays(2L)
                )
            )
        )
    }
}