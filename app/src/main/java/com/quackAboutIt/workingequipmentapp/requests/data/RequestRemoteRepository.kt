package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentInRequestDetails
import com.quackAboutIt.workingequipmentapp.requests.domain.Request
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetails
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestDetailsResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestListResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestSendResult
import com.quackAboutIt.workingequipmentapp.requests.domain.RequestState
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.ZonedDateTime

class RequestRemoteRepository : RequestRepository {

    override suspend fun getRequests(token: String): RequestListResult {
        delay(500L)
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
                    id = 2,
                    state = RequestState.CURRENT,
                    workplaceAddress = "куда-то",
                    equipmentCount = 3,
                    date = ZonedDateTime.now(),
                    progress = 5,
                    total = 6
                ),
                Request(
                    id = 3,
                    state = RequestState.FINISHED,
                    workplaceAddress = "куда-то",
                    equipmentCount = 4,
                    date = ZonedDateTime.now()
                ),
                Request(
                    id = 4,
                    state = RequestState.FUTURE,
                    workplaceAddress = "куда-то",
                    equipmentCount = 8,
                    date = ZonedDateTime.now().plusDays(2L)
                )
            )
        )
    }

    override suspend fun sendRequest(requestDTO: RequestDTO): RequestSendResult {
        delay(2000L)
        return RequestSendResult.Success
    }

    override suspend fun getRequestDetails(token: String, requestId: Long): RequestDetailsResult {
        delay(2000L)
        return RequestDetailsResult.Success(
            requestDetails = RequestDetails(
                id = 1,
                state = RequestState.CURRENT,
                workerName = "Образумов Артём Александрович",
                unitAddress = "где-то в подразделении",
                workplaceAddress = "там работать будем",
                distance = 69.69,
                arrivalDate = ZonedDateTime.now().plusDays(1),
                progress = 4,
                total = 10,
                equipmentInRequestDetails = listOf(
                    EquipmentInRequestDetails(
                        id = 2,
                        image = "https://sun9-16.userapi.com/impg/m1FtE45hMgJ3vlEnLXb-aRIibI_-BXdKGVUR5A/1dUGJrcomto.jpg?size=438x267&quality=95&sign=a027a3641aea65eec1433f7d00a4a6cd&type=album",
                        name = "машинка 100 тонн навоза",
                        number = "А 696 АА 696",
                        arrivalTime = ZonedDateTime.now(),
                        duration = Duration.ofHours(4).plusMinutes(32)
                    ),
                    EquipmentInRequestDetails(
                        id = 3,
                        image = "https://sun9-16.userapi.com/impg/m1FtE45hMgJ3vlEnLXb-aRIibI_-BXdKGVUR5A/1dUGJrcomto.jpg?size=438x267&quality=95&sign=a027a3641aea65eec1433f7d00a4a6cd&type=album",
                        name = "машинка 69 тонн навоза",
                        number = "А 696 АА 696",
                        arrivalTime = ZonedDateTime.now(),
                        duration = Duration.ofHours(8).plusMinutes(22)
                    )
                )
            )
        )
    }
}