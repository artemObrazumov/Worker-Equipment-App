package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Workplace
import com.quackAboutIt.workingequipmentapp.requests.domain.WorkplaceRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.WorkplaceResult

class WorkplaceRemoteRepository : WorkplaceRepository {

    override suspend fun getWorkplaces(token: String): WorkplaceResult {
        return WorkplaceResult.Success(
            listOf(
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                ),
                Workplace(
                    id = 1,
                    address = "test 1"
                )
            )
        )
    }
}