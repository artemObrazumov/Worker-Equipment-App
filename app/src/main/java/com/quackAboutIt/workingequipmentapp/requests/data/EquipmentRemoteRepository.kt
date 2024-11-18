package com.quackAboutIt.workingequipmentapp.requests.data

import com.quackAboutIt.workingequipmentapp.requests.domain.Equipment
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentRepository
import com.quackAboutIt.workingequipmentapp.requests.domain.EquipmentResult

class EquipmentRemoteRepository: EquipmentRepository {

    override suspend fun getEquipment(token: String): EquipmentResult {
        return EquipmentResult.Success(
            listOf(
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                ),
                Equipment(
                    id = 1,
                    imageUrl = "",
                    name = "Test"
                )
            )
        )
    }
}